u b l i c c l a s s TP_lastZero {
        3
        4 p u b l i c s t a t i c i n t l a s t Z e r o ( i n t [ ] x )
        5 {
        6 f o r ( i n t i =0; i <x . l e n g t h ; i ++)
        7 i f ( x [ i ]==0)
        8 r e t u r n i ;
        9 r e t u r n −1;
        10 }
        11
        12 p u b l i c s t a t i c v o i d main ( S t r i n g [ ] a r g s ) {
        13 i n t [ ] x1 = { 0 , 1 , 0 } ;
        14 System . o u t . p r i n t l n ( l a s t Z e r o ( x1 ) ) ; // E x p e c t e d 2
        15
        16 }
        17
        18 }