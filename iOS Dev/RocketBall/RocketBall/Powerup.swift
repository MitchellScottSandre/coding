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
    
    // Powerup looks
    var powerupRadius: CGFloat
    var node: SKShapeNode
    var location: CGPoint
    
    init(radius: CGFloat, startX: CGFloat, startY: CGFloat, color: SKColor){
        self.powerupRadius = radius
        self.location = CGPoint(x: startX, y: startY)
        
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
    
}
