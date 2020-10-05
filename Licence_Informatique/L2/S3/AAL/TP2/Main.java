import java.util.HashSet;
import java.util.Set;

public class Main{
	
    public static void testAutomate1(){
	Etat[] etats = new Etat[3];
	Etat[] etatsMoyen = new Etat[11];
	Etat[] etatsLong = new Etat[31];
	etats[0] = new Etat(0,false);
	etats[1] = new Etat(1,false);
	etats[2] = new Etat(2,true);
	etats[0].ajouteTransition('a', etats[1]);
	etats[0].ajouteTransition('a', etats[2]);
	etats[1].ajouteTransition('a', etats[1]);
	etats[1].ajouteTransition('c', etats[1]);
	etats[1].ajouteTransition('b', etats[2]);

	for (int i = 0 ; i<10; i++) {
		etatsMoyen[i] = new Etat(i, true);
	}
	etatsMoyen[10] = new Etat(10, false);
	etatsMoyen[0].ajouteTransition('a', etats[0]);
	for (int i = 1 ; i<10 ; i++) {
		etatsMoyen[i].ajouteTransition('a', etatsMoyen[i+1]);
		etatsMoyen[i].ajouteTransition('b', etatsMoyen[i+1]);
	}

	for (int i = 0 ; i<30; i++) {
		etatsLong[i] = new Etat(i, false);
	}
	etatsLong[30] = new Etat(30, true);
	for (int i = 0 ; i<31 ; i++) {
		etatsLong[i].ajouteTransition('a', etatsLong[i]);
		etatsLong[i].ajouteTransition('b', etatsLong[i]);
	}
	for (int i = 0 ; i<30 ; i++) {
		etatsLong[i].ajouteTransition('a', etatsLong[i+1]);
		etatsLong[i].ajouteTransition('a', etatsLong[i+1]);
	}

	Automate a = new Automate(etats[0]);
	Automate a1 = new Automate(etatsMoyen[0]);
	Automate a2 = new Automate(etatsLong[0]);


	System.out.println(a);

	String s = "aaab";
	System.out.println("Mot " + s + " accepté ? " + a.accepte(s));

    Set<Etat> s1 = new HashSet<Etat>();
	s1.add(etats[0]);
	Set<Etat> s2 = new HashSet<Etat>();
	s2.add(etats[1]);
	s2.add(etats[2]);
	Set<Etat> s3 = new HashSet<Etat>();
	s3.add(etats[2]);
	s3.add(etats[1]);
	s3.add(etats[2]);
	
	System.out.println(" s1 == s2 ? " + s1.equals(s2));
	System.out.println(" s2 == s3 ? " + s2.equals(s3));
    }

    public static void testAutomate2(){
	// écrire ici le premier automate de l'énoncé	
    }

    public static void testAutomate3(){
	// écrire ici le second automate de l'énoncé
    }

	
    public static void main(String[] args){
	testAutomate1();
	testAutomate2();
	testAutomate3();
	
	
	
    }
}
