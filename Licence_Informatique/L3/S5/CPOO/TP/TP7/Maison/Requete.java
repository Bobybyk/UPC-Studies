package Maison;

import java.util.*;
import java.util.stream.*;

public class Requete{
   
    
    public static void main(String[] args){
		Maison maison = new Maison();
		Meuble a = new Meuble("Armoire");
		a.add(new PetitObjet("chemise", 100));
		a.add(new PetitObjet("pantalon", 200));
		maison.add(a);
		Meuble b = new Meuble("Lit");
		maison.add(b);
		Meuble c = new Meuble("Vaisselier");
		c.add(new PetitObjet("assiette",150));
		c.add(new PetitObjet("verre",70));
		c.add(new PetitObjet("verre",75));
		maison.add(c);
    }

}



