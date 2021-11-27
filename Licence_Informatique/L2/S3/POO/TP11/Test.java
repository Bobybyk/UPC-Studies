public class Test {

    public static void main (String[] args) {
        Integer[] tab = {0,1,2,3,null,3};
        TestIter <Integer> iter = new TestIter<Integer>(tab);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        System.out.println("TestIter");
        System.out.println("--------------\n--------------\n");

        // Déclaration des TabSet
        TabSet <Integer> setInteger = new TabSet<Integer>(5); 
        TabSet <Boolean> setBoolean = new TabSet<Boolean>(5);
        TabSet <String> setString = new TabSet<String>(5);

        //Déclaration des objets String (manuellement pour plus de simplicité par la suite)
        String s1 = new String("aba");
        String s2 = new String("bba");
        String s3 = new String("aab");

        //Déclaration des objets Boolean
        Boolean b1 = new Boolean(true);
        Boolean b2 = new Boolean(false);
        Boolean b3 = new Boolean(true);

        //Déclaration des objets Integer
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(2);
        Integer i3 = new Integer(3);

        
        //Ajout des Integer à setInteger
        setInteger.add(i1);
        setInteger.add(i2);
        setInteger.add(i3);

        //Ajout des Boolean à setBoolean
        setBoolean.add(b1);
        setBoolean.add(b2);
        setBoolean.add(b3);

        //Ajout des String à setString
        setString.add(s1);
        setString.add(s2);
        setString.add(s3);

        setBoolean.afficher();
        System.out.println("setBoolean remplissage (on remarquera que le troisième élément manque car égal au premier");
        System.out.println("--------------\n");

        setString.afficher();
        System.out.println("setString remplissage");
        System.out.println("--------------\n");

        setInteger.afficher();
        System.out.println("setInteger remplissage");
        System.out.println("--------------\n--------------\n");


        //Retrait d'un élément
        setString.remove(s2);
        setBoolean.remove(b1);
        setInteger.remove(i2);

        setBoolean.afficher();
        System.out.println("Retrait d'un élément de setBoolean");
        System.out.println("--------------\n");

        setString.afficher();
        System.out.println("Retrait d'un élément de setString");
        System.out.println("--------------\n");

        setInteger.afficher();
        System.out.println("Retrait d'un élément de setInteger");
        System.out.println("--------------\n--------------\n");


        //retrait de tous les éléments
        setBoolean.clear();
        setString.clear();
        setInteger.clear();

        setBoolean.afficher();
        System.out.println("Supression des éléments de setBoolean");
        System.out.println("--------------\n");

        setString.afficher();
        System.out.println("Supression des éléments de setString");
        System.out.println("--------------\n");

        setInteger.afficher();
        System.out.println("Supression des éléments de setInteger");
        System.out.println("--------------\n--------------\n");

    }
}