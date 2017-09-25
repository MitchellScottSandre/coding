/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;

/**
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.
 */
public class HitCounter {

    private List<Integer> data;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        this.data = new ArrayList<>();
    }
    
    public void update(int t){
        int newest = t;
        int oldest = this.data.get(0);
        while (oldest <= newest - 300 && !this.data.isEmpty()){
            this.data.remove(0);
            oldest = this.data.get(0);
        }
    }

    
    
    public int mostRecentTimestamp(){
        return this.data.isEmpty() ? -1 : this.data.get(this.data.size() - 1);
    }
    
    public void addTimestamp(int t){
        if(t != mostRecentTimestamp()){
            this.data.add(t);
        }
        update(t);
    }



    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        this.addTimestamp(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        update(timestamp);
        return this.data.size();
    }
}
