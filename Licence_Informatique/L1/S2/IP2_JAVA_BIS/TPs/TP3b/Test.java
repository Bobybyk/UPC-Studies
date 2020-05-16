public class Test {

	public static void main (String[] args) {
		Tache courses = new Tache ("faire les courses");
		Tache mains = new Tache ("se laver les mains");
		Tache cuisine = new Tache ("faire la cuisine");
		Tache manger = new Tache ("manger");
		courses.estNecessairePour(cuisine);
		mains.estNecessairePour(cuisine);
		cuisine.estNecessairePour(manger);
		manger.affiche();
	}
}