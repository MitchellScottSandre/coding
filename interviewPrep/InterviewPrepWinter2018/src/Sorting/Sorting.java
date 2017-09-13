/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author scott
 */
public class Sorting {

    public static void print(List<Integer> list){
        System.out.println();
        list.stream().forEach(x -> {
            System.out.print(x + ", ");
        });
        System.out.println();
    }
    
    public static void print(Map<Integer, Integer> map){
        System.out.println();
        map.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getKey() + " -> " + entry.getValue() + ", ");
        });
        System.out.println();
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < 50; i++){
            int x = (int) (Math.random() * 50);
            int y = (int) (Math.random() * 50);
            list.add(x);
            map.put(x, y);
        }
        
        print(list);
        
        Collections.sort(list); // This is how you sort!!
        
        print(list);
        
//        Collections.reverse(list);
        
        print(list);
        
        System.out.println("xxxxx");
        
        print(map);
        
        Map result = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, 
                                          Map.Entry::getValue,
                                          (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        
        print(result);
        
        // Collections
       list.contains(10); // why not just use this??????
       int z = Collections.binarySearch(list, 10); // list must be sorted in ascending order
       System.out.println(z); // z will be -1 if it is not found. if there are multiple values, not
       // clear which index will be returned
    }
    
}
