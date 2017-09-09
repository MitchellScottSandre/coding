//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class DamagePowerUp : PowerUp {
    
    var damageLevel: Int = PowerUpConstants.ACTIVE_DAMAGE
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: PowerUpConstants.DAMAGE_POWERUP_COLOR)
    }
    
    override init(startPoint: CGPoint, color: SKColor){
        super.init(startPoint: startPoint, color: color)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        if (ball.damagePowerUpApplied == false){
            applyDamageLevel(ball: ball)
            addPowerUpTimer(ball: ball, time: self.timerLength)
        }
    }
    
    @objc override func removeThisPowerUp(sender: Timer){
        if let ball = sender.userInfo as? Ball {
            self.damageLevel = PowerUpConstants.DEFAULT_DAMAGE
            applyDamageLevel(ball: ball)
            self.damageLevel = PowerUpConstants.ACTIVE_DAMAGE
        }
    }
    
    func applyDamageLevel(ball: Ball){
        ball.damage = self.damageLevel
        ball.damagePowerUpApplied = !ball.damagePowerUpApplied
    }
}
