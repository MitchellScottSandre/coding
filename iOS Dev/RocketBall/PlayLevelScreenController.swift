//
//  PlayLevelScreenController.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-07.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import UIKit
import SpriteKit

class PlayLevelScreenController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let skView = self.view as! SKView
        skView.showsFPS = true
        skView.showsNodeCount = true
        skView.ignoresSiblingOrder = true
        
        //let levelOutline : UIBezierPath = UIBezierPath(rect: skView.bounds)
        let playerScoreZone = CGRect(x: skView.bounds.origin.x, y: skView.bounds.origin.y, width: 1, height: skView.bounds.size.height)
        let computerScoreZone = CGRect(x: skView.bounds.size.width - 1, y: skView.bounds.origin.y, width: 1, height: skView.bounds.size.height)
        let scoreZones = [playerScoreZone, computerScoreZone]
        let level = Level(startBalls: 1, numPlayers: 2, scoreLimit: 5, scoreRegions: scoreZones)
        
        let goToScene = LevelScene(size: skView.bounds.size, level: level)
        goToScene.scaleMode = .aspectFit
        
        skView.presentScene(goToScene)

//        if let goToScene = LevelScene(size: , level: <#T##Level#>){//fileNamed:"LevelScene"
//            let skView = self.view as! SKView
//            skView.showsFPS = true
//            skView.showsNodeCount = true
//            
//            skView.ignoresSiblingOrder = true
//            
//            goToScene.scaleMode = .aspectFit
//            goToScene.userData = NSMutableDictionary()
//            //Make Level
//            
//            let levelOutline : UIBezierPath = UIBezierPath(rect: goToScene.frame)
//            let level = Level(startBalls: 1, numPlayers: 2, scoreLimit: 5, borderOutline: levelOutline.cgPath)
//            
//            goToScene.level = level
//            //goToScene.userData?["level"] = level
//            //goToScene.userData?.setValue(level, forKey: "level")
//            skView.presentScene(goToScene)
//        }

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
