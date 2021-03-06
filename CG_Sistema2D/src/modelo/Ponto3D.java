package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Ponto3D extends Objeto3D implements TipoObjeto {

	private double x;
	private double y;
	private double z;

	public Ponto3D(String nome, ArrayList<CoordenadasHomogeneas> listaCoord,
			Color cor) {
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}
}
