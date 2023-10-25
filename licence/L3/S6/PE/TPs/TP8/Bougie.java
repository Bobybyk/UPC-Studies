import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Bougie {

	static int differenceAge = 0;
	static int nbBougiesRita = 0;
	static int nbBougiesTheo = 0;

	public static void main(String args[]) {
		parsing(args[0]);

		int ageRita = 3 + differenceAge;
		int ageTheo = 3;

		int nombreBougiesTheoriqueRita = sum(ageRita) - 6;	
		int nombreBougiesTheoriqueTheo = sum(ageTheo) - 3;	

		while((nbBougiesRita + nbBougiesTheo) != (nombreBougiesTheoriqueRita + nombreBougiesTheoriqueTheo)) {
			nombreBougiesTheoriqueRita += ++ageRita;
			nombreBougiesTheoriqueTheo += ++ageTheo;
		}

		System.out.println(nbBougiesRita - nombreBougiesTheoriqueRita);
	}

	static int sum(int n) {
		int sum =0;
		for(int i=1;i<=n;i++) {
			sum+= i;	
		}
		return sum;
	}

	static void parsing(String file) {
		try{
			Scanner sc = new Scanner(new File(file));
			for(int i=0;i<3;i++) {
				String line = sc.nextLine();
				int token = Integer.parseInt(line);
				if(i == 0)
					differenceAge = token;
				if(i == 1)
					nbBougiesRita = token;
				if(i == 2)
					nbBougiesTheo = token;
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}
