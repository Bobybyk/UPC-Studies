package fr.uparis.exemples;

//utilisation comparée des 3 techniques pour construire un objet 
//aux nombreux attributs obligatoire et optionnels

public class Coca {

	public static void tryAll() {

		// telescope
		NutritionFactsT cocaCola = new NutritionFactsT(240, 8, 100, 0, 35, 27);

		// JavaBeans
		NutritionFactsA coca = new NutritionFactsA();
		coca.setServingSize(240);
		coca.setServings(8);
		coca.setCalories(100);
		coca.setSodium(35);
		coca.setCarbohydrate(27);

		NutritionFactsB pepsiCola = new NutritionFactsB.Builder(240, 8).calories(100).sodium(35).carbohydrate(27)
				.build();

	}

}
