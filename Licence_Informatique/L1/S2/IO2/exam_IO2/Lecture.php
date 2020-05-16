<?php

/*
EXAMEN IO2 : 12/05/2020
NOM : LE FRANC
Prénom : MATTHIEU
N°Etudiant : 21800858
*/
	
include "Logged.php";

// QUESTION 3 :

$tabA = array(
	0 => array(0 => "Hello World", 1 => "Examen IO2", 2 => "12/05/2020", 3 => "Le premier article"),
	1 => array(0 => "World Hello", 1 => "IO2 Examen", 2 => "12/05/2020", 3 => "Le deuxième article"),
	2 => array(0 => "Bonjour", 1 => "abc", 2 => "def", 3 => "Le troisième article"),
	3 => array(0 => "Au revoir", 1 => "hif", 2 => "klm", 3 => "Le dernier article"));

$article = $_GET['article'];

?>

<!DOCTYPE html>
<html>
<body>

	<article>
		
		<?php
		for ($i = 0 ; $i<4 ; $i++){
			echo $tabA[$article][$i]; ?>
			<br> <?php
		} ?>

	</article>

	<form method="GET" action="Commentaire.php">
	
	<p> <?php

		if (isset($_SESSION['commentaire'][$article])) {	// On verifie que les commentaires à l'indice de l'article existent
			foreach ($_SESSION['commentaire'][$article] as $value) {
				echo $value; // On affiche le commentaire ?>
			}
		} ?>

	</p> 

		<input 	type="hidden" name="article" value="<?php echo $_GET['article']; ?>">
		Ajouter commentaire :<input type="submit" value="ajouter">
	
	</form>

</body>

</html>