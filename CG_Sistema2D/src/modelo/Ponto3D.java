package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Ponto3D extends Objeto3D implements TipoObjeto {

	private double x;
	private double y;
	private double z;

	public Ponto3D(String nome, ArrayList<TipoCoordenadas> listaCoord, Color cor) {
		super(nome, listaCoord, cor);
		x = listaCoord.get(0).getXD();
		y = listaCoord.get(0).getYD();
		z = listaCoord.get(0).getZD();
		// TODO Auto-generated constructor stub
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}
