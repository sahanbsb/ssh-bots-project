//package shellcommands;

import java.util.*;
import java.io.*;

public class FileWatcherTest {
  public static void main(String args[]) {
	  
	  FileRead.readFile("auth.log");
	  
    // monitor a single file
    TimerTask task = new FileWatcher( new File("auth.log") ) {
      protected void onChange( File file ) {
        // here we code the action on a change
        System.out.println( "File "+ file.getName() +" have change !" );
        
        FileRead.readFile("auth.log");
        
      }
    };

    Timer timer = new Timer();
    // repeat the check every second
    timer.schedule( task , new Date(), 100 );
  }
}
