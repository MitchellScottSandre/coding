//
//  ViewController.swift
//  Flo
//
//  Created by Caroline Begbie on 15/04/2015.
//  Copyright (c) 2015 Caroline Begbie. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var isGraphViewShowing = false
    //Counter outlets
    @IBOutlet weak var counterView: CounterView!
    @IBOutlet weak var counterLabel: UILabel!
    
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var graphView: GraphView!
    
    @IBOutlet weak var averageWaterDrunk: UILabel!
    @IBOutlet weak var maxLabel: UILabel!
    
    func setupGraphDisplay() {
        // using 7 labels for the graph
        let numOfDays:Int = 7
        
        // replace the last day data with today's actual data
        graphView.graphPoints[graphView.graphPoints.count - 1] = counterView.counter
        graphView.setNeedsDisplay() // graph needs to be redrawn
        
        maxLabel.text = "\(graphView.graphPoints.max())"
        
        let average = graphView.graphPoints.reduce(0, +) / graphView.graphPoints.count
        
        averageWaterDrunk.text = "\(average)"
    
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        counterLabel.text = String(counterView.counter)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func btnPushButton(_ button: PushButtonView) {
        if button.isAddButton {
            counterView.counter += 1
        } else {
            if counterView.counter > 0 {
                counterView.counter -= 1
            }
        }
        counterLabel.text = String(counterView.counter)
        
        if (isGraphViewShowing){
            counterViewTap(gesture: nil)
        }
    }
    
    @IBAction func counterViewTap(gesture: UITapGestureRecognizer?){
        
        if(isGraphViewShowing){
            // Hide Graph
            UIView.transition(from: graphView,
                              to: counterView,
                              duration: 1.0,
                              options: [UIViewAnimationOptions.transitionFlipFromLeft,
                                        UIViewAnimationOptions.showHideTransitionViews],
                              completion: nil)
        } else {
            UIView.transition(from: counterView,
                              to: graphView,
                              duration: 1.0,
                              options: [UIViewAnimationOptions.transitionFlipFromRight,
                                        UIViewAnimationOptions.showHideTransitionViews],
                              completion: nil)
        }
        isGraphViewShowing = !isGraphViewShowing
    }
}

