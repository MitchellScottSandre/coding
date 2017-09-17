package PracticeProblems;

// Takes unsorted array of axis-aligned rectanges (x1, y1), (x2, y2), and return any pairs of overlapping rectangles
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AxisAlignedRectangles {

    // assuming at most 2 rectangles over lap at one point
    public static Map<Rect, Rect> findOverlappingRects(List<Rect> rects) {
        Map<Rect, Rect> foundPairs = new HashMap<>();
        Multimap<String, Rect> coveredPoints = ArrayListMultimap.create();
        // add to coveredPoints
        for (Rect r : rects) {
            for (int i = r.x1; i <= r.x2; i++) {
                for (int j = r.y1; j <= r.y2; j++) {
                    String point = "(" + i + "," + j + ")";
                    coveredPoints.put(point, r);
                    if (coveredPoints.containsKey(point) == true) {
                        // check if this pair has already been covered
                        Collection<Rect> overlappingRectangles = coveredPoints.get(point);
                        for (Rect overlappingRectangle : overlappingRectangles) {
                            if (foundPairs.get(r) != overlappingRectangle && foundPairs.get(overlappingRectangle) != r) {
                                foundPairs.put(r, overlappingRectangle);
                            }
                        }
                    }
                }
            }
        }

        return foundPairs;
    }

    public static void main(String[] args) {
        Rect a = new Rect(1, 2, 3, 4);
        Rect b = new Rect(2, -2, 4, 3);
        Rect c = new Rect(-10, -10, 10, 10);

        List<Rect> rects = Arrays.asList(a, b, c);

        long time1 = System.nanoTime();
        Map<Rect, Rect> foundPairs = findOverlappingRects(rects);
        long time2 = System.nanoTime();
        System.out.println("TIME IN NANOS: " + (time2 - time1));
        for (Map.Entry<Rect, Rect> pair : foundPairs.entrySet()) {
            System.out.println("PAIR: " + pair.getKey().toString() + " :: " + pair.getValue().toString());
        }
    }

}

class Rect {

    int x1, x2, y1, y2;

    public Rect(int a, int b, int c, int d) {
        x1 = a;
        y1 = b;
        x2 = c;
        y2 = d;
    }

    @Override
    public String toString() {
        return "(" + x1 + "," + y1 + "), (" + x2 + "," + y2 + ")";
    }
}
