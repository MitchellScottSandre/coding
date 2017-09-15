package mipscompiler;

import java.util.Scanner;

public class MIPSCompiler {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("MIPS COMPILER - by Mitchell Scott Sandre");
        System.out.println("Type 'exit' at any time to exit");

        String command;

        while (true) {
            System.out.print("\n--> Enter command: ");
            command = input.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("\nPROGRAM ENDED");
                return;
            } else {
                command += ",";
                String operation = null, op1 = null, op2 = null, op3 = null, outputBinary = null, outputHex = null;
                try {
                    int numDollarSigns = command.length() - command.replace("$", "").length();
                    int i1 = 0;
                    int i2 = command.indexOf(" ");

                    operation = command.substring(i1, i2);
                    command = command.replace(" ", "");
                    int i3 = command.indexOf("$") + 1;     //op1
                    int i4 = command.indexOf(",");
                    op1 = i3 != -1 && i4 != -1 ? command.substring(i3, i4) : null;
                    
                    int i5 = command.indexOf("$", i4) + 1; //op2
                    int i6 = command.indexOf(",", i5);
                    op2 = numDollarSigns >= 2 ? command.substring(i5, i6) : null;
                                        
                    int i7 = numDollarSigns == 3 ? command.indexOf("$", i6) + 1 : command.indexOf(",", i6) + 1; //op3 or i
                    int i8 = command.indexOf(",", i7 + 1);
                    op3 = i7 != -1 && i8  != -1 ? command.substring(i7, i8) : null;
                    
                    if (operation != null && op1 != null && op2 != null && op3 != null) {
                        String regularOps = decToBinString(op2) + decToBinString(op3) + decToBinString(op1);
                        switch (operation.toLowerCase()) {
                            case "add":
                                outputBinary = "000000" + regularOps + "00000100000";
                                break;
                            case "sub":
                                outputBinary = "000000" + regularOps + "00000100010";
                                break;
                            case "beq":
                                outputBinary = "000100" + decToBinString(op1) + decToBinString(op2) + decToBinString(op3, 16);
                                break;
                            case "bne":
                                outputBinary = "000101" + decToBinString(op1) + decToBinString(op2) + decToBinString(op3, 16);
                                break;
                            case "slt":
                                outputBinary = "000000" + regularOps + "00000101010";
                                break;
                            default:
                                System.out.println("---> Could not recognize operation");
                                break;
                        }
                    } else if (operation != null && op1 != null && op2 == null && op3 == null){
                        switch (operation.toLowerCase()) {
                            case "jr":
                                outputBinary = "000000" + decToBinString(op1) + "000000000000000001000";
                                break;
                            case "lis":
                                outputBinary = "0000000000000000" + decToBinString(op1) + "00000010100";
                                break;
                            default:
                                System.out.println("---> Could not recognize operation");
                                break;
                        }
                    }
                    
                    // Display Output
                    System.out.println("BINARY: " + formatBinary(outputBinary));
                    System.out.println("HEX: " + binToHexString(outputBinary));
                } catch (Exception e) {
                    System.out.println("Yikes - you messed up. Remember commas!");
                }
            }
        }
    }
    
    private static String formatBinary(String binaryString){
        String output = "";
        for(int i = 0; i < binaryString.length(); i++){
            output += binaryString.charAt(i) + "";
            if (i % 4 == 3){
                output += " ";
            }
        }
        return output;
    }
    
    private static String decToBinString(String x){
        return decToBinString(x, 5);
    }
    
    private static String decToBinString(String x, int length){
        String binVal = Integer.toBinaryString(Integer.parseInt(x));
        String padding = "";
        for (int i = 0; i < length - binVal.length(); i++){
            padding +="0";
        }
        return padding + binVal;
    }
    
    private static String binToHexString(String binString){
        String hex = "0x";
        String temp = "";
        
        for (int i = 0; i < binString.length(); i++){
            temp += binString.charAt(i);
            if (i % 4 == 3){
                int val = Integer.parseInt(temp, 2);
                hex += Integer.toHexString(val);
                temp = "";
            }
        }
        
        return hex;
    }

}
