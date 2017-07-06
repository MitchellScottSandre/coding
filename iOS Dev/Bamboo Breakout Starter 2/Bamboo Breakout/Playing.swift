//
//  Playing.swift
//  BreakoutSpriteKitTutorial
//
//  Created by Michael Briscoe on 1/16/16.
//  Copyright © 2016 Razeware LLC. All rights reserved.
//

import SpriteKit
import GameplayKit

class Playing: GKState {
    unowned let scene: GameScene
    
    init(scene: SKScene) {
        self.scene = scene as! GameScene
        super.init()
    }
    
    override func didEnter(from previousState: GKState?) {
        if previousState is WaitingForTap {
            let ball = scene.childNode(withName: BallCategoryName) as! SKSpriteNode
            ball.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: randomDirection()))
        }
    }
    
    // called every frame while the game is in this state
    override func update(deltaTime seconds: TimeInterval) {
        let ball = scene.childNode(withName: BallCategoryName) as! SKSpriteNode
        let maxSpeed: CGFloat = 400.0
        
        let xSpeed = abs(ball.physicsBody!.velocity.dx)
        let ySpeed = abs(ball.physicsBody!.velocity.dy)
        
        let speed = sqrt(ball.physicsBody!.velocity.dx * ball.physicsBody!.velocity.dx + ball.physicsBody!.velocity.dy * ball.physicsBody!.velocity.dy)
        
        if xSpeed <= 10.0 {//Too slow! Give it a nudge
            ball.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: 0.0))
        }
        
        if ySpeed <= 10.0 {//Also too slow!
            ball.physicsBody!.applyImpulse(CGVector(dx: 0.0, dy: randomDirection()))
        }
        
        if speed > maxSpeed { //slow it down!
            ball.physicsBody!.linearDamping = 0.4
        } else {
            ball.physicsBody!.linearDamping = 0.0
        }
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return stateClass is GameOver.Type
    }
    
    // Helper Functions
    
    func randomDirection() -> CGFloat {
        let speedFactor: CGFloat = 3.0
        if scene.randomFloat(from: 0.0, to: 100.0) >= 50 { // flips a coin, randomness to its direction
            return -speedFactor
        } else {
            return speedFactor
        }
    }
    
}
