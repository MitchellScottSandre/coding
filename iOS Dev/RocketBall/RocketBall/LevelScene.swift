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
let bitMask_BallCategory:        UInt32 = 0x1 << 0
let bitMask_ScoreRegionCategory: UInt32 = 0x1 << 1
let bitMask_PaddleCategory:      UInt32 = 0x1 << 2
let bitMask_BorderCategory:      UInt32 = 0x1 << 3

//struct PhysicsCategory {
//    static let None:     UInt32   = 0
//    static let Player:   UInt32   = 1
//    static let Obstacle: UInt32   = 2
//    static let Edge:     UInt32   = 4
//}

class LevelScene: SKScene, SKPhysicsContactDelegate {
    
    //=========================================================================================================
    // MARK: Properties
    //=========================================================================================================
    
    var level : Level
    var balls : [SKShapeNode] = []
    
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
        borderBody.categoryBitMask = bitMask_BorderCategory
        self.physicsBody = borderBody
        self.physicsWorld.gravity = CGVector(dx: 0.0, dy: 0.0) //remove gravity
        self.physicsWorld.contactDelegate = self
        
        // Make starting balls
        for _ in 0..<level.startNumberBalls {
            let ball = SKShapeNode(circleOfRadius: 16)
            self.addChild(ball)
            ball.position = CGPoint(x: self.size.width / 2, y: self.size.height / 2)
            
            ball.fillColor = SKColor.blue
            ball.strokeColor = ball.fillColor
            
            ball.physicsBody = SKPhysicsBody(circleOfRadius: ball.frame.width / 2)
            
            ball.physicsBody!.categoryBitMask = bitMask_BallCategory
            ball.physicsBody!.contactTestBitMask = bitMask_BorderCategory | bitMask_PaddleCategory | bitMask_ScoreRegionCategory
            ball.physicsBody!.affectedByGravity = false
            ball.physicsBody!.isDynamic = true
            ball.physicsBody!.linearDamping = 0.0
            ball.physicsBody!.angularDamping = 0.0
            ball.physicsBody!.friction = 0.0
            ball.physicsBody!.restitution = 1.0
            
            ball.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: randomDirection()))

            balls.append(ball)
        }
        
        // Make contact detection areas
//        for scoreRegion in level.scoreRegions {
//            let scoreRegionNode = SKNode()
//            scoreRegionNode.physicsBody = SKPhysicsBody(edgeLoopFrom: scoreRegion)
//            scoreRegionNode.physicsBody?.categoryBitMask = bitMask_ScoreRegionCategory
//            self.addChild(scoreRegionNode)
//        }

    }
    
    func didBegin(_ contact: SKPhysicsContact){
        
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
        let speedFactor: CGFloat = 50.0
        if randomFloat(from: 0.0, to: 100.0) >= 50 { // flips a coin, randomness to its direction
            return -speedFactor
        } else {
            return speedFactor
        }
    }
    
}
