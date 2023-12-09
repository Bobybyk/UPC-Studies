# TP SDL

## 1/. MODELISATION



## 2/. VERIFICATION DE LA SPECIFICATION

**a). Récupérer une trace MSC d'un.e camarade et faire un "check" de cette trace sur votre spécification, que constatez-vous? pourquoi?** 

La trace MSC que j'ai importé *test_camarade* obtient un verdict pass. Cette trace test des débits avec différentes valeurs de montant. Ma spécification couvre plusieurs cas possibles testé par la trace que j'ai importé.

**b). Récupérer la trace MSC que je vous fournis (tr3vFAIL), faire un nouveau check avec seulement cette trace. Que constatez-vous? pourquoi?**

Check effectué avec seulement la trace *tr3vFAIL* : le test fail et la couverture est de 41%. *tr3vFail* ne pass pas car on demande 3 billets de 20 mais on en reçoit que 2.

**c). Refaire un check en ajoutant ma trace à vos traces. Que constatez-vous? pourquoi ?**

Mes traces passent le check, mais la t
race *tr3vFAIL* ne passe toujours pas. La couverture est de 76%, soit la même couverture que pour mes traces seules.

**d). Je vous fournis une nouvelle spécification du DAB, nommée MISD2019SOLUTION_FAIL, qui contient déjà une trace MSC (tr2d). Refaire un Check en y ajoutant toutes les traces précédentes. Que constatez-vous?**

Toutes mes traces passent à part mon test *test_montant_negatif_null*. Cela est normal, dans ma spécification de debitproc, je vérifie que le montant demandé est n'est pas négatif et, si tel est le cas, je ne fais rien. Or, dans la spécification fournie pour debitproc, cette vérification n'est pas faite.   

![Couverture](couverture_MP72022SOLUTION_FAIL.png)

**A partir de (d), cliquer sur 'Actions' à droite de la fenêtre de vérification et générer les TTCN pour tous ('ALL') les scenarios. Interpréter les résultats obtenus visibles dans le fichier TTCN_TestsAndControl.ttcn3.**

Dans le fichier ```TTCN_TestsAndControl.ttcn3```, on peut voir :
- *altstep RTDS_fail()* : pour gérer une situation d'échec. Si un message est reçu sur cardchan, l'étape déclenche un échec avec le message "Fail in default altstep!" et arrête l'exécution.
- Les cas de test (testcase) : avec chaque série d'interactions
- Les messages envoyés et reçus comme par exemple ``debit_tr2dMSG3`` qui représente un types d'action dans le scénario de test.

## 3/. SCRIPTS TTCN3

**a). Ecrire un Test Case TTCN3 (tous les TC seront insérés dans le même fichier TTCN_TestsAndControl.ttcn3) permettant de prouver ce comportement B1 (dans tous les cas) dans SPEC3 ==> verdict pass**

Le test case pour B1 obtient un pass,

**Dans SPEC3, 3 fautes (de type différents) ont été insérées (en comparant avec la spécification et les besoins initiaux du système), à vous de les détecter à l'aide de Test Cases TTCN3 bien définis.**

- première faute : dans la spec BASIS on permet à l'utilisateur de faire une erreur de code et de réessayer avant de l'éjecter alors que dans SPEC3 on éjecte directement après la première erreur de code. Situation mise en valeur avec le test case *test_err_code*.
- deuxième faute : après avoir fait un ``cancelop`` la condition est owait4confcancel dans la SPEC3 alors que dans la spec BASIS on attend avec *wait4confcancel*.
- troisième faute : après avoir demandé un credit, et au retour de la task creditproc(amountcredit), on a un output *OKop* dans la spec BASIS alors que dans SPEC3 on ne l'a pas.
