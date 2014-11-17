//package ssh_bots;

import java.util.Date;

public class IPdet {
	
	private String 	IP;
	private Date 	Time;
	
	public IPdet() {
		IP = "";
		Time = new Date(0);
	}
	
	public IPdet( String IP, String Time) {
		this.IP = IP;
		this.Time = Time_Manager.Str_to_time(Time);
	}
	
	public IPdet( String IP, Date Time) {
		this.IP = IP;
		this.Time = Time;
	}
	
	public String getIP() {
		return this.IP;
	}
	
	public Date getTime() {
		return this.Time;
	}
	
	public Date setTime(Date Time) {
		return this.Time = Time;
	}
}
