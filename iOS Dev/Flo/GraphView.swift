//
//  GraphView.swift
//  Flo
//
//  Created by Scott Sandre on 2017-07-03.
//  Copyright © 2017 Caroline Begbie. All rights reserved.
//

import UIKit

@IBDesignable class GraphView: UIView {
    
    //MARK: Properties
    @IBInspectable var startColor: UIColor = UIColor.red
    @IBInspectable var endColor: UIColor = UIColor.green
    
    override func draw(_ rect: CGRect) {
        
        
        // Get the current context
        let context = UIGraphicsGetCurrentContext()
        let colors = [startColor.cgColor, endColor.cgColor]
        
        //set up the color space
        let colorSpace = CGColorSpaceCreateDeviceRGB()
        
        // set up the colro stops
        let colorLocations: [CGFloat] = [0.0, 1.0]
        
        // create the gradient
        let gradient = CGGradient(colorsSpace: colorSpace, colors: colors, locations: colorLocations)
        
        //TODO
    }
 

}
