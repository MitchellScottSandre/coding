//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class ChangeDirectionPowerUp : PowerUp {
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat, color: SKColor){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: color)
    }
    
    override init(startPoint: CGPoint, color: SKColor){
        super.init(startPoint: startPoint, color: color)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        let dx = ball.node.physicsBody?.velocity.dx
        let dy = ball.node.physicsBody?.velocity.dy
        let currentSpeed = CGFloat(sqrt(dx! * dx! + dy! * dy!))
        
        let newVelocity: CGVector = LevelScene.randomDirection(desiredSpeed: currentSpeed)
        ball.node.physicsBody?.velocity = newVelocity
    }
}
