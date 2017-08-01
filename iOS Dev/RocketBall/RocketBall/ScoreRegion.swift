//
//  ScoreRegion.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-31.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation
import SpriteKit

class ScoreRegion {
    
    var body: UIBezierPath
    
    // firstPoint, secondPoint moving clockwise around screen.
    
    init(firstPoint: CGPoint, secondPoint: CGPoint){
        let direction: CGVector = CGVector(dx: secondPoint.x - firstPoint.x, dy: secondPoint.y - firstPoint.y)
        let normal: CGVector = CGVector(dx: direction.dx, dy: -direction.dy)
        let normalMagnitude = sqrt(pow(normal.dx, 2) + pow(normal.dy, 2))
        let thirdPoint = CGPoint(x: secondPoint.x + normal.dx/normalMagnitude, y: secondPoint.y + normal.dy/normalMagnitude)
        print("First point:: x:\(firstPoint.x), y: \(firstPoint.y)")
        print("2nd point:: x:\(secondPoint.x), y: \(secondPoint.y)")
        print("3rd point:: x:\(thirdPoint.x), y: \(thirdPoint.y)")
        body = UIBezierPath()
//        body.move(to: firstPoint)
//        body.addLine(to: secondPoint)

        //body.addLine(to: secondPoint.)
        
    }
    
}
