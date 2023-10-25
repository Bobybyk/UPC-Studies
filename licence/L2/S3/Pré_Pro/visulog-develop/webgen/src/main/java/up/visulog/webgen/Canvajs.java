
package up.visulog.webgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Canvajs {

//  public static Map<String, Integer> transformeTxtToArrayList() throws IOException {
//  	File file = new File("C:\\Users\\Sunay\\Desktop\\visulog\\webgen\\src\\main\\java\\up\\visulog\\webgen\\MergesTest.txt");
//  	if(file.exists()) {
//  		Map <String, Integer> ab = new HashMap<String, Integer>();
//  		BufferedReader b = new BufferedReader(new FileReader(file));
//  		String readLine = "";
//  		while ((readLine = b.readLine()) != null) {
//  			var nb = ab.getOrDefault(readLine.toLowerCase(), 0);
//  			ab.put(readLine.toLowerCase(), nb + 1);
//          }
//          b.close();
//          return ab;
//  	}else {
//  		file.createNewFile(); // creation du fichie	
//  		System.out.println(" Votre fichier n'est pas valide ###");
//  	}
//		return null;
//	}
	
	public static String canva(Map<String, Integer> bc, String title, String type, String typeplugin){
	    	String r = " 	window.onload = function() {\r\n"
			    			+ "\r\n"
			    			+ "var chart = new CanvasJS.Chart(\"chartContainer\", {\r\n"
			    			+ "	theme: \"light2\","
			    			+ "	exportEnabled: true,\r\n"
			    			+ "	animationEnabled: true,\r\n"
			    			+ "	title: {\r\n"
			    			+ "		text: \""+title+"\"\r\n"
			    			+ "	},\r\n"
			    			+ "	data: [{\r\n"
			    			+ "		type: \""+type+"\",\r\n"
			    			+ "		toolTipContent: \"<b>{label}</b>: {y} "+typeplugin+"\",\r\n"
			    			+ "		showInLegend: \"true\",\r\n"
			    			+ "		legendText: \"{label}\",\r\n"
			    			+ "		indexLabelFontSize: 16,\r\n"
			    			+ "		indexLabel: \"{label} - {y} "+typeplugin+"\",\r\n"
			    			+ "		dataPoints: [\r\n";
				            for (var item : bc.entrySet()) 
				            	if(item != null) {
						           	 r+= "{ y:"+ item.getValue() +", label: '"+item.getKey() +"'},"; 
				            	}
			    			r+="]"
			    			+ "	}]" 
			    			+ "});"
			    			+ "chart.render();"
			    			+ "}";
			    	return r;
			    }
			  
  public static String canvajsDiv() {
	  return "<div id=\"chartContainer\" style=\"height: 300px; width: 100%;\"></div>";
  }
  public static String canvajsImport() {
	  return "<script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>";
  }

}
