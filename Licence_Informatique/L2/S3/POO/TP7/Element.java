public abstract class Element {
	public abstract String getType();
	
	public Element() {
		
	}

	public String toString () {
		return "fichier de  type " + getType() ;
	}
}