//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class LargerPaddlePowerUp : PowerUp {
    
    var paddleAppliedTo: Paddle?
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: PowerUpConstants.SIZE_POWERUP_COLOR)
    }
    
    override init(startPoint: CGPoint, color: SKColor){
        super.init(startPoint: startPoint, color: color)
        
        self.timerLength = 10.0
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        if let paddle = ball.paddleLastTouched {
            if (paddle.largerPaddlePowerUpApplied == false){
                paddle.largerPaddlePowerUpApplied = true
                paddle.node.xScale = PowerUpConstants.ACTIVE_PADDLE_FACTOR
                self.paddleAppliedTo = paddle
                deleteMyNodes()
                addPowerUpTimer(ball: ball, time: self.timerLength)
            }
        }
    }
    
    @objc override func removeThisPowerUp(sender: Timer){
        print("Remove paddle length called")
        if let paddle = self.paddleAppliedTo {
            print("asdf")
            paddle.largerPaddlePowerUpApplied = false
            paddle.node.xScale = PowerUpConstants.DEFAULT_PADDLE_FACTOR
        }
    }

}
