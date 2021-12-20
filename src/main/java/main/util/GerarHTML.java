package main.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class GerarHTML {

	public static String gerarHtml(Map<Date, Integer> mapa) throws IOException {

		String html2 = 
				"<html>\r\n" 
				+ "<head>\r\n"
				+ "  <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
				+ "    <script type=\"text/javascript\">\r\n"
				+ "      google.charts.load('current', {'packages':['corechart']});\r\n"
				+ "      google.charts.setOnLoadCallback(drawChart);\r\n" 
				+ "\r\n" 
				+ "    function drawChart() {\r\n"
				+ "      array= "
				+ retornaDados(mapa)
				+ "\r\n"
				+ "      var data = new google.visualization.DataTable();\r\n"
				+ "      data.addColumn('string', 'Horas trabalhadas');\r\n"
				+ "      data.addColumn('number', 'Dia ');\r\n" 
				+ "for (var [key, value] of Object.entries(array)) {"
				+ "\r\n"
				+ " data.addRows([[key, value]]);"
				+ "\r\n"
				+ "}"
				+ "\r\n"
				+ "      var options = {\r\n"   
				+ "          title: 'Hora trabalhadas por dia',\r\n" 
				+ "        };\r\n" 

				+ "\r\n"
				+ "      var chart = new google.visualization.Histogram(document.getElementById('line_top_x'));\r\n" 
				+ "\r\n"
				+ "      chart.draw(data, options);\r\n" 
				+ "    }\r\n"
				+ "  </script>\r\n" 
				+ "</head>\r\n" 
				+ "<body>\r\n" 
				+ "  <div id=\"line_top_x\"></div>\r\n"
				+ "</body>\r\n" 
				+ "</html>\r\n" 
				+ "\r\n" + "";

		FileWriter arq = new FileWriter("grafico.html");
		PrintWriter gravarArq = new PrintWriter(arq);

		gravarArq.printf(html2);
		arq.close();
		return html2;

	}

	private static String retornaDados(Map<Date, Integer> mapa) {
		String dados = "";
		DateFormat dateFormat = new SimpleDateFormat("dd");

		for (Date i : mapa.keySet()) {
			String strDate = dateFormat.format(i);
			dados += "[" + mapa.get(i) + ", " + strDate + "],\r\n";
		}
		dados=dados.substring(0,dados.length()-3);
		return dados;
	}

}
