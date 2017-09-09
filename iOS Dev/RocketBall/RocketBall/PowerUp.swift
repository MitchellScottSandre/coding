//
//  PowerUp.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

struct PowerUpConstants {
    // Colours
    static let SPEED_POWERUP_COLOR: SKColor = SKColor.red
    static let SIZE_POWERUP_COLOR: SKColor = SKColor.blue
    static let DAMAGE_POWERUP_COLOR: SKColor = SKColor.purple
    static let CHANGE_DIRECTION_POWERUP_COLOR: SKColor = SKColor.green
    static let CHAIN_POWERUP_COLOR: SKColor = SKColor.gray
    static let TELEPORT_POWERUP_COLOR: SKColor = SKColor.brown
    
    // Defaults
    static let DEFAULT_SPEED_FACTOR: CGFloat = 1.0
    static let DEFAULT_SIZE_FACTOR: CGFloat = 1.0
    static let DEFAULT_DAMAGE: Int = 1
    static let DEFAULT_RADIUS: CGFloat = 15.0
    static let DEFAULT_TIMER_LENGTH: Double = 4.0
    static let DEFAULT_PADDLE_FACTOR: CGFloat = 1.0
    
    // New Values
    static let ACTIVE_SPEED_FACTOR: CGFloat = 2.0
    static let ACTIVE_SIZE_FACTOR: CGFloat = 2.0
    static let ACTIVE_DAMAGE: Int = 2
    static let ACTIVE_PADDLE_FACTOR: CGFloat = 1.5
    
    
}

class PowerUp {
    
    let powerUpRadius: CGFloat = PowerUpConstants.DEFAULT_RADIUS
    var node: SKShapeNode
    var location: CGPoint
    var timer: Timer
    var timerLength: Double = PowerUpConstants.DEFAULT_TIMER_LENGTH
    
    //=========================================================================================================
    // MARK: Initializers
    //=========================================================================================================
    
    // Initialize to a specific point
    init(startPoint: CGPoint, color: SKColor){
        self.location = startPoint
        self.timer = Timer()
        
        // Create node and physicsBody
        self.node = SKShapeNode(circleOfRadius: powerUpRadius)
        self.node.position = location
        self.node.fillColor = color
        self.node.strokeColor = color
        self.node.physicsBody = SKPhysicsBody(circleOfRadius: powerUpRadius)
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.PowerUp
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Ball
        self.node.physicsBody!.affectedByGravity = false
        self.node.physicsBody!.isDynamic = false
    }
    
    func deleteMyNodes(){
        self.node.removeAllChildren()
        self.node.removeFromParent()
    }
    
    func applyPowerUpToBall(ball: Ball, levelScene: LevelScene){
        fatalError("Must override this method")
    }
    
    func addPowerUpTimer(ball: Ball, time: Double){
        self.timer = Timer.scheduledTimer(timeInterval: time,
                                                        target: self,
                                                        selector: #selector(self.removeThisPowerUp(sender:)),
                                                        userInfo: ball,
                                                        repeats: false)
    }
    
    @objc func removeThisPowerUp(sender: Timer){
        fatalError("Must override this method")
    }
    
    
}
