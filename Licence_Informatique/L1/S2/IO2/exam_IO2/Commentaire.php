<?php

/*
EXAMEN IO2 : 12/05/2020
NOM : LE FRANC
Prénom : MATTHIEU
N°Etudiant : 21800858
*/

include "Logged.php";

// QUESTION 4 :

?>

<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>

	<form> <?php
	
		if (isset($_GET['text'])) { // On verifie que le text (le commentaire) soit défini
			$i = 0;
			while($i < sizeof($_SESSION['commentaire'][$_GET['article']])) {
				//	On incrémente i jusqu'à atteindre la fin du tableau.
				$i++;
			}
			$_SESSION['commentaire'][$_GET['article']][$i] = $_GET['text']; // On ajoute le commentaire à la fin du tableau
			header("Location: Lecture1.php"."?&article=".$_GET['article']);	// On charge la page Lecture.php avec la valeur de l'article
		} 
		
		else { ?>
			<textarea name="text" placeholder="commentaire"></textarea>
			<?php $a = $_GET['article']; ?>
			<input type="hidden" name="article" value="<?php echo $a; ?>">
			<input type="submit" value="ajouter"> <?php
			if (isset($_POST['enregistrer'])) {	// On vérifie si le bouton d'ajout est set
				$_SESSION['commentaire'] = $_POST['commentaire'];	// On ajoute le commentaire à la session
				header("Location: Commentaire.php");	// On recharge la page Commentaire.php afin de prendre en compte les modifications.
			} 
		} ?>
	</form>
</body>
</html>