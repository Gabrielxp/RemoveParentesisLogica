package application.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValidaFormula_1_2 {

	

	public static List<String> retiraParentesis(List<String> string) {
		List<String> result = string;
		for (int i = 0; i < result.size(); i++) {
			result = procuraParantesis(result);
			result = removeParentesisExtremos(result);

		}

		return result;

	}

	static List<String> procuraParantesis(List<String> formula) {
		int posicaoPrimeiroConectivo = 0;
		int posicaoSegundoConectivo = 0;
		int posicaoPrimeiroParentesis = 0;
		int posTercConectivo = 0;
		int contadorDeParentesis = 0;
		boolean temParentesis = false;
		HashMap<String, Integer> conectivos = new HashMap<String, Integer>();
		conectivos.put("V", 3);
		conectivos.put("Λ", 3);
		conectivos.put("→", 2);
		conectivos.put("↔", 2);
		conectivos.put("¬", 4);

		for (int i = 0; i < formula.size(); i++) {

			if (formula.get(i).contains("{") || formula.get(i).contains("}")) {
				continue;
			}

			if (temParentesis) {
				if (formula.get(i).equals("(")) {
					contadorDeParentesis++;
				} else if (formula.get(i).equals(")")) {
					contadorDeParentesis--;
					//QNDO FECHA PARENTESIS ARMAZENA A POSIÇAO DO CONECTIVO DE FORA
					if ((i + 1) < formula.size()) {
						if ((!formula.get(i+1).equals(")")) ||(!formula.get(i+1).equals("(")) )
							posTercConectivo = i + 1;
					}
				}

				if (eConectivo(formula.get(i))) {
					posicaoSegundoConectivo = i;

				}
				if (formula.get(i).equals(")") && contadorDeParentesis == 0) {

					String primeiroConectivo = formula
							.get(posicaoPrimeiroConectivo);
					int precedencia = conectivos.get(primeiroConectivo);

					String segundoConectivo = formula
							.get(posicaoSegundoConectivo);
					int precedenciaSegundo = conectivos.get(segundoConectivo);

					String terceiroConectivo = "";
					int precedenciaTerceiro = 0;
					if (posTercConectivo != 0) {
						terceiroConectivo = formula.get(posTercConectivo);
						precedenciaTerceiro = conectivos.get(terceiroConectivo);
					}
					//VERIFICA AS PRECEDENCIAS 
					if ((precedencia < precedenciaSegundo)
							&& (precedenciaTerceiro < precedenciaSegundo) ) {
						formula.set(posicaoPrimeiroParentesis, "");
						formula.set(i, "");
					} else { //CASO NAO PODE REMOVER OS PARENTESIS, TROCA POR CHAVES
						formula.set(posicaoPrimeiroParentesis, "{");
						formula.set(i, "}");
					}

					temParentesis = false;
				}

			} else {
				if (eConectivo(formula.get(i))) {

					posicaoPrimeiroConectivo = i;

					if (formula.get(i + 1).equals("(")) {

						temParentesis = true;
						if (formula.get(i + 1).equals("{")) {
							i++;
						}
						posicaoPrimeiroParentesis = i + 1;

					}
				}
			}

		}

		List<String> formula2 = new ArrayList<String>();

		for (String elemento : formula) {
			if (!elemento.equals(""))
				formula2.add(elemento);
		}

		return formula2;
	}

	static boolean eConectivo(String elemento) {
		String conectivos[] = { "V", "Λ", "→", "↔", "¬" };
		for (String string : conectivos) {
			if (elemento.equals(string)) {
				return true;
			}
		}
		return false;
	}

	static List<String> removeParentesisExtremos(List<String> formula) {

		int contParentesis = 0;

		for (int i = 0; i < formula.size() - 1; i++) {
			if (formula.get(i).equals("("))
				contParentesis++;
			else if (formula.get(i).equals(")"))
				contParentesis--;
		}

		if (contParentesis == 0)
			return formula;
		else {
			List<String> f = new ArrayList<String>();

			for (int i = 1; i < formula.size() - 1; i++) {
				f.add(formula.get(i));
			}

			return f;
		}

	}

}
