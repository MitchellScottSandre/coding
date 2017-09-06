//
//  StartScreenScene.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-09-05.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameKit

let PLAY_BUTTON_NAME: String = "playButton"

class StartScreenScene: SKScene {
    
    var viewController: StartScreenController
    let playLabel: SKLabelNode
    
    override init(size: CGSize) {
        self.playLabel = SKLabelNode(text: "Play Now")
        self.viewController = StartScreenController()
        super.init(size: size)
    }
    
    required init?(coder aDecoder: NSCoder){
        fatalError("init(coder:) has not been implemented")
    }
    
    override func didMove(to view: SKView){
        super.didMove(to: view)
        
        // Start Label
        let startLabel = SKLabelNode(text: "RocketBall")
        startLabel.zPosition = Constants.TEXT_Z_POSITION
        startLabel.position = CGPoint(x: self.frame.midX, y: self.frame.height * Constants.TOP_TEXT_1)
        
        // TODO: Settings
        
        // TODO: Difficulty
        
        // TODO: Show High Score
        playLabel.zPosition = Constants.TEXT_Z_POSITION
        playLabel.position = CGPoint(x: self.frame.midX, y: self.frame.midY)
        playLabel.name = PLAY_BUTTON_NAME
        
        self.addChild(startLabel)
        self.addChild(playLabel)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        let touch = touches.first
        let touchLocation = touch!.location(in: self)
        
        // Check Play Label
        if playLabel.contains(touchLocation){
            moveToPlayScene()
        }
        
        // Check Difficulty
        
        // Check Settings
    }
    
    //=========================================================================================================
    // MARK: Navigation Functions
    //=========================================================================================================
    
    func moveToPlayScene(){
        let level = Level(startBalls: 1, numPlayers: 2, scoreLimit: 30, height: self.frame.height, width: self.frame.width)
        let goToScene = LevelScene(size: self.frame.size, level: level)
        goToScene.scaleMode = .aspectFit
        
        self.view?.presentScene(goToScene)
    }
}
