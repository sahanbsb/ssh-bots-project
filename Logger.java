import java.io.*;
import java.util.*;
import java.text.*;

class Logger{
	public static void main(String [] args){

		String writeFile = "CpydFile.java";
		String l1 = "wala sshd[50382]: error: PAM: authentication error for illegal user admin from 91.220.131.";
		String l2 = "wala sshd[50382]: error: PAM: authentication error for illegal user admin from 91.220.131.";
		
		Date T1;
		Date T2;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd hh:mm:ss");
		
		for(int j=0; j<1 ; j++) {
			for(int i=0; i<5 ; i++) {
				try {
					Thread.sleep(1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
						
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auth.log", true)))) {
					
					//for(int i=0; i<60 ; i++) {
						//System.out.println(dateFormat.format(T));
						T1 = new Date();
							if(i%2 == 0){
								System.out.printf("%s %s\n",dateFormat.format(T1),l1+j);
								out.printf("%s %s\n",dateFormat.format(T1),l1+j);
							}
							try {
								Thread.sleep(1000);                 //1000 milliseconds is one second.
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
							T2 = new Date();
							System.out.printf("%s %s\n",dateFormat.format(T2),l2+(j+100));
							out.printf("%s %s\n",dateFormat.format(T2),l2+(j+100));
						
						//out.println("the text");
					//}
					
				} catch (FileNotFoundException x) {
					System.out.println("Make sure " + writeFile + " is also here!");
					System.exit(-1);
					
				} catch (IOException x) {
					System.out.println(x);
					System.exit(-1);
					
				}
			}
			
		}
	}
}
