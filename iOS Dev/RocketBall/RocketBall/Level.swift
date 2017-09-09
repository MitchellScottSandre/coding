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
    
    // Constants needed for Ball & Paddle & PowerUps
    let paddle_defaultWidth: CGFloat = 70.0
    
    let startNumberBalls : Int
    let numberPlayers : Int
    let scoreLimit : Int
    let frameHeight: CGFloat
    let frameWidth: CGFloat
    var scoreRegions : [SKSpriteNode]
    var players : [Player]
    var powerUps : [PowerUp] = []
    
    // Primary Constructor. Players at positions BOTTOM, TOP
    init(startBalls: Int, numPlayers: Int, scoreLimit: Int, height: CGFloat, width: CGFloat){
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
    
    func addPowerUps(){
        // do calculations based off percent changes, and add power ups
//        addSpeedPowerUp()
//        addSizePowerUp()
//        addDamagePowerUp()
//        addChangeDirectionPowerUp()
        addChainPowerUp()
    }
    
    func addSpeedPowerUp(){
        var powerUp = SpeedPowerUp(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerUpConstants.SPEED_POWERUP_COLOR)
        self.powerUps.append(powerUp)
    }

    func addSizePowerUp(){
        var powerUp = SizePowerUp(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerUpConstants.SIZE_POWERUP_COLOR)
        self.powerUps.append(powerUp)
    }

    func addDamagePowerUp(){
        var powerUp = DamagePowerUp(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerUpConstants.DAMAGE_POWERUP_COLOR)
        self.powerUps.append(powerUp)
    }

    func addChangeDirectionPowerUp(){
        var powerUp = ChangeDirectionPowerUp(frameWidth: self.frameWidth, frameHeight: self.frameHeight, color: PowerUpConstants.CHANGE_DIRECTION_POWERUP_COLOR)
        self.powerUps.append(powerUp)
    }
    
    func addChainPowerUp(){
        //Only add to middle 50% of the screen. From x = 25% to x = 75%
        let startX = LevelScene.randomFloat(from: frameWidth * 0.25, to: frameWidth * 0.75)
        let startY = LevelScene.randomFloat(from: frameHeight * 0.15, to: frameHeight * 0.85)
        var powerUp = ChainPowerUp(startPoint: CGPoint(x: startX, y: startY), color: PowerUpConstants.ADD_BALL_TO_CHAIN_POWERUP_COLOR)
        self.powerUps.append(powerUp)
    }

    // TODO: add more power up functions
    
}
