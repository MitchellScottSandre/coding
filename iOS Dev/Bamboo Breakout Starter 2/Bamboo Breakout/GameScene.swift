
//  GameScene.swift
//  Bamboo Breakout


import SpriteKit

let BallCategoryName = "ball"
let PaddleCategoryName = "paddle"
let BlockCategoryName = "block"
let GameMessageName = "gameMessage"


class GameScene: SKScene {
    
    override func didMove(to view: SKView) {
        super.didMove(to: view)
        
        // Create a barrier around the screen
        let borderBody = SKPhysicsBody(edgeLoopFrom: self.frame) //no mass or volume, not affected by physical interactions
        borderBody.friction = 0.0
        self.physicsBody = borderBody
        
    }
    
    
}
