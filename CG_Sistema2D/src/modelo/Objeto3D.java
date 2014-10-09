package modelo;

import java.awt.Color;
import java.util.ArrayList;

import Jama.Matrix;

public class Objeto3D extends Objeto {

	public Objeto3D(String nome, ArrayList<CoordenadasHomogeneas> listaCoord,
			Color cor) {
		super(nome, listaCoord, cor);
		// TODO Auto-generated constructor stub
	}

	public static TipoObjeto criarPonto(String nome,
			CoordenadasHomogeneas coordenadas, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> listaCoord = new ArrayList<CoordenadasHomogeneas>();
		listaCoord.add(coordenadas);
		TipoObjeto p = new Ponto3D(nome, listaCoord, cor);
		p.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return p;
	}

	public static TipoObjeto criarReta(String nome,
			CoordenadasHomogeneas coordenadas, CoordenadasHomogeneas coordenadas2, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> listaCoord = new ArrayList<CoordenadasHomogeneas>();
		listaCoord.add(coordenadas);
		listaCoord.add(coordenadas2);

		TipoObjeto r = new Reta3D(nome, listaCoord, cor);
		r.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return r;
	}

	public static TipoObjeto criarPoligono(String nome,
			ArrayList<CoordenadasHomogeneas> listCord, Color cor, boolean preenchido) {
		// TODO Auto-generated method stub
		TipoObjeto o = new Poligono3D(nome, listCord, cor, preenchido);
		o.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return o;
	}

	@Override
	public void escalonarSe(Escalonamento tipo) {
		// TODO Auto-generated method stub

		System.out.println("entrou");

		double sX = 1, sY = 1, sZ = 1;
		double cX = this.centro().getXD();
		double cY = this.centro().getYD();
		double cZ = this.centro().getZD();

		double cXwin = this.centroWin().getXD();
		double cYwin = this.centroWin().getYD();
		double cZwin = this.centroWin().getZD();

		switch (tipo) {
		case AUMENTAR:
			sX = 1.1;
			sY = 1.1;
			sZ = 1.1;
			break;
		case DIMINUIR:
			sX = 0.9;
			sY = 0.9;
			sZ = 0.9;
			break;
		}

		// Aplicando deslocamento para origem
		FabricaMatriz m = new FabricaMatriz();
		Matrix t1 = m.matrizTranslação(-cX, -cY, -cZ);
		Matrix s = m.matrizEscalonamento(sX, sY, sZ);
		Matrix t2 = m.matrizTranslação(-cX, -cY, -cZ);

		Matrix t1Win = m.matrizTranslação(-cXwin, -cYwin, -cZwin);
		Matrix t2Win = m.matrizTranslação(cXwin, cYwin, cZwin);

		// Aplicando escalonamento

		// Aplicando retorno para posição

		Matrix coord = new Matrix(1, 4);
		for (CoordenadasHomogeneas c : listaCoord) {
			coord.set(0, 0, c.getXD());
			coord.set(0, 1, c.getYD());
			coord.set(0, 2, c.getZD());
			coord.set(0, 3, 1.0);

			Matrix resultado = coord.times(t1);
			resultado = resultado.times(s);
			resultado = resultado.times(t2);

			c.setX(resultado.get(0, 0));
			c.setY(resultado.get(0, 1));
			c.setZ(resultado.get(0, 2));
		}
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			coord.set(0, 0, cW.getXD());
			coord.set(0, 1, cW.getYD());
			coord.set(0, 2, cW.getZD());
			coord.set(0, 3, 1.0);

			Matrix resultado = coord.times(t1Win);
			resultado = resultado.times(s);
			resultado = resultado.times(t2Win);

			cW.setX(resultado.get(0, 0));
			cW.setY(resultado.get(0, 1));
			cW.setZ(resultado.get(0, 2));
		}
	}

	public void rotacionarSe(double g) {
		CoordenadasHomogeneas co = this.centro();

		FabricaMatriz m = new FabricaMatriz();
		Matrix t1 = m.matrizTranslação(-co.getXD(), -co.getYD(), -co.getZD());
		Matrix t1Win = m.matrizTranslação(-this.centroWin().getXD(), -this.centroWin().getYD(), -this.centroWin().getZD());
		Matrix rx = m.matrizRotaçãoX(g);
		Matrix ry = m.matrizRotaçãoY(g);
		Matrix rz = m.matrizRotaçãoZ(g);
		Matrix t2 = m.matrizTranslação(co.getXD(), co.getYD(), co.getZD());
		Matrix t2Win = m.matrizTranslação(this.centroWin().getXD(), this.centroWin().getYD(), this.centroWin().getZD());


		Matrix coord = new Matrix(1, 4);
		for (CoordenadasHomogeneas c : listaCoord) {
			coord.set(0, 0, c.getXD());
			coord.set(0, 1, c.getYD());
			coord.set(0, 2, c.getZD());
			coord.set(0, 3, 1.0);

			Matrix resultado = coord.times(t1);
			resultado = resultado.times(rx);
			resultado = resultado.times(rz);
			resultado = resultado.times(ry);
			resultado = resultado.times(rz);
			resultado = resultado.times(rx);
			resultado = resultado.times(t2);

			c.setX(resultado.get(0, 0));
			c.setY(resultado.get(0, 1));
			c.setZ(resultado.get(0, 2));
		}
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			coord.set(0, 0, cW.getXD());
			coord.set(0, 1, cW.getYD());
			coord.set(0, 2, cW.getZD());
			coord.set(0, 3, 1.0);

			Matrix resultado = coord.times(t1Win);
			resultado = resultado.times(rx);
			resultado = resultado.times(rz);
			resultado = resultado.times(ry);
			resultado = resultado.times(rz);
			resultado = resultado.times(rx);
			resultado = resultado.times(t2Win);

			cW.setX(resultado.get(0, 0));
			cW.setY(resultado.get(0, 1));
			cW.setZ(resultado.get(0, 2));
		}

	}
}
