//
//  StartScreenController.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-07.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import UIKit
import SpriteKit

// separate screen that lets you select which level to go to , etc.

class StartScreenController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        if let goToScene = LevelScene(fileNamed:"LevelScene"){
            let skView = self.view as! SKView
            skView.showsFPS = true
            skView.showsNodeCount = true
            
            skView.ignoresSiblingOrder = true
            
            goToScene.scaleMode = .aspectFit
            
            //Make Level
            
            let levelOutline : UIBezierPath = UIBezierPath(rect: goToScene.frame)
            let level = Level(startBalls: 1, numPlayers: 2, scoreLimit: 5, borderOutline: levelOutline.cgPath)
            
            goToScene.level = level
            
            skView.presentScene(goToScene)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
