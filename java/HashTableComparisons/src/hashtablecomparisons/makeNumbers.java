/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtablecomparisons;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author scott
 */
public class makeNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("numbers.txt");
        for (int i = 0; i < 200000; i++){
            writer.println(i);
        }
        writer.close();
    }
    
}
