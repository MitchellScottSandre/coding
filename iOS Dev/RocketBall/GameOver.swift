//
//  GameOver.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-04.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameplayKit

class GameOver: GKState {
    unowned let scene: LevelScene
    
    init(scene: SKScene) {
        self.scene = scene as! LevelScene
        super.init()
    }
    
    override func didEnter(from previousState: GKState?) {
        let scaleToAppear = SKAction.scale(to: 1.0, duration: 0.25)
        
        self.scene.childNode(withName: LevelConstants.END_GAME_LABEL)!.run(scaleToAppear)
        self.scene.childNode(withName: LevelConstants.WINNER_LABEL)!.run(scaleToAppear)
    }
    
    override func willExit(to nextState: GKState) {
        // TODO
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return true
    }
}
