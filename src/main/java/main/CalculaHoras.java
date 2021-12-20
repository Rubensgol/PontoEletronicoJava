package main;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.Funcionario;
import main.model.Ponto;
import main.util.GerarHTML;

public class CalculaHoras {

	public String calculahora(Funcionario funcionario, List<Ponto> pontos) {
		
		Map<Date,Integer> mapa = new HashMap<Date,Integer>();
		int horaTrabalhada = 0;
		
		Date entrada = null;
		Date saida;
		for (Ponto p : pontos) {
			int totalHoraTrabalhada=0;
			if (p.getRegistro().equals("entrada")) {
				entrada = p.getMomento();
			} else {
				saida = p.getMomento();
				horaTrabalhada=diferencaHoras(entrada, saida);
				totalHoraTrabalhada+=horaTrabalhada;
				mapa.put(saida, totalHoraTrabalhada);
			}
		}
		try {
			return GerarHTML.gerarHtml(mapa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public int diferencaHoras(Date d1, Date d2) {
		long diff = d1.getTime() - d2.getTime();
		int diffHours = (int) (diff / (60 * 60 * 1000) % 24);
		return diffHours;
	}
}
