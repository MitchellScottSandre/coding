//
//  WaitingForTap.swift
//  BreakoutSpriteKitTutorial
//
//  Created by Michael Briscoe on 1/16/16.
//  Copyright © 2016 Razeware LLC. All rights reserved.
//

import SpriteKit
import GameplayKit

class WaitingForTap: GKState {
    unowned let scene: GameScene
    
    init(scene: SKScene) {
        self.scene = scene as! GameScene
        super.init()
    }
    
    override func didEnter(from previousState: GKState?) {
        let scaleToAppear = SKAction.scale(to: 1.0, duration: 0.25) //grow to full size, initially it is 0 (set in GameScene.swift)
        //childNode searches the RECEIVING NODE for a child with that name
        //the receiving node is what called this State
        self.scene.childNode(withName: GameMessageName)!.run(scaleToAppear)
        
    }
    
    override func willExit(to nextState: GKState) {
        if nextState is Playing {
            let scaleToDisappear = SKAction.scale(to: 0.0, duration: 0.5)
            self.scene.childNode(withName: GameMessageName)!.run(scaleToDisappear)
        }
    }
    
    override func isValidNextState(_ stateClass: AnyClass) -> Bool {
        return stateClass is Playing.Type
    }
    
}
