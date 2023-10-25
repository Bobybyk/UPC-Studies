public class Fibonacci {
    
    public static int fibonacci(int n){
    	if(n == 0){
        	return 1;
    	}
    	if(n == 1){
            return 1;
        }
    	return fibonacci(n-2) + fibonacci(n-1);
    }

    public static void main(String args[]) {
    	int max = 4;
    	
    	System.out.print("Pour f"+max+" : \n");
    	for(int i = 0; i < max; i++){
        	System.out.println(i + ": " + fibonacci(i));
    	} 
    	System.out.println("-------------");
    	max = 30;

    	System.out.print("Pour f"+max+" : \n");
    	for(int i = 0; i < max; i++){
        	System.out.println(i + ": " + fibonacci(i));
    	}
    } 

    // le nombre d'additions obéit à la formure : (n-2)+(n-1);
}