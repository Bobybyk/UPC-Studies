public class Voyelles {
    public static int vowels(String a){
	int x = 0;
	char b = a.charAt(0);
	for(int i=0 ; i<a.length(); i++ ){
	    b=a.charAt(i);
	    if(b=='a'||b=='e'||b=='i'||b=='o'||b=='u'||b=='y'){
	    x=x+1;

	} 
	}    
	return x ;
	
	
	    }

    public static void main(String[] args) {

	System.out.println(vowels("Marouflard"));
    }
}
