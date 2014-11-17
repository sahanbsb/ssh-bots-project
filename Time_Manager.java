//package ssh_bots;

import java.util.*;
import java.text.*;

public class Time_Manager {
   
    public static Date Str_to_time(String arg){
        /****
         * Argument contains month date and the time
         * it should be converted to the date type and the year is assumed as 
         * the current year..
         */
        Date t= new Date();
        SimpleDateFormat format = new SimpleDateFormat ("yyyy MMM dd hh:mm:ss");
            try { 
              Date date=new Date();             //getting the current time
              int year=date.getYear();          //getting the current year,this gives number of years after 1900

              arg=(1900+year)+" "+arg;          //changing to the format

              t = format.parse(arg);

            } catch (ParseException e) { 
              System.out.println("Unparseable using " + format); 
            }
        return t;
    }


    public static int Time_differ(Date time1,Date time2){
    //return time difference between 2 Date variables in seconds

        long n= time1.getTime() - time2.getTime();                      //time difference in milliseconds
        int dif = (int)(n/1000);                                       //changing milliseconds to seconds
        return dif;
    }
}
