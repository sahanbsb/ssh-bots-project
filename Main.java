public class Main {
	public static void main(String [] args) {
                SendEmail EmailSender = new SendEmail();
                EmailSender.start();
		FileRead.readFile("auth.log");
	}
}
