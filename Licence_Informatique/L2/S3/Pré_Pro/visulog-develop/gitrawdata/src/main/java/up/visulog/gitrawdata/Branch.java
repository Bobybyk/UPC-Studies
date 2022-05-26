package up.visulog.gitrawdata;
public class Branch {
	
	private String name;
	private boolean merged;
	
	public Branch(boolean merged,String name) {
		this.merged=merged;
		this.name=name;
	}
	
}
