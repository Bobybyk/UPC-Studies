package main.java.commands.dev;

import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandDebug extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.CONFIRM, "COMMAND : debug");

        if (args.length == 3) {
            int value;
            String type;
            try {
                value = Integer.parseInt(args[2]);
                if (args[1] instanceof String) {
                    type = args[1];
                    switch(type) {
                        case "ERROR": 
                            if (value == 0) {
                                System.out.println("debug ERROR mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.ERROR, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug ERROR mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.ERROR, true);
                            }
                            return;
                        case "WARNING": 
                            if (value == 0) {
                                System.out.println("debug warning modes : disabled");  
                                DebugLogger.typeMap.replace(DebugType.WARNING, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug warning modes : enabled");  
                                DebugLogger.typeMap.replace(DebugType.WARNING, true);
                            }
                            return;
                        case "ALL": 
                            if (value == 0) {
                                System.out.println("debug ALL modes : disabled");  
                                DebugLogger.typeMap.replace(DebugType.ALL, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug ALL modes : enabled");  
                                DebugLogger.typeMap.replace(DebugType.ALL, true);
                            }
                            return;
                        case "CONFIRM": 
                            if (value == 0) {
                                System.out.println("debug CONFIRM mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.CONFIRM, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug CONFIRM mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.CONFIRM, true);
                            }
                            return;
                        case "HELP": 
                            if (value == 0) {
                                System.out.println("debug HELP mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.HELP, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug HELP mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.HELP, true);
                            }
                            return;
                        case "MESSAGE": 
                            if (value == 0) {
                                System.out.println("debug MESSAGE mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.MESSAGE, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug MESSAGE mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.MESSAGE, true);
                            }
                            return;
                        case "COM": 
                            if (value == 0) {
                                System.out.println("debug COM mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.COM, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug COM mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.COM, true);
                            }
                            return;
                        case "FORMAT": 
                            if (value == 0) {
                                System.out.println("debug FORMAT mode : disabled");  
                                DebugLogger.typeMap.replace(DebugType.FORMAT, false);
                            }
                            else if (value == 1) {
                                System.out.println("debug FORMAT mode : enabled");  
                                DebugLogger.typeMap.replace(DebugType.FORMAT, true);
                            }
                            return;
                    }
                }
            } catch(NumberFormatException e) {}
        }
            // liste tous les types de debug et leur état
            else if (args.length == 2) {
                if (args[1].equals("LIST")) {
                    for(int i = -1 ; i<DebugLogger.typeMap.size()-1 ; i++) {
                        DebugLogger.print(DebugType.HELP, "Debug type (" + DebugLogger.typeMapDirectory.get(i) + ", " + i + ") : " + DebugLogger.typeMap.get(i));
                    }
                    return;
                }
            }
            DebugLogger.print(DebugType.WARNING, "[CommandDebug/ATTENTION] : erreur de syntaxe");  
    }
    
}
