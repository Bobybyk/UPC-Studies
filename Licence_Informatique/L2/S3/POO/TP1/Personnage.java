import java.lang.Math;

public class Personnage {
	private Informations info;
	private String nom;

	public Personnage (Informations info, String nom) {
		this.nom = nom;
		this.info = info;
	}

	public boolean estVivant() {
		if (this.info.getVitalite() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public String toString() {
		return "Je suis " + this.nom + ", mes charactéristiques sont : " + this.info.toString();
	}

	public void attaque (Personnage def) {
		
		int n = Math.max(1, this.info.getForce() - def.info.getForce() + 1);

		if (def.info.getVitalite() < this.info.getVitalite()) {
			def.info.setVitalite(def.info.getVitalite() - n);
		}
		else {
			def.info.setVitalite(def.info.getVitalite()-(n/2));
			def.info.setAgilite(def.info.getAgilite()-def.info.getAgilite()/3);
		}
	}

	public void lutte(Personnage def) {
		int x = 0;
		while (this.estVivant() && def.estVivant()) {
			if (x%2 == 0) {
				this.attaque(def);
				System.out.println(this.toString());
				System.out.println(def.toString());
				x++;
			}
			else {
				def.attaque(this);
				System.out.println(this.toString());
				System.out.println(def.toString());
				x++;
			}
			System.out.println("#########");
		}
		if (!this.estVivant())  {
			System.out.println(this.nom + " est battu");
		}
		else {
			System.out.println(this.nom + " a gagné");
		}
		if (!def.estVivant())  {
			System.out.println(def.nom + " est battu");
		}
		else {
			System.out.println(def.nom + " a gagné");
		}
	}

	public void lutteReq (Personnage def) {
		if (this.estVivant() && def.estVivant()) {
			this.attaque(def);
			def.attaque(this);
			System.out.println(def.toString());
			System.out.println(def.toString());
			this.lutteReq(def);
		}
	}

}
