//package ssh_bots;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
	
	private static HashMap<String, IPdetcount> stor = new HashMap<String, IPdetcount>();
	
	public static void clear() {
		stor.clear();
	}
	
	public static void clean() {
		
		ArrayList <String> expired = new ArrayList<String>();
			
		for(IPdetcount Obj: stor.values()){
			
			String objIP = Obj.getIP();
			Date objTime = Obj.getTime();
			Date nowTime = new Date();
			
			int timeDiff = Time_Manager.Time_differ(nowTime, objTime);
			//System.out.println("td = "+timeDiff);
			if( timeDiff > 10 ) {
				expired.add(objIP);
				System.out.println(Obj.getIP() + " expired");
			}
		}
		
		for(String IP : expired){
			stor.remove(IP);
		}
	}
	
	public static void insert( IPdet Obj ) {
		
		String IP = Obj.getIP();
		Date Time = Obj.getTime();
		
		IPdetcount gotObj = stor.get(IP);
		
		if(gotObj != null) {
			
			stor.get(IP).incCount();
			stor.get(IP).setTime(Time);
                        
                        if(stor.get(IP).isBot()){
							Storage.show();
							Blocker.blocker(stor.get(IP));
                            //System.out.println(IP + " removed from stor");
                            stor.remove(IP);
                        }
						
		}else{
			
			IPdetcount newObj = new IPdetcount(IP, Time);
			
			stor.put(IP,newObj);
			
			System.out.println("New IP FOUND"+IP);
			
		}
	}
	
	public static IPdetcount getIPdetc(String IP) {
		return stor.get(IP);
	}
	
	public static void show() {
		
		//ArrayList <String> expired = new ArrayList<String>();
			
		for(IPdetcount Obj: stor.values()){
			
			Obj.display();
			
			
		}
		
	}
	
}
