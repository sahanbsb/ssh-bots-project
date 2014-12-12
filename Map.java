import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {
	
	public static ArrayList<String> locations = new ArrayList<String>();
	
    public static void mapCountries(ArrayList<String> countries) {
        String s;
        String address = "https://maps.googleapis.com/maps/api/staticmap?center=Sri%20Lanka&zoom=1&size=1000x700&maptype=roadmap&format=jpg";
        Process p;
        String country;
        
        for(int i=0; i < countries.size(); i++){
        	//System.out.println(countries[i]);
        	 country = countries.get(i).replaceAll(" ", "%20");
        	//System.out.println(countries[i]);
        	address = address + "&markers=color:red|label:"+ (i+1) +"|" + country;
        }
        
        System.out.println(address);
        
        try {
            p = Runtime.getRuntime().exec("firefox "+address);
            //BufferedReader br = new BufferedReader(
               // new InputStreamReader(p.getInputStream()));
           // while ((s = br.readLine()) != null)
                //System.out.println("line: " + s);
            p.waitFor();
            //System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
        	
        }
    }
    
    public static double Ip2Num(String Ip){
    	String [] ip = Ip.split("\\.");
    	Double num = Double.parseDouble(ip[0]) *256*256*256 + 
    			Double.parseDouble(ip[1]) *256*256 +
    			Double.parseDouble(ip[2]) *256 +
    			Double.parseDouble(ip[3]);
    	
    	return num;
    }
    
    public static String GetLocation(Double ip){
    	String fileName1 = "GeoLiteCity-Blocks.csv";
        String fileName2 = "GeoLiteCity-Location.csv";
    	try {
			FileReader fileRd1 = new FileReader(fileName1);
			BufferedReader bufferRd1 = new BufferedReader(fileRd1);
			String line1 = bufferRd1.readLine();
                        
                        FileReader fileRd2 = new FileReader(fileName2);
			BufferedReader bufferRd2 = new BufferedReader(fileRd2);
			String line2 = bufferRd2.readLine();
			
			while( (line1 = bufferRd1.readLine()) != null) {
				//System.out.println(line);
				String [] s1 = line1.split(",");
				s1[0]=s1[0].replaceAll("\"", "");
				s1[1]=s1[1].replaceAll("\"", "");
				Double fromIp = Double.parseDouble(s1[0]);
				Double toIp = Double.parseDouble(s1[1]);
				if(fromIp<=ip && ip<=toIp){
					s1[2]=s1[2].replaceAll("\"", "");
					//return s[6];
                                        while((line2 = bufferRd2.readLine()) != null){
                                            String [] s2 = line2.split(",");
                                            if(s1[2].equals(s2[0])){
                                                String longitude = s2[5].replaceAll("\"", "");
                                                String latitude = s2[6].replaceAll("\"", "");
                                                String location = longitude + "," + latitude;
                                                return location;
                                            }
                                        }
				}
			}
			
			fileRd1.close();
			bufferRd1.close();
			
		} catch (FileNotFoundException x) {
			System.out.println("Make sure " + fileName1 + " is also here!");
			System.exit(-1);
			
		} catch (IOException x) {
			System.out.println(x);
			System.exit(-1);
			
		}
    	
    	return null;
    }
    
    public static void showMap(){
    	Map.mapCountries(locations);
    }
    
    public static void addIp(String ip){
    	Double ipNum = Map.Ip2Num(ip);
    	String location = Map.GetLocation(ipNum);
    	boolean flag = false;
    	for(int i=0; i<locations.size(); i++){
    		if(locations.get(i).equals(location)){
    			flag = true;
    		}
    	}
    	if(flag == false){
            System.out.printf(ip+"  ");
    		Map.locations.add(location);
    		System.out.println(location);
    	}
    	
    }
    
    public static void main(String [] args){
		
    	
    	String fileName = "blocked.txt";
    	try {
			FileReader fileRd = new FileReader(fileName);
			BufferedReader bufferRd = new BufferedReader(fileRd);
			String line = null;
			
			while( (line = bufferRd.readLine()) != null) {
				System.out.println(line);
                            String [] s = line.split(",");
				Map.addIp(s[0]);
			}
			
			fileRd.close();
			bufferRd.close();
			
		} catch (FileNotFoundException x) {
			System.out.println("Make sure " + fileName + " is also here!");
			System.exit(-1);
			
		} catch (IOException x) {
			System.out.println(x);
			System.exit(-1);
		}
		
		Map.showMap();
		
		//System.out.println(Map.Ip2Num("1.2.3.4"));
		
		//ystem.out.println(Map.GetCountry(Map.Ip2Num("193.104.41.55")));
    }
}