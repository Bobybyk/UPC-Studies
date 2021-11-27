public class Test {

	public static void main (String[] args) {
		Robot r1 = new Robot("Bonjour"); 
		Robot r2 = new Robot("Robot 2");
		Robot r3 = new Robot("Robot 3");
		Robot r4 = new Robot("Robot 4");
		
		TableRonde t = new TableRonde(r1);
		t.affiche();
		System.out.println("------------");
		t.ajouteRob(r2);
		t.affiche();
		System.out.println("------------");
		t.ajouteRob(r3);
		t.affiche();
		System.out.println("------------");
		t.ajouteRob(r4);
		t.affiche();
		System.out.println("------------");
		t.supprimer(2);
		t.affiche();
		System.out.println("------------");
	}
}