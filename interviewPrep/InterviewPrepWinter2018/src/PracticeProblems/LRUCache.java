
package PracticeProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?
 */
public class LRUCache {
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> keyValueMap = new HashMap<>();
    int capacity;
    public LRUCache(int capacity) {
        capacity = capacity;
    }
    
    public int get(int key) {
        if (keyValueMap.containsKey(key)){
            list.remove(key);
            list.add(key);
            return keyValueMap.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        keyValueMap.put(key,value);
        if (list.size() < capacity){
            list.add(key);
        } else {
            list.remove(0);
            list.add(key);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
