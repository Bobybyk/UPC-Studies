public class ListeCircS {
	private CelluleS tete;


	public void retireLastCell() { 
		if (this.getNext() != null) {
			CelluleS aux = this.tete;
			while (this.getNext() != this.tete) {
				aux = aux.getNext();
			}
			this.setNext(aux);
		}
	}
}