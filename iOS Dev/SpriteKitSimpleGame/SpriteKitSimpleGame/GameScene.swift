//
//  GameScene.swift
//  SpriteKitSimpleGame
//
//  Created by Scott Sandre on 2017-06-26.
//  Copyright © 2017 Razeware LLC. All rights reserved.
//

import SpriteKit

class GameScene: SKScene {
    
    //MARK: Properties
    let player = SKSpriteNode(imageNamed: "player")
    
    //MARK: Actions
    
    override func didMove(to view: SKView){
        self.backgroundColor = SKColor.white // Don't actually need the 'self', I am just using it for readability
        player.position = CGPoint(x : size.width * 0.1, y: size.height * 0.5) // Recall, we are using our app in landscape mode
        self.addChild(player)
    }
    
    // Guide says to delete this?
    override func update(_ currentTime: TimeInterval) {
        // Called before each frame is rendered
    }
}
