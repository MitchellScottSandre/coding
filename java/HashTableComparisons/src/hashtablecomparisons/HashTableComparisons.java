package hashtablecomparisons;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
 
public class HashTableComparisons extends Application {
    public static final int TABLE_SIZE = 10;
    public static final int MAX_NUM_WORDS = 10;
    
    public static Scanner input = new Scanner (System.in);
    public static Scanner fileScanner;
    public static ArrayList<String> words; 
    public static Hashtable<String, Integer> table;
    

    public static int numWords;
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        table =  new Hashtable<String, Integer>(TABLE_SIZE);
        File file = new File ("twl-words.txt");
        fileScanner = new Scanner(file);
        words = new ArrayList<String>();
        
        int counter = 0;
        while (fileScanner.hasNextLine() && counter < MAX_NUM_WORDS){
            words.add(fileScanner.nextLine());
            counter++;
        }
    
        numWords = words.size();
        System.out.println(numWords);
        System.out.println("Finished scanning file");
        call_DJBHash();
        System.out.println(table.toString());
        //System.out.println(table.toString());
        //Group root = new Group();
        //Canvas canvas = new Canvas(300, 250);
       // GraphicsContext gc = canvas.getGraphicsContext2D();
        //drawShapes(gc);
        //root.getChildren().add(canvas);
        //primaryStage.setScene(new Scene(root));
        //primaryStage.show();
    }
    
  
    
    public static void call_DJBHash(){
        for (int i = 0; i < words.size(); i++){
            String s = words.get(i);
            long loc = Math.abs(DJBHash(s));
            System.out.println(loc);
            table.put(s, (int)loc);
        }
        
    }
    
     public static long DJBHash(String str)
   {
      long hash = 5381;

      for(int i = 0; i < str.length(); i++)
      {
         hash = ((hash << 5) + hash) + str.charAt(i);
      }
      
      return hash % TABLE_SIZE;
      
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
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                       new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                         new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                          new double[]{210, 210, 240, 240}, 4);
    }
}