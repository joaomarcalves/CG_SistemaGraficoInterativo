package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Reta extends Objeto implements TipoObjeto {

	public Reta(String nome, ArrayList<CoordenadasHomogeneas> listaCoord, Color cor) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
	}
	
	public CoordenadasHomogeneas p1(){
		return super.coordenadas().get(0);
	}
	
	public CoordenadasHomogeneas p2(){
		return super.coordenadas().get(1);
	}
}
