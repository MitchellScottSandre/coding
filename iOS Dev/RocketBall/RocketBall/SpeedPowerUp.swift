//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class SpeedPowerUp : PowerUp {
    
    var speedFactor: CGFloat = PowerUpConstants.ACTIVE_SPEED_FACTOR
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: PowerUpConstants.SPEED_POWERUP_COLOR)
    }
    
    override init(startPoint: CGPoint, color: SKColor){
        super.init(startPoint: startPoint, color: color)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        if (ball.speedPowerUpApplied == false){
            applySpeedFactor(ball: ball)
            addPowerUpTimer(ball: ball, time: self.timerLength)
        }
    }
    
    @objc override func removeThisPowerUp(sender: Timer){
        if let ball = sender.userInfo as? Ball {
            removeSpeedFactor(ball: ball)
        }
    }
    
    func applySpeedFactor(ball: Ball){
        var newVelocity = ball.node.physicsBody?.velocity
        newVelocity?.dx *= self.speedFactor
        newVelocity?.dy *= self.speedFactor
        ball.node.physicsBody?.velocity = newVelocity!
        ball.speedPowerUpApplied = true
    }
    
    func removeSpeedFactor(ball: Ball){
        var newVelocity = ball.node.physicsBody?.velocity
        newVelocity?.dx /= self.speedFactor
        newVelocity?.dy /= self.speedFactor
        ball.node.physicsBody?.velocity = newVelocity!
        ball.speedPowerUpApplied = false
    }
}
