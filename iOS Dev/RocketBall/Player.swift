//
//  Player.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-19.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import Foundation

class Player {
    
    var human : Bool
    var name : String
    var numberLives : Int
    
    init(human: Bool, name: String, startNumberLives: Int){
        self.human = human
        self.name = name
        self.numberLives = startNumberLives
    }
    
    func loseLife(damage: Int){
        self.numberLives = self.numberLives - damage
    }
    
    func isAlive() -> Bool {
        return self.numberLives > 0
    }
    
    
}
