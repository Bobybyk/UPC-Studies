import java.util.HashMap;


public class Entrepot {
    
    int x, y, id;
    HashMap<Integer,Integer> inventaire;

    public Entrepot(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.inventaire= new HashMap<>();
    }

    public HashMap<Integer, Integer> getInventaire() {
        return inventaire;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void removeProduct(int product, int weight) {
        if (inventaire.get(product)==weight) {
            inventaire.put(product, 0);
        }
        else if(inventaire.get(product)<weight){
            inventaire.put(product, inventaire.get(product)-weight);
        }
    }

}
