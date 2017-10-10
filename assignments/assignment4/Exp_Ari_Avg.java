import java.io.File;  
import java.io.PrintWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
//import java.util.ArrayList;  
import java.io.IOException;  
import java.util.Random;
import java.text.DecimalFormat;

public class Exp_Ari_Avg{  	 
   public static double expAvg(double preExpAvg, double newSample){ 
      //(1 - a) * EstimatedRTT + a * SampleRTT
      double a = 0.125;
      return (1 - a) * preExpAvg + a * newSample;
   }
	 
   public static double ariAvg(double preAriAvg, double newSample, int n){
   	//(n-1/n) * EstimatedRTT_(n-1) + 1/n * SampleRTT_(n)
      return ((n-1)/(double)n) * preAriAvg + (1/(double)n) * newSample; //fix this
   }
	 
   public static void sampleRTT2EstimatedRTT(Scanner in, PrintWriter out)throws IOException{ 
      double sampleRTT;
      String line, temp;
      double preExpAvg = 0;
      double preAriAvg = 0;
      int n = 1;
      Random random = new Random();
      DecimalFormat df = new DecimalFormat("0.00");
      while (in.hasNextLine()){
         line = in.nextLine();
         if (line.contains("time=")){
            //add sampleRTT to output
            temp = (line.substring(line.indexOf("time=")+5,line.indexOf("ms")));
            sampleRTT = Double.parseDouble(temp.trim());
            out.print(sampleRTT + "\t\t");
            //add expAvg to output
            if(preExpAvg == 0){
               preExpAvg = expAvg(random.nextInt(101), sampleRTT);
               out.print(preExpAvg + "\t\t");
            }
            else {
               preExpAvg = expAvg(preExpAvg, sampleRTT);
               out.print(df.format(preExpAvg) + "\t\t");
            }
            //add ariAvg to output
            if(preAriAvg == 0){
               preAriAvg = ariAvg(random.nextInt(101), sampleRTT, n);
               out.print(preAriAvg);
               n++;
            }
            else {
               preAriAvg = ariAvg(preAriAvg, sampleRTT, n);
               out.print(df.format(preAriAvg));
               n++;
            }
            out.println();
         }
      }
   }
   
   public static void main (String[] args){ 
      try{
         Scanner fileScanner = new Scanner(new File("data.txt")); 
         PrintWriter filePrinter = new PrintWriter(new File("output.txt"));
      	
         filePrinter.print("SampleRTTs\tExpAvgs\t\tAriAvgs");
         filePrinter.println();
      	
         sampleRTT2EstimatedRTT(fileScanner, filePrinter);
      	
         fileScanner.close();
         filePrinter.close();
      }
      catch(IOException e){
         System.out.println("Error opening file.");
         System.exit(0);
      }
   }
}