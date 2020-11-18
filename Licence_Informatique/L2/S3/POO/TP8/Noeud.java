import java.util.ArrayList;
import java.io.*;

public class Noeud  {
	String nom;
	int taille;
	boolean repertoire;
	ArrayList<Noeud> fils;
	private static int tab = 0;
	
	public Noeud (File file) {
		try {
			if(file.exists()) {
				this.nom = file.getName();
				this.taille = (int) file.length();
				if(file.isDirectory()) {
					this.repertoire = true;
					File[] f = file.listFiles();
					this.fils = new ArrayList<Noeud>();
					for (int i = 0 ; i < f.length ; i++) {
						this.fils.add(new Noeud(f[i]));
					}
				} else {
					this.repertoire = false;
					this.fils = null;
				}
			} else { 
				throw new FileNotFoundException("Nothing found"); 
			}
		} 
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public void afficher() {		
		System.out.println(this.nom + " [" + this.taille+ "]");
		if (this.fils != null) {
			tab++;
			for (int i = 0 ; i<this.fils.size() ; i++) {
				for (int j = 0 ; j<tab ; j++) {
					System.out.print("	");
				}
				this.fils.get(i).afficher();
			}
			tab--;

		} 
	}

	public void map(StringTransformation t) {
  		if (this.repertoire) {
  			this.fils.forEach(f->f.map(t));
  		} else {
  			this.nom = t.transf(this.nom);
		}
	}

	public void traverser(String extension) {
		if (this.repertoire) {
			this.fils.forEach(f->f.traverser(extension));
		} 
		else if (this.nom.endsWith(extension)) {
			System.out.println(this.nom + " [" + this.taille+ "]");
		}
	}
}