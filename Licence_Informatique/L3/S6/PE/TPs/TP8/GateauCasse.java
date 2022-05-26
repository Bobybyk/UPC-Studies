import java.util.*;
import java.io.*;

public class GateauCasse {
	static int largeurGateau = 0;
	static int nbPieces = 0;
	static int[][] pieces;

	public static void main(String args[]) {
		parsing(args[0]);	
		int area = 0;
		for(int i=0;i<pieces.length;i++) {
			area += (pieces[i][0] * pieces[i][1]);
		}
		System.out.println(area/largeurGateau);
	}

	static void parsing(String file) {
		try {
			Scanner sc = new Scanner(new File(file));
			int i = 0;
			int j = 0;
			while(sc.hasNext()) {
				String s = sc.nextLine();
				if(i == 0) {
					largeurGateau = Integer.parseInt(s);
					i++;
					continue;
				} else if(i == 1) {
					nbPieces = Integer.parseInt(s);
					i++;
					pieces = new int[nbPieces][];
				} else {
					String sizes[] = s.split(" ");
					pieces[j] = new int[2];
					pieces[j][0] = Integer.parseInt(sizes[0]);
					pieces[j][1] = Integer.parseInt(sizes[1]);
					j++;
				}
			}

		} catch(IOException e) { e.printStackTrace(); }
	}	
}
