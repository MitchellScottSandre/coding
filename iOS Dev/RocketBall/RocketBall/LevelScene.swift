//
//  PlayLevel.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-07.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameKit

// Sprite Names
let spriteName_startingBall : String = "ball"

// Category Bit Masks

struct PhysicsCategory {
    static let Ball:           UInt32 = 0x1 << 0
    static let Paddle:         UInt32 = 0x1 << 1
    static let ScoreRegions: [UInt32] = [0x1 << 2, 0x1 << 3, 0x1 << 4, 0x1 << 5] //for players 0, 1, 2, 3
    static let Border:         UInt32 = 0x1 << 6
    
    static let PLAYER: Int = 0
}

class LevelScene: SKScene, SKPhysicsContactDelegate {
    
    //=========================================================================================================
    // MARK: Properties
    //=========================================================================================================
    
    var level : Level
    var balls : [Ball] = []
    var players: [Player] = []
    var paddles: [Paddle] = []
    //=========================================================================================================
    // MARK: Game Logic
    //=========================================================================================================

    init(size: CGSize, level: Level){
        self.level = level
        
        super.init(size: size)
    }
    
    // Need this so Xcode doesn't complain
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func update(_ currentTime: TimeInterval) {
        //gameState.update(deltaTime: currentTime) TODO
    }
    
    // Called immediately after this scene is presented by the main view
    override func didMove(to view: SKView) {
        super.didMove(to: view)
        
        // Create a barrier around the screen
        let borderBody = SKPhysicsBody(edgeLoopFrom: self.frame)
        borderBody.friction = 0.0
        borderBody.categoryBitMask = PhysicsCategory.Border
        self.physicsBody = borderBody
        self.physicsWorld.gravity = CGVector(dx: 0.0, dy: 0.0) //remove gravity
        self.physicsWorld.contactDelegate = self
        
        // Make starting balls
        for _ in 0..<level.startNumberBalls {
            let ball = Ball(radius: 16, fillColor: SKColor.green, strokeColor: SKColor.green, startX: self.size.width / 2, startY: self.size.height / 2)
            
            self.addChild(ball.node)

            ball.node.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: randomDirection()))

            balls.append(ball)
        }
        
        // Make paddles
        for i in 0..<level.scoreRegions.count {
            var paddle: Paddle
            switch(level.playerLocations[i]){
            case PlayerLocations.LEFT:
                paddle = Paddle(isHuman: i == 0,
                                width: level.paddle_defaultWidth,
                                fill: SKColor.blue,
                                startY: level.scoreRegions[i].midY,
                                orientation: CGVector(dx: level.scoreRegions[i].maxX - level.scoreRegions[i].minX, dy: level.scoreRegions[i].maxY - level.scoreRegions[i].minY),
                                location: PlayerLocations.LEFT,
                                scoreRegionX: level.scoreRegions[i].midX)
            case PlayerLocations.RIGHT:
                paddle = Paddle(isHuman: i == 0,
                                width: level.paddle_defaultWidth,
                                fill: SKColor.blue,
                                startY: level.scoreRegions[i].midY,
                                orientation: CGVector(dx: level.scoreRegions[i].maxX - level.scoreRegions[i].minX, dy: level.scoreRegions[i].maxY - level.scoreRegions[i].minY),
                                location: PlayerLocations.RIGHT,
                                scoreRegionX: level.scoreRegions[i].midX) //TODO what about regions at an angle?
            default:
                fatalError("Impossible player location")
            
            }
            
            self.addChild(paddle.node)
            paddles.append(paddle)
        }
        
        // Make contact detection areas
        for i in 0..<level.scoreRegions.count {
            let scoreRegion = level.scoreRegions[i]
            let scoreRegionNode = SKNode()
            scoreRegionNode.physicsBody = SKPhysicsBody(edgeLoopFrom: scoreRegion)
            scoreRegionNode.physicsBody?.categoryBitMask = PhysicsCategory.ScoreRegions[i]
            self.addChild(scoreRegionNode)
        }

    }
    
    // Handle contact collisions
    func didBegin(_ contact: SKPhysicsContact){
        var firstBody: SKPhysicsBody
        var secondBody: SKPhysicsBody
        
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
            firstBody = contact.bodyA
            secondBody = contact.bodyB
        } else {
            firstBody = contact.bodyB
            secondBody = contact.bodyA
        }

        for i in 0..<level.numberPlayers {
            if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.ScoreRegions[i]){
                print("Player \(i) got hit!")
            }
        }
        
        if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.Paddle){
            print("hit paddle!")
        }
        
        
    }
    
    //=========================================================================================================
    // MARK: User Touch Handling
    //=========================================================================================================
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    //=========================================================================================================
    // MARK: Helper Functions
    //=========================================================================================================
    
    func randomFloat(from: CGFloat, to: CGFloat) -> CGFloat {
        let rand: CGFloat = CGFloat(Float(arc4random()) / 0xFFFFFFFF)
        return (rand) * (to - from) + from
    }
    
    func randomDirection() -> CGFloat {
        let speedFactor: CGFloat = 12.0
        if randomFloat(from: 0.0, to: 100.0) >= 50 { // flips a coin, randomness to its direction
            return -speedFactor
        } else {
            return speedFactor
        }
    }
    
}
