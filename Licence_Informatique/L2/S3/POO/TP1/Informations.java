public class Informations {
	private int v;
	private int f;
	private int a;

	public Informations (int v, int f, int a) {
		this.v = v;
		this.f = f;
		this.a = a;
	}


	public String toString() {
		return "Vilatité = " + v + ", Force = " + f + ", Agilité = " + a;
	}

	public int getVitalite()  {
		return this.v;
	}
	public int getForce() {
		return this.f;
	}
	public int getAgilite() {
		return this.a;
	}

	public void setVitalite (int v) {
		this.v = v;
	}
	public void setForce (int f) {
		this.f = f;
	}
	public void setAgilite (int a) {
		this.a = a;
	}
}