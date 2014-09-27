package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Poligono extends Objeto implements TipoObjeto {

	private boolean preenchido;

	public Poligono(String nome, ArrayList<TipoCoordenadas> listaCoord, Color cor, boolean preenchido) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
		this.setPreenchido(preenchido);
	}

	public boolean preenchido() {
		return preenchido;
	}

	private void setPreenchido(boolean preenchido) {
		this.preenchido = preenchido;
	}

}
