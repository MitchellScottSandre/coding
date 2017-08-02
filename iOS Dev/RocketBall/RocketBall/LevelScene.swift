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
    static let ScoreRegions: [UInt32] = [0x1 << 2, 0x1 << 3]
    static let Border:         UInt32 = 0x1 << 6
}

struct Constants {
    static let userPaddleName = "user_paddle"
    static let compPaddleName = "comp_paddle"
    
    static let PLAYER: Int = 0
    static let COMP_1: Int = 1
    static let COMP_2: Int = 2
    static let COMP_3: Int = 3
}

class LevelScene: SKScene, SKPhysicsContactDelegate {
    
    //=========================================================================================================
    // MARK: Properties
    //=========================================================================================================
    
    var level : Level
    var balls : [Ball] = []
    var players: [Player] = []
    var paddles: [Paddle] = []
    var userTouchingPaddle: Bool = false
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
        var userPaddle: Paddle = Paddle(isHuman: true,
                                        width: level.paddle_defaultWidth,
                                        fill: SKColor.blue,
                                        startX: level.scoreRegions[0].frame.midX,
                                        scoreRegionY: level.scoreRegions[0].frame.midY)
        var compPaddle: Paddle = Paddle(isHuman: false,
                                        width: level.paddle_defaultWidth,
                                        fill: SKColor.blue,
                                        startX: level.scoreRegions[1].frame.midX,
                                        scoreRegionY: level.scoreRegions[1].frame.midY)
        paddles.append(userPaddle)
        paddles.append(compPaddle)
        self.addChild(userPaddle.node)
        self.addChild(compPaddle.node)
        
        // Make contact detection areas
        for i in 0..<level.scoreRegions.count {
            level.scoreRegions[i].physicsBody = SKPhysicsBody(edgeLoopFrom: level.scoreRegions[i].frame)
            level.scoreRegions[i].physicsBody?.categoryBitMask = PhysicsCategory.ScoreRegions[i]
            self.addChild(level.scoreRegions[i])
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
        let touch = touches.first
        let touchLocation = touch!.location(in: self)
        
        if let body = self.physicsWorld.body(at: touchLocation){
            if body.node!.name == Constants.userPaddleName{
                userTouchingPaddle = true
            }
        }
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        //TODO SCOTT handle slanted regions?
        if userTouchingPaddle {
            let touch = touches.first
            let thisLocation = touch!.location(in: self)
            let prevLocation = touch!.previousLocation(in: self)
            let paddle = paddles[Constants.PLAYER].node
            
            var paddleX = paddle.position.x + thisLocation.x - prevLocation.x
            paddleX = max(paddleX, paddle.size.width / 2)
            paddleX = min(paddleX, level.scoreRegions[Constants.PLAYER].frame.maxX - paddle.size.width / 2)
            
            paddle.position = CGPoint(x: paddleX, y: paddle.position.y)
        }
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
