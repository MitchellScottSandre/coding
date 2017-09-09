//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class SizePowerUp : PowerUp {
    
    var sizeFactor: CGFloat = PowerUpConstants.ACTIVE_SIZE_FACTOR
    
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
        if (ball.sizePowerUpApplied == false){
            applySizeFactor(ball: ball)
            addPowerUpTimer(ball: ball, time: self.timerLength)
        }
    }
    
    @objc override func removeThisPowerUp(sender: Timer){
        if let ball = sender.userInfo as? Ball {
            self.sizeFactor = PowerUpConstants.DEFAULT_SIZE_FACTOR
            applySizeFactor(ball: ball)
            self.sizeFactor = PowerUpConstants.ACTIVE_SIZE_FACTOR
        }
    }
    
    func applySizeFactor(ball: Ball){
        ball.node.xScale = self.sizeFactor
        ball.node.yScale = self.sizeFactor
        ball.sizePowerUpApplied = !ball.sizePowerUpApplied
    }
}
