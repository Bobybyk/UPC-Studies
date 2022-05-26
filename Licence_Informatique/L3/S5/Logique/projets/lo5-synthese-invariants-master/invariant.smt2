; Invar prend 2 entiers et renvoie une valeure de vérité 
; pour la configuration à l'intérieur de l'invariant
(declare-fun Invar (Int Int) Bool)

; on s'assure que Invar contienne la configuration initiale
(assert (Invar 0 0))

; on vérifie que Invar soit un invariant de boucle
(assert (forall ((i Int) (v Int))
	(=> (and (Invar i v) (< i 3)) (Invar (+ i 1) (+ v 3)))))

; on s'assure que l'Invariant soit sûr : 
; si pout tout x, y dans l'invariant && x>= 10, alors y<10
(assert (forall ((i Int) (v Int ))
	(=> (and (Invar i v) (>= i 3)) (= v 9))))

; on fait appel au solveur pour vérifier la satisfiabilité
; des 3 conjonctions
(check-sat-using (then qe smt))
(get-model)
(exit)