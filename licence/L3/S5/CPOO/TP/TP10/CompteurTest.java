
public class CompteurTest {

	private final Compteur compteur = new Compteur();
	
	public synchronized void incrementerTest() {
		compteur.incrementer();
	 	System.out.println(compteur.getCompte() + " obtenu après incrémentation");
	}
	
	 public synchronized void decrementerTest()  {
		 try {
			compteur.decrementer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(compteur.getCompte() + " obtenu après décrémentation");
	}

}
