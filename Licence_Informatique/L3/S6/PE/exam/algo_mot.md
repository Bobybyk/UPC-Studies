* tant que mot dans la liste par indice :
    
    1. prend mot le plus long dans ``listMots``
    2. test placer mot
        
        2.1 si pas possible : continu (return null)
        2.2 si possible : place mot et return String avec **indice**, **coordonnées** (x, y), **orientation**

    3. supprime mot d'**indice** retourné dans ``listMots`` et print dans ``listeMotsPlaces`` la String retournée dans 2.2