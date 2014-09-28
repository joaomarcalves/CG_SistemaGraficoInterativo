package modelo;

import java.awt.Color;
import java.util.ArrayList;

import Jama.Matrix;

public abstract class Objeto implements TipoObjeto {

	private static final int DIVISOES = 10;
	private String nome;
	private ArrayList<TipoCoordenadas> listaCoord;
	private ArrayList<TipoCoordenadasNormalizada> listaCoordWin = new ArrayList<TipoCoordenadasNormalizada>();

	private Color cor;

	public Objeto(String nome, ArrayList<TipoCoordenadas> listaCoord, Color cor) {
		construirCoordNorm(listaCoord);
		this.nome = nome;
		this.listaCoord = listaCoord;
		this.cor = cor;
	}

	private void construirCoordNorm(ArrayList<TipoCoordenadas> coordMundo) {
		// TODO Auto-generated method stub
		for (TipoCoordenadas cM : coordMundo) {
			listaCoordWin.add(new CoordenadasNorm(cM.getXD(), cM.getYD(), cM
					.getZD()));
		}

	}

	public static TipoObjeto criarPonto(String nome,
			TipoCoordenadas coordenadas, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<TipoCoordenadas> listaCoord = new ArrayList<TipoCoordenadas>();
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
			TipoCoordenadas coordenadas, TipoCoordenadas coordenadas2, Color cor) {
		// TODO Auto-generated method stub
		ArrayList<TipoCoordenadas> listaCoord = new ArrayList<TipoCoordenadas>();
		listaCoord.add(coordenadas);
		listaCoord.add(coordenadas2);

		TipoObjeto r = new Reta(nome, listaCoord, cor);
		r.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return r;
	}

	public static TipoObjeto criarPoligono(String nome,
			ArrayList<TipoCoordenadas> listCord, Color cor, boolean preenchido) {
		// TODO Auto-generated method stub
		TipoObjeto o = new Poligono(nome, listCord, cor, preenchido);
		o.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return o;
	}

	public ArrayList<TipoCoordenadasNormalizada> coordenadas() {
		return listaCoordWin;
	}

	public String coordenadasString() {
		String cS = "";
		for (TipoCoordenadas c : listaCoord) {
			cS = cS + "(" + c.getXD() + ", " + c.getYD() + ", " + c.getZD()
					+ ") ";
		}
		return cS;
	}

	public boolean preenchido() {
		return false;
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

		for (TipoCoordenadas c : listaCoord) {
			c.setX(c.getXD() + dX);
			c.setY(c.getYD() + dY);
		}
		for (TipoCoordenadas cW : listaCoordWin) {
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

		for (TipoCoordenadas cW : listaCoordWin) {
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

		for (TipoCoordenadas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();

			c.setX(((-cX * sX) + cX) + (sX * x));
			c.setY(((-cY * sY) + cY) + (sY * y));
		}

		double cXwin = this.centroWin().getXD();
		double cYwin = this.centroWin().getYD();

		for (TipoCoordenadasNormalizada cN : listaCoordWin) {
			double x = cN.getXD();
			double y = cN.getYD();

			cN.setX(((-cXwin * sX) + cXwin) + (sX * x));
			cN.setY(((-cYwin * sY) + cYwin) + (sY * y));
		}
	}

	public TipoCoordenadas centro() {
		double ccX = 0;
		double ccY = 0;
		double ccZ = 1;
		for (TipoCoordenadas c : listaCoord) {
			ccX += c.getXD();
			ccY += c.getYD();
			// ccZ += c.getZ();
		}
		ccX = ccX / listaCoord.size();
		ccY = ccY / listaCoord.size();
		return new CoordenadasHomogeneas(ccX, ccY, ccZ);
	}

	public TipoCoordenadasNormalizada centroWin() {
		double ccX = 0;
		double ccY = 0;
		double ccZ = 1;
		for (TipoCoordenadasNormalizada c : listaCoordWin) {
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

		TipoCoordenadas co = this.centro();
		TipoCoordenadasNormalizada coN = this.centroWin();

		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));

		for (TipoCoordenadas c : listaCoord) {
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

		for (TipoCoordenadasNormalizada c : listaCoordWin) {
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

		TipoCoordenadas co = new CoordenadasHomogeneas(0, 0, 1);

		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));

		for (TipoCoordenadas c : listaCoord) {
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

		TipoCoordenadas co = new CoordenadasHomogeneas(Double.parseDouble(eX),
				Double.parseDouble(eY), 1);

		double cX = co.getXD();
		double cY = co.getYD();
		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));

		for (TipoCoordenadas c : listaCoord) {
			double x = c.getXD();
			double y = c.getYD();

			coordenadaX = cX - cX * cosTeta + x * cosTeta - cY * senTeta + y
					* senTeta;
			coordenadaY = cY - cY * cosTeta + y * cosTeta + cX * senTeta - x
					* senTeta;

			c.setX(coordenadaX);
			c.setY(coordenadaY);
		}

		TipoCoordenadasNormalizada coN = new CoordenadasNorm(
				Double.parseDouble(eX), Double.parseDouble(eY), 1);

		double cXwin = coN.getXD();
		double cYwin = coN.getYD();

		for (TipoCoordenadasNormalizada cWin : listaCoordWin) {
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

	public void rotacionarEmCoordWin(double g, TipoCoordenadas c) {

		double coordenadaX;
		double coordenadaY;

		double cosTeta = Math.cos(Math.toRadians(g));
		double senTeta = Math.sin(Math.toRadians(g));

		for (TipoCoordenadas cW : listaCoordWin) {
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
			ArrayList<TipoCoordenadas> listCord, Color cor2, String numPontos) {
		// TODO Auto-generated method stub
		ArrayList<TipoCoordenadas> novaListCoord = new ArrayList<TipoCoordenadas>();
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
			novaListCoord.add(new CoordenadasNorm(pX, pY, 1));
		}
		TipoObjeto c = new Curva(nome, novaListCoord, cor2);
		c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return c;
	}

	public static TipoObjeto criarCurvaSpline(String nome,
			ArrayList<SemiPonto> listaCamposDeCoordenadas, Color cor) {
		// TODO Auto-generated method stub

		ArrayList<TipoCoordenadas> listCoord = new ArrayList<TipoCoordenadas>();

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

		double[][] g = { { -1.0 / 6.0, 3.0 / 6.0, -3.0 / 6.0, 1.0 / 6.0 },
				{ 3.0 / 6.0, -1.0, 3.0 / 6.0, 0.0 },
				{ -3.0 / 6.0, 0.0, 3.0 / 6.0, 0.0 },
				{ 1.0 / 6.0, 4.0 / 6.0, 1.0 / 6.0, 0.0 } };
		Matrix gbs = new Matrix(g);

		ArrayList<TipoCoordenadas> ptsCurva = new ArrayList<TipoCoordenadas>();

		for (int i = 0; i < listCoord.size() - 3; i++) {
			for (int j = 0; j < matrizGeo.getRowDimension(); j++) {
				matrizGeo.set(j, 0, listCoord.get(j + i).getXD());
				matrizGeo.set(j, 1, listCoord.get(j + i).getYD());
				matrizGeo.set(j, 2, listCoord.get(j + i).getZD());
			}

			Matrix coef = gbs.times(matrizGeo);
			/*
			 * System.out.println("matriz coef ::"); for (int p = 0; p <
			 * coef.getRowDimension(); p++) { for (int d = 0; d <
			 * coef.getColumnDimension(); d++) { System.out.println(coef.get(p,
			 * d)); } } System.out.println("matriz coef ^^");
			 */

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

			TipoCoordenadas p = listCoord.get(i);
			ArrayList<TipoCoordenadas> ptsSegCurva = forwardDifrencies(p, f0,
					deltaF0, delta2F0, delta3F0);

			for (TipoCoordenadas c : ptsSegCurva) {
				ptsCurva.add(c);
			}
		}
		//

		TipoObjeto c = new Curva(nome, ptsCurva, cor);
		c.rotacionarEmCoordWin(Janela.getInstance().anguloAtual(), Janela
				.getInstance().centroWin());
		return c;
	}

	private static ArrayList<TipoCoordenadas> forwardDifrencies(
			TipoCoordenadas coord, double[] f0, double[] deltaF0,
			double[] delta2F0, double[] delta3F0) {
		// TODO Auto-generated method stub

		ArrayList<TipoCoordenadas> ptsCurva = new ArrayList<TipoCoordenadas>();

		for (int i = 0; i < DIVISOES; i++) {
			coord.setX(f0[0]);
			coord.setY(f0[1]);
			coord.setZ(1);

			ptsCurva.add(new CoordenadasNorm(f0[0], f0[1], 1));
			System.out.println("coord x: "+coord.getXD());
			System.out.println("coord y: "+coord.getYD());


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
		
		for (TipoCoordenadas c : ptsCurva) {
			System.out.println("pts curva x: "+c.getXD());
			System.out.println("pts curva y: "+c.getYD());
		}
		
		return ptsCurva;
	}
}
