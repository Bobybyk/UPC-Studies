public class Compteur {
	private int valeur = 0;
	
	public int getValeur() { 
		return valeur; 
	}
	public void incrementer() { 
		valeur++; 
	}
	public void decrementer() { 
		try {
			if(valeur < 0) {
				wait();
				notify();
			}else {
				valeur--;
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
