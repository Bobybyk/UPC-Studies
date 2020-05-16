public class LapinBlanc {

    public static void secondes (int t) {

	int s = (t%60);
	int m = t/60%60;
	int h = t/3600;
	
	System.out.println(h);
	System.out.println(m);
	System.out.println(s);	

}

    public static void main(String[] args) {

        secondes(14139);

    }
}
