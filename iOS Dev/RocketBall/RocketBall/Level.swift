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
    static let BOTTOM_LEFT = 1
    static let LEFT = 2
    static let TOP_LEFT = 3
    static let TOP = 4
    static let TOP_RIGHT = 5
    static let RIGHT = 6
    static let BOTTOM_RIGHT = 7
}

class Level{

    // Constants needed for Ball & Paddle
    let paddle_defaultWidth: CGFloat = 70.0
    
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    var scoreRegions : [CGRect]
    let playerLocations: [Int]
    
    // Primary Constructor. Players at positions BOTTOM, TOP
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, height: CGFloat, width: CGFloat, locations: [Int]){
        self.startNumberBalls = startBalls
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        self.playerLocations = locations
        self.scoreRegions = []
        for i in 0..<self.playerLocations.count {
            let scoreZone: CGRect
            
            switch (self.playerLocations[i]){
            case PlayerLocations.BOTTOM:
                scoreZone = CGRect(x: 0.0, y: 0.0,        width: width, height: 1)
            case PlayerLocations.TOP:
                scoreZone = CGRect(x: 0.0, y: height - 1, width: width, height: 1)
            default:
                fatalError("ToDo: other possible score zones!")
            }
            self.scoreRegions.append(scoreZone)
        }
        
        //let playerScoreZone = CGRect(x: 0.0, y: bottomY, width: width, height: 1)
        //let computerScoreZone = CGRect(x: 0.0, y: topY - 1, width: width, height: 1)
        //self.scoreRegions = [playerScoreZone, computerScoreZone]
    }
    
//    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, scoreRegions: [CGRect], locations: [Int]){
//        self.startNumberBalls = startBalls
//        self.scoreLimit = scoreLimit
//        self.numberPlayers = numPlayers
//        self.scoreRegions = scoreRegions
//        self.playerLocations = locations
//    }
    
    
}
