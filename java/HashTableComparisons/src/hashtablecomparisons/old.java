package hashtablecomparisons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
 
public class old extends Application {
    public static final int TABLE_DEFAULT_SIZE = 100000;
    
    public static Scanner input = new Scanner (System.in);
    public static Scanner fileScanner;
    public static ArrayList<String> words; 
    public static Hashtable<String, Integer> table;

    public static int numWords;
 
    public static void main(String[] args) throws FileNotFoundException {
       
        launch(args);
        
    }
 
    @Override
    public void start(Stage primaryStage) {
        int choice;
    
        //Stage secondaryStage = new Stage();
       // System.out.println("Enter hash function to display: ");
      //  System.out.println("1: DJB2 ");
        //choice = Integer.parseInt(input.nextLine());
//        switch (choice){
//            case 1: 
//                primaryStage.setTitle("Hash-Function Displays: DJB2");
//                //callDJB2();
//                break;
//        }
        Group root = new Group();
        Canvas canvas = new Canvas(900, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.show();
        
        //secondaryStage.show();
    }
    
    private  void callDJB2(){
        for (int i = 0; i < numWords; i++){
            String s = words.get(i);
            int slot = DJBHash(s);
            table.put(s, slot);
        }
       // table.printTable();
    }

    private void drawShapes(GraphicsContext gc) {
//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.BLUE);
//        gc.setLineWidth(5);
//        gc.strokeLine(40, 10, 10, 40);
//        gc.fillOval(10, 60, 30, 30);
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40}, new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90}, new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140}, new double[]{210, 210, 240, 240}, 4);
    }
    
    
     public long RSHash(String str)
   {
      int b     = 378551;
      int a     = 63689;
      long hash = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = hash * a + str.charAt(i);
         a    = a * b;
      }

      return hash;
   }
   /* End Of RS Hash Function */


   public long JSHash(String str)
   {
      long hash = 1315423911;

      for(int i = 0; i < str.length(); i++)
      {
         hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
      }

      return hash;
   }
   /* End Of JS Hash Function */


   public long PJWHash(String str)
   {
      long BitsInUnsignedInt = (long)(4 * 8);
      long ThreeQuarters     = (long)((BitsInUnsignedInt  * 3) / 4);
      long OneEighth         = (long)(BitsInUnsignedInt / 8);
      long HighBits          = (long)(0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
      long hash              = 0;
      long test              = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = (hash << OneEighth) + str.charAt(i);

         if((test = hash & HighBits)  != 0)
         {
            hash = (( hash ^ (test >> ThreeQuarters)) & (~HighBits));
         }
      }
      return hash;

   }
   /* End Of  P. J. Weinberger Hash Function */


   public long ELFHash(String str)
   {
      long hash = 0;
      long x    = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = (hash << 4) + str.charAt(i);

         if((x = hash & 0xF0000000L) != 0)
         {
            hash ^= (x >> 24);
         }
         hash &= ~x;
      }

      return hash;
   }
   /* End Of ELF Hash Function */


   public long BKDRHash(String str)
   {
      long seed = 131; // 31 131 1313 13131 131313 etc..
      long hash = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = (hash * seed) + str.charAt(i);
      }

      return hash;
   }
   /* End Of BKDR Hash Function */


   public long SDBMHash(String str)
   {
      long hash = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
      }

      return hash;
   }
   /* End Of SDBM Hash Function */


   public static int DJBHash(String str)
   {
      int hash = 5381;

      for(int i = 0; i < str.length(); i++)
      {
         hash = ((hash << 5) + hash) + str.charAt(i);
      }
      
      System.out.println("xxx" + hash % table.size());
      return hash % table.size();
   }
   /* End Of DJB Hash Function */


   public long DEKHash(String str)
   {
      long hash = str.length();

      for(int i = 0; i < str.length(); i++)
      {
         hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
      }

      return hash;
   }
   /* End Of DEK Hash Function */


   public long BPHash(String str)
   {
      long hash = 0;

      for(int i = 0; i < str.length(); i++)
      {
         hash = hash << 7 ^ str.charAt(i);
      }

      return hash;
   }
   /* End Of BP Hash Function */


   public long FNVHash(String str)
   {
      long fnv_prime = 0x811C9DC5;
      long hash = 0;

      for(int i = 0; i < str.length(); i++)
      {
      hash *= fnv_prime;
      hash ^= str.charAt(i);
      }

      return hash;
   }
   /* End Of FNV Hash Function */


   public long APHash(String str)
   {
      long hash = 0xAAAAAAAA;

      for(int i = 0; i < str.length(); i++)
      {
         if ((i & 1) == 0)
         {
            hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
         }
         else
         {
            hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
         }
      }

      return hash;
   }
   /* End Of AP Hash Function */
    
   
}

