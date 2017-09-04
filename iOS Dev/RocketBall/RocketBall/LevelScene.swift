//
//  PlayLevel.swift
//  RocketBall
//
//  Created by Scott Sandre on 2017-07-07.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import SpriteKit
import GameKit

// Sprite Names
let spriteName_startingBall : String = "ball"

// Category Bit Masks
struct PhysicsCategory {
    static let Ball:           UInt32 = 0x1 << 0
    static let Paddle:         UInt32 = 0x1 << 1
    static let ScoreRegions: [UInt32] = [0x1 << 2, 0x1 << 3]
    static let Border:         UInt32 = 0x1 << 6
}

struct Constants {
    static let userPaddleName = "user_paddle"
    static let compPaddleName = "comp_paddle"
    
    static let USER_PLAYER: Int = 0
    static let COMP_PLAYER: Int = 1
    
    static let START_LEVEL_LABEL = "startLevelLabel"
    static let END_GAME_LABEL = "endGameLabel"
    static let WINNER_LABEL = "winnerLabel"
    
    static let TOP_TEXT_1: CGFloat = 0.65
    static let TOP_TEXT_2: CGFloat = 0.60
}

class LevelScene: SKScene, SKPhysicsContactDelegate {
    
    //=========================================================================================================
    // MARK: Properties
    //=========================================================================================================
    
    var level : Level
    var balls : [Ball] = []
    var players: [Player]
    var paddles: [Paddle] = []
    var userTouchingPaddle: Bool = false
    lazy var gameState: GKStateMachine = GKStateMachine(states: [
        WaitingForTap(scene: self),
        Playing(scene: self),
        GameOver(scene: self)])
    
    //=========================================================================================================
    // MARK: LevelScene Init/SetUp Logic
    //=========================================================================================================
    
    init(size: CGSize, level: Level){
        self.level = level
        self.players = level.players
        super.init(size: size)
    }
    
    // Need this so Xcode doesn't complain
    required init?(coder aDecoder: NSCoder){
        fatalError("init(coder:) has not been implemented")
    }
    
    override func update(_ currentTime: TimeInterval){
        //gameState.update(deltaTime: currentTime) TODO
    }
    
    // Called immediately after this scene is presented by the main view
    override func didMove(to view: SKView){
        super.didMove(to: view)
        
        // Create a barrier around the screen
        let borderBody = SKPhysicsBody(edgeLoopFrom: self.frame)
        borderBody.friction = 0.0
        borderBody.categoryBitMask = PhysicsCategory.Border
        self.physicsBody = borderBody
        self.physicsWorld.gravity = CGVector(dx: 0.0, dy: 0.0) //remove gravity
        self.physicsWorld.contactDelegate = self
        
        // Make starting balls
        for _ in 0..<level.startNumberBalls {
            let ballStartX: CGFloat = self.size.width / (CGFloat(level.startNumberBalls + 1))
            let ball = Ball(radius: 16, fillColor: SKColor.green, strokeColor: SKColor.green, startX: ballStartX, startY: self.size.height / 2, partOfChain: false) //startY always the same
            
            self.addChild(ball.node)
            
            balls.append(ball)
        }
        
        // Make paddles
        var userPaddle: Paddle = Paddle(isHuman: true,
                                        width: level.paddle_defaultWidth,
                                        fill: SKColor.blue,
                                        startX: level.scoreRegions[0].frame.midX,
                                        scoreRegionY: level.scoreRegions[0].frame.midY)
        var compPaddle: Paddle = Paddle(isHuman: false,
                                        width: level.paddle_defaultWidth,
                                        fill: SKColor.blue,
                                        startX: level.scoreRegions[1].frame.midX,
                                        scoreRegionY: level.scoreRegions[1].frame.midY)
        paddles.append(userPaddle)
        paddles.append(compPaddle)
        self.addChild(userPaddle.node)
        self.addChild(compPaddle.node)
        
        // Make contact detection areas
        for i in 0..<level.scoreRegions.count {
            level.scoreRegions[i].physicsBody = SKPhysicsBody(edgeLoopFrom: level.scoreRegions[i].frame)
            level.scoreRegions[i].physicsBody?.categoryBitMask = PhysicsCategory.ScoreRegions[i]
            self.addChild(level.scoreRegions[i])
        }

        startGame()
    }
    
    // Handle contact collisions
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
        
        for i in 0..<level.scoreRegions.count {
            if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.ScoreRegions[i]){
                self.players[i].loseLife()
                print("User Lives: \(self.players[0].numberLives)")
                print("Comp Lives: \(self.players[1].numberLives)")
                if isGameOver() {
                    endGame()
                }
            }
        }
        
        if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.Paddle){
            // Hit Paddle
        }
    }
    
    //=========================================================================================================
    // MARK: GamePlay Logic
    //=========================================================================================================
    
    func startGame(){
        // Wait for user to tap to play
        let tapToStartLabel = SKLabelNode(text: "Tap to Start")
        tapToStartLabel.name = Constants.START_LEVEL_LABEL
        tapToStartLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * Constants.TOP_TEXT_1)
        tapToStartLabel.zPosition = 5
        tapToStartLabel.setScale(0.0)
        self.addChild(tapToStartLabel)
        self.gameState.enter(WaitingForTap.self)
    }
    
    func isGameOver() -> Bool {
        if (self.players[0].numberLives <= 0 || self.players[1].numberLives <= 0) {
            print("Game over!")
            return true
        }
        
        return false
    }
    
    func endGame(){
        print("End Game")
        let endGameLabel = SKLabelNode(text: "Game Over")
        endGameLabel.name = Constants.END_GAME_LABEL
        endGameLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * Constants.TOP_TEXT_1)
        endGameLabel.zPosition = 5
        endGameLabel.setScale(0.0)
        self.addChild(endGameLabel)
        
        let winnerText: String = players[0].numberLives <= 0 ? "You Lost!" : "You Won!"
        let winnerLabel = SKLabelNode(text: winnerText)
        winnerLabel.name = Constants.WINNER_LABEL
        winnerLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * Constants.TOP_TEXT_2)
        winnerLabel.zPosition = 5
        winnerLabel.setScale(0.0)
        self.addChild(winnerLabel)
        
        self.gameState.enter(GameOver.self)
    }
    
    //=========================================================================================================
    // MARK: User Touch Handling
    //=========================================================================================================
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        switch self.gameState.currentState {
        case is WaitingForTap:
            print("Waiting For Tap")
            self.gameState.enter(Playing.self)
        case is Playing:
            print("Playing")
            let touch = touches.first
            let touchLocation = touch!.location(in: self)
            
            if let body = self.physicsWorld.body(at: touchLocation){
                if body.node!.name == Constants.userPaddleName{
                    userTouchingPaddle = true
                }
            }
        case is GameOver:
            print("GameOver")
        default:
            print("Error State!")
            break
        }
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?){
        
        if userTouchingPaddle {
            let touch = touches.first
            let thisLocation = touch!.location(in: self)
            let prevLocation = touch!.previousLocation(in: self)
            let paddle = paddles[Constants.USER_PLAYER].node
            
            var paddleX = paddle.position.x + thisLocation.x - prevLocation.x
            paddleX = max(paddleX, paddle.size.width / 2)
            paddleX = min(paddleX, level.scoreRegions[Constants.USER_PLAYER].frame.maxX - paddle.size.width / 2)
            
            paddle.position = CGPoint(x: paddleX, y: paddle.position.y)
        }
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
}
