import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Automate extends HashSet<Etat> {

    private Etat init;

    public Automate(Etat e) {
	super();
	this.init = e;
	this.initialiseAutomate(e);
    }

    @Override
	public String toString() {
	String s = "" + this.size() + " etats; ";
	s += "état intial : " + init.getId()+ "\n\n";
	for (Etat e : this) {
	    s += e.toString() + "\n";
	}
	return s;
    }

    //return true if e was not already in the set,
    //false elsewhere
    boolean ajouteEtatSeul(Etat e) {
	return this.add(e);
    }

    // automate = ensemble des états accessibles depuis l'état initial
    boolean initialiseAutomate(Etat e) {
	if (!ajouteEtatSeul(e)) {
	    return false;
	}
	for (char c : e.alphabet()) { 
		for (Etat e1: e.succ(c))
		{
			initialiseAutomate(e1);
		}	
	}
		return true;
    }
	
	// Le nombre de transitions dans l'automate
	int nombreTransitions() {
		int count = 0;
		for (Etat e : this) {
			for (char c : e.alphabet()) {
				count += e.succ(c).size();
			}
		}
		return count;
	}
	
	// Pour tester si l'automate est complet
	boolean estComplet() {
		HashSet<Character> alpha = new HashSet<Character>;

		for (Etat e : this) {
			alpha.addAll(e.alphabet());
		}
		for (Etat e : this) {
			for (char c : alpha) {
				if (e.succ(c) == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Pour tester si l'automate est deterministe
	boolean estDeterministe() {
		for (Etat e : this) {
			for(char c : e.alphabet()) {
				if (e.succ(c).size() > 1) {
					return false;
				}
			}
		}
		return true;
	}
	  
    // premier algorithme d'acceptation d'un mot
    boolean accepte(String mot) {
      	return this.init.accepte(mot);
    }

    // algorithme plus efficace
    boolean accepte2(String mot) {
	// écrire ici l'algorithme plus efficace
	return true;
    }
	
	// Pour enlever les etats co-accessibles
    Automate enleverEtatsCoAccessible() {
		// à écrire
		return null;
	}

    // Déterminisation
    Automate determinisation(){
	// à écrire
	return null;
    }
    
    
}
