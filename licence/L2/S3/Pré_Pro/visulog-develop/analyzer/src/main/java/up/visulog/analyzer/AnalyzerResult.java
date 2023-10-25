package up.visulog.analyzer;

import java.util.List;


import java.awt.Desktop;
import java.io.*;
import java.net.URI;

public class AnalyzerResult { // recupere les resultats de AnalyzerPlugin
    public List<AnalyzerPlugin.Result> getSubResults() {
        return subResults;
    }

    private final List<AnalyzerPlugin.Result> subResults;

    public AnalyzerResult(List<AnalyzerPlugin.Result> subResults) { // ON le met dans une liste
        this.subResults = subResults;
    }

    @Override
    public String toString() { // Affiche la liste en la transformant en String
        return subResults.stream().map(AnalyzerPlugin.Result::getResultAsString).reduce("", (acc, cur) -> acc + "\n" + cur);
    }

    public String toHTML() { // Affiche en ligne HTML dans la page
        return "<html><body>"+subResults.stream().map(AnalyzerPlugin.Result::getResultAsHtmlDiv).reduce("", (acc, cur) -> acc + cur) + "</body></html>";
    }

    public boolean toFile(AnalyzerResult c) throws IOException {
        File f = new File("index.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(c.toHTML());
        bw.close();
        return true;
    }
    
    public void generateFileHtml(String namefile) throws FileNotFoundException {
    	System.out.println(subResults.stream().map(AnalyzerPlugin.Result::getResultAsHtmlFlow).reduce("", (acc, cur) -> acc + cur));
        File f = new File("file:" + namefile + ".html");
//    	System.out.println(subResults.stream().map(AnalyzerPlugin.Result::getResultAsHtmlFlow).reduce("", (acc, cur) -> cur));
    	if(f.exists()) {
    	      FileWriter myWriter;
			try {
				myWriter = new FileWriter(namefile + ".html");
	    	    myWriter.write(subResults.stream().map(AnalyzerPlugin.Result::getResultAsHtmlFlow).reduce("", (acc, cur) -> acc + cur));
	    	    myWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          try {
  				Desktop.getDesktop().browse(URI.create(namefile + ".html"));
  			} catch (IOException e) {
  				System.out.println("Aucun navigateur installé sur votre machine");
  				e.printStackTrace();
  			}	
    	}else {
    		try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
//    	((HtmlView<?>) subResults.stream().map(AnalyzerPlugin.Result::getResultAsHtmlFlow)).setPrintStream(new PrintStream(new FileOutputStream(namefile + ".html"))).write();      
    
        }
		
}
