//
//  WaitingForTap.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-04.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameplayKit

class WaitingForTap: GKState {
    unowned let scene: LevelScene
    
    init(scene: SKScene) {
        self.scene = scene as! LevelScene
        super.init()
    }
    
    override func didEnter(from previousState: GKState?) {
        let scaleToAppear = SKAction.scale(to: 1.0, duration: 0.25) //grow to full size, initially it is 0 (set in GameScene.swift)

        self.scene.childNode(withName: Constants.START_LEVEL_LABEL)!.run(scaleToAppear)
    }
    
    override func willExit(to nextState: GKState) {
        if nextState is Playing {
            let scaleToDisappear = SKAction.scale(to: 0.0, duration: 0.5)
            self.scene.childNode(withName: Constants.START_LEVEL_LABEL)!.run(scaleToDisappear)
        }
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return stateClass is Playing.Type
    }
}
