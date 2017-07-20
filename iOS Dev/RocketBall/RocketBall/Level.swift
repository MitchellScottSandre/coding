//
//  Level.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-19.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

class Level{
    
    let gameBorderOutline: CGPath
    public var startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    //var
    
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, borderOutline: CGPath){
        self.startNumberBalls = startBalls
        self.gameBorderOutline = borderOutline
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
    }
    
    
}
