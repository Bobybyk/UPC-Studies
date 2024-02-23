package exo3;

public class LectRed {
	public static final int TAILLE = 2;

	public static void main(String[] args) {
		
		BD base = new BD(TAILLE);
		Lect lecteur[] = new Lect[TAILLE];
		Red ecrivain[] = new Red[TAILLE];
		
		for (int i = 0; i < TAILLE; i++) {
			
			lecteur[i] = new Lect(base);
			ecrivain[i] = new Red(base);
		}
		for (int i = 0; i < TAILLE; i++) {
			
			lecteur[i].start();
			ecrivain[i].start();
		}
		lecteur[0].interrupt();
	}
}