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
        self.scene.addImpulseToBalls()
        
        // Perform game logic checks
        // TODO
        
        
    }
    
    override func willExit(to nextState: GKState) {
        
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return stateClass is GameOver.Type
    }
    
    //=========================================================================================================
    // MARK: Game Logic Functions
    //=========================================================================================================
    
//    func addImpulseToBalls(){
//        let balls = scene.balls
//        for i in 0..<balls.count {
//            balls[i].node.physicsBody!.applyImpulse(CGVector(dx: randomDirection(), dy: randomDirection()))
//        }
//    }
}
