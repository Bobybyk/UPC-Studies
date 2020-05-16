public class Fonctions {

    public static int div10(int x) {
	return 10/x;
	}
    public static int sumproduct(int x,int y,int z) {
	return (x*y)+(x*z)+(y*z);

    public static void main(String[] args) {

        System.out.println(div10(10));
	System.out.println(div10(2));
/*	System.out.println(div10(0)); Impossible de faire div10(0) */
	System.out.println(div10(1)+div10(3)+div10(5)+div10(7));	

    }
}
