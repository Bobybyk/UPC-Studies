model FeuRouge

  // Paramètres des états
  enumeration EtatType
    "État fixe : arrêt circulation obligatoire, haut"
    rouge,
    "État fixe : arrêt circulation imminent, milieu"
    jaune,
    "État fixe : circulation autorisée, bas"
    vert,
    "État intermitent : circulation autorisée en respectant les priorités, milieu"
    jauneIntermitent,
    "État éteint : pas d'informations visuelles, toutes positions"
    eteint;
  end EtatType;

  // Paramètres des temps d'activité par états
  parameter Real tempsRouge = 30;
  parameter Real tempsJaune = 3;
  parameter Real tempsVert = 30;
  parameter Real tempsJauneIntermitent = 0.5;
  parameter Real tempsEteint = -1; // -1 signifie tant que éteint

  // Paramètres des transitions d'états
  enumeration TransitionType
    "Transition de l'état rouge à vert"
    rougeVersVert,
    "Transition de l'état vert à jaune"
    vertVersJaune,
    "Transition de l'état jaune à rouge"
    jauneVersRouge;
  end TransitionType;

  // Variables d'état
  EtatType etatActuel;
  Real tempsActuel;

equation

  // Conditions initiales
  initial equation
    etatActuel = EtatType.rouge;
    tempsActuel = tempsRouge;

  // Logique de transition d'états
  when {tempsActuel <= 0, rougeVersVert, vertVersJaune, jauneVersRouge} then
    // Mettre à jour l'état actuel
    if tempsActuel <= 0 then
      tempsActuel = -1; // -1 signifie que l'état doit changer
    end if;
    etatActuel = if rougeVersVert then EtatType.vert elseif vertVersJaune then EtatType.jaune elseif jauneVersRouge then EtatType.rouge else etatActuel;

    // Mise à jour du temps
    tempsActuel = if etatActuel == EtatType.rouge then tempsRouge elseif etatActuel == EtatType.jaune then tempsJaune elseif etatActuel == EtatType.vert then tempsVert elseif etatActuel == EtatType.jauneIntermitent then tempsJauneIntermitent else tempsEteint;

  end when;

  // Contraintes sur les transitions, deux états ne peuvent pas cohabiter
  when {pre(etatActuel) == EtatType.rouge, pre(etatActuel) == EtatType.vert, pre(etatActuel) == EtatType.jaune} then
    tempsActuel = 0;

  end when;

end FeuRouge;
