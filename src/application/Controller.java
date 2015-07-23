package application;

import java.util.ArrayList;
import java.util.List;




import application.validation.ValidaFormula_1;
import application.validation.ValidaFormula_1_2;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextArea imprime2;
	@FXML
	private TextArea imprime;
	@FXML
	private ProgressBar progresso;
	@FXML
	private TextField digita2;

	static List<String> texto = new ArrayList<String>();

	@FXML
	public void add() {
		imprime2.setText(imprime2.getText() + digita2.getText());
		texto.add(digita2.getText());
		digita2.clear();

	}

	@FXML
	public void limpar() {

		progresso.setStyle("-fx-accent: white;");
		progresso.setProgress(1f);
		imprime2.clear();
		imprime.clear();
		texto.clear();

	}

	public void digita2() {

	}

	@FXML
	public void ok() {
		progresso.setStyle("-fx-accent: yellow;");
		
		String pegaTexto = ValidaFormula_1_2.retiraParentesis(texto).toString()
				.replace(",", "").replace("[", "").replace("{", "(")
				.replace("}", ")").replace("]", "");
		
		
		//TAMANHO DA FORMULA
		String tamanho = pegaTexto.replace("(", "").replace(")", "").replace(" ", "");
		int comp=0;
		for(int i=0;i<tamanho.length();i++){
			if((tamanho.indexOf(i)!='(')&&(tamanho.indexOf(i)!=')') ){
				comp++;
			}
		}
		imprime.setText("\n" + pegaTexto + "\n" + "Tamanho da Formula="
				+ comp);

		progresso.setProgress(1f);
	
	}

	@FXML
	public void abrep() {// botao abre parentesis
		imprime2.setText(imprime2.getText() + "(");
		texto.add("(");
	}

	@FXML
	public void fechap() {// botao fecha parentesis
		imprime2.setText(imprime2.getText() + ")");
		texto.add(")");
	}

	@FXML
	public void botaoE() {

		imprime2.setText(imprime2.getText() + "Λ");
		texto.add("Λ");
	}

	@FXML
	public void botaoOu() {

		imprime2.setText(imprime2.getText() + "V");
		texto.add("V");
	}

	@FXML
	public void botaoEmplica() {
		imprime2.setText(imprime2.getText() + "→");
		texto.add("→");
	}

	@FXML
	public void botaoBiemplica() {
		imprime2.setText(imprime2.getText() + "↔");
		texto.add("↔");
	}

	@FXML
	public void negacao() {
		imprime2.setText(imprime2.getText() + "¬");
		texto.add("¬");
	}

	public void setImprime(TextArea imprime) {
		this.imprime = imprime;
	}

	public TextArea getImprime() {
		return imprime;
	}

	public ProgressBar getProgresso() {
		return progresso;
	}

	public void setProgresso(ProgressBar progresso) {
		this.progresso = progresso;
	}

	public TextField getDigita2() {
		return digita2;
	}

	public void setDigita2(TextField digita2) {
		this.digita2 = digita2;
	}

	public TextArea getImprime2() {
		return imprime2;
	}

	public void setImprime2(TextArea imprime2) {
		this.imprime2 = imprime2;
	}

}
