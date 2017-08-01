//
//  Paddle.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

class Paddle {
    var paddleLocation: Int
    var isHuman: Bool
    let paddleHeight: CGFloat = 20.0
    
    var paddleWidth: CGFloat
    var fillColor: SKColor
    var distanceFromScoreRegion: CGFloat = 50 {
        didSet{
            //TODO:
        }
    }
    
    var orientationVector: CGVector = CGVector(dx: 0, dy: 1)
    //TODO var sensitivity (ratio of distanceFingerMoved to distancePaddleMoved) always < 1
    
    var node: SKSpriteNode
    
    
    init(isHuman: Bool, width: CGFloat, fill: SKColor, startX: CGFloat, orientation: CGVector, location: Int, scoreRegionY: CGFloat){
        self.isHuman = isHuman
        self.paddleWidth = width
        self.fillColor = fill
        self.orientationVector = orientation
        self.paddleLocation = location
        
        self.node = SKSpriteNode(color: fillColor, size: CGSize(width: width, height: paddleHeight))
        self.node.physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: width, height: paddleHeight))
        self.node.physicsBody!.isDynamic = false
        self.node.physicsBody!.mass = 1.0
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.Paddle
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Ball
        
        switch(paddleLocation){
        case PlayerLocations.BOTTOM:
            //self.node.zRotation = CGFloat(Double.pi / 2)
            self.node.position = CGPoint(x: startX, y: scoreRegionY + distanceFromScoreRegion)
        case PlayerLocations.TOP:
            //self.node.zRotation = CGFloat(Double.pi / 2)
            self.node.position = CGPoint(x: startX, y: scoreRegionY - distanceFromScoreRegion)
        default:
            fatalError("Impossible player location")
        }
        
        self.node.name = isHuman ? Constants.userPaddleName : Constants.compPaddleName
    }
}
