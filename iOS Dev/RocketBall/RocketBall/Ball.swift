//
//  Ball.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

struct BallConstants {
    static let DEFAULT_SPEED: CGFloat = 400.0
    static let CHAIN_DISTANCE: CGFloat = 10.0
    static let DEFAULT_RADIUS: CGFloat = 16.0
    
    static let CHAIN_ERROR_TOLERANCE:CGFloat = 15 // for some reason, keeps being 6.66 longer than expected // bouncing more makes it longer
    static let CHAIN_VELOCITY_ERROR_TOLERANCE: CGFloat = 1.0
}

class Ball{
    
    // Ball info
    var ballRadius: CGFloat
    var ballSpeed: CGFloat
    var damage: Int = 1
    var node: SKShapeNode
    var startPoint: CGPoint
    var paddleLastTouched: Paddle?
    
    var lastBallInChain = true
    var firstBallInChain = true
    var nextBallInChain: Ball?
    
    // Ball Powerup Info
    var speedPowerUpApplied = false
    var sizePowerUpApplied = false
    var damagePowerUpApplied = false
    var justTeleported = false
    
    var fillColor: SKColor
    
    var strokeColor: SKColor
    
    init(radius: CGFloat, fillColor: SKColor, strokeColor: SKColor, startX: CGFloat, startY: CGFloat){
        self.ballRadius = radius
        self.fillColor = fillColor
        self.strokeColor = strokeColor
        self.startPoint = CGPoint(x: startX, y: startY)
        self.ballSpeed = BallConstants.DEFAULT_SPEED
        
        self.node = SKShapeNode(circleOfRadius: ballRadius)
        self.node.position = startPoint
        self.node.strokeColor = self.strokeColor
        self.node.fillColor = self.fillColor
        
        addToPhysicsBody()
    }
    
    func addToPhysicsBody(){
        self.node.physicsBody = SKPhysicsBody(circleOfRadius: self.ballRadius)
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.Ball
        self.node.physicsBody!.collisionBitMask = PhysicsCategory.Border | PhysicsCategory.Paddle | PhysicsCategory.ScoreRegions[0] | PhysicsCategory.ScoreRegions[1]
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Paddle | PhysicsCategory.ScoreRegions[0] | PhysicsCategory.ScoreRegions[1]
        self.node.physicsBody!.affectedByGravity = false
        self.node.physicsBody!.isDynamic = true
        self.node.physicsBody!.linearDamping = 0.0
        self.node.physicsBody!.angularDamping = 0.0
        self.node.physicsBody!.friction = 0.0
        self.node.physicsBody!.restitution = 1.0
        self.node.physicsBody!.allowsRotation = false
        self.node.physicsBody!.angularVelocity = 0.0
    }
    
    func resetPositionToMiddle(){
        let prevPhysicsBody = self.node.physicsBody
        self.node.physicsBody = nil
        self.node.position = self.startPoint
        self.node.physicsBody = prevPhysicsBody
    }
    
    public static func distanceBetweenBalls(ballA: Ball, ballB: Ball) -> CGFloat {
        let x1 = ballA.node.position.x
        let y1 = ballA.node.position.y
        
        let x2 = ballB.node.position.x
        let y2 = ballB.node.position.y
        
        let dx = x2 - x1
        let dy = y2 - y1
        
        return sqrt(dx * dx + dy * dy)
    }
    
    func determineIfBallIsPartOfChain(balls: [Ball]) -> Bool {
        for ball in balls {
            if (ball.node == self.node){
                continue
            } else {
                if Ball.arePartOfTheSameChain(distance: Ball.distanceBetweenBalls(ballA: self, ballB: ball),
                                              ballA_velocity: (self.node.physicsBody?.velocity)!,
                                              ballB_velocity: (ball.node.physicsBody?.velocity)!) {
                    return true
                }
            }
        }
        
        return false
    }
    
    public static func arePartOfTheSameChain(distance: CGFloat, ballA_velocity: CGVector, ballB_velocity: CGVector) -> Bool {
        
        if LevelScene.distanceBetweenValues(val1: distance, val2: BallConstants.DEFAULT_RADIUS * 2 + BallConstants.CHAIN_DISTANCE) <= BallConstants.CHAIN_ERROR_TOLERANCE
                && LevelScene.distanceBetweenValues(val1: (ballA_velocity.dx), val2: (ballB_velocity.dx)) <= BallConstants.CHAIN_VELOCITY_ERROR_TOLERANCE
                && LevelScene.distanceBetweenValues(val1: (ballA_velocity.dy), val2: (ballB_velocity.dy)) <= BallConstants.CHAIN_VELOCITY_ERROR_TOLERANCE {
                return true
        }
        
        return false
    }
    
    func getSpeed() -> CGFloat {
        let dx = self.node.physicsBody?.velocity.dx
        let dy = self.node.physicsBody?.velocity.dy
        
        return sqrt(dx! * dx! + dy! * dy!)
    }
    
}
