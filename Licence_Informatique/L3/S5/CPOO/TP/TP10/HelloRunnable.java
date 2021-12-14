
public class HelloRunnable implements Runnable {
	
	int id;
	static int ids = 0;
	static Integer Nbcompte = 0;
	
	public HelloRunnable() {
		this.id =++ids;
	}

	@Override
	public void run() {
		synchronized(Nbcompte) {
			if(Nbcompte < 3) {
				System.out.println(id + "Hello world!");
				Nbcompte++;
			}
		}
	}
}
