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
    var player = SKShapeNode(circleOfRadius: 40)
    var obstacles: [SKNode] = [] // Empty array  of SKNodes, which are the container nodes for SKShapeNodes
    let obstacleSpacing: CGFloat = 800
    let cameraNode = SKCameraNode()
    
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
        
        let playerBody = SKPhysicsBody(circleOfRadius: 35)
        playerBody.mass = 1.5
        playerBody.isDynamic = true
        playerBody.categoryBitMask = PhysicsCategory.Player
        playerBody.collisionBitMask = PhysicsCategory.Edge
        self.player.physicsBody = playerBody
        
        let ledge = SKNode()
        ledge.position = CGPoint(x: size.width/2, y: 160)
        let ledgeBody = SKPhysicsBody(rectangleOf: CGSize(width: 200, height: 10))
        ledgeBody.isDynamic = false
        ledgeBody.categoryBitMask = PhysicsCategory.Edge
        ledge.physicsBody = ledgeBody
        
        addChild(ledge)
        self.physicsWorld.gravity.dy = -22
        
        addChild(self.cameraNode)
        camera = cameraNode
        cameraNode.position = CGPoint(x: size.width / 2, y: size.height / 2)
    }
    
    // update() gets called before each frame is rendered
    override func update(_ currentTime: TimeInterval) {
        // Add new obstacles
        if player.position.y > obstacleSpacing * CGFloat(obstacles.count - 2){
            print("score")
            // TODO: Update score
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
        let choice = Int(arc4random_uniform(2))
        switch choice {
        case 0:
            addCircleObstacle()
        case 1:
            addSquareObstacle()
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
