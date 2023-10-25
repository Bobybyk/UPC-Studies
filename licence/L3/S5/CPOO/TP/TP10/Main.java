
public class Main {

	public static void main(String[] args) {
		//(new Thread(new HelloRunnable())).start();
//	hellofromThreads(200);
		CompteurTest c = new CompteurTest();
		
		class CompteurTestThread extends Thread {

			public void run() {
				c.decrementerTest();
			}
			
		}
				
		//Class Local
		Thread t1 = new Thread(new CompteurTestThread());
	
		
		//Class anonyme
		Thread t2 = new Thread() {
			public void run() {
	            c.decrementerTest();
	         }
		};
		t2.start();
	
		
		//Lamda basique
		Thread t3 = new Thread(() -> c.incrementerTest());
		t3.start();
		t1.start();
		
		//Lamda par reference
		Thread t4 = new Thread(c::incrementerTest);
		t4.start();
		

	}
	
	static void hellofromThreads(int n) {
		if(n < 6)return;
		for (int i = 0; i < n; i++) {
			(new Thread(new HelloRunnable())).start();
		}
	}
}
