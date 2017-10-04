/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author scott
 */
public class ArrayListTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<LinkedList<Integer>> answer = new ArrayList<>();
        answer.add(new LinkedList());
        answer.add(3, new LinkedList());
    }
    
}
