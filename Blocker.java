//package sshbots;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Blocker {

	private static HashMap<String, IPdet> blockd = new HashMap<String, IPdet>();
        
        public static void delete_node( String IP ){
            blockd.remove( IP );
        }
        
        public static void blocker(IPdetcount obj){
            String IP = obj.getIP();
            //System.out.println(IP + " is blocked");//should implement the blocker here
            block_ip(IP,true);
            remove_blck(IP);
            
            String writeFile = "blocked.txt";
			
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(writeFile, true)))) {
				
				out.printf(IP + "\n");
				
			} catch (FileNotFoundException x) {
				System.out.println("Make sure " + writeFile + " is also here!");
				System.exit(-1);
				
			} catch (IOException x) {
				System.out.println(x);
				System.exit(-1);
				
			}
        }
        
        public static void remove_blck(String IP){
			final String IPt = IP;
            new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                //System.out.println("block removed " + IP);
                block_ip(IPt,false);
            }
        },5000 );
        }
        
        public static void block_ip(String ip , boolean d){
            //String ip_localhost = "127.0.0.1";
                if ( d == true ){

                        System.out.println("IP Address blocked : " + ip);
                        System.out.println("\t\tsudo ufw deny from "+ ip);
                        //	Block IP Address in Ubuntu
                        //	Process Ubuntu_Block_IP = Runtime.getRuntime().exec("sudo ufw deny from "+ ip);
                        //	BufferedReader stdInputp = new BufferedReader(new InputStreamReader(Ubuntu_Block_IP.getInputStream()));	// Get Output
                        //	BufferedReader stdErrorp = new BufferedReader(new InputStreamReader(Ubuntu_Block_IP.getErrorStream()));	// Get Error
                }else{

                        System.out.println("IP Address unblocked : " + ip);
                        System.out.println("\t\tsudo ufw allow from "+ ip);
                        //	Unblock IP Address in Ubuntu
                        //	Process Ubuntu_Unblock_IP = Runtime.getRuntime().exec("sudo ufw allow from "+ ip);
                        //	BufferedReader stdInputp = new BufferedReader(new InputStreamReader(Ubuntu_Unblock_IP.getInputStream()));	// Get Output
                        //	BufferedReader stdErrorp = new BufferedReader(new InputStreamReader(Ubuntu_Unblock_IP.getErrorStream()));	// Get Error
                }
	}
}
