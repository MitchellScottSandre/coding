///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package PracticeProblems;
//
//import static com.google.common.collect.ComparisonChain.start;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * @author scott
// */
//public class CourseSchedule {
//
//    class Node {
//        int label;
//        boolean canTake = false;
//        List<Node> allows;
//        List<Node> requirements;
//        public Node(int label){
//            this.label = label;
//            this.allows = new ArrayList<>();
//            this.requirements = new ArrayList<>();
//        }
//    }
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        // create all my nodes
//        Map<Integer, Node> nodes = new HashMap<>();
//        for (int i = 0; i < prerequisites.length; i++){
//            int a = prerequisites[i][0];
//            int b = prerequisites[i][1];
//            Node course;
//            Node prereq;
//            if (!nodes.containsKey(a)){ // get, or set, courseNode
//                course = new Node(a);
//            } else {
//                course = nodes.get(a);
//            }
//            if (!nodes.containsKey(b)){ //get or set prereq node
//                prereq = new Node(b);
//            } else {
//                prereq = nodes.get(b);
//            }
//            
//            prereq.allows.add(course);
//            course.requirements.add(prereq);
//        }
//        Node curr = ((ArrayList<Node>) nodes.values()).get(0);
//        while(true){
//            
//        }
//        if (searchDown(curr) == true){
//            
//        }
//    }
//    
//    public boolean search(Node n){
//        
//    }
//    
//    public boolean searchUp(Node n){
//        if (n.allows.size() == 0) return true;
//        
//    }
//    
//    public boolean searchDown(Node n){
//        if (n.requirements.size() == 0) return true;
//        int need = n.requirements.size();
//        int count = 0;
//        for (Node prereq : n.requirements){
//            if (searchDown(prereq) == true){
//                count++;
//            }
//        }
//        return count == need;
//    }
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}
