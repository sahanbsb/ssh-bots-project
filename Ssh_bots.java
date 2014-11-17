/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package ssh_bots;
import java.util.*;
import java.text.*;
/**
 *
 * @author waruna
 */
public class Ssh_bots {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
        

   public static void main(String args[]) {
      SimpleDateFormat ft = 
      new SimpleDateFormat ("yyyy MMM dd,hh:mm:ss");
      

      String input = args.length == 0 ? "Nov  6 06:10:37" : args[0]; 

      System.out.print(input + " Parses as "); 
      
     Date t;
     t=Time_Manager.Str_to_time(input);
     System.out.println(t.toString());

      Date now=new Date();
      System.out.println("now="+now.toString());
      int n = Time_Manager.Time_differ(now, t);
       System.out.println("differnce="+n);

     /*try { 
          
          Date date=new Date();
          int curent=date.getYear();
          
          input=(1900+curent)+" "+input;
          System.out.println(input);
          t = ft.parse(input);
          long n= date.getTime() - t.getTime();
          System.out.println(n);
          
          
          
          System.out.println(t.toString());
      } catch (ParseException e) { 
          System.out.println("Unparseable using " + ft); 
      }*/
   }
}
    
    

