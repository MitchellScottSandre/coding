//
//  PlayLevel.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-07.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameKit

// Sprite Names
let spriteName_startingBall : String = "ball"

// Category Bit Masks
let bitMask_ballCategory:   UInt32 = 0x1 << 0
let bitMask_paddleCategory: UInt32 = 0x2 << 0

class LevelScene: SKScene, SKPhysicsContactDelegate {
    
    //=========================================================================================================
    // MARK: Properties
    //=========================================================================================================
    
    var level : Level?
    var balls : [SKNode]?
    //=========================================================================================================
    // MARK: Game Logic
    //=========================================================================================================

    //override init coder???
    override func update(_ currentTime: TimeInterval) {
        //gameState.update(deltaTime: currentTime) TODO
    }
    
    // Called immediately after this scene is presented by the main view
    override func didMove(to view: SKView) {
        super.didMove(to: view)
        //level!.numBalls = 5
        // Create ball & paddle
        //let ball = self.childNode(withName: spriteName_startingBall)
        
        
        // Make a thin bottom rectangle at each side of the screen
    }
    //=========================================================================================================
    // MARK: User Touch Handling
    //=========================================================================================================
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    //=========================================================================================================
    // MARK: Helper Functions
    //=========================================================================================================
    
    
}
