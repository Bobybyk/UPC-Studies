public class Test {
	
	public static void main (String[] args) {
		
		Robot r1 = new Robot('a', "Hello World");
		Robot r2 = new Robot('b', "Test 2");
		Robot r3 = new Robot('c', "Test 3");
		Robot r4 = new Robot('d', "Test 4");
		Robot r5 = new Robot('e', "Test 5");
		Robot r6 = new Robot('f', "Test 6");
		Robot r7 = new Robot('g', "Test 7");

		TableRonde t1 = new TableRonde(r1);

		t1.ajouteRob(r1);
		t1.ajouteRob(r2);
		t1.ajouteRob(r3);
		t1.ajouteRob(r4);
		t1.ajouteRob(r5);
		t1.ajouteRob(r6);
		t1.ajouteRob(r7);

		//t1.parle();

		t1.affiche();
		System.out.println("------------------");

		System.out.println(t1.supprimer(2));

		t1.affiche();
		System.out.println("------------------");

		System.out.println(t1.supprimer(3));

		t1.affiche();
		System.out.println("------------------");

		System.out.println(t1.supprimer('e'));

		t1.affiche();
		System.out.println("------------------");

		System.out.println(t1.supprimer('f'));

		t1.affiche();
		System.out.println("------------------");

	}
}