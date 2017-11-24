/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package random;

import java.util.Scanner;

/**
 *
 * @author scott
 */
public class TransitionMaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 1 for numbers, enter 2 for Characters");
        int choice = Integer.parseInt(input.nextLine());
        String t1 = "", t2 = "";
        int start = -1;
        int end = -1;
        if (choice == 1) {
            System.out.println("Enter:\n TRANS_1\n START_RANGE\n END_RANGE\n TRANS_2:");
            t1 = input.nextLine();
            start = Integer.parseInt(input.nextLine());
            end = Integer.parseInt(input.nextLine());
            t2 = input.nextLine();
            for (int i = start; i <= end; i++) {
                System.out.println(t1 + " " + i + " " + t2);
            }
        } else {
                    System.out.println("Enter:\n TRANS_1\n START_LETTER\n END_LETTER\n TRANS_2:");
        t1 = input.nextLine();
        char startC = input.nextLine().charAt(0);
        char endC = input.nextLine().charAt(0);
        t2 = input.nextLine();
        for (int i = startC; i <= endC; i++){
            System.out.println(t1 + " " + (char)(i) + " " + t2);
        }
        }
    }

}
