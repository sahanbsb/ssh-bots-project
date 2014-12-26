
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Date;

 
class HtmlMail {
	
	private static String html = "";
	
	 public static String getHtml(){
            print_static_mail(Map.getAddess());
            return HtmlMail.html;
     }
	
	public static void main(String[] args) {
		print_static_mail("a");
	}
        
        public static void print_static_mail(String url){
        
            try {
                        
//                        File mail = new File("AutomaticMail.html");
//                        if (!mail.exists()) {
//				mail.createNewFile();
//			}
                        
//                        FileWriter fw = new FileWriter(mail.getAbsoluteFile());
                    
                        FileReader fr1 = new FileReader("Mail/mail.part_1"); 
                        BufferedReader br1 = new BufferedReader(fr1); 
                        String s1;
                        
                        while((s1 = br1.readLine()) != null) { 
                           //System.out.println(s1);
                           //fw.write(s1);
                           HtmlMail.html = HtmlMail.html + s1;
                        }
                        
                        //print_dynamic_mail (fw,"blocked.txt");
                        print_dynamic_mail ("blocked.txt");
                        
                        FileReader fr2 = new FileReader("Mail/mail.part_2"); 
                        BufferedReader br2 = new BufferedReader(fr2); 
                        String s2;
                        
                        
                        while((s2 = br2.readLine()) != null) { 
                            //System.out.println(s2);
                            //fw.write(s2);
                            HtmlMail.html = HtmlMail.html + s2;
                        }
                        
                        
                        /**********************************************/
                        //fw.write(print_date());
                        HtmlMail.html = HtmlMail.html + print_date();
                        
                        String mail_part_3 = ".To see  <a style=\"text-decoration: none;color: #00FF00\" href=\"";
                        //fw.write(mail_part_3);
                        HtmlMail.html = HtmlMail.html + mail_part_3;
                        
                        //fw.write(url);              // Print Url
                        HtmlMail.html = HtmlMail.html + url;
                        
                        String mail_part_4 = "\">MAP</a>  Click Here.</p>\n" +
"        </div>\n" +
"        \n" +
"        <div>     \n" +
"            <p style=\"font-size:12px;text-align:center\"> This is created automatically by SSH-Bot team 2.</p>    \n" +
"        </div>\n" +
"    </div>";
                        //fw.write(mail_part_4);
                        HtmlMail.html = HtmlMail.html + mail_part_4;
                        
                        
                        fr1.close(); 
                        fr2.close();
                        //fw.close();
                        
                        //System.out.print(print_date());
                        //System.out.println("\t : \t Done Mailing");

		} catch (IOException e) {
                    System.out.println("Error: " + e);
                    //e.setStackTrace();
		}
        
        }
        
        //public static void print_dynamic_mail (FileWriter fw, String blocked){
        public static void print_dynamic_mail (String blocked){
            
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(blocked));
                
                String line;
                
                while ((line = br.readLine()) != null) {
                    
                        //System.out.println(line);
                        String[] Array = extract_IP(line);
                        
                        // Writes the content to the file
                        
//                        fw.write("<tr style=\"border-top:1px solid #C1C3D1;\n" +
//"                               border-bottom-:1px solid #C1C3D1;\n" +
//"                               color:#666B85;\n" +
//"                               font-size:16px;\n" +
//"                               font-weight:400;\n" +
//"                               text-shadow:0 1px 1px rgba(256,256,256,0.1)\">\n" + "<td style=\"background:#FFF;\n" +
//"                                   padding:10px;\n" +
//"                                   text-align:center;\n" +
//"                                   vertical-align:middle;\n" +
//"                                   font-weight:300;\n" +
//"                                   font-size:16px;\n" +
//"                                   text-shadow:-1px -1px 1px rgba(0,0,0,0.1);\n" +
//"                                   border-right:1px solid #C1C3D1;\n" +
//"                                   text-align:center\">"+Array[0]); // Print IP In the table
                        HtmlMail.html = HtmlMail.html + "<tr style=\"border-top:1px solid #C1C3D1;\n" +
"                               border-bottom-:1px solid #C1C3D1;\n" +
"                               color:#000;\n" +
"                               font-size:16px;\n" +
"                               font-weight:400;\n" +
"                               text-shadow:0 1px 1px rgba(256,256,256,0.1)\">\n" + "<td style=\"background:#A4C7FF;\n" +
"                                   padding:10px;\n" +
"                                   text-align:center;\n" +
"                                   vertical-align:middle;\n" +
"                                   font-weight:300;\n" +
"                                   font-size:16px;\n" +
"                                   text-shadow:-1px -1px 1px rgba(0,0,0,0.1);\n" +
"                                   border-right:1px solid #C1C3D1;\n" +
"                                   text-align:center\">"+Array[0];

//                        fw.write("</td>\n" + "<td style=\"background:#FFF;\n" +
//"                                   padding:10px;\n" +
//"                                   text-align:center;\n" +
//"                                   vertical-align:middle;\n" +
//"                                   font-weight:300;\n" +
//"                                   font-size:16px;\n" +
//"                                   text-shadow:-1px -1px 1px rgba(0,0,0,0.1);\n" +
//"                                   border-right:1px solid #C1C3D1;\n" +
//"                                   text-align:center\">"+Array[1]); // Print Time In the table
                        HtmlMail.html = HtmlMail.html + "</td>\n" + "<td style=\"background:#A4C7FF;\n" +
"                                   padding:10px;\n" +
"                                   text-align:center;\n" +
"                                   vertical-align:middle;\n" +
"                                   font-weight:300;\n" +
"                                   font-size:16px;\n" +
"                                   text-shadow:-1px -1px 1px rgba(0,0,0,0.1);\n" +
"                                   border-right:1px solid #C1C3D1;\n" +
"                                   text-align:center\">"+Array[1];

                        //fw.write("</td>\n" + "</tr> ");
                        HtmlMail.html = HtmlMail.html + "</td>\n" + "</tr> ";
                        
		}
                
            } catch (IOException e) {
                    System.out.println("Error: " + e);
                    //e.setStackTrace();
		}
        
        }
        
        public static String [] extract_IP(String a){            
            
            if (a.contains("@")) {
                String[] parts = a.split("@");
                return parts;
            } else {
                throw new IllegalArgumentException("Error: String " + a + " is NOT VALID..!");
            }
        }
        
        public static String print_date(){
            Date date =new Date();
            return (date.toString());  
        }
           
        
        
}
