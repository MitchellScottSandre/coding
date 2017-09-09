//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class TeleportPowerUp : PowerUp {

    let ALLOWED_BALLS_TELEPORTED_LIMIT = 30
    var numberBallsTeleported: Int = 0
    var otherEndPowerUp: TeleportPowerUp?
    
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        self.init(startPoint: point)
    }
    
    init(startPoint: CGPoint){
        super.init(startPoint: startPoint, color: PowerUpConstants.TELEPORT_POWERUP_COLOR)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        if ball.justTeleported == false {
            if let otherLocation = self.otherEndPowerUp?.location {
                
                // Teleport the ball to the other powerup location. Ensure save velocity and direction
                var prevPhysicsBody = ball.node.physicsBody
                var prevVelocity = prevPhysicsBody?.velocity
                ball.node.physicsBody = nil
                ball.node.position = otherLocation
                ball.node.physicsBody = prevPhysicsBody
                ball.node.physicsBody?.velocity = prevVelocity!
                ball.justTeleported = true
                
                // Delete the powerup and its pair if it has teleported enough balls
                self.numberBallsTeleported += 1
                if (self.numberBallsTeleported == self.ALLOWED_BALLS_TELEPORTED_LIMIT){
                    deleteMyNodes()
                    self.otherEndPowerUp?.deleteMyNodes()
                }
            }
        }
    }
    
}
