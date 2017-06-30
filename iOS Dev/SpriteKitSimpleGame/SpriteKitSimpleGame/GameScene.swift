//
//  GameScene.swift
//  SpriteKitSimpleGame
//
//  Created by Scott Sandre on 2017-06-26.
//  Copyright © 2017 Razeware LLC. All rights reserved.
//

import SpriteKit
// MARK: Collision Detection Struct
// SpriteKit Categories are 32 BIT, at most 32 categories
struct PhysicsCategory {
    static let None         : UInt32 = 0          // 0
    static let All          : UInt32 = UInt32.max // 32
    static let Monster      : UInt32 = 0b1        // 1 (binary)
    static let Projectile   : UInt32 = 0b10       // 2
}

// MARK: Math Functions
func + (left: CGPoint, right: CGPoint) -> CGPoint {
    return CGPoint(x: left.x + right.x, y: left.y + right.y)
}

func - (left: CGPoint, right: CGPoint) -> CGPoint {
    return CGPoint(x: left.x - right.x, y: left.y - right.y)
}

func * (point: CGPoint, scalar: CGFloat) -> CGPoint {
    return CGPoint(x: point.x * scalar, y: point.y * scalar)
}

func / (point: CGPoint, scalar: CGFloat) -> CGPoint {
    return CGPoint(x: point.x / scalar, y: point.y / scalar)
}

#if !(arch(x86_64) || arch(arm64))
    func sqrt(a: CGFloat) -> CGFloat {
        return CGFloat(sqrt(Float(a)))
    }
#endif

extension CGPoint {
    func length() -> CGFloat {
        return sqrt(x*x + y*y)
    }
    
    func normalized() -> CGPoint {
        return self / length()
    }
}

class GameScene: SKScene, SKPhysicsContactDelegate{
    
    //MARK: Properties
    var numberOfLivesLabel : SKLabelNode = SKLabelNode()
    let player = SKSpriteNode(imageNamed: "player")
    let projectileSpeed = 10.0
    var numberOfLives = 3
    var numMonstersDestroyed = 0
    
    
    //MARK: Logic Functions
    
    func random() -> CGFloat {
        return CGFloat(Float(arc4random()) / 0xFFFFFFFF)
    }
    
    func random(min: CGFloat, max: CGFloat) -> CGFloat{
        return random() * (max - min) + min
    }
    
    func addMonster() {
        // Create sprite
        let monster = SKSpriteNode(imageNamed: "monster")
        
        // Add physics atrributes to the monster sprite
        monster.physicsBody = SKPhysicsBody(rectangleOf: monster.size)
        monster.physicsBody?.isDynamic = true // I control the movement of the monster through code I have written. Not the physics engine
        monster.physicsBody?.categoryBitMask = PhysicsCategory.Monster // What type of category the monster is
        monster.physicsBody?.contactTestBitMask = PhysicsCategory.Projectile // Indicates what categories of objects this object should notify the contact listener when they interset
        monster.physicsBody?.collisionBitMask = PhysicsCategory.None // Monsters can contact other monsters
        
        // Determine where to spawn the monster along the Y axis
        let actualY = random(min: monster.size.height/2, max: self.size.height - monster.size.height/2)
        
        // Position the monster slightly off-screen along the right edge and along a random position along the Y axis as calcuation above
        monster.position = CGPoint(x: size.width + monster.size.width/2, y: actualY)
        
        addChild(monster)
        
        // Determine the speed of the monster
        let actualDuration = random(min: CGFloat(2.0), max: CGFloat(4.0))
        
        // Move the monster to the left
        let actionMove = SKAction.move(to: CGPoint(x: -monster.size.width/2, y: actualY), duration: TimeInterval(actualDuration))
        
        //let actionMoveDone = SKAction.removeFromParent()
        
        monster.run(actionMove, completion: {
            self.loseLife()
        })
        //monster.run(SKAction.sequence([actionMove, loseAction, actionMoveDone]))
    }
    
    // Mark: Gameplay Logic/Running/Detections
    
    // Recall: this scene is the contact delegate of the physics world
    // This method is called when 2 physics bodies collide, passed in any order
    func didBegin(_ contact: SKPhysicsContact){
        var firstBody: SKPhysicsBody
        var secondBody: SKPhysicsBody
        
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
            firstBody = contact.bodyA
            secondBody = contact.bodyB
        } else {
            firstBody = contact.bodyB
            secondBody = contact.bodyA
        }
        
        // uses bit operation &
        if ((firstBody.categoryBitMask & PhysicsCategory.Monster != 0) && (secondBody.categoryBitMask & PhysicsCategory.Projectile != 0)){
            if let monster = firstBody.node as? SKSpriteNode, let projectile = secondBody.node as? SKSpriteNode {
                projectileCollidedWithMonster(projectile: projectile, monster: monster)
            }
        }
    }
    
    // This scene is about to be presented to the view
    override func didMove(to view: SKView){
        // Add a label for number of lives
        numberOfLivesLabel = SKLabelNode(fontNamed: "Chalkduster")
        numberOfLivesLabel.text = "You have \(numberOfLives) lives left"
        numberOfLivesLabel.fontSize = 15
        numberOfLivesLabel.fontColor = SKColor.black
        numberOfLivesLabel.position = CGPoint(x: size.width * 0.35, y: size.height * 0.9)
        self.addChild(numberOfLivesLabel)
        
        self.backgroundColor = SKColor.white // Don't actually need the 'self', I am just using it for readability
        player.position = CGPoint(x : size.width * 0.1, y: size.height * 0.5) // Recall, we are using our app in landscape mode. x is along long axis, y is along shorter axis
        self.addChild(player)
        
        run(SKAction.repeatForever(SKAction.sequence([SKAction.run(addMonster), SKAction.wait(forDuration: 1.0)])))
        
        self.physicsWorld.gravity = CGVector.zero
        self.physicsWorld.contactDelegate = self // This scene is the delegate, will be notified when two physics bodies collide
        
        let backgroundMusic = SKAudioNode(fileNamed: "background-music-aac.caf")
        backgroundMusic.autoplayLooped = true
        self.addChild(backgroundMusic)
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        // Choose which touch to work with
        guard let touch = touches.first else {
            return
        }
        
        let touchLocation = touch.location(in: self)
        
        // Set up initial location of projectile
        let projectile = SKSpriteNode(imageNamed: "projectile")
        projectile.position = player.position;
        
        // Add physics attributes to the projectile
        projectile.physicsBody = SKPhysicsBody(circleOfRadius: projectile.size.width/2)
        projectile.physicsBody?.isDynamic = true
        projectile.physicsBody?.categoryBitMask = PhysicsCategory.Projectile
        projectile.physicsBody?.contactTestBitMask = PhysicsCategory.Monster
        projectile.physicsBody?.collisionBitMask = PhysicsCategory.None
        projectile.physicsBody?.usesPreciseCollisionDetection = true
        
        // Determine offset of location to projectile
        let offset : CGPoint = touchLocation - projectile.position
        
        // Don't shoot if you are shooting down or backwards
        if (offset.x < 0) { return }
        
        // It is a valid direction, so play the sound
        run(SKAction.playSoundFileNamed("pew-pew-lei.caf", waitForCompletion: false))
        
        // OK to add now since we have checked the position
        addChild(projectile)
        
        // Get direction of where to shoot
        let direction : CGPoint = offset.normalized()
        
        // Make it shoot far enough to ensure it gets shot offscreen
        let shootAmount : CGPoint = direction * 1000
        
        let realDest : CGPoint = shootAmount + projectile.position
        
        // Create the actions
        let actionMove = SKAction.move(to: realDest, duration: 10 / projectileSpeed)
        let actionMoveDone = SKAction.removeFromParent()
        projectile.run(SKAction.sequence([actionMove, actionMoveDone]))
    }
    
    func projectileCollidedWithMonster(projectile: SKSpriteNode, monster: SKSpriteNode){
        print("Hit")
        projectile.removeFromParent()
        monster.removeFromParent()
        
        self.numMonstersDestroyed += 1
        if (numMonstersDestroyed >= 20){
            let reveal = SKTransition.flipHorizontal(withDuration: 0.5)
            let gameOverScene = GameOverScene(size: self.size, won: true)
            self.view?.presentScene(gameOverScene, transition: reveal)
        }
    }
    
    private func loseLife(){
        self.numberOfLives -= 1
        self.numberOfLivesLabel.text = "You have \(self.numberOfLives) lives left"
    }
    
    override func update(_ currentTime: TimeInterval) {
        if (numberOfLives <= 0){
            print("out of lives!")
            let loseAction = SKAction.run(){
                let reveal = SKTransition.flipHorizontal(withDuration: 0.5)
                let gameOverScene = GameOverScene(size: self.size, won: false)
                self.view?.presentScene(gameOverScene, transition: reveal)
            }
            self.run(loseAction)
        }
    }
}
