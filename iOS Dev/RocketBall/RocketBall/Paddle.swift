//
//  Paddle.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

struct PaddleConstants {
    static let DEFAULT_WIDTH: CGFloat = 70.0
}

class Paddle {
    var isHuman: Bool
    let paddleHeight: CGFloat = 20.0
    var largerPaddlePowerUpApplied = false
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
    
    
    init(isHuman: Bool, fill: SKColor, startX: CGFloat, scoreRegionY: CGFloat){
        self.isHuman = isHuman
        self.paddleWidth = PaddleConstants.DEFAULT_WIDTH
        self.fillColor = fill
        
        self.node = SKSpriteNode(color: fillColor, size: CGSize(width: self.paddleWidth, height: self.paddleHeight))
        
        addToPhysicsBody()
        
        if isHuman {
            self.node.position = CGPoint(x: startX, y: scoreRegionY + distanceFromScoreRegion)
        } else {
            self.node.position = CGPoint(x: startX, y: scoreRegionY - distanceFromScoreRegion)
        }
        
        self.node.name = isHuman ? LevelConstants.userPaddleName : LevelConstants.compPaddleName
    }
    
    func addToPhysicsBody(){
        self.node.physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: self.paddleWidth, height: paddleHeight))
        self.node.physicsBody!.isDynamic = false
        self.node.physicsBody!.mass = 1.0
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.Paddle
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Ball
    }
}
