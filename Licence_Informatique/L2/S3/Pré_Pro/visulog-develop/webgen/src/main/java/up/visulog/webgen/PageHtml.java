package up.visulog.webgen;

import java.util.Map;

import htmlflow.HtmlView;
import htmlflow.StaticHtml;

public class PageHtml {

	/* 
    public void getFichier() throws FileNotFoundException {
        PrintWriter fichier = new PrintWriter("OutputData/" + new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Date()) + ".html");
        fichier.write(HTMLEntities.DOCTYPE + HTMLEntities.HEAD);
        fichier.write("<body>\n" + HTMLEntities.HEADER + "\n<div class=\"\"\n");
        fichier.write("<div class=\"\">");
        fichier.write(resultat.getSubResults().stream().map(AnalyzerPlugin.Result::getResultAsHtmlDiv).reduce("", (acc, cur) -> acc + cur));
        fichier.write("\n </div>\n <div class=\"\">");
        //TODO: Rajouter les graphs
        fichier.write("</div></div>\n</body>\n</html>");
        fichier.close();
    }*/
    public String generateFile(String titre,  String type, String typeinfo, Map<String, Integer> map){
        HtmlView view = StaticHtml.view(v -> {
			//Building html structure
			//  <DOCTYPE html> is added automatically by htmlflow  (see https://htmlflow.org/features/ for more informations)
			v.html()
			    .head()
			        .meta().attrCharset("utf-8").__()
			        .link().addAttr("rel","stylesheet").attrHref("css/style.css").__()
			        .text(Canvajs.canvajsImport())
			    .__()  //head
			    .body()
			        .header()
			            .div().attrClass("head")
			                .a().attrHref("https://gaufre.informatique.univ-paris-diderot.fr/nodin/visulog").__()
			                .span().text("Visulog").__()
			                .div().attrClass("?") // missing div class name 
			                .__() // div
			            .__() //div
			        .__() // header
			        .div().text(Canvajs.canvajsDiv())
			        .__()
			        .script().text(Canvajs.canva(map, titre, type, typeinfo)).__()//change pie to bar pour avoir un graph <script>  </script>
			        .div().attrClass("?") // missing  div class name
			            .div().attrClass("?") // missing  div class name
			                .p()
			                .__() //p
			            .__() //div
			            .div().attrClass("?") //missing div class name
			                //TODO :  Add Pie charts
			            .__() // div
			        .__() // div
			    .__() //body
			.__();
		});  // html // + new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Date()) + "
       return view.render();       
       
    }
    
    /*
     *  view.setPrintStream(new PrintStream(new FileOutputStream("details.html"))).write();      
        try {
			Desktop.getDesktop().browse(URI.create("details.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    }

     * 
     */
}
