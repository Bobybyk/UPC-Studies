/* Ici on doit  ecrire toute la classe, un plugin sert apporter une fonctionnalité à un ou plusieurs logiciels, ici on doit aider la classe
 AnalyzerResult qui va regrouper tous les resultats qu'obtient cette classe.*/

package up.visulog.analyzer;

public interface AnalyzerPlugin {
    interface Result {
        String getResultAsString();
        String getResultAsHtmlDiv();
        String getResultAsHtmlFlow();
    }

    /**
     * run this analyzer plugin
     */
    void run();

    /**
     *
     * @return the result of this analysis. Runs the analysis first if not already done.
     */
    Result getResult();
}
