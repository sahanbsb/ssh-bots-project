

//package sshbots;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blocker {

	private static final HashMap<String, IPdet> blockd = new HashMap<>();
        
        public static void delete_node( String IP ){
            blockd.remove( IP );
        }
        
        public static void insert(String IP, Date time){
            IPdet obj = blockd.get(IP);
            
            if (obj==null){
                IPdet new_IPdet = new IPdet(IP,time);
                blockd.put(IP, new_IPdet);
            }
            else{
                blockd.get(IP).setTime(time);
            }
            
            
        }
        
        public static void IP_check(String IP, String to_print){
            File fileName = new File("blocked.txt");
            File tmp = new File("tmp.txt");
            
            Pattern pIP = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
            
            try{
                FileReader fileRd = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fileRd);
                BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
                String line;

                while ((line = br.readLine()) != null) {
                    Matcher matched_ip = pIP.matcher(line);
                    matched_ip.find();
                    
                    //System.out.println(matched_ip.group(0) + " equals to " + IP);
                    if (!matched_ip.group(0).equals(IP)){
                        System.out.println("sdgasg");
                        writer.write(line);
                        writer.newLine();
                    }  
		}
			
			//-----------------
                writer.write(to_print);
		tmp.renameTo(fileName);
                writer.close();
                br.close();
	
			
                }catch (FileNotFoundException x) {
			System.out.println("Make sure " + fileName + " is also here!");
			System.exit(-1);
		} catch (IOException x) {
			System.out.println(x);
			System.exit(-1);
		}
        }
        
        public static void blocker(IPdetcount obj){
            String IP = obj.getIP();
            Date time = obj.getTime();
            //System.out.println(IP + " is blocked");//should implement the blocker here
            block_ip(IP,true);
            remove_blck(IP);
            
            insert(IP,time);
            
            String to_print = IP + " @ " + time;
            
            IP_check(IP, to_print);
            
            
           /* String writeFile = "blocked.txt";
			
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(writeFile, true)))) {
				
				out.printf(IP +" @ " + time + "\n");
				
		} catch (FileNotFoundException x) {
                    System.out.println("Make sure " + writeFile + " is also here!");
                    System.exit(-1);
				
                } catch (IOException x) {
                    System.out.println(x);
                    System.exit(-1);
				
		}*/	
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
        
        public static void mail(){
            //System.out.println("blocked IPS are:");
            for (IPdet obj: blockd.values()){
                String IP = obj.getIP();
                System.out.println("blocked IPS are:" + IP);
            }
        }
}
