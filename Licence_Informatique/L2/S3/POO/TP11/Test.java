public class Test {

    public static void main (String[] args) {
        Integer[] tab = {0,1,2,3,null,3};
        TestIter <Integer> iter = new TestIter<Integer>(tab);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        TabSet <Integer> setInteger = new TabSet<Integer>(5); 
        TabSet <Boolean> setBoolean = new TabSet<Boolean>(5);
        TabSet <String> setString = new TabSet<String>(5);

        String s1 = new String("aba");
        String s2 = new String("bba");
        setString.add(s1);
        setString.add(s2);
    }
}