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
    static let LEFT = 0
    static let TOP_LEFT = 1
    static let TOP = 2
    static let TOP_RIGHT = 3
    static let RIGHT = 4
    static let BOTTOM_RIGHT = 5
    static let BOTTOM = 6
    static let BOTTOM_LEFT = 7
}

class Level{

    // Constants needed for Ball & Paddle
    let paddle_defaultWidth: CGFloat = 70.0
    
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    let scoreRegions : [CGRect]
    let playerLocations: [Int]
    
    // Primary Constructor. Players at positions 0, 4
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, leftX: CGFloat, rightX: CGFloat, height: CGFloat){
        self.startNumberBalls = startBalls
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        
        let playerScoreZone = CGRect(x: leftX, y: 0.0, width: 1, height: height)
        let computerScoreZone = CGRect(x: rightX - 1, y: 0.0, width: 1, height: height)
        self.scoreRegions = [playerScoreZone, computerScoreZone]
        self.playerLocations = [PlayerLocations.LEFT, PlayerLocations.RIGHT]
    }
    
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, scoreRegions: [CGRect], locations: [Int]){
        self.startNumberBalls = startBalls
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        self.scoreRegions = scoreRegions
        self.playerLocations = locations
    }
    
    
}
