package visao;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import modelo.CoordenadasHomogeneas;
import modelo.FabricaMatriz;
import modelo.Janela;
import modelo.Ponto3D;
import modelo.TipoClipador;
import modelo.TipoObjeto;
import modelo.Transformadora;
import Jama.Matrix;

public class CanvasViewPort extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TipoObjeto> objetos;
	private double xMax;
	private double xMin;
	private double yMax;
	private double yMin;
	private Polygon areaDesenhavel;
	private boolean clipping;
	private Ponto3D cop;

	public void construirViewPort(ArrayList<TipoObjeto> objetos) {
		// TODO Auto-generated constructor stub
		clipping = true;
		xMax = Janela.getInstance().xMax();
		yMax = Janela.getInstance().yMax();
		xMin = Janela.getInstance().xMin();
		yMin = Janela.getInstance().yMin();
		this.objetos = objetos;
		this.setSize(new Dimension((int) (xMax - xMin), (int) (yMax - yMin)));
		this.setBackground(Color.BLACK);
		ArrayList<CoordenadasHomogeneas> lcop = new ArrayList<CoordenadasHomogeneas>();
		lcop.add(new CoordenadasHomogeneas(0, 0, -100));
		cop = new Ponto3D("COP", lcop, null);
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		// Exibindo área desenhável
		g.setColor(Color.WHITE);

		int[] subCanvasX1 = { -310, -310, 310, 310 };
		int[] subCanvasY1 = { -310, 310, 310, -310 };
		areaDesenhavel = new Polygon(subCanvasX1, subCanvasY1, 4);

		int[] subCanvasX = { 30, 30, 650, 650 };
		int[] subCanvasY = { 30, 650, 650, 30 };
		Polygon quadradoBranco = new Polygon(subCanvasX, subCanvasY, 4);
		g.drawPolygon(quadradoBranco);

		// Exibindo os objetos
		desenharObjetos(g);
	}

	private void desenharObjetos(Graphics g) {
		for (TipoObjeto o : objetos) {
			ArrayList<CoordenadasHomogeneas> lC = o.coordenadas();
			g.setColor(o.cor());
			double[] xPoints = new double[lC.size()];
			double[] yPoints = new double[lC.size()];
			double[] zPoints = new double[lC.size()];
			for (CoordenadasHomogeneas c : lC) {
				xPoints[lC.indexOf(c)] = c.getXD();
				yPoints[lC.indexOf(c)] = c.getYD();
				zPoints[lC.indexOf(c)] = c.getZD();
			}

			if (o.nome().startsWith("P")) {
				if (o.getClass().getSimpleName().equals("Ponto")) {
					clipparPonto(g, xPoints, yPoints, zPoints);
				} else
					projetarPonto3D(g, xPoints, yPoints, zPoints);
			} else if (o.nome().startsWith("R")) {
				if (o.getClass().getSimpleName().equals("Reta"))
					clipparReta(g, xPoints, yPoints);
				else
					// Reta 3D
					projetarReta3D(g, xPoints, yPoints, zPoints);
			} else if (o.nome().startsWith("O")) {
				if (clipping) {
					clipparPoligono(g, xPoints, yPoints, o.preenchido());
				} else {
					int[] xPointsT = new int[xPoints.length];
					int[] yPointsT = new int[yPoints.length];

					for (int j = 0; j < yPointsT.length; j++) {
						xPointsT[j] = (int) new Transformadora()
								.transVPx(xPoints[j]);
						yPointsT[j] = (int) new Transformadora()
								.transVPy(yPoints[j]);
					}
					g.drawPolygon(xPointsT, yPointsT, xPointsT.length);
					if (o.preenchido()) {
						g.fillPolygon(xPointsT, yPointsT, xPointsT.length);
					}
				}
			} else if (clipping) {
				clipparCurvas(g, xPoints, yPoints);
			} else {
				int[] xPointsT = new int[xPoints.length];
				int[] yPointsT = new int[yPoints.length];

				for (int j = 0; j < yPointsT.length; j++) {
					xPointsT[j] = (int) new Transformadora()
							.transVPx(xPoints[j]);
					yPointsT[j] = (int) new Transformadora()
							.transVPy(yPoints[j]);
				}
				g.drawPolygon(xPointsT, yPointsT, xPointsT.length);
				g.drawPolyline(xPointsT, yPointsT, xPoints.length);
			}
		}
	}

	private void projetarReta3D(Graphics g, double[] xPoints, double[] yPoints,
			double[] zPoints) {

		// TODO Auto-generated method stub
		double d = Math.abs(cop.getZ());
		Matrix mper = new FabricaMatriz().matrizMper(d);

		Matrix p1 = new Matrix(4, 1);
		p1.set(0, 0, xPoints[0]);
		p1.set(1, 0, yPoints[0]);
		p1.set(2, 0, zPoints[0]);
		p1.set(3, 0, 1);
		Matrix p2 = new Matrix(4, 1);
		p2.set(0, 0, xPoints[1]);
		p2.set(1, 0, yPoints[1]);
		p2.set(2, 0, zPoints[1]);
		p2.set(3, 0, 1);

		Matrix pW1 = mper.times(p1);

		xPoints[0] = (int) (pW1.get(0, 0) / (pW1.get(2, 0) / d));
		yPoints[0] = (int) (pW1.get(1, 0) / (pW1.get(2, 0) / d));
		zPoints[0] = (int) (d);

		xPoints[0] = (int) (pW1.get(0, 0));
		yPoints[0] = (int) (pW1.get(1, 0));
		zPoints[0] = (int) (pW1.get(2, 0));

		Matrix pW2 = mper.times(p2);
		System.out.println(pW2.get(2, 0));
		System.out.println((pW2.get(0, 0) / (pW2.get(2, 0) / d)));
		xPoints[1] = (int) (pW2.get(0, 0) / (pW2.get(2, 0) / d));
		yPoints[1] = (int) (pW2.get(1, 0) / (pW2.get(2, 0) / d));
		zPoints[1] = (int) (d);

		clipparReta(g, xPoints, yPoints);
	}

	private void projetarPonto3D(Graphics g, double[] xPoints,
			double[] yPoints, double[] zPoints) {
		// TODO Auto-generated method stub
		double d = Math.abs(cop.getZ());
		Matrix mper = new FabricaMatriz().matrizMper(d);
		System.out.println("velho x: " + xPoints[0] + " y: " + yPoints[0]);

		Matrix p1 = new Matrix(4, 1);
		p1.set(0, 0, xPoints[0]);
		p1.set(1, 0, yPoints[0]);
		p1.set(2, 0, zPoints[0]);
		p1.set(3, 0, 1);

		Matrix pW1 = mper.times(p1);
		
		for (int i = 0; i < pW1.getColumnDimension(); i++) {
			for (int j = 0; j < pW1.getRowDimension(); j++) {
				System.out.println(pW1.get(j, i));
			}
			System.out.println("");
		}

		xPoints[0] = (int) (pW1.get(0, 0) / (pW1.get(2, 0) / d));
		yPoints[0] = (int) (pW1.get(1, 0) / (pW1.get(2, 0) / d));
		zPoints[0] = (int) (d);

		System.out.println("novo x: " + xPoints[0] + " y: " + yPoints[0]);

		clipparPonto(g, xPoints, yPoints, zPoints);

	}

	private void clipparPoligono(Graphics g, double[] xPoints,
			double[] yPoints, boolean preenchido) {
		// TODO Auto-generated method stub
		System.out
				.println("Fazendo Clipping da polígono usando o algoritmo de Liang Barsky");
		ArrayList<double[]> segmentosX = new ArrayList<double[]>();
		ArrayList<double[]> segmentosY = new ArrayList<double[]>();
		for (int i = 0; i < xPoints.length; i++) {
			if (i != (xPoints.length - 1)) {
				double[] segmentoRetaX = { xPoints[i], xPoints[i + 1] };
				double[] segmentoRetaY = { yPoints[i], yPoints[i + 1] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			} else {
				double[] segmentoRetaX = { xPoints[i], xPoints[0] };
				double[] segmentoRetaY = { yPoints[i], yPoints[0] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			}
		}
		double[][] pontos = null;
		ArrayList<Double> newX = new ArrayList<Double>();
		ArrayList<Double> newY = new ArrayList<Double>();
		for (int i = 0; i < segmentosX.size(); i++) {
			TipoClipador lB = new LiangBarsky(segmentosX.get(i),
					segmentosY.get(i), areaDesenhavel);
			pontos = lB.cliparPoligono(g, clipping);
			if (pontos != null) {
				for (int j = 0; j < pontos[0].length; j++) {
					newX.add(pontos[0][j]);
					newY.add(pontos[1][j]);
				}

				int[] xPointsT = new int[pontos[0].length];
				int[] yPointsT = new int[pontos[1].length];

				for (int j = 0; j < yPointsT.length; j++) {
					xPointsT[j] = (int) new Transformadora()
							.transVPx(pontos[0][j]);
					yPointsT[j] = (int) new Transformadora()
							.transVPy(pontos[1][j]);
				}
				g.drawPolygon(xPointsT, yPointsT, pontos[0].length);
			}
		}

		int[] ptosX = new int[newX.size() * 2];
		int[] ptosY = new int[newX.size() * 2];

		for (int i = 0; i < newX.size(); i++) {
			ptosX[i] = (int) new Transformadora().transVPx(newX.get(i));
			ptosY[i] = (int) new Transformadora().transVPy(newY.get(i));
		}
		if (preenchido) {
			g.fillPolygon(ptosX, ptosY, ptosX.length);
		}

	}

	private void clipparCurvas(Graphics g, double[] xPoints, double[] yPoints) {
		// TODO Auto-generated method stub
		System.out
				.println("Fazendo Clipping da curva usando o algoritmo de Liang Barsky");

		ArrayList<double[]> segmentosX = new ArrayList<double[]>();
		ArrayList<double[]> segmentosY = new ArrayList<double[]>();

		for (int i = 0; i < xPoints.length; i++) {
			if (i != (xPoints.length - 1)) {
				double[] segmentoRetaX = { xPoints[i], xPoints[i + 1] };
				double[] segmentoRetaY = { yPoints[i], yPoints[i + 1] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			}
		}
		double[][] pontos = null;

		for (int i = 0; i < yPoints.length; i++) {
			for (int j = 0; j < segmentosX.size(); j++) {
				TipoClipador lB = new LiangBarsky(segmentosX.get(j),
						segmentosY.get(j), areaDesenhavel);
				pontos = lB.cliparPoligono(g, clipping);
				if (pontos != null) {

					int[] xPointsT = new int[pontos[0].length];
					int[] yPointsT = new int[pontos[1].length];

					for (int k = 0; k < yPointsT.length; k++) {
						xPointsT[k] = (int) new Transformadora()
								.transVPx(pontos[0][k]);
						yPointsT[k] = (int) new Transformadora()
								.transVPy(pontos[1][k]);
					}

					g.drawPolyline(xPointsT, yPointsT, pontos[0].length);
				}
			}
		}
	}

	private void clipparReta(Graphics g, double[] xPoints, double[] yPoints) {
		switch (InterfaceGrafica.getInstance().algoClipping()) {
		case LIANG_BARSKY:
			liangBarsky(g, xPoints, yPoints);
			break;
		case COHNEN_SUTHERLAND:
			if (clipping) {
				cohenSutherland(g, xPoints, yPoints);
			} else {
				int xPointsT[] = new int[2];
				int yPointsT[] = new int[2];
				xPointsT[0] = (int) new Transformadora().transVPx(xPoints[0]);
				xPointsT[1] = (int) new Transformadora().transVPx(xPoints[1]);
				yPointsT[0] = (int) new Transformadora().transVPy(yPoints[0]);
				yPointsT[1] = (int) new Transformadora().transVPy(yPoints[1]);

				g.drawPolygon(xPointsT, yPointsT, xPointsT.length);
			}
			break;
		}
	}

	private void cohenSutherland(Graphics g, double[] xPoints, double[] yPoints) {
		System.out
				.println("Fazendo Clipping da reta usando o algoritmo de Cohen-Sutherland");
		TipoClipador cS = new CohenSutherland(xPoints, yPoints, areaDesenhavel);
		cS.clipar(g, clipping);

	}

	private void liangBarsky(Graphics g, double[] xPoints, double[] yPoints) {
		System.out
				.println("Fazendo Clipping da reta usando o algoritmo de Liang Barsky");
		TipoClipador lB = new LiangBarsky(xPoints, yPoints, areaDesenhavel);
		lB.clipar(g, clipping);
	}

	private void clipparPonto(Graphics g, double[] xPoints, double[] yPoints,
			double[] zPoints) {

		double x = xPoints[0];
		double y = yPoints[0];
		System.out.println("lol 1");
		if (areaDesenhavel.contains(x, y)) {
			System.out.println("lol 2");
			int xT = (int) transformadaViewPortX(x);
			int yT = (int) transformadaViewPortY(y);
			g.drawLine(xT, yT, xT, yT);
		} else {
			System.out.println("Ponto fora da área desenhável");
			if (!clipping) {
				int xT = (int) transformadaViewPortX(x);
				int yT = (int) transformadaViewPortY(y);
				g.drawLine(xT, yT, xT, yT);
			}
		}

	}

	private double transformadaViewPortX(double x) {
		// TODO Auto-generated method stub
		return new Transformadora().transVPx(x);
	}

	private double transformadaViewPortY(double y) {
		return new Transformadora().transVPy(y);
	}

	public void setClipping(boolean clipping) {
		// TODO Auto-generated method stub
		this.clipping = clipping;
	}

}