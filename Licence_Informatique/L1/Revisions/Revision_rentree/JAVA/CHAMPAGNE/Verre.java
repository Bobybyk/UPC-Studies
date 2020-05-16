public class Verre {
	private int _volume;
	private int _quantite;

	public Verre (int vol, int quantite) {
		this._volume = vol;
		this._quantite = quantite;
	}

	public int getVol() {
		return this._volume;
	}
	public int getQuantite() {
		return this._quantite;
	}

	public void setVol (int v) {
		this._volume = v;
	}
	public void setQuantite (int q) {
		this._quantite = q;
	}
}