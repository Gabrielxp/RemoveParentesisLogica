package application.validation;

import javax.swing.JOptionPane;

public class ValidaConectivo {

	public static boolean lado(String valor) {
		if (valor.equals("")) {
			return true;

		} else {
			char ultimo = valor.charAt(valor.length() - 1);

			if (ultimo == 'Λ' || ultimo == 'V' || ultimo == '→'
					|| ultimo == '↔') {
				JOptionPane.showMessageDialog(null,
						"Conectivo ao lado de conectivo não pode =(");

				return false;
			}
			return true;

		}
	}
}
