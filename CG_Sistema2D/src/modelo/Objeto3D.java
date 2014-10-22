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
			CoordenadasHomogeneas coordenadas,
			CoordenadasHomogeneas coordenadas2, Color cor) {
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
			ArrayList<CoordenadasHomogeneas> listCord, Color cor,
			boolean preenchido) {
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

	public void rotacionarSe(double graus, Eixo e) {
		CoordenadasHomogeneas co = this.centro();
		CoordenadasHomogeneas coW = this.centroWin();
		FabricaMatriz m = new FabricaMatriz();
		Matrix t1 = m.matrizTranslação(-co.getXD(), -co.getYD(), -co.getZD());
		Matrix t1Win = m.matrizTranslação(-coW.getXD(), -coW.getYD(),
				-coW.getZD());
		Matrix rx = m.matrizRotaçãoX(graus);
		Matrix t2 = m.matrizTranslação(co.getXD(), co.getYD(), co.getZD());
		Matrix t2Win = m
				.matrizTranslação(coW.getXD(), coW.getYD(), coW.getZD());

		Matrix coord = new Matrix(1, 4);
		if (e == Eixo.X) {
			for (CoordenadasHomogeneas c : listaCoord) {
				coord.set(0, 0, c.getXD());
				coord.set(0, 1, c.getYD());
				coord.set(0, 2, c.getZD());
				coord.set(0, 3, 1.0);

				Matrix resultado = coord.times(t1);
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
				resultado = resultado.times(t2Win);

				cW.setX(resultado.get(0, 0));
				cW.setY(resultado.get(0, 1));
				cW.setZ(resultado.get(0, 2));
			}
		}

		if (e == null) {
			// atualizarCoordenadaObjeto(t1, rx, ry, rz, t2, coord);
			// atualizarCoordenadaJanela(t1Win, rx, ry, rz, t2Win, coord);
		}
	}

	public static TipoObjeto criarSpfSpline(String nome,
			ArrayList<ArrayList<SemiPonto>> curvasCtrl, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<TipoObjeto> listaCurvas1 = new ArrayList<TipoObjeto>();
		ArrayList<TipoObjeto> listaCurvas2 = new ArrayList<TipoObjeto>();

		ArrayList<CoordenadasHomogeneas> listCoord = new ArrayList<CoordenadasHomogeneas>();
		int[] passo = new int[curvasCtrl.size()];
		for (int i = 0; i < curvasCtrl.size(); i++) {
			ArrayList<CoordenadasHomogeneas> ptsCurva = criarMesh(listCoord,
					curvasCtrl.get(i));
			TipoObjeto c = new Curva(nome, ptsCurva, cor);
			c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
					.getInstance().centroWin());
			listaCurvas1.add(c);
			passo[i] = (c.coordenadas().size()) / (curvasCtrl.size() - 1);
		}
		ArrayList<ArrayList<CoordenadasHomogeneas>> listaSPCurvaParcial = new ArrayList<ArrayList<CoordenadasHomogeneas>>();
		CoordenadasHomogeneas[][] lSPCP;
		for (int i = 0; i < listaCurvas1.size(); i++) {
			for (int j = 0; j <= listaCurvas1.get(i).coordenadas().size(); j = j
					+ passo[i]) {
				lSPCP = new CoordenadasHomogeneas[listaCurvas1.size()][listaCurvas1.get(i).coordenadas().size()];
				CoordenadasHomogeneas ptoParcial = listaCurvas1.get(i).coordenadas().get(j);
				// rever
				
				for (int k = 0; k < listaCurvas1.size(); k++) {
					listaSPCurvaParcial.get(i).add(k, ptoParcial);
				}
			}
		}
		for (int i = 0; i < curvasCtrl.size(); i++) {
			ArrayList<CoordenadasHomogeneas> ptsCurva = criarMesh2(listCoord,
					listaSPCurvaParcial.get(i));
			TipoObjeto c = new Curva(nome, ptsCurva, cor);
			c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
					.getInstance().centroWin());
			listaCurvas2.add(c);
		}
		TipoObjeto s = new Superficie(nome, null, cor);
		s.incluirCurvas(listaCurvas1, listaCurvas2);
		return s;
	}

	private static ArrayList<CoordenadasHomogeneas> criarMesh2(
			ArrayList<CoordenadasHomogeneas> listCoord,
			ArrayList<CoordenadasHomogeneas> listaSPCurvaParcial) {
		// TODO Auto-generated method stub
		for (CoordenadasHomogeneas semiPonto : listaSPCurvaParcial) {
			listCoord.add(new CoordenadasNorm(semiPonto.getXD(), semiPonto
					.getYD(), 1.0));
		}
		double[][] m = new double[4][3];
		Matrix matrizGeo = new Matrix(m);
		double[] f0 = new double[3];
		double[] deltaF0 = new double[3];
		double[] delta2F0 = new double[3];
		double[] delta3F0 = new double[3];

		FabricaMatriz ma = new FabricaMatriz();

		Matrix gbs = ma.matrizGBS();

		ArrayList<CoordenadasHomogeneas> ptsCurva = new ArrayList<CoordenadasHomogeneas>();

		for (int i = 0; i < listCoord.size() - 3; i++) {
			for (int j = 0; j < matrizGeo.getRowDimension(); j++) {
				matrizGeo.set(j, 0, listCoord.get(j + i).getXD());
				matrizGeo.set(j, 1, listCoord.get(j + i).getYD());
				matrizGeo.set(j, 2, listCoord.get(j + i).getZD());
			}

			Matrix coef = gbs.times(matrizGeo);

			double delta = (1.0 / DIVISOES);

			for (int k = 0; k < 3; k++) {
				f0[k] = coef.get(3, k);
				deltaF0[k] = coef.get(0, k) * (Math.pow(delta, 3))
						+ coef.get(1, k) * (Math.pow(delta, 2))
						+ coef.get(2, k) * delta;
				delta2F0[k] = 6 * coef.get(0, k) * (Math.pow(delta, 3)) + 2
						* coef.get(1, k) * (Math.pow(delta, 2));
				delta3F0[k] = 6 * coef.get(0, k) * (Math.pow(delta, 3));

			}

			CoordenadasHomogeneas p = listCoord.get(i);
			ArrayList<CoordenadasHomogeneas> ptsSegCurva = forwardDiferencies(
					p, f0, deltaF0, delta2F0, delta3F0);

			for (CoordenadasHomogeneas c : ptsSegCurva) {
				ptsCurva.add(c);
			}
		}
		return ptsCurva;
	}

	protected static ArrayList<CoordenadasHomogeneas> criarMesh(
			ArrayList<CoordenadasHomogeneas> listCoord,
			ArrayList<SemiPonto> listaCamposDeCoordenadas) {
		for (SemiPonto semiPonto : listaCamposDeCoordenadas) {
			listCoord.add(new CoordenadasNorm(Double.parseDouble(semiPonto
					.gettFx().getText()), Double.parseDouble(semiPonto.gettFy()
					.getText()), 1.0));
		}
		double[][] m = new double[4][3];
		Matrix matrizGeo = new Matrix(m);
		double[] f0 = new double[3];
		double[] deltaF0 = new double[3];
		double[] delta2F0 = new double[3];
		double[] delta3F0 = new double[3];

		FabricaMatriz ma = new FabricaMatriz();

		Matrix gbs = ma.matrizGBS();

		ArrayList<CoordenadasHomogeneas> ptsCurva = new ArrayList<CoordenadasHomogeneas>();

		for (int i = 0; i < listCoord.size() - 3; i++) {
			for (int j = 0; j < matrizGeo.getRowDimension(); j++) {
				matrizGeo.set(j, 0, listCoord.get(j + i).getXD());
				matrizGeo.set(j, 1, listCoord.get(j + i).getYD());
				matrizGeo.set(j, 2, listCoord.get(j + i).getZD());
			}

			Matrix coef = gbs.times(matrizGeo);

			double delta = (1.0 / DIVISOES);

			for (int k = 0; k < 3; k++) {
				f0[k] = coef.get(3, k);
				deltaF0[k] = coef.get(0, k) * (Math.pow(delta, 3))
						+ coef.get(1, k) * (Math.pow(delta, 2))
						+ coef.get(2, k) * delta;
				delta2F0[k] = 6 * coef.get(0, k) * (Math.pow(delta, 3)) + 2
						* coef.get(1, k) * (Math.pow(delta, 2));
				delta3F0[k] = 6 * coef.get(0, k) * (Math.pow(delta, 3));

			}

			CoordenadasHomogeneas p = listCoord.get(i);
			ArrayList<CoordenadasHomogeneas> ptsSegCurva = forwardDiferencies(
					p, f0, deltaF0, delta2F0, delta3F0);

			for (CoordenadasHomogeneas c : ptsSegCurva) {
				ptsCurva.add(c);
			}
		}
		return ptsCurva;
	}

}
