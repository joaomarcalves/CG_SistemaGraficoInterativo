package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Reta extends Objeto implements TipoObjeto {

	public Reta(String nome, ArrayList<TipoCoordenadas> listaCoord, Color cor) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
	}
	
	public TipoCoordenadas p1(){
		return super.coordenadas().get(0);
	}
	
	public TipoCoordenadas p2(){
		return super.coordenadas().get(1);
	}
}
