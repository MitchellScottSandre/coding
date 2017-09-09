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
    static let PowerUp:        UInt32 = 0x2 << 4
    static let Border:         UInt32 = 0x1 << 6
}

struct LevelConstants {
    static let userPaddleName = "user_paddle"
    static let compPaddleName = "comp_paddle"
    
    static let USER_PLAYER: Int = 0
    static let COMP_PLAYER: Int = 1
    
    static let START_LEVEL_LABEL = "startLevelLabel"
    static let END_GAME_LABEL = "endGameLabel"
    static let WINNER_LABEL = "winnerLabel"
    
    static let TOP_TEXT_1: CGFloat = 0.65
    static let TOP_TEXT_2: CGFloat = 0.60
    static let TOP_SCORE_TEXT_Y: CGFloat = 0.55
    static let BOTTOM_SCORE_TEXT_Y: CGFloat = 0.45
    static let SCORE_TEXT_X: CGFloat = 0.95
    
    static let TEXT_Z_POSITION: CGFloat = 5.0
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
    var scoreLabels:  [SKLabelNode] = []// [userScore, compScore]
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
    
    required init?(coder aDecoder: NSCoder){
        fatalError("init(coder:) has not been implemented")
    }
    
    override func update(_ currentTime: TimeInterval){
        //gameState.update(deltaTime: currentTime) TODO
    }

    override func didMove(to view: SKView){
        super.didMove(to: view)
        
        // ---------- Create a barrier around the screen ----------
        let borderBody = SKPhysicsBody(edgeLoopFrom: self.frame)
        borderBody.friction = 0.0
        borderBody.categoryBitMask = PhysicsCategory.Border
        self.physicsBody = borderBody
        self.physicsWorld.gravity = CGVector(dx: 0.0, dy: 0.0) //remove gravity
        self.physicsWorld.contactDelegate = self
        
        // ---------- Add powerUps to screen ---------- 
        for powerUp in self.level.powerUps {
            self.addChild(powerUp.node)
        }
        
        // ---------- Make starting balls ----------
        for _ in 0..<level.startNumberBalls {
            let ballStartX: CGFloat = self.size.width / (CGFloat(level.startNumberBalls + 1))
            let ball = Ball(radius: BallConstants.DEFAULT_RADIUS, fillColor: SKColor.green, strokeColor: SKColor.green, startX: ballStartX, startY: self.size.height / 2) //startY always the same
            
            self.addChild(ball.node)
            
            balls.append(ball)
        }
        
        // ---------- Make paddles ----------
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
        
        // ---------- Create Score region physics bodies ----------
        for i in 0..<2 {
            level.scoreRegions[i].physicsBody = SKPhysicsBody(rectangleOf: CGSize(width: level.scoreRegions[i].frame.width, height: level.scoreRegions[i].frame.height))
            level.scoreRegions[i].physicsBody?.categoryBitMask = PhysicsCategory.ScoreRegions[i]
            level.scoreRegions[i].physicsBody?.contactTestBitMask = PhysicsCategory.Ball
            level.scoreRegions[i].physicsBody?.collisionBitMask = PhysicsCategory.Ball
            level.scoreRegions[i].physicsBody?.isDynamic = false
            self.addChild(level.scoreRegions[i])
        }
        
        // ---------- Score Labels ----------
        for i in 0..<2 {
            var scoreLabel = SKLabelNode(text: String(self.level.players[i].numberLives))
            scoreLabel.zPosition = LevelConstants.TEXT_Z_POSITION
            scoreLabel.position = CGPoint(x: self.frame.width * LevelConstants.SCORE_TEXT_X,
                                          y: i == 0 ? self.frame.height * LevelConstants.BOTTOM_SCORE_TEXT_Y : self.frame.height * LevelConstants.TOP_SCORE_TEXT_Y)
            self.addChild(scoreLabel)
            scoreLabels.append(scoreLabel)
        }

        startGame()
    }
    
    // Handle contact collisions
    func didBegin(_ contact: SKPhysicsContact){
        if self.gameState.currentState is Playing {
            var firstBody: SKPhysicsBody
            var secondBody: SKPhysicsBody
            
            if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
                firstBody = contact.bodyA
                secondBody = contact.bodyB
            } else {
                firstBody = contact.bodyB
                secondBody = contact.bodyA
            }
            
            // ----------  Check if someone scored ----------
            for i in 0..<2 {
                if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.ScoreRegions[i]){
                    for ball in self.balls {
                        if ball.node == firstBody.node {
                            playerLoseLife(playerNum: i, damage: ball.damage)
                            break
                        }
                    }
                }
            }
            
            // ---------- Check if ball hit paddle ----------
            if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.Paddle){
                for ball in self.balls {
                    if ball.node == firstBody.node {
                        for paddle in self.paddles {
                            if paddle.node == secondBody.node {
                                ball.paddleLastTouched = paddle
                                break
                            }
                        }
                    }
                    break
                }
            }
            
            // ---------- Check if ball hit powerUp ---------- 
            if (firstBody.categoryBitMask == PhysicsCategory.Ball && secondBody.categoryBitMask == PhysicsCategory.PowerUp){
                // Identify and find the powerUp based on the unique key of node.name
                for ball in self.balls {
                    if ball.node == firstBody.node {
                        for powerUp in self.level.powerUps {
                            if powerUp.node == secondBody.node {
                                powerUp.applyPowerUpToBall(ball: ball, levelScene: self)
                                break
                            }
                        }
                        break
                    }
                }
                
            }
        }
    }
    
    //=========================================================================================================
    // MARK: GamePlay Logic
    //=========================================================================================================
    
    // Wait for user to tap to play
    func startGame(){
        let tapToStartLabel = SKLabelNode(text: "Tap to Start")
        tapToStartLabel.name = LevelConstants.START_LEVEL_LABEL
        tapToStartLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * LevelConstants.TOP_TEXT_1)
        tapToStartLabel.zPosition = LevelConstants.TEXT_Z_POSITION
        tapToStartLabel.setScale(0.0)
        self.addChild(tapToStartLabel)
        self.gameState.enter(WaitingForTap.self)
    }
    
    func isGameOver() -> Bool {
        if (!self.players[0].isAlive() || !self.players[1].isAlive()){
            print("Game over!")
            return true
        }
        
        return false
    }
    
    func endGame(){
        let endGameLabel = SKLabelNode(text: "Game Over")
        endGameLabel.name = LevelConstants.END_GAME_LABEL
        endGameLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * LevelConstants.TOP_TEXT_1)
        endGameLabel.zPosition = LevelConstants.TEXT_Z_POSITION
        endGameLabel.setScale(0.0)
        self.addChild(endGameLabel)
        
        let winnerText: String = players[0].numberLives <= 0 ? "You Lost!" : "You Won!"
        let winnerLabel = SKLabelNode(text: winnerText)
        winnerLabel.name = LevelConstants.WINNER_LABEL
        winnerLabel.position = CGPoint(x: self.frame.midX, y: self.frame.maxY * LevelConstants.TOP_TEXT_2)
        winnerLabel.zPosition = LevelConstants.TEXT_Z_POSITION
        winnerLabel.setScale(0.0)
        self.addChild(winnerLabel)
        
        self.gameState.enter(GameOver.self)
    }
    
    func playerLoseLife(playerNum: Int, damage: Int){
        // Remove a life
        self.level.players[playerNum].loseLife(damage: damage)
        
        // Update Score
        self.scoreLabels[playerNum].text = String(self.level.players[playerNum].numberLives)
        
        // Check if game is over
        if isGameOver(){
            endGame()
        }
        
        // Put ball back to middle
        for ball in self.balls {
//            ball.resetPositionToMiddle()
        }
        
        // Add impulse to balls
//        addImpulseToBalls()
    }
    
    func addImpulseToBalls(){
        for i in 0..<self.balls.count {
            self.balls[i].node.physicsBody?.velocity = LevelScene.randomDirection(desiredSpeed: BallConstants.DEFAULT_SPEED)
        }
    }
    
    //=========================================================================================================
    // MARK: User Touch Handling
    //=========================================================================================================
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?){
        switch self.gameState.currentState {
        case is WaitingForTap:
            self.gameState.enter(Playing.self)
        case is Playing:
            let touch = touches.first
            let touchLocation = touch!.location(in: self)
            
            if let body = self.physicsWorld.body(at: touchLocation){
                if body.node!.name == LevelConstants.userPaddleName{
                    userTouchingPaddle = true
                }
            }
        case is GameOver:
            print("Game Over")
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
            let paddle = paddles[LevelConstants.USER_PLAYER].node
            
            var paddleX = paddle.position.x + thisLocation.x - prevLocation.x
            paddleX = max(paddleX, paddle.size.width / 2)
            paddleX = min(paddleX, level.scoreRegions[LevelConstants.USER_PLAYER].frame.maxX - paddle.size.width / 2)
            
            paddle.position = CGPoint(x: paddleX, y: paddle.position.y)
        }
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?){
        
    }
    
    //=========================================================================================================
    // MARK: Helper Methods
    //=========================================================================================================
    
    public static func randomFloat(from: CGFloat, to: CGFloat) -> CGFloat {
        let time = UInt32(NSDate().timeIntervalSinceReferenceDate)
        srand48(Int(time))
        return CGFloat(drand48()) * (to - from) + from
    }
    
    public static func randomDirection(desiredSpeed: CGFloat) -> CGVector {
        let rads = randomFloat(from: 0, to: 360) * CGFloat(M_PI / 180.0)
        var dx = cos(rads)
        var dy = sin(rads)
        dx *= desiredSpeed
        dy *= desiredSpeed
        return CGVector(dx: dx, dy: dy)
    }
    
    public static func distanceBetweenValues(val1: CGFloat, val2: CGFloat) -> CGFloat {
        return abs(val2 - val1)
    }
    
}
