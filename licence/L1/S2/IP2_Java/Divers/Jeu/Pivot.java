import java.util.Random;

public class Pivot {

	public int [][] tPivot = new int[4][4];

	Pivot () {

		Random rand = new Random();

		for (int i = 0 ; i<tPivot.length ; i++) {
			for (int j = 0 ; j<tPivot.length ; j++) {
				tPivot[i][j] = rand.nextInt(2)+0;
			}
		}
	} 


	public void afficher () {

		int xi = 0;
		int xj = 0;
		int val = 0;

		for (int i = 0 ; i<this.tPivot.length ; i++) {
			for (int j = 0 ; j<this.tPivot.length ; j++) {
				val = tPivot[i][j];
				if (xi != i) {
					System.out.println(val);
				} else { System.out.print(val); }
				xi = i;
			}
		}
	}
}