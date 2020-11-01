import java.util.LinkedList;

public class BoiteComposite {
	LinkedList<Boite> lb = new LinkedList<Boite>();

	public int length() {
		int lengthList = 0;
		for (int i = 0 ; i<lb.size() ; i++) {
			lengthList += lb.get(i).length();
		}
		return lengthList;
	}

	@Override
	public String toString() {
		String p = "";
		for (int i = 0 ; i < lb.size() ; i++) {
			p += lb.get(i);
		}
		return p;
	}

	public boolean isEmpty() {
		return lb.size() == 0;
	}

	public void addBoite(Boite b) {
		lb.addLast(b);
	}
}