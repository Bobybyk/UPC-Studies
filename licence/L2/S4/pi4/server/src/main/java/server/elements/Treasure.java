package server.elements;

public class Treasure extends Element {

	private int value;

	public Treasure(int x, int y, int value) {
		super(x,y,'T');
	    this.value = value;
	}

	public Treasure(int value) {
		super('T');
		this.value = value;
	}
    
    public void setTreasureValue(int v){
		this.value = v; //used to set a treasure value to zero once it's taken by a player
	}

	public int getTreasureValue() {
		return this.value;
	}
}
