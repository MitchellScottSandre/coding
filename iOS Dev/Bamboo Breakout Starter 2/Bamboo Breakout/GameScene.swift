
//  GameScene.swift
//  Bamboo Breakout


import SpriteKit
import GameplayKit

let BallCategoryName = "ball"
let PaddleCategoryName = "paddle"
let BlockCategoryName = "block"
let GameMessageName = "gameMessage"

let BallCategory     : UInt32 = 0x1 << 0 // 0x1. make all bits but the right-most one 0
let BottomCategory   : UInt32 = 0x1 << 1 // then make right-most one a 1
let BlockCategory    : UInt32 = 0x1 << 2 // then shift all digits to the left n spaces using <<
let PaddleCategory   : UInt32 = 0x1 << 3
let BorderCategory   : UInt32 = 0x1 << 4


class GameScene: SKScene, SKPhysicsContactDelegate {
    //====================================================================================================
    // MARK: Properties
    //====================================================================================================
    
    var isFingerOnPaddle = false
    lazy var gameState: GKStateMachine = GKStateMachine(states: [
        WaitingForTap(scene: self),
        Playing(scene: self),
        GameOver(scene: self)])
    
    //====================================================================================================
    // MARK: Game Action Functions
    //====================================================================================================
    
    override func update(_ currentTime: TimeInterval) {
        gameState.update(deltaTime: currentTime)
    }
    
    // Called immediately after this scene is presented by the main view
    override func didMove(to view: SKView) {
        super.didMove(to: view)
        
        // Create a barrier around the screen
        let borderBody = SKPhysicsBody(edgeLoopFrom: self.frame) //no mass or volume, not affected by physical interactions
        borderBody.friction = 0.0
        self.physicsBody = borderBody
        
        self.physicsWorld.gravity = CGVector(dx: 0.0, dy: 0.0) //remove gravity
        self.physicsWorld.contactDelegate = self
        
        let ball = childNode(withName: BallCategoryName) as! SKSpriteNode
        
        // Make a very thin bottom rectangle for detection at the bottom of the screen
        let bottomRect = CGRect(x: self.frame.origin.x, y: self.frame.origin.y, width: self.frame.width, height: 1)
        let bottom = SKNode()
        bottom.physicsBody = SKPhysicsBody(edgeLoopFrom: bottomRect)
        self.addChild(bottom)
        
        let paddle = childNode(withName: PaddleCategoryName) as! SKSpriteNode
        
        // Set up the category bit masks:
        bottom.physicsBody!.categoryBitMask = BottomCategory
        ball.physicsBody!.categoryBitMask = BallCategory
        paddle.physicsBody!.categoryBitMask = PaddleCategory
        borderBody.categoryBitMask = BorderCategory
        
        //Set up detection
        ball.physicsBody!.contactTestBitMask = BottomCategory | BlockCategory
        
        // Setup Blocks
        let numberOfBlocks = 8
        let blockWidth = SKSpriteNode(imageNamed: "block.png").size.width
        let totalBlocksWidth = blockWidth * CGFloat(numberOfBlocks)
        
        let xOffset = (self.frame.width - totalBlocksWidth) / 2 //offset, to be spaced from left and right side
        
        for i in 0..<numberOfBlocks {
            let block = SKSpriteNode(imageNamed: "block.png")
            block.position = CGPoint(x: xOffset + CGFloat(CGFloat(i) + 0.5) * blockWidth, y: self.frame.height * 0.8)
            block.physicsBody = SKPhysicsBody(rectangleOf: block.frame.size)
            block.physicsBody!.allowsRotation = false
            block.physicsBody!.friction = 0.0
            block.physicsBody!.affectedByGravity = false
            block.physicsBody!.isDynamic = false
            block.name = BlockCategoryName
            block.physicsBody!.categoryBitMask = BlockCategory
            block.zPosition = 2 //above the background, same level as ball
            self.addChild(block)
        }
        
        // STATE: WaitingForTap
        let gameMessage = SKSpriteNode(imageNamed: "TapToPlay")
        gameMessage.name = GameMessageName
        gameMessage.position = CGPoint(x: self.frame.midX, y: self.frame.midY)
        gameMessage.zPosition = 4 //highest so far
        gameMessage.setScale(0.0)
        self.addChild(gameMessage)
        
        self.gameState.enter(WaitingForTap.self)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        switch self.gameState.currentState {
        case is WaitingForTap:
            gameState.enter(Playing.self)
            isFingerOnPaddle = true
        case is Playing:
            let touch = touches.first
            let touchLocation = touch!.location(in: self)
            
            if let body = self.physicsWorld.body(at: touchLocation){
                if body.node!.name == PaddleCategoryName{
                    isFingerOnPaddle = true
                }
            }
        default:
            break
        }
        
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        if isFingerOnPaddle {
            let touch = touches.first
            let touchLocation = touch!.location(in: self)
            let previousLocation = touch!.previousLocation(in: self)
            
            let paddle = childNode(withName: PaddleCategoryName) as! SKSpriteNode
            var paddleX = paddle.position.x + touchLocation.x - previousLocation.x
            
            paddleX = max(paddleX, paddle.size.width / 2) // Don't let paddle go past left side of screen
            paddleX = min(paddleX, self.size.width - paddle.size.width/2) // Don't let paddle go past right side of screen
            paddle.position = CGPoint(x: paddleX, y: paddle.position.y)
        }
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?){
        isFingerOnPaddle = false
    }
    
    func didBegin(_ contact: SKPhysicsContact) {
        var firstBody: SKPhysicsBody
        var secondBody: SKPhysicsBody
        
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
            firstBody = contact.bodyA
            secondBody = contact.bodyB
        } else {
            firstBody = contact.bodyB
            secondBody = contact.bodyA
        }
        
        if firstBody.categoryBitMask == BallCategory && secondBody.categoryBitMask == BottomCategory {
            print("hit bittom")
        }
        
        if firstBody.categoryBitMask == BallCategory && secondBody.categoryBitMask == BlockCategory {
            breakBlock(node: secondBody.node!)
            //TODO check if game has been won
            print("Break node")
        }
    }
    
    //====================================================================================================
    // Helper Methods
    //====================================================================================================
    
    func breakBlock(node: SKNode){
        let particles = SKEmitterNode(fileNamed: "BrokenPlatform")!
        particles.position = node.position
        particles.zPosition = 3 //on top
        self.addChild(particles)
        
        particles.run(SKAction.sequence([SKAction.wait(forDuration: 1.0),
                                        SKAction.removeFromParent()]))
        
        node.removeFromParent()
    }
    
    func randomFloat(from: CGFloat, to: CGFloat) -> CGFloat {
        let rand: CGFloat = CGFloat(Float(arc4random()) / 0xFFFFFFFF)
        return (rand) * (to - from) + from
    }
    
    
}







