public class Echec {

/*
	public static String dble(String s) {
	String x = s+s;
	return x;


	}

	public static void ntimes (String s, int x) {

	for (int i = 0; i<x ; i++) {
		System.out.println(s);	

	}	

	}

	public static int square (int x) {
		int y = x+x+x+x+x; //car je n'arrive pas à utiliser : ² et sans le symbole : * je ne vois pas comment faire autrement 
		return y;	
	}

	public static void perfect (int x, int y, int z) {

		if (x*x+y*y==z*z) {

		System.out.println("oui");
		
		} else { System.out.println("Non"); }


	}
		*/
	public static void echecetmat (int n, int xU1, int yU1, int xU2, int yU2, int xU3, int yU3, int xU4, int yU4, int xU5, int yU5, int xU6, int yU6, int xU7, int yU7, int xU8, int yU7, int xD1, int yD1, int xD2, int yD2, int xD3, int yD3, int xD4, int yD4, int xD5, int yD5, int xD6, int yD6, int xD7, int yD7, int xD8, int yD8 ) {

		int t = n*n; //nbr cases échiquier
		int p11 = xU1*yU1; //coordonnées pion 1 team 1
		int p12 = xU2*yU2; //coordonnées pion 2 team 1
		int p13 = xU3*yU3; //coordonnées pion 3 team 1
		int p14 = xU4*yU4; //coordonnées pion 4 team 1
		int p15 = xU5*yU5; //coordonnées pion 5 team 1
		int p16 = xU6*yU6; //coordonnées pion 6 team 1
		int p17 = xU7*yU7; //coordonnées pion 7 team 1
		int p18 = xU8*yU8; //coordonnées pion 8 team 1

		int p21 = xD1*yD1; //coordonnées pion 1 team 2
		int p22 = xD2*yD2; //coordonnées pion 2 team 2
		int p23 = xD3*yD3; //coordonnées pion 3 team 2
		int p24 = xD4*yD4; //coordonnées pion 4 team 2
		int p25 = xD5*yD5; //coordonnées pion 5 team 2
		int p26 = xD6*yD6; //coordonnées pion 6 team 2
		int p27 = xD7*yD7; //coordonnées pion 7 team 2
		int p28 = xD8*yD8; //coordonnées pion 8 team 2
		//System.out.println(t);
		//System.out.println(f);
		//System.out.println(r);
		if (f+9==r) {
		System.out.println("oui");
		}
		if (f-9==r) {
		System.out.println("oui");
		}
		else { System.out.println("non"); }

			

	}

	public static void main (String[] args) {

	   /* System.out.println(dble("salut"));	
		ntimes("Salut", 4);
		System.out.println(square(5));
		perfect(8, 15, 17); */
		System.out.println("Team 1 : ");
		System.out.println("Coordonnées x de pion 1 = ");			int a = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 1 = ");			int b = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 2 = ");			int c = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 2	= ");			int d = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 3 = ");			int e = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 3 = ");			int f = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 4 = ");			int g = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 4 = ");			int h = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 5 = ");			int i = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 5 = ");			int j = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 6 = ");			int k = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 6 = ");			int l = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 7 = ");			int m = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 7 = ");			int n = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 8 = ");			int o = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 8 = ");			int p = Integer.parseInt(System.console ().readLine ());

		System.out.println("Team 2 : ");
		System.out.println("Coordonnées x de pion 1 = ");			int q = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 1 = ");			int r = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 2 = ");			int s = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 2	= ");			int t = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 3 = ");			int u = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 3 = ");			int v = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 4 = ");			int w = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 4 = ");			int x = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 5 = ");			int y = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 5 = ");			int z = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 6 = ");			int aa = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 6 = ");			int ab = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées x de pion 7 = ");			int ac = Integer.parseInt(System.console ().readLine ());	
		System.out.println("Coordonnées y de pion 7 = ");			int ad = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées x de pion 8 = ");			int ae = Integer.parseInt(System.console ().readLine ());
		System.out.println("Coordonnées y de pion 8 = ");			int af = Integer.parseInt(System.console ().readLine ());
		
		echecetmat(8, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, u, v, w, x, y, z, aa, ab, ac, ad, ar, af);
	   /* echecetmat(8,5,7,2,4);
		echecetmat(8,3,4,6,7);
		echecetmat(8,6,7,3,4); */

	}

}


