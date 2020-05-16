<?php
	
/*
EXAMEN IO2 : 12/05/2020
NOM : LE FRANC
Prénom : MATTHIEU
N°Etudiant : 21800858
*/

include "Logged.php";

// QUESTION 5 : 

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
			echo $tabA[$article][$i]; // On affiche l'article ?>
			<br> <?php
		} ?>
	</article>


	<form method="get" action="Lecture1.php">
	
	<p> <?php
		if (isset($_SESSION['commentaire'][$article])) {	// On verifie que les commentaires à l'indice de l'article existent
			foreach ($_SESSION['commentaire'][$article] as $value) {
				echo $value; // On affiche le commentaire ?>
				<input type="checkbox" name="<?php echo $value ?>"> <?php // On affiche la checkbox associée.
			}
		} 
		if (isset($_GET['del'])) {	// On verifie si le submit a été set.
			for ($k = 0 ; $k < sizeof($_SESSION['commentaire'][$article]) ; $k++) {	// Tant que la hauteur de k est inférieure à la longueur du tableau, on incrémente.
				if(isset($_GET[$_SESSION['commentaire'][$article][$k]])) {	// On vérifie si le get du commentaire à l'indice k est set (si oui, cela indique que la checkbox associée est cochée). 
					unset($_SESSION['commentaire'][$article][$k]);	// On retire le commentaire à l'indice k
					header("Location: Lecture1.php"."?&article=".$_GET['article']);	// On recharge la page Lecture.php avec la valeur de l'article afin d'afficher les modifications.
				}
			}	
		}?>
	</p> 
	<input 	type="hidden" name="article" value="<?php echo $_GET['article']; ?>">
		Supprimer des commentaires :<input type="submit" name="del" value="supprimer">
	</form>

	<form method="GET" action="Commentaire.php">
		<input 	type="hidden" name="article" value="<?php echo $_GET['article']; ?>">
		Ajouter commentaire :<input type="submit" value="ajouter">
	</form>

</body>

</html>