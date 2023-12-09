PROGRAM program0
  VAR
    RED : BOOL;
    YELLOW : BOOL;
    GREEN : BOOL;
    CONTROL : BOOL;
    INPUT : BOOL;
    TP0 : TP;
    TP1 : TP;
    TP2 : TP;
    TP3 : TP;
  END_VAR

  TP0(IN := NOT(CONTROL) AND INPUT, PT := T#5s);
  RED := TP0.Q;
  TP1(IN := NOT(GREEN) AND NOT(RED) AND INPUT OR CONTROL, PT := T#3s);
  YELLOW := TP1.Q;
  TP2(IN := NOT(RED) AND NOT(YELLOW) AND INPUT OR CONTROL, PT := T#5s);
  GREEN := TP2.Q;
  TP3(IN := NOT(YELLOW) AND NOT(GREEN) AND NOT(RED) AND INPUT, PT := T#3s);
  CONTROL := TP3.Q;
END_PROGRAM


CONFIGURATION Config0

  RESOURCE Res0 ON PLC
    TASK task0(INTERVAL := T#20ms,PRIORITY := 0);
    PROGRAM instance0 WITH task0 : program0;
  END_RESOURCE
END_CONFIGURATION