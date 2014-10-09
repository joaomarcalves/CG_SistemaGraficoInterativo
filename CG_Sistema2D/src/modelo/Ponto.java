package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Ponto extends Objeto implements TipoObjeto {

	public Ponto(String nome, ArrayList<CoordenadasHomogeneas> listaCoord, Color cor) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
	}
	
	public CoordenadasHomogeneas p1(){
		return super.coordenadas().get(0);
	}
}
