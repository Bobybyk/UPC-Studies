package up.visulog.cli;

import up.visulog.analyzer.Analyzer;
import up.visulog.config.Configuration;
import up.visulog.config.PluginConfig;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
	

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileWriter;

public class CLILauncher {

    public static void main(String[] args){
    		      var config = makeConfigFromCommandLineArgs(args);
    		        if (config.isPresent()) {
    		            var analyzer = new Analyzer(config.get());
    		            var results = analyzer.computeResults();
    		            try {
							results.generateFileHtml("test");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
    		        } else displayHelpAndExit();
      
    }

    static Optional<Configuration> makeConfigFromCommandLineArgs(String[] args) {
        var gitPath = FileSystems.getDefault().getPath(".");
        var plugins = new HashMap<String, PluginConfig>();
        for (var arg : args) {
            if (arg.startsWith("--")) {
                String[] parts = arg.split("=");
                if (parts.length != 2) return Optional.empty();
                else {
                    String pName = parts[0];
                    String pValue = parts[1];
                    switch (pName) {
                        case "--addPlugin":
                            // TODO: parse argument and make an instance of PluginConfig #Issue 46

                            // Let's just trivially do this, before the TODO is fixed:

                            if (pValue.equals("countCommits")) plugins.put("countCommits", new PluginConfig() {});
                            /*          if (pValue.equals("countCommitsPerYear")) plugins.put("countCommitsPerYear", new PluginConfig() {
                            });
                            if (pValue.equals("countCommitsPerMonth")) plugins.put("countCommitsPerMonth", new PluginConfig() {
                            });
                            if (pValue.equals("countCommitsPerWeek")) plugins.put("countCommitsPerWeek", new PluginConfig() {
                            });
                            if (pValue.equals("sumOfCommits")) plugins.put("sumOfCommits", new PluginConfig() {
                            });
                            if (pValue.equals("sumOfCommitsPerMonth")) plugins.put("sumOfCommitsPerMonth", new PluginConfig() {
                            });*/
                            break;
                            //TODO  : Fill the next cases #Issue 47
                        case "--loadConfigFile":
                            // TODO (load options from a file)
                            break;
                        case "--justSaveConfigFile":
                            // TODO (save command line options to a file instead of running the analysis)
                            	try {
                            	    String fileName = "SaveConfig.txt";
                            		FileWriter fileWriter = new FileWriter (fileName, true); // on écris dans le fichier SaveConfig.txt sans écraser son contenu
                            		fileWriter.write( pValue +"\n"); // on ecrit et on sauvegarde l'option de la commande dans le fichier 
                            		fileWriter.close(); // on ferme le fichier
                            	}
                            	catch(IOException exception) {
                            		System.err.println("IOException : " + exception.getMessage());
                            	}
                        	break;
                        default:
                            return Optional.empty();
                    }
                }
            } else {
                gitPath = FileSystems.getDefault().getPath(arg);
            }
        }
        return Optional.of(new Configuration(gitPath, plugins));
    }

    private static void displayHelpAndExit() {
        System.out.println("Wrong command...");
        //TODO: print the list of options and their syntax
        System.exit(0);
    }
}
