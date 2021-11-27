<?php

/*
EXAMEN IO2 : 12/05/2020
NOM : LE FRANC
Prénom : MATTHIEU
N°Etudiant : 21800858
*/

session_start();
include "Logged.php";

// QUESTION 1 :

function page_login($login) { ?>

	<form method="POST" action="Home.php">
			<?php
				if (isset($login)) {
					$val = $login;
				} else {
					$val = "user name";
				} ?>
			login :<input type="text" name="login" value="<?php echo $val ?>"> <br>
			mot de passe :<input type="password" name="pwd"> <br>
			valider :<input type="submit" name="submit">
	</form>
	<?php

// Question 2 :

	if (isset($_POST['submit'])) {
		if (!empty($_POST['login']) AND !empty($_POST['pwd'])) {
			if ($_POST['login'] == "user0" AND $_POST['pwd'] == "user0") {
				$_SESSION['login'] = $_POST['login'];
				header("Location: Home.php");
			}
		}
		else {
			echo "login et/ou mot de passe non reconnus";
		}
	} 
}

?>

<h1>Choisissez un article</h1>
<form method="GET" action="lecture1.php">
	<select name="article">
		<option value="0">Article 1</option>
		<option value="1">Article 2</option>
		<option value="2">Article 3</option>
		<option value="3">Article 4</option>
	</select>
	<input type="submit" value="Lire">
</form>

<a href="Deconnexion.php">Deconnexion</a>