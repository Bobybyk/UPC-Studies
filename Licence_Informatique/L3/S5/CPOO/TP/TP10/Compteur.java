
public class Compteur {

	private int compte = 0;
	public int getCompte() { return compte; }
	public void incrementer() { compte++; }
	public void decrementer() throws InterruptedException { 
		if(compte < 0) {
			wait();
			notify();
		}else {
			compte--;
		}
		 }
}
