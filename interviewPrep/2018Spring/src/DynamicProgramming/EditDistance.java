
package DynamicProgramming;

/**
 * Mitchell Scott Sandre
 * 
 * Given two strings str1 and str2 and below operations that can performed on str1. 
 * Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
 * replace, remove, insert
 */

public class EditDistance {
    
    public static int getMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
    
    // turn string a into string b
    
    public static int editDistance(String a, String b, int m, int n) {
        // Base Cases
        if (m == 0) return n; // will have to insert n letters
        if (n == 0) return m; // will have to remove m letters
        if (a.charAt(m - 1) == b.charAt(n - 1)) {
            // last letters are equal
            return editDistance(a, b, m - 1, n - 1);
        }
        
        return 1 + getMin(
               editDistance(a, b, m - 1, n - 1), // we REPLACED the last letter, so it matches. Move on to left letter. ABC .. ABB
               editDistance(a, b, m - 1, n),    // we REMOVED the last letter of string a, so string b is the same length
               editDistance(a, b, m, n - 1)     // we inserted a letter, a stays same length, n gets shorter
        );
    }

    public static void main(String[] args) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(editDistance( str1 , str2 , str1.length(), str2.length()));
    }

}
