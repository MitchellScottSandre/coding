//
//  SizePowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-09.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class ChainPowerUp : PowerUp {
    
    let ADDED_BALLS_LIMIT: Int = 4
    var numberBallsAdded: Int = 0
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat){
        let x = LevelScene.randomFloat(from: 0.0, to: frameWidth)
        let y = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point)
    }
    
    init(startPoint: CGPoint){
        super.init(startPoint: startPoint, color: PowerUpConstants.CHAIN_POWERUP_COLOR)
    }
    
    override func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        if ball.sizePowerUpApplied {
            return
        }
        
        // Determine if ball is part of chain
        if ball.determineIfBallIsPartOfChain(balls: levelScene.balls) == false {
            ball.firstBallInChain = true
        }
        
        if ball.firstBallInChain == true {
            // Find last ball in chain that is still visually, physically part of the chain
            // If the nextBallInChain is not physically a part of the chain anymore, then set it to firstBallInChain
            var currLastBall: Ball = ball
            var counter = 0
            while true {
                if let nextBall = currLastBall.nextBallInChain {
                    print("Checking nextball in chain: distance between them is: \(Ball.distanceBetweenBalls(ballA: nextBall, ballB: currLastBall))")
                    if LevelScene.distanceBetweenValues(val1: Ball.distanceBetweenBalls(ballA: nextBall, ballB: currLastBall),
                                                        val2: nextBall.ballRadius + currLastBall.ballRadius + BallConstants.CHAIN_DISTANCE) <= BallConstants.CHAIN_ERROR_TOLERANCE {
                        currLastBall = nextBall
                        counter += 1
                    } else {
                        nextBall.firstBallInChain = true
                        currLastBall.nextBallInChain = nil
                        currLastBall.lastBallInChain = true
                        break
                    }
                } else {
                    break
                }
            }
            
            // Add new ball to currLastBall with same velocity, but location just behind it
            if let currVelocity = currLastBall.node.physicsBody?.velocity {
                let dx = currVelocity.dx
                let dy = currVelocity.dy
                
                let radAngle = atan(dy / dx)
                let distance = currLastBall.ballRadius * 2 + BallConstants.CHAIN_DISTANCE
                
                let xDiff = cos(radAngle) * distance
                let yDiff = sin(radAngle) * distance
                
                var newBall = Ball(radius: currLastBall.ballRadius,
                                   fillColor: currLastBall.fillColor,
                                   strokeColor: ball.strokeColor,
                                   startX: currLastBall.node.position.x - xDiff,
                                   startY: currLastBall.node.position.y - yDiff)
                
                newBall.node.physicsBody?.velocity = currVelocity
                
                currLastBall.lastBallInChain = false
                currLastBall.nextBallInChain = newBall
                newBall.lastBallInChain = true
                newBall.firstBallInChain = false
                
                print("Just added ball to chain: distance between balls is \(Ball.distanceBetweenBalls(ballA: currLastBall, ballB: newBall))")
                print("currLastBall speed: \(currLastBall.getSpeed())")
                print("newBall speed: \(newBall.getSpeed())")
                
                levelScene.balls.append(newBall)
                levelScene.addChild(newBall.node)
                
                self.numberBallsAdded += 1
                if self.numberBallsAdded == self.ADDED_BALLS_LIMIT {
                   deleteMyNodes()
                }
            }
        }
    }
}
