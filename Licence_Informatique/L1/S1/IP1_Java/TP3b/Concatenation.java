public class Concatenation {

   public static String concatNTimes(String s, int n) {
   		String t=s;
   		if (n<0) {

   			return "";

   		}
   		else {
   			for(int i = 0 ; i<n ; i++) 
   				s = s+t;
   				return(s);
   		 		

   }			

}

    public static void main(String[] args) {

        System.out.println(concatNTimes("tartuffle", 69));
   }
}
