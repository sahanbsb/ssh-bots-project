//package ssh_bots;

import java.util.regex.*;
import java.util.Date;
import java.io.*;

public class FileRead {
	
	public static Date lastReadTime = new Date(0);
	
	public static IPdet readLine(String line) {

		Pattern pIP = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
		Pattern pIU = Pattern.compile("error");
		Pattern pDt = Pattern.compile("[A-Z][a-z]{1,2} ([0-9]| )[0-9] ([0-9]){1,3}:([0-9]){1,3}:([0-9]){1,3}");
			
		Matcher mIP = pIP.matcher(line);
		Matcher mIU = pIU.matcher(line);
		Matcher mDt = pDt.matcher(line);
		
		mDt.find();
		
		//int TD = Time_Manager.Time_differ(Time_Manager.Str_to_time(mDt.group(0)), lastReadTime );
		
		
		//if( TD >= 0 ){
			
			//System.out.println("File reader time diff = "+TD);
			//System.out.println("last accessed "+lastReadTime);
			
			lastReadTime = Time_Manager.Str_to_time(mDt.group(0));

			if(mIU.find()) {
				if(mIP.find()) {
						
						IPdet Obj = new IPdet(mIP.group(0), mDt.group(0));
						
						//System.out.printf("                            %s read @ %s\n", mIP.group(0), mDt.group(0));
						
						return Obj;
				}
			}
		
		//}
		
		return null;
	}
	
	public static void readFile(String fileName) {
		
		//String fileName = "auth.log";
		
		try {
			/*
			FileReader fileRd = new FileReader(fileName);
			BufferedReader bufferRd = new BufferedReader(fileRd);
			String line = bufferRd.readLine();
			
			while( (line = bufferRd.readLine()) != null) {
				
				IPdet Obj = FileRead.readLine(line);
				
				if(Obj != null) Storage.insert(Obj);
				
				
			}
			
			fileRd.close();
			bufferRd.close();
			*/
			//----------------------
			FileReader fileRd = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileRd);
			String line;
			while (true) {
				line = br.readLine();
				if (line == null) {
					//wait until there is more of the file for us to read
					try {
						Thread.sleep(100);                 //100 milliseconds is one second.
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					Storage.clean();
				}
				else {
					//do something interesting with the line
					IPdet Obj = FileRead.readLine(line);
				
					if(Obj != null) Storage.insert(Obj);
					//System.out.println(line);
				}
			}
			
			//-----------------
			
			
		} catch (FileNotFoundException x) {
			System.out.println("Make sure " + fileName + " is also here!");
			System.exit(-1);
		} catch (IOException x) {
			System.out.println(x);
			System.exit(-1);
		}
		
		
	}
}
