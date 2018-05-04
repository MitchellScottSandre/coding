
package GooglePracticeProblems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Data {
    char val;
    int index;
    public Data(char c, int i) {
        this.val = c;
        this.index = i;
    }
}

public class MinWindow {

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        Queue<Data> q = new LinkedList<>();
        Map<Character, Data> map = new HashMap<>();
        int leftIndex = -1;
        int rightIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = t.indexOf(c);
            if (index != -1) {
                Data d = new Data(c, i);
                if (map.get(c) != null) {
                    q.remove(map.get(c));
                }
                map.put(c, d);
                q.offer(d);
                q.stream().forEach(data -> System.out.println(data.val + " .. " + data.index));
                System.out.println("---");
                if (q.size() == t.length() && i - q.peek().index < minDistance) {
                    leftIndex = q.peek().index;
                    rightIndex = i;
                    minDistance = i - q.peek().index;
                }
            }
        }
        
        if (leftIndex == -1 || rightIndex == -1) {
            return "";
        }
        return s.substring(leftIndex, rightIndex + 1);
    }
    public static void main(String[] args) {
        minWindow("A", "AA");
    }
    
}
