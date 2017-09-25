/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author scott
 */
public class ReconstructItinerary {

    List<List<String>> allOptions = new ArrayList<>();
    
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++){
            String from = tickets[i][0];
            String to = tickets[i][1];
            if (map.containsKey(from)){
                map.get(from).add(to);
            } else {
                List<String> dest = new ArrayList<>();
                dest.add(to);
                map.put(from, dest);
            }
        } 
        return null;
    }
    public static void main(String[] args) {
        ReconstructItinerary sol = new ReconstructItinerary();
        String[][] data = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        sol.findItinerary(data);
    }
    
}
