//
//  Ball.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

class Ball{
    
    var ballRadius: CGFloat
    var ballSpeed: CGFloat = 10.0
    var damage: Int = 1
    var node: SKShapeNode
    var partOfChain: Bool
    var startPoint: CGPoint
    
    var fillColor: SKColor {
        didSet {
            self.node.fillColor = fillColor
        }
    }
    
    var strokeColor: SKColor {
        didSet{
            self.node.strokeColor = strokeColor
        }
    }
    
    init(radius: CGFloat, fillColor: SKColor, strokeColor: SKColor, startX: CGFloat, startY: CGFloat, partOfChain: Bool){
        self.ballRadius = radius
        self.fillColor = fillColor
        self.strokeColor = strokeColor
        self.partOfChain = partOfChain
        self.startPoint = CGPoint(x: startX, y: startY)

        self.node = SKShapeNode(circleOfRadius: ballRadius)
        self.node.position = startPoint
        self.node.strokeColor = strokeColor
        self.node.fillColor = fillColor
        
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
    }
    
    func resetToMiddle(){
        var prevPhysicsBody = self.node.physicsBody
        self.node.physicsBody = nil
        self.node.position = self.startPoint
        self.node.physicsBody = prevPhysicsBody
    }
}
