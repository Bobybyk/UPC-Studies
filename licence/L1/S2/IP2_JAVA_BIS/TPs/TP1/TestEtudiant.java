public class TestEtudiant {
	
	public static void main (String[] args) {
		Etudiant etu = new Etudiant ("Le Franc", "Matthieu", 21800858, 20);
		Etudiant.afficher(etu);
		System.out.println(Etudiant.estAdmis(etu));
		System.out.println(Etudiant.mention(etu));
	} 
}