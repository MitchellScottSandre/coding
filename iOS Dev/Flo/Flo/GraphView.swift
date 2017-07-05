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
    var graphPoints: [Int] = [4,2,6,4,1,8,3]
    
    override func draw(_ rect: CGRect) {
        
        let path = UIBezierPath(roundedRect: rect, cornerRadius: 8)
        path.addClip()
        
        // Get the current context
        let context = UIGraphicsGetCurrentContext()
        let colors = [startColor.cgColor, endColor.cgColor] as CFArray
        
        //set up the color space
        let colorSpace = CGColorSpaceCreateDeviceRGB()
        
        // set up the colro stops
        let colorLocations: [CGFloat] = [0.0, 1.0]
        
        // create the gradient
        let gradient = CGGradient(colorsSpace: colorSpace, colors: colors, locations: colorLocations)
       
        // draw the gradient
        var startPoint = CGPoint.zero
        var endPoint = CGPoint(x: 0, y: self.bounds.height)
        context!.drawLinearGradient(gradient!, start: startPoint, end: endPoint, options: CGGradientDrawingOptions(rawValue: 0))
        
        // variables for x, y calculations
        let width = rect.width
        let margin: CGFloat = 20.0
        let topBorder: CGFloat = 60
        let bottomBorder: CGFloat = 50
        let graphHeight = rect.height - topBorder - bottomBorder
        let maxValue = graphPoints.max()
        
        //====================================================================================================
        // draw the line graph
        UIColor.white.setFill()
        UIColor.white.setStroke()
        
        //set up the points line
        let graphPath = UIBezierPath()
        graphPath.move(to: CGPoint(x:columnXPoint(column: 0,
                                                  margin: margin,
                                                  width: width),
                                   y: columnYPoint(dataPointValue: graphPoints[0],
                                                   maxValue: maxValue!,
                                                   graphHeight: graphHeight,
                                                   topBorder: topBorder)))
        
        // add points for each item in the graphPoints array
        for i in 1..<graphPoints.count {
            let nextPoint = CGPoint(x:columnXPoint(column: i,
                                                   margin: margin,
                                                   width: width),
                                    y: columnYPoint(dataPointValue: graphPoints[i],
                                                    maxValue: maxValue!,
                                                    graphHeight: graphHeight,
                                                    topBorder: topBorder))
            graphPath.addLine(to: nextPoint)
        }
        
        graphPath.stroke() // Used this just to show that the white lines are drawn
        //context?.saveGState()
        
        //====================================================================================================
        // Create the clipping path for the graph gradient (make the beneath the graph part gradient, too)
        
        let clippingPath = graphPath.copy() as! UIBezierPath
        
        // bottom right point to graph (want to fill it, eventually)
        clippingPath.addLine(to: CGPoint(x: columnXPoint(column: graphPoints.count - 1, margin: margin, width: width),
                                         y: rect.height))
        // bottom left point to graph
        clippingPath.addLine(to: CGPoint(x: columnXPoint(column: 0, margin: margin, width: width),
                                         y: rect.height))
        clippingPath.close()
        
        clippingPath.addClip() // Intersects the area encolsed by the receiver's path with the clipping path of the current graphics context and makes the resulting shape the current clipping path
        
        let highestYPoint = columnYPoint(dataPointValue: maxValue!,
                                         maxValue: maxValue!,
                                         graphHeight: graphHeight,
                                         topBorder: topBorder)
        startPoint = CGPoint(x: margin, y: highestYPoint)
        endPoint = CGPoint(x: margin, y:self.bounds.height)
        
        context!.drawLinearGradient(gradient!, start: startPoint, end: endPoint, options: CGGradientDrawingOptions(rawValue: 0))
        
        graphPath.lineWidth = 2.5
        graphPath.stroke()
        
        //====================================================================================================
        // add the 3 horizontal lines
        let linePath = UIBezierPath()
        //top line
        linePath.move(to: CGPoint(x: margin, y: topBorder))
        linePath.addLine(to: CGPoint(x: width - margin, y: topBorder))
        
        // middle line
        linePath.move(to: CGPoint(x: margin, y: graphHeight / 2 + topBorder))
        linePath.addLine(to: CGPoint(x: width - margin, y: graphHeight / 2 + topBorder))
        
        //buttom line
        linePath.move(to: CGPoint(x: margin, y: rect.height - bottomBorder))
        linePath.addLine(to: CGPoint(x: width - margin, y: rect.height - bottomBorder))
        
        let linePathColor = UIColor(white: 1.0, alpha: 0.3)
        linePathColor.setStroke()
        linePath.lineWidth = 1.5
        
        linePath.stroke()
        
        //====================================================================================================
        // Addinhg the graph labels
        
        
    }
    
    // Mark: Private Functions
    private func columnXPoint(column: Int, margin: CGFloat, width: CGFloat) -> CGFloat {
        let spacer = (width - margin*2 - 4) / CGFloat((self.graphPoints.count - 1))
        var x: CGFloat = CGFloat(column) * spacer
        x += margin + 2
        return x
    }
    
    private func columnYPoint(dataPointValue: Int, maxValue: Int, graphHeight: CGFloat, topBorder: CGFloat) -> CGFloat {
        var y: CGFloat = CGFloat(dataPointValue) / CGFloat(maxValue) * graphHeight
        y = graphHeight + topBorder - y //CG has y = 0 at top, but we want y = 0 at the bottom
        return y
    }
 

}
