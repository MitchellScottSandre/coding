//
//  GameScene.swift
//  ColourSwitch
//
//  Created by Scott Sandre on 2017-06-28.
//  Copyright © 2017 Razeware LLC. All rights reserved.
//

import SpriteKit

extension UIBezierPath {
    func addArcRegular(withCenter: CGPoint, radius: CGFloat, startAngle: CGFloat, endAngle: CGFloat, clockwise: Bool){
        addArc(withCenter: withCenter, radius: radius, startAngle: startAngle, endAngle: endAngle, clockwise: !clockwise)
    }
}

class GameScene: SKScene {
    //================================================================================================
    // MARK: Structures
    //================================================================================================
    
    struct PhysicsCategory {
        static let None:     UInt32   = 0
        static let Player:   UInt32   = 1
        static let Obstacle: UInt32   = 2
        static let Edge:     UInt32   = 4
    }
    
    //================================================================================================
    // MARK: Properties
    //================================================================================================
    
    let colors = [SKColor.yellow, SKColor.red, SKColor.blue, SKColor.purple]
    let obstacleSpacing: CGFloat = 800
    let cameraNode = SKCameraNode()
    let scoreLabel = SKLabelNode()
    
    var player = SKShapeNode(circleOfRadius: 40)
    var obstacles: [SKNode] = [] // Empty array  of SKNodes, which are the container nodes for SKShapeNodes
    var score = 0
    
    //================================================================================================
    //MARK: Scene Updating/Detection/Gameplay
    //================================================================================================
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        player.physicsBody?.velocity.dy = 800.0
        
        // Check player position to see if camera needs to be moved
    }
    
    override func didMove(to view: SKView) {
        self.physicsWorld.contactDelegate = self
        setupPlayerAndObstacles()
        
        // Add player
        let playerBody = SKPhysicsBody(circleOfRadius: 35)
        playerBody.mass = 1.5
        playerBody.isDynamic = true
        playerBody.categoryBitMask = PhysicsCategory.Player
        playerBody.collisionBitMask = PhysicsCategory.Edge
        self.player.physicsBody = playerBody
        
        // Add ledge at bottom
        let ledge = SKNode()
        ledge.position = CGPoint(x: size.width/2, y: 160)
        let ledgeBody = SKPhysicsBody(rectangleOf: CGSize(width: 200, height: 10))
        ledgeBody.isDynamic = false
        ledgeBody.categoryBitMask = PhysicsCategory.Edge
        ledge.physicsBody = ledgeBody
        
        addChild(ledge)
        self.physicsWorld.gravity.dy = -22
        
        // Setup Camera
        addChild(self.cameraNode)
        camera = cameraNode
        cameraNode.position = CGPoint(x: size.width / 2, y: size.height / 2)
        
        scoreLabel.position = CGPoint(x: -350, y: -900) // from centre of screen
        scoreLabel.fontColor = SKColor.white
        scoreLabel.fontSize = 150
        scoreLabel.text = String(score)
        cameraNode.addChild(scoreLabel) // Score moves with camera
        
    }
    
    // update() gets called before each frame is rendered
    override func update(_ currentTime: TimeInterval) {
        // Add new obstacles
        if player.position.y > obstacleSpacing * CGFloat(obstacles.count - 2){
            print("score")
            score += 1
            scoreLabel.text = String(score)
            addObstacle()
        }
        
        // Follow the player's position. Move camera up if they are in the top half of the screen
        let playerPositionInCamera = cameraNode.convert(player.position, from: self)// Player position relative to center of camera
        if playerPositionInCamera.y > 0 && !cameraNode.hasActions() {
            cameraNode.position.y = player.position.y
        }
        
        if playerPositionInCamera.y < -size.height/2 {
            dieAndRestart()
        }
    }
    
    // Player made contact with section of the wrong color
    func dieAndRestart(){
        print("BOOM")
        player.physicsBody?.velocity.dy = 0
        player.removeFromParent()
        score = 0
        scoreLabel.text = String(score)
        
        // Remove the obstacles
        for node in obstacles {
            node.removeFromParent()
        }
        obstacles.removeAll()
        
        setupPlayerAndObstacles()
        cameraNode.position = CGPoint(x: size.width / 2, y: size.height / 2)
    }
    
    //================================================================================================
    // MARK: Player and Obstacle Setup
    //================================================================================================
    
    func setupPlayerAndObstacles(){
        addObstacle()
        addObstacle()
        addObstacle()
        addPlayer()
    }
    
    func addPlayer(){
        self.player.fillColor = SKColor.blue
        self.player.strokeColor = self.player.fillColor
        self.player.position = CGPoint(x: size.width / 2, y: 200)
        
        self.addChild(player)
    }
    
    func addObstacle(){
        let choice = Int(arc4random_uniform(3))
        switch choice {
        case 0:
            addCircleObstacle()
        case 1:
            addSquareObstacle()
        case 2:
            addCrossObstacle()
        default: print("Something went wrong")
        }
    }
    
    func addCircleObstacle(){
        // This creates a quarter of a circle
        let path = UIBezierPath() // UIBezierPath uses +y as DOWN. Spritekit uses +y as UP.
        path.move(to: CGPoint(x: 0, y: -200))
        path.addLine(to: CGPoint(x: 0, y: -160))
        path.addArc(withCenter: CGPoint.zero,
                    radius: 160,
                    startAngle: CGFloat(3.0 * M_PI_2),
                    endAngle: CGFloat(0),
                    clockwise: true)
        
        path.addLine(to: CGPoint(x: 200, y: 0))
        
        path.addArc(withCenter: CGPoint.zero,
                    radius: 200,
                    startAngle: CGFloat(0),
                    endAngle: CGFloat(3.0 * M_PI_2),
                    clockwise: false)
        
        let obstacle = obstacleByDuplicatingPath(path, clockwise: true)
        obstacles.append(obstacle)
        obstacle.position = CGPoint(x: self.size.width/2, y: self.obstacleSpacing * CGFloat(obstacles.count))
        
        addChild(obstacle)
        
        let rotateAction = SKAction.rotate(byAngle: 2.0 * CGFloat(M_PI), duration: 5.0)
        obstacle.run(SKAction.repeatForever(rotateAction))
    }
    
    func addSquareObstacle(){
        let path = UIBezierPath(roundedRect: CGRect(x: -200, y: -200, width: 400, height: 40), cornerRadius: 20)
        let obstacle = obstacleByDuplicatingPath(path, clockwise: false)
        obstacles.append(obstacle)
        obstacle.position = CGPoint(x: size.width / 2, y: obstacleSpacing * CGFloat(obstacles.count))
        addChild(obstacle)
        
        let rotateAction = SKAction.rotate(byAngle: -2.0 * CGFloat(M_PI), duration: 7.0)
        obstacle.run(SKAction.repeatForever(rotateAction))
    }
    
    func addCrossObstacle(){
        // Make the cross shape
        let crossPath = UIBezierPath(roundedRect: CGRect(x: 0, y: 0, width: 350, height: 40), cornerRadius: 20)
        let obstacle = obstacleByDuplicatingPath(crossPath, clockwise: true)
        obstacles.append(obstacle)
        
        // Choose where to place the cross shape
        let sideShift = self.size.width / CGFloat((Int(arc4random_uniform(3)) + 5))
        let leftOrRightSide = Int(arc4random_uniform(2)) //0 left, 1 right
        if leftOrRightSide == 0 {
            obstacle.position = CGPoint(x: size.width / 2 - sideShift, y: obstacleSpacing * CGFloat(obstacles.count))
        } else {
            obstacle.position = CGPoint(x: size.width / 2 + sideShift, y: obstacleSpacing * CGFloat(obstacles.count))
        }
        obstacles.append(obstacle)
        addChild(obstacle)
        
        let rotateAction = SKAction.rotate(byAngle: -2.0 * CGFloat(M_PI), duration: 7.0)
        obstacle.run(SKAction.repeatForever(rotateAction))
    }
    
    
    // Create the 4 different coloured parts of the shape
    func obstacleByDuplicatingPath(_ path: UIBezierPath, clockwise: Bool) -> SKNode {
        let container = SKNode()
        
        var rotationFactor = CGFloat(M_PI_2)
        
        if !clockwise {
            rotationFactor *= -1
        }
        
        for i in 0...3 {
            // Create the section of the path
            let section = SKShapeNode(path: path.cgPath)
            section.fillColor = self.colors[i]
            section.strokeColor = self.colors[i]
            section.zRotation = rotationFactor * CGFloat(i) // Rotate 90 degrees about the z axis
            
            // Add physics bodies to the section
            let sectionBody = SKPhysicsBody(polygonFrom: path.cgPath)
            sectionBody.categoryBitMask = PhysicsCategory.Obstacle
            sectionBody.collisionBitMask = PhysicsCategory.None
            sectionBody.contactTestBitMask = PhysicsCategory.Player
            sectionBody.affectedByGravity = false
            section.physicsBody = sectionBody

            container.addChild(section)
        }
        
        return container
    }
    
}

extension GameScene : SKPhysicsContactDelegate {
    func didBegin(_ contact: SKPhysicsContact) {
        let nodeA = contact.bodyA.node as? SKShapeNode
        let nodeB = contact.bodyB.node as? SKShapeNode
        
        if nodeA?.fillColor != nodeB?.fillColor {
            dieAndRestart()
        }
    }
}
