package modelo;

import java.awt.Label;
import java.awt.TextField;

public class SemiPonto {
	public SemiPonto(Label label, TextField textField, TextField textField2) {
		// TODO Auto-generated constructor stub
		nome = label;
		tFx = textField;
		tFy = textField2;
	}

	private Label nome;
	private TextField tFx;
	private TextField tFy;

	public Label getNome() {
		return nome;
	}

	public void setNome(Label nome) {
		this.nome = nome;
	}

	public TextField gettFx() {
		return tFx;
	}

	public void settFx(TextField tFx) {
		this.tFx = tFx;
	}

	public TextField gettFy() {
		return tFy;
	}

	public void settFy(TextField tFy) {
		this.tFy = tFy;
	}
}
