public class EstUnLivre extends Predicat {

	public boolean estVrai (Media m) {
		if (m instanceof Livre) {
			return true;
		} return false;
	}
}