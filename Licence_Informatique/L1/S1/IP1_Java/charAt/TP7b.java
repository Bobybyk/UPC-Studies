public class TP7b{
    //On fait une fonction booléan pour faciliter la chose :
    //Si le caractère est pareil qu'à la position i du string alors c'est true
    public static boolean sameLetter(char lettre, String tab){
        for(int i=0;i<tab.length();i++){
            if(tab.charAt(i)==lettre){
                return true;
            }
        }
        return false;
    }
    //On fait la fonction demander :
    public static char[] letters(String s){
        String finalWord=""+s.charAt(0);
        for(int i=1;i<s.length();i++){
            //Ici, on voit si c'est l'inverse la fonction sameLetter : 
            //On test le caractère à la position i de s avec le caractère position i de finalWord
            if(!sameLetter(s.charAt(i),finalWord)){
                finalWord+=s.charAt(i);
            }
        }
        //Tableau de fin
        char[] tabFin=new char[finalWord.length()];
        for(int i=0;i<finalWord.length();i++){
            tabFin[i]=finalWord.charAt(i);
        }
        System.out.println(java.util.Arrays.toString(tabFin));
        return tabFin;
    }

    public static void main(String[] args) {
        letters("test");
    }
}

