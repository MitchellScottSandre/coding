package PracticeProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
    class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
public class MergeIntervals {


    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
            
        });
        List<Interval> answer = new ArrayList<>();
        if (intervals.size() == 0) return answer;
        if (intervals.size() == 1) return intervals;
        for (int i = 0; i < intervals.size() - 1; i++){
            Interval start = intervals.get(i);
            Interval next = start;
            int nextIndex = i;
            int maxEnd = start.end;
            for (int j = i + 1; j < intervals.size(); j++){
                Interval temp = intervals.get(j);
                if (temp.start <= maxEnd){
                    next = temp;
                    nextIndex = j;
                    if (temp.end > maxEnd){
                        maxEnd = temp.end;
                    }
                } else {
                    break;
                }
            }
            
            Interval n = new Interval(start.start, maxEnd);
            answer.add(n);
            i = nextIndex;
        }
        
        // if last one has not been added
        Interval last = intervals.get(intervals.size() - 1);
        if (last.end > answer.get(answer.size() - 1).end){
            answer.add(last);
        }
        return answer;
    }

    public static void main(String[] args) {
        MergeIntervals sol = new MergeIntervals();
        Interval a = new Interval(8,20);
        Interval b = new Interval(9,15);
        Interval c = new Interval(10,25);
        Interval d = new Interval(24,50);
        Interval e = new Interval(55,100);
        Interval f = new Interval(90,120);
        Interval g = new Interval(130,134);
        
        List<Interval> ans = sol.merge(Arrays.asList(a,b,c,d,e,f,g));
        for (Interval x : ans){
            System.out.println(x.start + " .. " + x.end);
        }
    }

}
