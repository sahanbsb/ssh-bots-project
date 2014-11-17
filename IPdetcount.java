//package ssh_bots;

import java.util.Date;

public class IPdetcount extends IPdet {

	private int	Count;
	
	public IPdetcount() {
		super();
		this.Count = 0;
	}
	
	public IPdetcount( String IP, Date Time) {
		super(IP, Time);
		this.Count = 1;
	}
	
	public void incCount() {
		this.Count++;
	}
        
        public boolean isBot() {
            if(this.Count == 5 ) return true;
            else return false;
        }
	
	public int getCount() {
		return this.Count;
	}
	
	public void display() {
		System.out.println(super.getIP() +"  "+ super.getTime() +"  "+ this.Count );
                
	}
}
