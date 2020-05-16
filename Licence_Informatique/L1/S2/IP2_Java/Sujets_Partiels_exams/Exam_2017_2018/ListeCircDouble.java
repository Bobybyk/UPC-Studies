public class ListeCircDouble {
	private Cellule2 tete;


	public TableRonde (Cellule2 tete) {
		this.courant = new Cellule2 (tete, null, null);
		this.tete.setPrev(this.tete);
		this.tete.setNext(this.tete);
	}

	public void retireLastCell() {
		
		Cellule2 tmp = this;
		if (tete != tmp.getNext()) {
			tmp = tmp.getNext();
			retireLastCell();
		} else {
			tmp.setNext(tmp.getNext().getNext())
		}
	}

}