let rec ack m n = if n = 0 && m > 0 then ack (m-1) 1
  else if m > 0 && n > 0 then ack (m-1) (ack m (n-1))
  else n+1;;
