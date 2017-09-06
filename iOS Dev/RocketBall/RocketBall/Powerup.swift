//
//  Powerup.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

struct PowerupConstants {
    
    // Defaults
    static let DEFAULT_SPEED_FACTOR: CGFloat = 1.0
    static let DEFAULT_SIZE_FACTOR: CGFloat = 1.0
    static let DEFAULT_DAMAGE: Int = 1
    
    // New Values
    static let NEW_SPEED_FACTOR = 1.5
}

class Powerup {
    
    // Powerup effects
    var speedFactor: CGFloat
    var sizeFactor: CGFloat
    var damageFactor: Int
    var changeToRandomDirection: Bool
    var addBallToChain: Bool
    var teleporter: Bool
    var teleporterPowerup: Powerup?
    
    // Other info
    let powerupRadius: CGFloat = 20.0
    var node: SKShapeNode
    var location: CGPoint
    
    // Random Point
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat, color: SKColor){
        let x = CGFloat(drand48()) * frameWidth
        let y = CGFloat(drand48()) * frameHeight
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: color)
    }
    
    // Specific Point
    init(startPoint: CGPoint, color: SKColor){
        self.location = startPoint
        
        self.node = SKShapeNode(circleOfRadius: powerupRadius)
        self.node.position = location
        self.node.fillColor = color
        self.node.strokeColor = color
        self.node.physicsBody = SKPhysicsBody(circleOfRadius: powerupRadius)
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.PowerUp
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Ball
        self.node.physicsBody!.affectedByGravity = false
        self.node.physicsBody!.isDynamic = false
        
        self.speedFactor = PowerupConstants.DEFAULT_SPEED_FACTOR
        self.sizeFactor = PowerupConstants.DEFAULT_SIZE_FACTOR
        self.damageFactor = PowerupConstants.DEFAULT_DAMAGE
        self.changeToRandomDirection = false
        self.addBallToChain = false
        self.teleporter = false
        self.teleporterPowerup = nil
    }
    
    func applyPowerupToBall(ball: Ball){
        if (self.speedFactor != PowerupConstants.DEFAULT_SPEED_FACTOR && ball.speedFactorApplied == false){
            var newVelocity = ball.node.physicsBody?.velocity
            newVelocity?.dx *= self.speedFactor
            newVelocity?.dy *= self.speedFactor
            ball.node.physicsBody?.velocity = newVelocity!
            
            ball.speedFactorApplied = true
        }
    }
    
    
}
