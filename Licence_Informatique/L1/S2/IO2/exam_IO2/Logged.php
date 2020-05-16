<?php

/*
EXAMEN IO2 : 12/05/2020
NOM : LE FRANC
Prénom : MATTHIEU
N°Etudiant : 21800858
*/

require_once "Home.php";

if(!isset($_SESSION['login'])) {
	page_login("unset");
	exit;
}

?>