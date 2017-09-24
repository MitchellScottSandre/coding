
package PracticeProblems;

/**
Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromNumber {
    
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        while (x > 9){
            System.out.println("x is : " + x + ", left: " + x / (int)(Math.pow(10, (int)Math.log10(x))) + ", right: " + x % 10 + ", dividing by " + (int)(Math.pow(10, (int)Math.log10(x))));
            if (x % 10 != x / (int)(Math.pow(10, (int)Math.log10(x)))){
                return false;
            }
            x = x -  (x / (int)(Math.pow(10, (int)Math.log10(x)))) * (int)(Math.pow(10, (int)Math.log10(x))) ; // get rid of largest digit
            x /= 10; // get rid of smallest digit
        }
        return true;
    }

   
    public static void main(String[] args) {
        PalindromNumber sol = new PalindromNumber();
        System.out.println(sol.isPalindrome(1000021));
    }
    
}
