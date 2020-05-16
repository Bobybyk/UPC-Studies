public class Noeud {
	private char lettre;
	private ArrayList<Noeud> suiv;

	public Noeud() {
		this.lettre = '';
		this.suiv = new ArrayList<Noeud>();
	}

	public Noeud aPourEnfant (Char a) {
		
		for (int i = 0 ; i<this.suiv.size() ; i++) {
			if(this.suiv.get(i).lettre == a) {
				return this.suiv.get(i);
			}
		}
	}

	public boolean appartient (String w) {
			
		if (this.lettre == w.charAr(0)) {
			if (w.length() == 1) {
				return this.aPourEnfant('') != null;
			} else {
				return this.aPourEnfant(w.charAt(1)).appartient(w.substring(1));
			}
		}
		return false;
	}

	public void ajouter (String w) {
		
		if (this.lettre != a.charAt(0)) {
			return;
		}
		Noeud tmp = this;
		int = 0;
		while (tmp.aPourEnfant(w.charAt(i) != null)) {
			tmp = tmp.aPourEnfant(w.charAt(i));
			i++;
		}
		Noeud n = new Noeud(); n.lettre = co.charAt(i);
		tmp.suiv.add(n);
		for (int j++; j<w.length(); j++) {
			tmp = tmp.suiv.get(0);
			tmp.suiv.add(new Noeud(w.charAt(j)));
		}
		tmp  = tmp.suiv.get(0);
		tmp.suiv.add(new Noeud());
	}

	public void ajouterRec (String w) {
		
		if (this.lettre == a.charAt(0)) {
			if (w.length() == 1) {
				this.suiv.add('', new ArrayList<Noeud>());
			} else {
				if (this.aPourEnfant(a.charAt(i) == null)) {
					this.suiv.add(new Noeud(a.charAt(i), new ArrayList<Noeud>()));
				}
				this.aPourEnfant(a.charAt(i).ajouter(a.substring(1)));
			}
		}
	}

	public void afficher (String s) {
		for (int i = 0 ; i<this.suiv.size() ; i++) {
			if (this.lettre == '') {
				System.out.println(s);
			} else {
				this.suiv.get(i).affiche(s + this.lettre);
			}
		}
	}

}