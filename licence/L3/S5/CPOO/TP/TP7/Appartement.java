import java.util.*;

public class Appartement {
    public int nbPieces;
    public int prix;
    public String lieu;


    public Appartement(int nbPieces, int prix, String lieu){
	this.nbPieces = nbPieces;
	this.prix = prix;
	this.lieu = lieu;
    }

    public String toString(){
	return "("+nbPieces+ ", "+prix+ ", "+ lieu+")";
    }


    public static void main(String[] args){
	    List<Appartement> appartements = new ArrayList<>();
	    appartements.add(new Appartement(5, 5_000_000, "Paris"));
        appartements.add(new Appartement(1, 200_000, "Paris"));	
        appartements.add(new Appartement(5, 1_000_000, "Nancy"));
        appartements.add(new Appartement(4, 200_000, "Limoges"));
        appartements.add(new Appartement(1, 80_000, "Lille"));
        appartements.add(new Appartement(2, 200_000, "Brest"));
	    appartements.add(new Appartement(1, 180_000, "Paris"));
        appartements.add(new Appartement(2, 500_000, "Paris"));

        // le plus petit prix pour un appartement de plus de 3 pièces
        int x = appartements.stream()
        .filter(a -> a.nbPieces >= 3)
        .map(a -> a.prix)
        .reduce(Integer.MAX_VALUE, (a, b) -> (a<b)?a:b);
        System.out.println("3 pièces moins cher (stream) : " + x);

        int xF = 0;
        for (int i = 0 ; i<appartements.size() ; i++) {
            if (appartements.get(i).nbPieces >= 3) {
                if (xF == 0) {
                    xF = appartements.get(i).prix;
                }
                else if (appartements.get(i).prix < xF) {
                    xF = appartements.get(i).prix;
                }
            }
        }
        System.out.println("3 pièces moins cher (boucle) : " + xF);
    
        for (Appartement a : appartements) {
            if (a.prix<=200_000) {
            System.out.println(a.lieu);
            }
        }

            
    }

}
