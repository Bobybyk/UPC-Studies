public abstract class Diplome {
	public final String intitule;
	public final Mention mention;
	public final int annee;

	public Diplome(String intitule, Mention mention, int annee) {
		this.intitule = intitule;
		this.mention = mention;
		this.annee = annee;
	}

	public enum Mention { PASSABLE, ASSEZ_BIEN, BIEN, TRES_BIEN, FELICITATIONS; }
}