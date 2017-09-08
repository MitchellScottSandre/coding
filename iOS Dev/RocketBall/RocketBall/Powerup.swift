//
//  Powerup.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-23.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit

struct PowerupConstants {
    static let SPEED_POWERUP_COLOR: SKColor = SKColor.red
    static let SIZE_POWERUP_COLOR: SKColor = SKColor.blue
    
    // Defaults
    static let DEFAULT_SPEED_FACTOR: CGFloat = 1.0
    static let DEFAULT_SIZE_FACTOR: CGFloat = 1.0
    static let DEFAULT_DAMAGE: Int = 1
    
    // New Values
    static let SPEED_FACTOR: CGFloat = 1.5
    static let SIZE_FACTOR: CGFloat = 2.0
    
    // Indeces
    static let INDEX_SPEED = 0
    static let INDEX_SIZE = 1
    static let INDEX_DAMAGE = 2
    static let INDEX_RANDOM_DIRECTION = 3
    static let INDEX_CHAIN_ADD = 4
    static let INDEX_TELEPORT = 5
    
}

class Powerup {
    
    // Powerup effects
    var speedFactor: CGFloat
    var sizeFactor: CGFloat
    var damageFactor: Int
    var changeToRandomDirection: Bool
    var addBallToChain: Bool
    var teleporterPowerup: Powerup?
    
    let numPowerUps = 6
    
    var powerupEffects = [Any]()
    var powerupTimers = [Timer]()
    
    // Other info
    let powerupRadius: CGFloat = 20.0
    var node: SKShapeNode
    var location: CGPoint
    
    // Convenience Initialize to a random point within given frame
    convenience init(frameWidth: CGFloat, frameHeight: CGFloat, color: SKColor){
        // Ensure it is truly a random number
        let time = UInt32(NSDate().timeIntervalSinceReferenceDate)
        srand48(Int(time))
        
        let x = CGFloat(drand48()) * frameWidth
        let y = CGFloat(drand48()) * frameHeight
        print("random x \(x) and random y \(y)")
        let point = CGPoint(x: x, y: y)
        
        self.init(startPoint: point, color: color)
    }
    
    // Initialize to a specific point
    init(startPoint: CGPoint, color: SKColor){
        self.location = startPoint
        
        // Create node and physicsBody
        self.node = SKShapeNode(circleOfRadius: powerupRadius)
        self.node.position = location
        self.node.fillColor = color
        self.node.strokeColor = color
        self.node.physicsBody = SKPhysicsBody(circleOfRadius: powerupRadius)
        self.node.physicsBody!.categoryBitMask = PhysicsCategory.PowerUp
        self.node.physicsBody!.contactTestBitMask = PhysicsCategory.Ball
        self.node.physicsBody!.affectedByGravity = false
        self.node.physicsBody!.isDynamic = false
        
        self.speedFactor = PowerupConstants.DEFAULT_SPEED_FACTOR
        self.sizeFactor = PowerupConstants.DEFAULT_SIZE_FACTOR
        self.damageFactor = PowerupConstants.DEFAULT_DAMAGE
        self.changeToRandomDirection = false
        self.addBallToChain = false
        self.teleporterPowerup = nil
        
        for i in 0..<self.numPowerUps {
            var timer: Timer = Timer()
            self.powerupTimers.append(timer)
        }
    }
    
    func applyPowerupToBall(ball: Ball){
        print("apply power up called")
        if (self.speedFactor != PowerupConstants.DEFAULT_SPEED_FACTOR && ball.speedFactorApplied == false){
            applyNewSpeedFactor(ball: ball)
        }
        
        if (self.sizeFactor != PowerupConstants.DEFAULT_SIZE_FACTOR && ball.sizeFactorApplied == false){
            applyNewSizeFactor(ball: ball)
            self.powerupTimers[PowerupConstants.INDEX_SPEED] = Timer.scheduledTimer(timeInterval: 3.0,
                                                                                    target: self,
                                                                                    selector: #selector(self.removeThisPowerup(sender:)),
                                                                                    userInfo: ["index": PowerupConstants.INDEX_SIZE, "ball": ball],
                                                                                    repeats: false)
        }
    }
    
    @objc func removeThisPowerup(sender: Timer){
        // Cast to a dictionary
        let info = sender.userInfo as? [String: AnyObject]
        let index = info?["index"] as? Int
        let ball = info?["ball"] as? Ball
        
        if let indexNotNil = index, let ballNotNil = ball{
            switch indexNotNil {
            case PowerupConstants.INDEX_SPEED:
                self.speedFactor = PowerupConstants.DEFAULT_SPEED_FACTOR
                applyNewSpeedFactor(ball: ballNotNil)
                self.speedFactor = PowerupConstants.SPEED_FACTOR
                break
            case PowerupConstants.INDEX_SIZE:
                self.sizeFactor = PowerupConstants.DEFAULT_SIZE_FACTOR
                applyNewSizeFactor(ball: ballNotNil)
                self.speedFactor = PowerupConstants.SIZE_FACTOR
                break
            default:
                print("TODO: add more remove power up functions")
            }
        }
    }
    
    func applyNewSpeedFactor(ball: Ball){
        print("Powerup > applyNewSpeedFactor")
        var newVelocity = ball.node.physicsBody?.velocity
        newVelocity?.dx *= self.speedFactor
        newVelocity?.dy *= self.speedFactor
        ball.node.physicsBody?.velocity = newVelocity!
        ball.speedFactorApplied = !ball.speedFactorApplied
    }
    
    func applyNewSizeFactor(ball: Ball){
        print("Powerup > applyNewSizeFactor: \(self.sizeFactor)")
        ball.node.xScale = self.sizeFactor
        ball.node.yScale = self.sizeFactor
        ball.sizeFactorApplied = !ball.sizeFactorApplied
    }
    
}
