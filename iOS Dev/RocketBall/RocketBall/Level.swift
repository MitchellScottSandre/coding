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
    
    //let gameBorderOutline: CGPath
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    let scoreRegions : [CGRect]
    
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, scoreRegions: [CGRect]){
        self.startNumberBalls = startBalls
        //self.gameBorderOutline = borderOutline
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        self.scoreRegions = scoreRegions
    }
    
    
}
