/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author scott
 */
public class Scrabble1 {
    public static List<String> list = new ArrayList<>();
    public static Set<String> set = new HashSet<>();
    public static int count;
    public static int count2;
    public static int count3;

    public static void generate(String curr, char[] letters){
        if (curr.length() == 7) {
            list.add(curr);
            set.add(curr);//adds unique elements only
        }
        for (int i = 0; i < letters.length; i++){
            char c = letters[i];
            if(c!= '!'){
                char[] copy = letters.clone();
                copy[i] = '!';
                generate(curr + c,copy);
            }
        }
    }
    public static void main(String[] args) {
        char[] letters = {'a','a','e','n','s','t','t'};
        generate("",letters);
        
        set.stream().forEach(x -> {
            if(x.contains("aae") || x.contains("aea") || x.contains("eaa")){
                count++;
            }
        });
        list.forEach(x -> {
            if (x.charAt(0) == 'n' || (x.charAt(0) == 's' || (x.charAt(0) == 't' ))){
                if (x.charAt(6) == 'n' || (x.charAt(6) == 's' || (x.charAt(6) == 't' ))){
                    count2++;
                }
            }
            if(x.charAt(0) != x.charAt(6)){
                count3++;
            }
        });
        System.out.println(set.size());
        System.out.println(count);
        System.out.println(count2);
        System.out.println(count3);
    }
    
}
