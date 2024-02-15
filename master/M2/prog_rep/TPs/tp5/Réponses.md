# TP5

## Exercice 1

**n notera scani le résultat du scan effectué par la thread i. Parmi les propriétés suivantes, lesquelles sont vraies ? (démonstration ou contre-exemple)**
- **a). Pour tout i: scani[i] = i**, vrai car chaque thread exécute d'abord update(i), ce qui signifie qu'elle écrit sa propre valeur i à l'indice i dans le tableau partagé. Ensuite, lors du scan, chaque thread récupère la dernière valeur écrite à son indice, qui est précisément i dans ce cas.
- **b). Pour j != i, scanj [i] = i ou scanj [i] = −1**, vrai car pour toute thread j différente de la thread i, scanj[i] sera soit i (si la thread i a écrit à cet indice) ou -1 (si la thread i n'a pas encore écrit à cet indice).
- **c). Pour j != i, si scanj [i] = i alors scani[j] = j**, faux car si scanj[i] = i, pour une thread j ≠ i, alors scani[j] = i. Cela découle du fait que si une thread j voit une valeur i à l'indice i lors de son scan, alors la thread i doit avoir écrit cette valeur i à cet indice, et par conséquent, la valeur à l'indice j lors du scan de la thread i est également i.
- **d). Pour j != i, si scanj [i] = i alors scani[j] = −1**, faux car si :
    - la thread i exécute update(i), écrivant i à l'indice i,
    - la thread j exécute update(j), écrivant j à l'indice j,
    - la thread j effectue scan et obtient scanj[i] = i,
    - la thread i effectue scan

    alors il est possible que la thread j n'ait pas encore terminé son update, donc scani[j] pourrait ne pas être -1, mais plutôt la valeur que j a écrite à l'indice j.

- **Pour j != i, scanj [i] = i ou scani[j] = j** vrai :
    - Si scanj[i] = i, cela signifie que la thread j a lu la valeur i à l'indice i lors de son scan. Cela ne peut se produire que si la thread i a effectivement écrit à l'indice i. Ainsi, la dernière valeur écrite à l'indice i est i. Par conséquent, lorsque la thread i effectue son scan, scani[j] devrait également être égal à j, car la dernière valeur écrite à l'indice j est j (et j ≠ i).
    - Si scani[j] = j, cela signifie que la thread i a lu la valeur j à l'indice j lors de son scan. Cela ne peut se produire que si la thread j a effectivement écrit à l'indice j. Ainsi, la dernière valeur écrite à l'indice j est j. Par conséquent, lorsque la thread j effectue son scan, scanj[i] devrait également être égal à i, car la dernière valeur écrite à l'indice i est i (et i ≠ j).

    Donc scanj[i] = i, ou scani[j] = j car si une thread j observe la valeur i à l'indice i, alors la thread i observera la valeur j à l'indice j.
 
- **Pour j != i, scanj ⊆ scani ou scani ⊆ scanj ( la relation ⊆ est A ⊆ B si et seulement si "pour tout i, si A[i]̸ = −1 alors A[i] = B[i]")**, vrai car l'opération est atomique donc si la thread j scan avant la thread i, alors scanj ⊆ scani, et si la thread i scan avant la thread j, alors scani ⊆ scanj.
