//
//  Powerup.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class Powerup {
    
    // Powerup effects
    var speedFactor: CGFloat = 1.0
    var sizeFactor: CGFloat = 1.0
    var damageFactor: Int = 1
    var changeToRandomDirection: Bool = false
    var addBallToChain: Bool = false
    var teleporter: Bool = false
    var teleporterPowerup: Powerup? = nil
    
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
    }
    
    func applyPowerupToBall(ball: Ball){
        print("TODO: apply power up")
    }
    
    
}
