//
//  ViewController.swift
//  DynamicsDemo
//
//  Created by Scott Sandre on 2017-06-30.
//  Copyright © 2017 ScottSandre. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UICollisionBehaviorDelegate {
    
    // MARK: Properties
    var animator: UIDynamicAnimator! // Implicitly-unwrapped options. Won't be initializing them in this class's init method
    var gravity: UIGravityBehavior!
    var collision: UICollisionBehavior!
    var firstContact = false
    var square: UIView!
    var snap: UISnapBehavior!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Create a square
        self.square = UIView(frame: CGRect(x: 100, y:50, width: 100, height: 100))// y = 0 is top of screen
        square.backgroundColor = UIColor.red
        view.addSubview(square)
        
        animator = UIDynamicAnimator(referenceView: view)
        gravity = UIGravityBehavior(items: [square])
        animator.addBehavior(gravity)
        
        // Create barrier
        let barrier = UIView(frame: CGRect(x: 0, y: 300, width: 139, height: 20))
        barrier.backgroundColor = UIColor.gray
        view.addSubview(barrier)
        
        // Create boundaries for collision detection
        collision = UICollisionBehavior(items: [square]) // barrier was added here, but then the barrier moved, too!
        collision.collisionDelegate = self
        collision.addBoundary(withIdentifier: "barrier" as NSString, for: UIBezierPath(rect: barrier.frame)) // Sets the barrier to be an immovable boundary
        collision.translatesReferenceBoundsIntoBoundary = true // sets boundries to be bounds of reference view
        var updateCount = 0
        updateCount = addShapeOutlines(collision: collision, shape: square, count: updateCount)
        
        animator.addBehavior(collision)
        
        let itemBehaviour = UIDynamicItemBehavior(items: [square])
        itemBehaviour.elasticity = 1.0 // Bounciness. 1 = Completely elastic collision (no energy is lost)
        animator.addBehavior(itemBehaviour)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        if (snap != nil) { // If there is an existing snap behaviour, remove it
            animator.removeBehavior(snap)
        }
        
        let touch = touches.first as? UITouch
        snap = UISnapBehavior(item: square, snapTo: (touch?.location(in: view))!) // Move to where user touched
        animator.addBehavior(snap)
    }
    
    // Implementation for one of collisionBehaviour delegate methods
    func collisionBehavior(_ behavior: UICollisionBehavior, beganContactFor item: UIDynamicItem, withBoundaryIdentifier identifier: NSCopying?, at p: CGPoint) {
        print("Boundary contact occured - \(identifier)")
        
        let collidingView = item as! UIView
        collidingView.backgroundColor = UIColor.yellow
        UIView.animate(withDuration: 0.3) { 
            collidingView.backgroundColor = UIColor.red
        }
        
        if (firstContact == false){ // Detect initial contact between barrier and square
            firstContact = true
            // Create a new square and let it collide with barriers, let gravity affect it
            let square = UIView(frame: CGRect(x: 30, y: 0, width: 100, height: 100))
            square.backgroundColor = UIColor.red
            view.addSubview(square)
            
            collision.addItem(square)
            self.gravity.addItem(square)
            
            let attach = UIAttachmentBehavior(item: collidingView, attachedTo: square)
            self.animator.addBehavior(attach)
        }
    }
    
    // MARK: Private Methods
    
    func addShapeOutlines(collision: UICollisionBehavior, shape: UIView, count: Int) -> Int{
        collision.action = {
            if (count % 3 == 0){
                let outline = UIView(frame: shape.bounds)
                outline.transform = shape.transform
                outline.center = shape.center
                
                outline.alpha = 0.5 // This does:
                outline.backgroundColor = UIColor.clear
                outline.layer.borderColor = shape.layer.presentation()?.backgroundColor
                outline.layer.borderWidth = 1.0
                self.view.addSubview(outline)
            }
        }
        return count + 1
    }


}

