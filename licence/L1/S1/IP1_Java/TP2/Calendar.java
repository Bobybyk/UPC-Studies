public class Calendar {

    /* ************************************************************************** */
    /* EXERCICE 1 */

    // EXERCICE 1 QUESTION 1 
    public static int diffCounter (int start, int end) {
	// Modifier le code ci-dessous
	return -1;
    }

    // EXERCICE 1 QUESTION 2
    public static int weekdayOfCounter(int date) {
	// Modifier le code ci-dessous
	return -1;
    }
    
    /* ************************************************************************** */
    /* EXERCICE 2 */

    // EXERCICE 2 QUESTION 1
    public static boolean isLeapYearJulian(int year) {
	// Modifier le code ci-dessous
	return true;
    }

    // EXERCICE 2 QUESTION 2
    public static boolean isLeapYearGregorian(int year) {
	// Modifier le code ci-dessous
	return true;
    }

    // EXERCICE 2 QUESTION 3
    public static int daysInYearJulian(int year) {
	// Modifier le code ci-dessous
	return -1;
    }

    public static int daysInYearGregorian(int year) {
	// Modifier le code ci-dessous
	return -1;
    }



    /* ************************************************************************** */
    /* EXERCICE 3 */

    // EXERCICE 3 QUESTION 1
    public static int daysInMonth(int month, boolean inLeapYear) {
	// Modifier le code ci-dessous
	return -1;
    }



    /* ************************************************************************** */
    /* EXERCICE 4 */

    // EXERCICE 4 QUESTION 1
    // Declarez la fonction gregorianToCounter prenant en paramètre l'année, le mois, et le jour et renvoyant la date correspondante au format "compteur"


    // EXERCICE 4 QUESTION 2
    // Declarez la fonction julianToCounter prenant en paramètre l'année, le mois, et le jour et renvoyant la date correspondante au format "compteur"


    /* ************************************************************************** */
    /* EXERCICE 5 */

    // EXERCICE 5 QUESTION 1
    // Completez

    // EXERCICE 5 QUESTION 3
    // Completez
    

    /* ************************************************************************** */
    /* EXERCICE 6 */

    // EXERCICE 6 QUESTION 1
    public static int julianToCounterClever(int year, int month, int day) {

	// Modifier le code ci-dessous
	return -1;

    }
    

    /* ************************************************************************** */
    /* TESTS */
    
    public static void testInt(String cmd, int exp, int val) {
	System.out.println(cmd + " == " + exp);
	if (exp != val) {
	    System.out.println("Ce n'est pas la bonne réponse!");
	}
    }

    public static void testBoolean(String cmd, boolean exp, boolean val) {
	System.out.println(cmd + " == " + exp);
	if (exp != val) {
	    System.out.println("Ce n'est pas la bonne réponse!");
	}
    }


    public static void main (String[] args) {

	// ENLEVEZ LES COMMENTAIRES AU FUR ET A MESURE POUR TESTER VOS FONCTIONS
	
	
	System.out.println("====E1Q1====");
	testInt("diffCounter(582,584)", diffCounter(582,584), 2);

	System.out.println("====E1Q2====");
	testInt("weekdayOfCounter(1)", weekdayOfCounter(1), 1);
	testInt("weekdayOfCounter(7)", weekdayOfCounter(7), 0);
	testInt("weekdayOfCounter(11)", weekdayOfCounter(11), 4);

	// System.out.println("====E2Q1====");
	// testBoolean("isLeapYearJulian(1900)", isLeapYearJulian(1900), true);
	// testBoolean("isLeapYearJulian(1901)", isLeapYearJulian(1901), false);
	// testBoolean("isLeapYearJulian(2000)", isLeapYearJulian(2000), true);

	// System.out.println("====E2Q2====");
	// testBoolean("isLeapYearGregorian(1900)", isLeapYearGregorian(1900), false);
	// testBoolean("isLeapYearGregorian(1901)", isLeapYearGregorian(1901), false);
	// testBoolean("isLeapYearGregorian(2000)", isLeapYearGregorian(2000), true);

	// System.out.println("====E2Q3====");
	// testInt("daysInYearJulian(1900)", daysInYearJulian(1900), 366);
	// testInt("daysInYearJulian(2000)", daysInYearJulian(2000), 366);
	// testInt("daysInYearGregorian(1900)", daysInYearGregorian(1900), 365);
	// testInt("daysInYearGregorian(2000)", daysInYearGregorian(2000), 366);

	// System.out.println("====E3Q1====");
	// testInt("daysInMonth(1,false)", daysInMonth(1,false), 31);
	// testInt("daysInMonth(2,false)", daysInMonth(2,false), 28);
	// testInt("daysInMonth(2,true)", daysInMonth(2,true), 29);
	// testInt("daysInMonth(11,true)", daysInMonth(11,true), 30);

	// System.out.println("====E4Q1====");
	// testInt("gregorianToCounter(1,1,1)", gregorianToCounter(1,1,1), 1);
	// testInt("gregorianToCounter(2,2,2)", gregorianToCounter(2,2,2), 398);
	// testInt("gregorianToCounter(101,1,1)", gregorianToCounter(101,1,1), 36525);
	// testInt("gregorianToCounter(2016,9,14)", gregorianToCounter(2016,9,14), 736221);

	// System.out.println("====E4Q2====");
	// testInt("julianToCounter(1,1,1)", julianToCounter(1,1,1), -1);
	// testInt("julianToCounter(2,2,2)", julianToCounter(2,2,2), 396);
	// testInt("julianToCounter(101,1,1)", julianToCounter(101,1,1), 36524);
	// testInt("julianToCounter(2016,9,14)", julianToCounter(2016,9,14), 736234);

	// System.out.println("====E5Q1====");
	// testInt("weekdayOfGregorian(2016,9,14)", weekdayOfGregorian(2016,9,14), 3);
	// System.out.println("====E5Q2====");
	// Ecrire le test

	// System.out.println("====E5Q3====");
	// testInt("dayOfSummertime(2017)", dayOfSummertime(2017), 26);

	// System.out.println("====E6Q1====");
	// testInt("julianToCounterClever(1,1,1)", julianToCounterClever(1,1,1), -1);
	// testInt("julianToCounterClever(2,2,2)", julianToCounterClever(2,2,2), 396);
	// testInt("julianToCounterClever(101,1,1)", julianToCounterClever(101,1,1), 36524);
	// testInt("julianToCounterClever(2016,9,14)", julianToCounterClever(2016,9,14), 736234);
    }
}
