let rec pasA n = if n > 0 then 1 + pasB (n-1) else 0

and pasB n = 
  if n = 0 then 0 else
    1 + (if n mod 2 = 0 then pasA (n - 2) else pasA (n - 1))
