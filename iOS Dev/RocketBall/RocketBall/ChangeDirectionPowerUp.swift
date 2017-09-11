//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class ChangeDirectionPowerUp : PowerUp {
    
    var lastIncomingDistanceBetweenBallAndNextBallInChain: CGFloat?
    var lastIncomingVelocity: CGVector?
    var lastOutgoingVelocity: CGVector?
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: PowerUpConstants.CHANGE_DIRECTION_POWERUP_COLOR)
    }
    
    override init(startPoint: CGPoint, color: SKColor){
        super.init(startPoint: startPoint, color: color)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        self.lastIncomingVelocity = ball.node.physicsBody?.velocity
        
        if bounceNewDirection(ball: ball) == true {
            let dx = ball.node.physicsBody?.velocity.dx
            let dy = ball.node.physicsBody?.velocity.dy
            let currentSpeed = CGFloat(sqrt(dx! * dx! + dy! * dy!))
            
            let newVelocity: CGVector = LevelScene.randomDirection(desiredSpeed: currentSpeed)
            
            ball.node.physicsBody?.velocity = newVelocity
            self.lastOutgoingVelocity = newVelocity
            
        } else {
            ball.node.physicsBody?.velocity = lastOutgoingVelocity!
        }
        
        if let nextBall = ball.nextBallInChain {
            self.lastIncomingDistanceBetweenBallAndNextBallInChain = Ball.distanceBetweenBalls(ballA: ball, ballB: nextBall)
        } else {
            self.lastIncomingDistanceBetweenBallAndNextBallInChain = nil
            self.lastIncomingVelocity = nil
            self.lastOutgoingVelocity = nil
        }
    }
    
    // Only bounce to a new direction if the ball is not part of a chain. However, the first part of of that chain may now be going in a new direction,
    // so need to compare incoming velicoty to last incoming velocity
    func bounceNewDirection(ball: Ball) -> Bool {
        
        if self.lastIncomingVelocity == nil || self.lastOutgoingVelocity == nil || self.lastIncomingDistanceBetweenBallAndNextBallInChain == nil{
            return true
        }
        
        if let lastDistance = self.lastIncomingDistanceBetweenBallAndNextBallInChain {
            if LevelScene.distanceBetweenValues(val1: lastDistance, val2: BallConstants.DEFAULT_RADIUS * 2 + BallConstants.CHAIN_DISTANCE) <= BallConstants.CHAIN_ERROR_TOLERANCE
            && LevelScene.distanceBetweenValues(val1: (ball.node.physicsBody?.velocity.dx)!, val2: (self.lastIncomingVelocity?.dx)!) <= 1.0
            && LevelScene.distanceBetweenValues(val1: (ball.node.physicsBody?.velocity.dy)!, val2: (self.lastIncomingVelocity?.dy)!) <= 1.0 {
                return false
            }
        }
        
        return true
    }
}
