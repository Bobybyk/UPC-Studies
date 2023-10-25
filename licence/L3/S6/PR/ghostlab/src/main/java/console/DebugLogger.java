package main.java.console;

import java.util.HashMap;

import main.java.gui.ConsolePanel;

public class DebugLogger {
    
    // map with debug type (as integer) and related boolean value
    public static HashMap<Integer, Boolean> typeMap = new HashMap<Integer, Boolean>();
    // copy of typeMap with string macro instead of boolean value
    public static HashMap<Integer, String> typeMapDirectory = new HashMap<Integer, String>();

    // print debug message if debug type is enable
    public static void print(int debugType, String str) {
            
            // print debug message if debug type is enable
            if(typeMap.get(debugType)) {
                System.out.println(str);
                    ConsolePanel.print(str);
                    ConsolePanel.print("\n");
            } 

            // print debug message in every case
            else if(typeMap.get(DebugType.ALL)) {
                System.out.println(str);
                    ConsolePanel.print(str);
                    ConsolePanel.print("\n");
            } else {
                return;
            }
        Console.layout();
    }

    public static void setTypeMap() {
        typeMap.put(DebugType.ERROR, true);
        typeMapDirectory.put(DebugType.ERROR, "ERROR");
        typeMap.put(DebugType.WARNING, true);
        typeMapDirectory.put(DebugType.ERROR, "WARNING");
        typeMap.put(DebugType.ALL, false);
        typeMapDirectory.put(DebugType.ALL, "ALL");
        typeMap.put(DebugType.CONFIRM, true);
        typeMapDirectory.put(DebugType.CONFIRM, "CONFIRM");
        typeMap.put(DebugType.HELP, true);
        typeMapDirectory.put(DebugType.HELP, "HELP");
        typeMap.put(DebugType.MESSAGE, true);
        typeMapDirectory.put(DebugType.MESSAGE, "MESSAGE");
        typeMap.put(DebugType.COM, true);
        typeMapDirectory.put(DebugType.COM, "COM");
        typeMap.put(DebugType.FORMAT, false);
        typeMapDirectory.put(DebugType.FORMAT, "FORMAT");
    }

}
