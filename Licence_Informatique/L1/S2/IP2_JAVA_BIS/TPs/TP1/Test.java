public class Test {
	
	public static void main (String[] args) {
		Fruit f = new Fruit ("Pamplemousse", 330);
		Fruit g = new Fruit ("Pamplemousse", 330);
		Fruit h = f;
		Fruit.afficher(f);
		Fruit.afficher(Fruit.hybridation(f, g));
		Fruit[] tF = {f, g};
		Panier p = new Panier(tF);
		Panier.afficher(p);
		System.out.println("Test Termine");
	}
}

/* Exercice 1 :
	2/. Les variables f, g et h sont des variables d'objets. f et g contiennent deux arguements (une chaine de caractères et un entier).
		Elles pointent vers le constructeur Fruit. 
	4/. Il est nécessaire de garder la classe Test et fruit dans le même répertoire et de passer par la fonction main() pour accéder aux autres class. 
*/ 