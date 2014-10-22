package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Superficie extends Objeto3D implements TipoObjeto {

	private ArrayList<TipoObjeto> listaCurva1;
	private ArrayList<TipoObjeto> listaCurva2;

	public Superficie(String nome, ArrayList<CoordenadasHomogeneas> listaCoord,
			Color cor) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
	}

	public void incluirCurvas(ArrayList<TipoObjeto> listaCurvas1,
			ArrayList<TipoObjeto> listaCurvas2) {
		this.listaCurva1 = listaCurvas1;
		this.listaCurva2 = listaCurvas2;
	}

	public String coordenadasString() {
		String cS = "";

		for (TipoObjeto t : listaCurva1) {
			for (CoordenadasHomogeneas c : t.coordenadas()) {
				cS = cS + "(" + c.getXD() + ", " + c.getYD() + ", " + c.getZD()
						+ ") ";
			}
		}

		for (TipoObjeto t : listaCurva2) {
			for (CoordenadasHomogeneas c : t.coordenadas()) {
				cS = cS + "(" + c.getXD() + ", " + c.getYD() + ", " + c.getZD()
						+ ") ";
			}
		}
		return cS;
	}

	public ArrayList<TipoObjeto> curva1() {
		return listaCurva1;
	}

	public ArrayList<TipoObjeto> curva2() {
		return listaCurva2;
	}

}
