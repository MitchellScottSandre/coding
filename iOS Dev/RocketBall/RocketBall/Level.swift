//
//  Level.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-19.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

struct PlayerLocations{
    static let BOTTOM = 0
    static let TOP = 1
}

class Level{

    // Constants needed for Ball & Paddle
    let paddle_defaultWidth: CGFloat = 70.0
    
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    var scoreRegions : [SKShapeNode]
    
    // Primary Constructor. Players at positions BOTTOM, TOP
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, height: CGFloat, width: CGFloat){
        self.startNumberBalls = startBalls
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        self.scoreRegions = []
        
        scoreRegions.append(SKShapeNode(rect: CGRect(x: 0.0, y: 0.0, width: width, height: 1)))
        scoreRegions.append(SKShapeNode(rect: CGRect(x: 0.0, y: height - 1, width: width, height: 1)))
    }
    
}
