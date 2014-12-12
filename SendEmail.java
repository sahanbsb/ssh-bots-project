import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import org.junit.Test;

public class SendEmail {

//@Test
public static void main(String [] args){
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", true); // added this line
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", "ape.wade.ssh@gmail.com");
    props.put("mail.smtp.password", "sshB0tsp");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", true);



    Session session = Session.getInstance(props,null);
    MimeMessage message = new MimeMessage(session);

    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

    // Create the email addresses involved
    try {
        InternetAddress from = new InternetAddress("ape.wade.ssh");
        message.setSubject("Yes we can");
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("anprabhath@gmail.com"));

        // Create a multi-part to combine the parts
        Multipart multipart = new MimeMultipart("alternative");

        // Create your text message part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("some text to send");

        // Add the text part to the multipart
        multipart.addBodyPart(messageBodyPart);

        // Create the html part
        messageBodyPart = new MimeBodyPart();
        
        String msg = "Blocked IPs <br>";
        
        //System.out.println(text2string());
        String htmlMessage = text2string();
        messageBodyPart.setContent(htmlMessage, "text/html");


        // Add html part to multi part
        multipart.addBodyPart(messageBodyPart);

        // Associate multi-part with message
        message.setContent(multipart);

        // Send message
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "ape.wade.ssh@gmail.com", "sshB0tsp");
        System.out.println("Transport: "+transport.toString());
        transport.sendMessage(message, message.getAllRecipients());


    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
public static String text2string(){
    String fileName = "blocked.txt";
    String res = "";
    	try {
			FileReader fileRd = new FileReader(fileName);
			BufferedReader bufferRd = new BufferedReader(fileRd);
			String line;
			
			while( (line = bufferRd.readLine()) != null) {
				//System.out.println(line);
                            res = res + line + "<br>";
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
        return res;
}
}