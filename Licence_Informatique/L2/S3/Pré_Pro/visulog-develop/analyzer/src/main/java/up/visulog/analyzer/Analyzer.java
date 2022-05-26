// Abrevation utilisé dans le code :
// AP = AnalyzerPlugin
package up.visulog.analyzer;
import up.visulog.config.Configuration;
import up.visulog.config.PluginConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Analyzer {
    private final Configuration config; /* Créer une nouvelle Configuration qui est privée mais surtout inchangeable.
     Configuration = 1 Path et 1 maps, voir dans le main de Configuration ce que c'est Path et Map*/

    public AnalyzerResult result;

    public Analyzer(Configuration config) { /* Le constructeur de Analyzer, on remarque que l'on ne met rien dans "result" puisque c'est
  dans la methode computeResults qu'il sera rempli */
        this.config = config;
    }

    public AnalyzerResult computeResults() { // ici il faudra remplir result
        List<AnalyzerPlugin> plugins = new ArrayList<>(); /*  on créé une liste d'AnalyzerPlugin ( donc y'a plein de AP dedans) qu'on appelle plugins
         Ici un lien qui explique ArrayList :  http://imss-www.upmf-grenoble.fr/prevert/Prog/Java/Conteneurs/ArrayList.html*/
        for (var pluginConfigEntry: config.getPluginConfigs().entrySet()) {/* PAS SUR  : On parcourt chaque plugin integré dans la map, une variable de Configuration et ce plugin est créé .
        Je ne trouve pas la methode "entrySet()"" = IL faudra surement la faire dans la classe Configuration
           Var peut etre un string int etc, c'est le programme qui le definira par rapport a ce qu'il contient. */
            var pluginName = pluginConfigEntry.getKey(); //  Je ne trouve pas la methode "getKey()"= IL faudra surement la faire dans la classe Configuration
            var pluginConfig = pluginConfigEntry.getValue();//  Je ne trouve pas la methode "getValue()"= IL faudra surement la faire dans la classe Configuration
            var plugin = makePlugin(pluginName, pluginConfig);//  Je ne trouve pas la methode "makePlugin"= IL faudra surement la faire dans la classe Configuration ou PluginConfig
            plugin.ifPresent(plugins::add); /* rempli la liste les nouveaux plugins si ils sont presents (objet::nomMethode) l'instance( plugins) est mit en parametre à la methode (add)
            Je ne trouve pas la methode "ifPresent et add"= IL faudra surement les faire dans la classe Configuration ou PluginConfig*/
        }
        // run all the plugins
        // TODO: try running them in parallel #Issue 44
        for (var plugin: plugins) { // parcourt la liste maintenant qu'elle est remplie
          plugin.run(); // methode run a faire de AnalyzerPlugin
        }
        // store the results together in an AnalyzerResult instance and return it
        return new AnalyzerResult(plugins.stream().map(AnalyzerPlugin::getResult).collect(Collectors.toList()));
    } // on créé un nouveau analyserResult mais on en a un en haut en attribut, bizarre.

    // TODO: find a way so that the list of plugins is not hardcoded in this factory #Issue 45
    private Optional<AnalyzerPlugin> makePlugin(String pluginName, PluginConfig pluginConfig) {
        switch (pluginName) {
            case "countCommits" : return Optional.of(new CountCommitsPerAuthorPlugin(config));
            default : return Optional.empty();
        } // Pas compris ici !
    }

}
