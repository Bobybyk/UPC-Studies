public class TestEtudiant {
	
	public static void main (String[] args) { 
		
		Etudiant etu = new Etudiant();
		
		etu.nom = "Le Franc";
		etu.prenom = "Matthieu";
		etu.num = 21800858;
		etu.note = 20;
		
		Etudiant.afficher(etu);

		boolean admis = Etudiant.estAdmis(etu);

		System.out.println(admis);

		String ment = Etudiant.mention(etu);

		System.out.println(ment);
	}
}
