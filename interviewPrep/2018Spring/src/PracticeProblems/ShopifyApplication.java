///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package PracticeProblems;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
///**
// *
// * @author scott
// */
//public class ShopifyApplication {
//    
//    public static Scanner scanner = new Scanner(System.in);
//    
//    class Product {
//        int price;
//        String name;
//        String collection;
//        
//        public Product(int price, String name, String collection) {
//            this.price = price;
//            this.name = name;
//            this.collection = collection;
//        }
//    }
//    
//    public 
//    
//    public static void main(String args[] ) throws Exception {
//        // Read in input
//        // String input = scanner.nextLine();
//        Map<String, String> map = new HashMap<>();
//        String input = "{ \"id\": 1, \"discount_type\": \"product\", \"discount_value\": 5.0, \"collection\": \"Lifestyle\" }";
//        input = input.replaceAll("\\s|\\{|\\}|\\\"","");
//        
//        // Add input to map
//        String[] words = input.split(",");
//        for (String word : words) {
//            String[] data = word.split(":");
//            map.put(data[0], data[1]);
//        }
//        
//        // Get api data from map
//        String id = map.get("id");
//        
//        
//    }
//    
//}
