//
//  Level.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-19.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

struct PlayerLocations {
    static let BOTTOM = 0
    static let TOP = 1
}

class Level{
    // TODO: percent chances for getting certain power ups. be able to make a random level
    
    // Constants needed for Ball & Paddle & Powerups
    let paddle_defaultWidth: CGFloat = 70.0
    
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    let frameHeight: CGFloat
    let frameWidth: CGFloat
    var scoreRegions : [SKSpriteNode]
    var players : [Player]
    var powerups : [Powerup] = []
    
    // Primary Constructor. Players at positions BOTTOM, TOP
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, height: CGFloat, width: CGFloat) {
        self.startNumberBalls = startBalls
        self.scoreLimit = scoreLimit
        self.numberPlayers = numPlayers
        self.frameHeight = height
        self.frameWidth = width
        
        var userScoreRegion = SKSpriteNode(color: SKColor.purple, size: CGSize(width: width, height: 3))
        userScoreRegion.position = CGPoint(x: width / 2.0, y: 0.0 + userScoreRegion.frame.height)
        
        var compScoreRegion = SKSpriteNode(color: SKColor.purple, size: CGSize(width: width, height: 3))
        compScoreRegion.position = CGPoint(x: width / 2.0, y: height - compScoreRegion.frame.height)
        
        self.scoreRegions = [userScoreRegion, compScoreRegion]
        
        var userPlayer: Player = Player(human: true, name: "userName", startNumberLives: self.scoreLimit)
        var compPlayer: Player = Player(human: false, name: "compName", startNumberLives: self.scoreLimit)
        
        players = [userPlayer, compPlayer]
        
        addPowerUps()
    }
    
    func addPowerUps() {
        // do calculations based off percent changes, and add power ups
//        addSpeedPowerUp()
        addSizePowerUp()
    }
    
    func addSpeedPowerUp() {
        let powerup = Powerup(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerupConstants.SPEED_POWERUP_COLOR)
        powerup.speedFactor = CGFloat(PowerupConstants.SPEED_FACTOR)
        self.powerups.append(powerup)
    }
    
    func addSizePowerUp() {
        let powerup = Powerup(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerupConstants.SIZE_POWERUP_COLOR)
        powerup.sizeFactor = CGFloat(PowerupConstants.SIZE_FACTOR)
        self.powerups.append(powerup)
    }

    // TODO: add more power up functions
    
}
