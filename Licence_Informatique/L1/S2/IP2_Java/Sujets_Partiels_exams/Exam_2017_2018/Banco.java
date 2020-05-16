import java.util.Random;

public class Banco {
	static int _prix = 1;
	static int _num;
	final int _gain;
	final int _nulSi;
	private boolean _lecGain;
	private boolean _lecNulSi;


	public Banco() {
		Random rand = new Random();
		int i = rand.nextInt(101);
		if (i == 0) {
			this._gain = 1000;
		} 
		else if (i > 0 && i < 25) {
			this._gain = 2;
		}
		else {
			this._gain = 0;
		}
		this._num ++;
		i = 100 + rand.nextInt(900);
		this._nulSi = i;
		this._lecGain = false;
		this._lecNulSi = false;
	}

	public int lectGain() {
		if (!(this._lecGain)) {
			return -1;
		} else {
			return this._gain;
		}
	}

	public int lectNulSi() {
		if (!(this._lecNulSi)) {
			return -1;
		} else {
			return this._nulSi;
		}
	}

	public void grattageGain() {
		if (!(this._lecGain)) {
			this._lecGain = true;
		}
	}

	public void grattageNulSi() {
		if (!(this._lecNulSi)) {
			this._lecNulSi = true;
		}
	}
}