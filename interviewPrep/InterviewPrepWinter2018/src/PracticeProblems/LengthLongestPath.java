///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package PracticeProblems;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//
///**
// *
// * @author scott
// */
//public class LengthLongestPath {
//
//    class Directory {
//        String name;
//        List<Directory> children;
//        
//        public Directory(String name){
//            this.name = name;
//            this.children = new ArrayList<>();
//        }
//        
//        public void addChild(Directory d){
//            this.children.add(d);
//        }
//    }
//    public int lengthLongestPath(String input) {
//        Directory top = null;
//        Directory currentInside = null;
//        Stack<Directory> recent = new Stack<>();
//        while(input.length() > 0){
//             String s = input.substring(0, input.indexOf("\""));
//             s = s.substring(s.indexOf("\"") + 1, s.length() - 1);  
//             if (s.equals("n")){
//                 currentInside = recent.peek();
//             }
//             if (top == null){
//                 top = new Directory(s);
//                 recent.add(top);
//             } else {
//                 
//             }                                    
//        }
//    }
//
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}
