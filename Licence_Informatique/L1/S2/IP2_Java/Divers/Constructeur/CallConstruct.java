public class CallConstruct {

	public static void main (String[] args) {

		int[] tab1 = new int [5];
		System.out.println("class CallConstruct : J'initialise un tableau tab1 : " + java.util.Arrays.toString(tab1));

		System.out.println("class CallConstruct : J'appelle le premier contructeur Construct");
		Construct tabConstruct = new Construct(tab1);

		int[] tab2 = tab1;
		System.out.println("class CallConstruct : J'initialise un tableau tab2 avec les mêmes valeurs que tab1 : " + java.util.Arrays.toString(tab2));

		System.out.println("class CallConstruct : J'appelle le deuxième constructeur Add");
		Add tabAddi = new Add(tab2);
	}
}