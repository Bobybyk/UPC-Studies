public class Main {
	
	public static void main (String[] args) {
		Verre v1 = new Verre(1, 9);
		Verre v2 = new Verre (8, 9);
		Verre v3 = new Verre (7, 9);
		Verre v4 = new Verre (9, 9);
		Verre v5 = new Verre (9, 9);
		Verre v6 = new Verre (2, 9);
		Verre v7 = new Verre (9, 9);

		Noeud n1 = new Noeud (v1);
		Noeud n2 = new Noeud (v2);
		Noeud n3 = new Noeud (v3);
		Noeud n4 = new Noeud (v4);
		Noeud n5 = new Noeud (n1, n2, v5);
		Noeud n6 = new Noeud (n3, n4, v6);
		Noeud n7 = new Noeud (n5, n6, v7);

		Tour t1 = new Tour (n7);
		Tour.afficher();
	}
}