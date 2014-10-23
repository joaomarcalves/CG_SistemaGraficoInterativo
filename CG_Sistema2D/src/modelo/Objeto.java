package modelo;

import java.awt.Color;
import java.util.ArrayList;

import Jama.Matrix;

public abstract class Objeto implements TipoObjeto {
	protected static final int DIVISOES = 10;
	protected String nome;
	protected ArrayList<CoordenadasHomogeneas> listaCoord;
	protected ArrayList<CoordenadasHomogeneas> listaCoordWin = new ArrayList<CoordenadasHomogeneas>();
	private Color cor;

	public Objeto(String nome, ArrayList<CoordenadasHomogeneas> listaCoord2,
			Color cor) {
		construirCoordNorm(listaCoord2);
		this.nome = nome;
		this.listaCoord = listaCoord2;
		this.cor = cor;
	}

	private void construirCoordNorm(ArrayList<CoordenadasHomogeneas> coordMundo) {
		// TODO Auto-generated method stub
		for (CoordenadasHomogeneas cM : coordMundo) {
			listaCoordWin.add(new CoordenadasNorm(cM.getXD(), cM.getYD(), cM
					.getZD()));
		}
	}

	public static TipoObjeto criarPonto(String nome,
			CoordenadasHomogeneas coordenadas, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> listaCoord = new ArrayList<CoordenadasHomogeneas>();
		listaCoord.add(coordenadas);
		TipoObjeto p = new Ponto(nome, listaCoord, cor);
		p.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return p;
	}

	public String nome() {
		return nome;
	}

	public static TipoObjeto criarReta(String nome,
			CoordenadasHomogeneas coordenadas,
			CoordenadasHomogeneas coordenadas2, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> listaCoord = new ArrayList<CoordenadasHomogeneas>();
		listaCoord.add(coordenadas);
		listaCoord.add(coordenadas2);
		TipoObjeto r = new Reta(nome, listaCoord, cor);
		r.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return r;
	}

	public static TipoObjeto criarPoligono(String nome,
			ArrayList<CoordenadasHomogeneas> listCord, Color cor,
			boolean preenchido) {
		// TODO Auto-generated method stub
		TipoObjeto o = new Poligono(nome, listCord, cor, preenchido);
		o.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return o;
	}

	public ArrayList<CoordenadasHomogeneas> coordenadas() {
		return listaCoordWin;
	}

	public String coordenadasString() {
		String cS = "";
		for (CoordenadasHomogeneas c : listaCoord) {
			cS = cS + "(" + c.getXD() + ", " + c.getYD() + ", " + c.getZD()
					+ ") ";
		}
		return cS;
	}

	public boolean preenchido() {
		return false;
	}

	public void moverSe(double dX, double dY, double dZ) {
		FabricaMatriz t1 = new FabricaMatriz();
		Matrix t = t1.matrizTranslação(dX, dY, dZ);
		Matrix coord = new Matrix(1, 4);
		for (CoordenadasHomogeneas c : listaCoord) {
			coord.set(0, 0, c.getXD());
			coord.set(0, 1, c.getYD());
			coord.set(0, 2, c.getZD());
			coord.set(0, 3, 1.0);
			Matrix resultado = coord.times(t);
			c.setX(resultado.get(0, 0));
			c.setY(resultado.get(0, 1));
			c.setZ(resultado.get(0, 2));
		}
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			coord.set(0, 0, cW.getXD());
			coord.set(0, 1, cW.getYD());
			coord.set(0, 2, cW.getZD());
			coord.set(0, 3, 1.0);
			Matrix resultado = coord.times(t);
			cW.setX(resultado.get(0, 0));
			cW.setY(resultado.get(0, 1));
			cW.setZ(resultado.get(0, 2));
		}
	}

	@Override
	public void moverSe(Direcoes dir) {
		// TODO Auto-generated method stub
		double dX = 0, dY = 0;
		switch (dir) {
		case ESQUERDA:
			dX = -10;
			dY = 0;
			break;
		case CIMA:
			dX = 0;
			dY = 10;
			break;
		case BAIXO:
			dX = 0;
			dY = -10;
			break;
		case DIREITA:
			dX = 10;
			dY = 0;
		}
		/*
		 * |1 0 0| |0 1 0| * |x y 1| = |(x+Dx) (y+Dy) 1| |Dx Dy 1|
		 */
		for (CoordenadasHomogeneas c : listaCoord) {
			c.setX(c.getXD() + dX);
			c.setY(c.getYD() + dY);
		}
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			cW.setX(cW.getXD() + dX);
			cW.setY(cW.getYD() + dY);
		}
	}

	public void moverSeWin(Direcoes dir) {
		// TODO Auto-generated method stub
		double dXvertical = 0, dYvertical = 0, dXhorizontal = 0, dYhorizontal = 0, dX = 0, dY = 0;
		dXvertical = Janela.getInstance().getVetorVertical().p2().getXD();
		dYvertical = Janela.getInstance().getVetorVertical().p2().getYD();
		dXhorizontal = Janela.getInstance().getVetorHorizontal().p2().getXD();
		dYhorizontal = Janela.getInstance().getVetorHorizontal().p2().getYD();
		switch (dir) {
		case ESQUERDA:
			dX = -dXhorizontal;
			dY = -dYhorizontal;
			break;
		case CIMA:
			dX = dXvertical;
			dY = dYvertical;
			break;
		case BAIXO:
			dX = -dXvertical;
			dY = -dYvertical;
			break;
		case DIREITA:
			dX = dXhorizontal;
			dY = dYhorizontal;
			break;
		}
		/*
		 * |1 0 0| |0 1 0| * |x y 1| = |(x+Dx) (y+Dy) 1| |Dx Dy 1|
		 */
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			cW.setX(cW.getXD() + dX);
			cW.setY(cW.getYD() + dY);
		}
	}

	@Override
	public void escalonarSe(Escalonamento tipo) {
		// TODO Auto-generated method stub
		double sX = 1, sY = 1;
		double cX = this.centro().getXD();
		double cY = this.centro().getYD();
		switch (tipo) {
		case AUMENTAR:
			sX = 1.1;
			sY = 1.1;
			break;
		case DIMINUIR:
			sX = 0.9;
			sY = 0.9;
			break;
		}
		/*
		 * Aplicando o deslocamento para origem e depois o escalonamento | 1 0
		 * 0| |Sx 0 0| |Sx 0 0| | 0 1 0| * |0 Sy 0| = |0 Sy 0| |-Cx -Cy 1| |0 0
		 * 1| |-CxSx -CySy 1|
		 * 
		 * Aplicando o deslocamento de volta para o ponto original |Sx 0 0| | 1
		 * 0 0| |Sx 0 0| |0 Sy 0| * | 0 1 0| = |0 Sy 0| |-CxSx -CySy 1| | Cx Cy
		 * 1| |-CxSx+Cx -CySy+Cy 1|
		 * 
		 * |Sx 0 0| |x y 1| * |0 Sy 0| = |((-Cx*Sx)+Cx)+(Sx*x)
		 * ((-Cy*Sy)+Cy)+(Sy*y) 1| |-CxSx+Cx -CySy+Cy 1|
		 */
		for (CoordenadasHomogeneas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();
			c.setX(((-cX * sX) + cX) + (sX * x));
			c.setY(((-cY * sY) + cY) + (sY * y));
		}
		double cXwin = this.centroWin().getXD();
		double cYwin = this.centroWin().getYD();
		for (CoordenadasHomogeneas cN : listaCoordWin) {
			double x = cN.getXD();
			double y = cN.getYD();
			cN.setX(((-cXwin * sX) + cXwin) + (sX * x));
			cN.setY(((-cYwin * sY) + cYwin) + (sY * y));
		}
	}

	public CoordenadasHomogeneas centro() {
		double ccX = 0;
		double ccY = 0;
		double ccZ = 1;
		for (CoordenadasHomogeneas c : listaCoord) {
			ccX += c.getXD();
			ccY += c.getYD();
			ccZ += c.getZD();
		}
		ccX = ccX / listaCoord.size();
		ccY = ccY / listaCoord.size();
		ccZ = ccZ / listaCoord.size();
		return new CoordenadasHomogeneas(ccX, ccY, ccZ);
	}

	public CoordenadasHomogeneas centroWin() {
		double ccX = 0;
		double ccY = 0;
		double ccZ = 1;
		for (CoordenadasHomogeneas c : listaCoordWin) {
			ccX += c.getXD();
			ccY += c.getYD();
			// ccZ += c.getZ();
		}
		ccX = ccX / listaCoordWin.size();
		ccY = ccY / listaCoordWin.size();
		return new CoordenadasNorm(ccX, ccY, ccZ);
	}

	public Color cor() {
		return cor;
	}

	public void rotacionarSe(double g) {
		double coordenadaX;
		double coordenadaY;
		CoordenadasHomogeneas co = this.centro();
		CoordenadasHomogeneas coN = this.centroWin();
		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));
		for (CoordenadasHomogeneas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();
			coordenadaX = cX - cX * cosTeta + x * cosTeta - cY * senTeta + y
					* senTeta;
			coordenadaY = cY - cY * cosTeta + y * cosTeta + cX * senTeta - x
					* senTeta;
			c.setX(coordenadaX);
			c.setY(coordenadaY);
		}
		double cXwin = coN.getXD();
		double cYwin = coN.getYD();
		for (CoordenadasHomogeneas c : listaCoordWin) {
			double x = c.getXD();
			double y = c.getYD();
			coordenadaX = cXwin - cXwin * cosTeta + x * cosTeta - cYwin
					* senTeta + y * senTeta;
			coordenadaY = cYwin - cYwin * cosTeta + y * cosTeta + cXwin
					* senTeta - x * senTeta;
			c.setX(coordenadaX);
			c.setY(coordenadaY);
		}
	}

	public void rotacionarOrigem(double g) {
		double coordenadaX;
		double coordenadaY;
		CoordenadasHomogeneas co = new CoordenadasHomogeneas(0, 0, 1);
		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));
		for (CoordenadasHomogeneas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();
			coordenadaX = cX - cX * cosTeta + x * cosTeta - cY * senTeta + y
					* senTeta;
			coordenadaY = cY - cY * cosTeta + y * cosTeta + cX * senTeta - x
					* senTeta;
			c.setX(coordenadaX);
			c.setY(coordenadaY);
		}
		rotacionarEmCoordWin(g, Janela.getInstance().centroWin());
	}

	public void rotacionarAoRedorPto(double g, String eX, String eY) {
		double coordenadaX;
		double coordenadaY;
		CoordenadasHomogeneas co = new CoordenadasHomogeneas(
				Double.parseDouble(eX), Double.parseDouble(eY), 1);
		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));
		for (CoordenadasHomogeneas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();
			coordenadaX = cX - cX * cosTeta + x * cosTeta - cY * senTeta + y
					* senTeta;
			coordenadaY = cY - cY * cosTeta + y * cosTeta + cX * senTeta - x
					* senTeta;
			c.setX(coordenadaX);
			c.setY(coordenadaY);
		}
		CoordenadasHomogeneas coN = new CoordenadasNorm(Double.parseDouble(eX),
				Double.parseDouble(eY), 1);
		double cXwin = coN.getXD();
		double cYwin = coN.getYD();
		for (CoordenadasHomogeneas cWin : listaCoordWin) {
			double x = cWin.getXD();
			double y = cWin.getYD();
			coordenadaX = cXwin - cXwin * cosTeta + x * cosTeta - cYwin
					* senTeta + y * senTeta;
			coordenadaY = cYwin - cYwin * cosTeta + y * cosTeta + cXwin
					* senTeta - x * senTeta;
			cWin.setX(coordenadaX);
			cWin.setY(coordenadaY);
		}
	}

	public void rotacionarEmCoordWin(double g, CoordenadasHomogeneas c) {
		double coordenadaX;
		double coordenadaY;
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));
		for (CoordenadasHomogeneas cW : listaCoordWin) {
			double x = cW.getXD();
			double y = cW.getYD();
			coordenadaX = c.getXD() - c.getXD() * cosTeta + x * cosTeta
					- c.getYD() * senTeta + y * senTeta;
			coordenadaY = c.getYD() - c.getYD() * cosTeta + y * cosTeta
					+ c.getXD() * senTeta - x * senTeta;
			cW.setX(coordenadaX);
			cW.setY(coordenadaY);
		}
	}

	public static TipoObjeto criarCurvaBezier(String nome,
			ArrayList<CoordenadasHomogeneas> listCord, Color cor2,
			String numPontos) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> novaListCoord = new ArrayList<CoordenadasHomogeneas>();
		double limite = (1.0 / Double.parseDouble(numPontos));
		novaListCoord.add(new CoordenadasNorm(listCord.get(0).getXD(), listCord
				.get(0).getYD(), 1));
		for (double i = limite; i <= 1 + limite; i = (i + limite)) {
			// px=(1-t).^3*x(1)+3*t.*(1-t).^2*x(2)+3*t.^2.*(1-t)*x(3)+t.^3*x(4);
			double pX = Math.pow((1 - i), 3) * listCord.get(0).getXD() + 3 * i
					* Math.pow((1 - i), 2) * listCord.get(1).getXD() + 3
					* Math.pow(i, 2) * (1 - i) * listCord.get(2).getXD()
					+ Math.pow(i, 3) * listCord.get(3).getXD();
			double pY = Math.pow((1 - i), 3) * listCord.get(0).getYD() + 3 * i
					* Math.pow((1 - i), 2) * listCord.get(1).getYD() + 3
					* Math.pow(i, 2) * (1 - i) * listCord.get(2).getYD()
					+ Math.pow(i, 3) * listCord.get(3).getYD();
			novaListCoord.add(new CoordenadasHomogeneas(pX, pY, 1));
		}
		TipoObjeto c = new Curva(nome, novaListCoord, cor2);
		c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return c;
	}

	public static TipoObjeto criarCurvaSpline(String nome,
			ArrayList<SemiPonto> listaCamposDeCoordenadas, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> listCoord = new ArrayList<CoordenadasHomogeneas>();
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
		FabricaMatriz fab = new FabricaMatriz();
		Matrix gbs = fab.matrizGBS();
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
		//
		TipoObjeto c = new Curva(nome, ptsCurva, cor);
		c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return c;
	}

	protected static ArrayList<CoordenadasHomogeneas> forwardDiferencies(
			CoordenadasHomogeneas p, double[] f0, double[] deltaF0,
			double[] delta2F0, double[] delta3F0) {
		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> ptsCurva = new ArrayList<CoordenadasHomogeneas>();
		for (int i = 0; i < DIVISOES; i++) {
			p.setX(f0[0]);
			p.setY(f0[1]);
			p.setZ(1);
			ptsCurva.add(new CoordenadasHomogeneas(f0[0], f0[1], 1));
			f0[0] += deltaF0[0];
			deltaF0[0] += delta2F0[0];
			delta2F0[0] += delta3F0[0];
			f0[1] += deltaF0[1];
			deltaF0[1] += delta2F0[1];
			delta2F0[1] += delta3F0[1];
			f0[2] += deltaF0[2];
			deltaF0[2] += delta2F0[2];
			delta3F0[2] += delta3F0[1];
		}
		return ptsCurva;
	}

	public void rotacionarSe(double parseDouble, Eixo x) {
		System.out.println("Ooops");
	}

	public void incluirCurvas(ArrayList<TipoObjeto> listaCurvas1,
			ArrayList<TipoObjeto> listaCurvas2) {
		System.out.println("Ooops");
	}

	public ArrayList<TipoObjeto> curva1() {
		return null;
	}

	public ArrayList<TipoObjeto> curva2() {
		return null;
	}
}