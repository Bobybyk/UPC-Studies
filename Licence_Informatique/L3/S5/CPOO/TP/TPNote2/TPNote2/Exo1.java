public class Exo1Bis {

	static int globalID;

	static int calcule(int n, int t) {
		Compteur c = new Compteur();

		class CptThread extends Thread {
			int id = globalID++;

			@Override
			public void run() {
				if (id%2 == 0) {
					for (int i = 0 ; i < n ; i++) {
						c.incrementer();
					}
				} else {
					for (int i = 0 ; i < n ; i++) {
						c.decrementer();
					}
				}
			}
		}
		for (int i = 0 ; i<t ; i++) {
			//création des threads
			CptThread te = new CptThread();
			te.start();
			te.stop();
		}
		return c.getValeur();
	}

	public static void main(String[] args) {
		System.out.println(calcule(1000000, 4));
	}
}