# rapport du projet

**Structure du projet :**
- implémentations/ : contient les implémentations de chacun
- spécifications/ : contient les spécifications de chacun et la spécification finale en crml
- groupe.txt : la liste des membres du groupe

**Ce que nous avons fait**

- Chaque membre de l'équipe a décris le mode de fonctionnement de son système et les différentes parties à implémenter.
- Les spécifications du système de chacun a été implémenté par un autre membre de l'équipe.
    - Matthieu a implémenté en python la spec de Gabriel
    - Gabriel a implémenté en java la spec de Hugo
    - Hugo a implémenté en java la spec de Matthieu

**Constat**

Quelques différences entre les propositions de chacun ont pu être constatées (penser une fonctionnalité de différentes manière) mais tout a, dans l'ensemble, quand même été pensé de façon relativement similaire.

La manière de décrire les éxigences a pu varier. Les formulations un peu floues ou incomplètes ont pu amener à des implémentations légèrement différentes de ce que le concepteur pouvait imaginer à l'origine.

- *Exemple* : le temps entre chaque changement de couleur du feu tricolore n'est pas précisé dans la spécification de Gabriel. Il a donc été choisi de le fixer arbitrairement dans l'implémentation.*

## Conclusion

L'uniformisation des moyens de spécifier les besoins est un point important pour éviter les malentendus ainsi que les zones d'ombres et possibles erreurs d'implémentation.

Les descriptions plus formelles semblent être à privilégier pour amener à une mise en oeuvre la moins ambiguë possible. Des descriptions plus informelles/littérales forcent à revenir régulièrement vers l'auteur pour clarifier certaines règles et son donc une importante perte de temps et sources d'erreurs supplémentaires.