package main;


import java.util.Date;
import java.util.List;


import main.model.Funcionario;
import main.model.Ponto;

public class Tabela {
	public String tabela(Funcionario funcionario, List<Ponto> pontos) {
		String html = "";
		html += "<table>";
		html += "<tr>";
		html += "<th>CPF</hd>";
		html += "<td>" + funcionario.getCpf() + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>Nome</th>";
		html += "<td>" + funcionario.getNome() + " </td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>telefone </th>";
		html += "<td>" + funcionario.getTelefone() + "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th>E-mail</th>";
		html += "<td>" + funcionario.getEmail() + "</td>";
		html += "</tr>";

		html += "<tr>";
		html += "<th>Pontos</th>";
		html += "</tr>";
		html += "<tr>";
		float entradas = 0;
		float saidas = 0;
		float tothorasTrabalhadas = 0;
		float mimtrabalhados = 0;
		float diaTrabalhados = 0;
		Date dia = null;
		Date dia2 = null;
		Date entrada = new Date();
		Date saida = new Date();
		for (Ponto p : pontos) {

			float horastrabalhdas = 0;
			if (p.getRegistro().equals("entrada")) {
				entrada = p.getMomento();
				entradas += 1;
				if (entradas == 1)
					dia = entrada;
			} else {
				saida = p.getMomento();
				horastrabalhdas = diferencaHoras(saida, entrada);
				saidas += 1;
				tothorasTrabalhadas += horastrabalhdas;

			}
			html += "<tr>";
			html += "<td>" + p.getMomento() + "</td>";
			html += "<td>" + p.getRegistro() + "</td>";
			html += "</tr>";
			dia2 = saida;
		}
		
		diaTrabalhados = diferencaDias(dia2, dia);
		html += "<tr>";
		html += "<th>Numero de entradas</th>";
		html += "<td>" + entradas + "</td>";
		html += "<th>Numero de saidas</th>";
		html += "<td>" + saidas + "</td>";
		html += "<th>Total Trabalhado</th>";
		html += "<td>" + tothorasTrabalhadas + ":" + mimtrabalhados + "</td>";
		diaTrabalhados = diaTrabalhados + 1;
		// if (tothorasTrabalhadas > 24) {
		html += "<th>Dias trabalhados</th>";
		html += "<td>" + diaTrabalhados + "</td>";
		// }
		html += "</tr>";
		html += "</table>";

		return html;
	}

	public int diferencaHoras(Date d1, Date d2) {
		long diff = d1.getTime() - d2.getTime();
		int diffHours = (int) (diff / (60 * 60 * 1000) % 24);
		return diffHours;
	}
	public int diferencaDias(Date d1, Date d2) {
		long diff = d1.getTime() - d2.getTime();
		int diffHours = (int) (diff / (60 * 60 * 1000));
		return diffHours;
	}
}
