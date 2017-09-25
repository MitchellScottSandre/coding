/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeProblems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scott
 */
public class UTF8Validation {

    public boolean validUtf8(int[] data) {
        List<String> binaryData = new ArrayList<>();
        for(int i = 0; i < data.length; i++){
            binaryData.add(decToHex(data[i]));
        }
        
        //validate
        while(binaryData.size() != 0){
            String a = binaryData.get(0);
            if (a.charAt(0) == '0'){ // 1 byte character, valid
                binaryData.remove(0);
            } else {
                if (a.substring(0,3).equals("110")){
                    if (binaryData.size() <= 1 || !binaryData.get(1).substring(0,2).equals("10")){
                        return false;
                    } else {
                        binaryData.remove(0);
                        binaryData.remove(0);
                    }
                } else if (a.substring(0,4).equals("1110")){
                    if (binaryData.size() <= 2 || 
                            (!binaryData.get(1).substring(0,2).equals("10") && 
                             !binaryData.get(2).substring(0,2).equals("10"))){
                        return false;
                    } else {
                        binaryData.remove(0);
                        binaryData.remove(0);
                        binaryData.remove(0);
                    }
                } else if (a.substring(0,5).equals("11110")){
                    if (binaryData.size() <= 3 || 
                            (!binaryData.get(1).substring(0,2).equals("10") && 
                             !binaryData.get(2).substring(0,2).equals("10") &&
                             !binaryData.get(2).substring(0,2).equals("10") )){
                        return false;
                    } else {
                        binaryData.remove(0);
                        binaryData.remove(0);
                        binaryData.remove(0);
                        binaryData.remove(0);
                    }
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public String decToHex(int x){
        String v = Integer.toString(x,2); //get hex 
        int l = v.length();
        for (int i = 0; i < 8 - l; i++){
            v = "0" + v;
        }
        return v;
    }
    public static void main(String[] args) {
        UTF8Validation sol = new UTF8Validation();
        System.out.println(sol.decToHex(2));
    }
    
}
