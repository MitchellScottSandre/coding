//
//  Playing.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-04.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameplayKit

class Playing: GKState {
    unowned let scene: LevelScene
    
    init(scene: SKScene) {
        self.scene = scene as! LevelScene
        super.init()
    }
    
    override func didEnter(from previousState: GKState?) {
        let balls = scene.balls
        
        // Start the Game - get the balls moving
        for i in 0..<balls.count {
            balls[i].node.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: randomDirection()))
        }
        
        // Perform game logic checks
        // TODO
        
        
    }
    
    override func willExit(to nextState: GKState) {
        
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return stateClass is GameOver.Type
    }
    
    //=========================================================================================================
    // MARK: Helper Functions
    //=========================================================================================================
    
    func randomFloat(from: CGFloat, to: CGFloat) -> CGFloat {
        let rand: CGFloat = CGFloat(Float(arc4random()) / 0xFFFFFFFF)
        return (rand) * (to - from) + from
    }
    
    func randomDirection() -> CGFloat {
        let speedFactor: CGFloat = 12.0
        if randomFloat(from: 0.0, to: 100.0) >= 50 {
            return -speedFactor
        } else {
            return speedFactor
        }
    }
}
