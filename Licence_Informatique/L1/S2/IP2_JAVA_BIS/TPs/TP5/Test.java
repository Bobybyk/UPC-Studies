public class Test {

	public static void main (String[] args) {
		
		ListeDEntiers liste = new ListeDEntiers();
		
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");
		liste.add(1);
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + "Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");
		liste.add(5);
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");
		liste.add(4);
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");
		liste.ajouter_en_i(1, 5);
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");

		ListeDEntiers liste2 = new ListeDEntiers();

		System.out.println("Liste 1 = liste 2 : " + liste.egal(liste2));
		liste2.add(1);
		System.out.println("Liste 1 = liste 2 : " + liste.egal(liste2));
		liste2.add(10);
		System.out.println("Liste 1 = liste 2 : " + liste.egal(liste2));
		liste2.add(4);
		System.out.println("Liste 1 = liste 2 : " + liste.egal(liste2));

		liste.supprimer_en_i(0);
		System.out.println("La liste contient : " + liste.description());
		System.out.println("Elle est de longueur : " + liste.taille());
		System.out.println("La somme de ses éléments est : " + liste.somme());
		System.out.println("");

		//System.out.println(liste.supprimer_k_premieres_occ(3, 10));


	}
}