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
	private double zMax;
	private double zMin;
	private Polygon areaDesenhavel;
	private boolean clipping;

	public void construirViewPort(ArrayList<TipoObjeto> objetos) {
		// TODO Auto-generated constructor stub
		clipping = true;
		xMax = Janela.getInstance().xMax();
		yMax = Janela.getInstance().yMax();
		xMin = Janela.getInstance().xMin();
		yMin = Janela.getInstance().yMin();
		zMin = Janela.getInstance().zMin();
		zMin = Janela.getInstance().zMin();
		this.objetos = objetos;
		this.setSize(new Dimension((int) (xMax - xMin), (int) (yMax - yMin)));
		this.setBackground(Color.BLACK);
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		// Exibindo área desenhável
		g.setColor(Color.WHITE);
		int[] subCanvasX = { 30, 30, 650, 650 };
		int[] subCanvasY = { 30, 650, 650, 30 };
		areaDesenhavel = new Polygon(subCanvasX, subCanvasY, 4);
		g.drawPolygon(areaDesenhavel);

		// Exibindo os objetos
		desenharObjetos(g);
	}

	private void desenharObjetos(Graphics g) {
		for (TipoObjeto o : objetos) {
			ArrayList<CoordenadasHomogeneas> lC = o.coordenadas();
			g.setColor(o.cor());
			int[] xPoints = new int[lC.size()];
			int[] yPoints = new int[lC.size()];
			int[] zPoints = new int[lC.size()];
			for (CoordenadasHomogeneas c : lC) {
				xPoints[lC.indexOf(c)] = (int) transformadaViewPortX(c.getXD());
				yPoints[lC.indexOf(c)] = (int) transformadaViewPortY(c.getYD());
				zPoints[lC.indexOf(c)] = (int) transformadaViewPortZ(c.getZD());
			}

			if (o.nome().startsWith("P")) {
				if (o.getClass().getSimpleName().equals("Ponto")) {
					clipparPonto(g, o, lC);
				} else
					clipparPonto3D(g, o, lC);
			} else if (o.nome().startsWith("R")) {
				if (o.getClass().getSimpleName().equals("Reta"))
					clipparReta(g, xPoints, yPoints);
				else
					projeçãoReta(g, xPoints, yPoints, zPoints);
			} else if (o.nome().startsWith("O")) {
				if (clipping) {
					clipparPoligono(g, xPoints, yPoints, o.preenchido());
				} else {
					g.drawPolygon(xPoints, yPoints, xPoints.length);
					if (o.preenchido()) {
						g.fillPolygon(xPoints, yPoints, xPoints.length);
					}
				}
			} else if (clipping) {
				clipparCurvas(g, xPoints, yPoints);
			} else {
				g.drawPolyline(xPoints, yPoints, xPoints.length);
			}
		}
	}

	private void projeçãoReta(Graphics g, int[] xPoints, int[] yPoints,
			int[] zPoints) {

		// TODO Auto-generated method stub
		ArrayList<CoordenadasHomogeneas> lcop = new ArrayList<CoordenadasHomogeneas>();
		lcop.add(new CoordenadasHomogeneas(0, 0, -120));
		Ponto3D cop = new Ponto3D("COP", lcop, null);
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

	private void clipparPonto3D(Graphics g, TipoObjeto o,
			ArrayList<CoordenadasHomogeneas> lC) {
		// TODO Auto-generated method stub
		double xNT = lC.get(0).getXD();
		double yNT = lC.get(0).getYD();
		double zNT = lC.get(0).getZD();
		int xT = (int) transformadaViewPortX(xNT);
		int yT = (int) transformadaViewPortY(yNT);
		int zT = (int) transformadaViewPortZ(zNT);

		g.setColor(o.cor());

		g.drawLine(xT, yT, xT, yT);
		/*
		 * if (areaDesenhavel.contains(xT, yT)) { g.drawLine(xT, yT, xT, yT); }
		 * else if (!clipping) { g.drawLine(xT, yT, xT, yT);
		 * System.out.println("Ponto fora da área desenhável"); }
		 */
	}

	private void clipparPoligono(Graphics g, int[] xPoints, int[] yPoints,
			boolean preenchido) {
		// TODO Auto-generated method stub
		System.out
				.println("Fazendo Clipping da polígono usando o algoritmo de Liang Barsky");
		ArrayList<int[]> segmentosX = new ArrayList<int[]>();
		ArrayList<int[]> segmentosY = new ArrayList<int[]>();
		for (int i = 0; i < xPoints.length; i++) {
			if (i != (xPoints.length - 1)) {
				int[] segmentoRetaX = { xPoints[i], xPoints[i + 1] };
				int[] segmentoRetaY = { yPoints[i], yPoints[i + 1] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			} else {
				int[] segmentoRetaX = { xPoints[i], xPoints[0] };
				int[] segmentoRetaY = { yPoints[i], yPoints[0] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			}
		}
		int[][] pontos = null;
		ArrayList<Integer> newX = new ArrayList<Integer>();
		ArrayList<Integer> newY = new ArrayList<Integer>();
		for (int i = 0; i < segmentosX.size(); i++) {
			TipoClipador lB = new LiangBarsky(segmentosX.get(i),
					segmentosY.get(i), areaDesenhavel);
			pontos = lB.cliparPoligono(g, clipping);
			if (pontos != null) {
				for (int j = 0; j < pontos[0].length; j++) {
					newX.add(pontos[0][j]);
					newY.add(pontos[1][j]);
				}
				g.drawPolygon(pontos[0], pontos[1], pontos[0].length);
			}
		}

		int[] ptosX = new int[newX.size() * 2];
		int[] ptosY = new int[newX.size() * 2];

		for (int i = 0; i < newX.size(); i++) {
			ptosX[i] = newX.get(i);
			ptosY[i] = newY.get(i);
		}
		if (preenchido)
			g.fillPolygon(ptosX, ptosY, ptosX.length);
	}

	private void clipparCurvas(Graphics g, int[] xPoints, int[] yPoints) {
		// TODO Auto-generated method stub
		System.out
				.println("Fazendo Clipping da curva usando o algoritmo de Liang Barsky");

		ArrayList<int[]> segmentosX = new ArrayList<int[]>();
		ArrayList<int[]> segmentosY = new ArrayList<int[]>();

		for (int i = 0; i < xPoints.length; i++) {
			if (i != (xPoints.length - 1)) {
				int[] segmentoRetaX = { xPoints[i], xPoints[i + 1] };
				int[] segmentoRetaY = { yPoints[i], yPoints[i + 1] };
				segmentosX.add(segmentoRetaX);
				segmentosY.add(segmentoRetaY);
			}
		}
		int[][] pontos = null;

		for (int i = 0; i < yPoints.length; i++) {
			for (int j = 0; j < segmentosX.size(); j++) {
				TipoClipador lB = new LiangBarsky(segmentosX.get(j),
						segmentosY.get(j), areaDesenhavel);
				pontos = lB.cliparPoligono(g, clipping);
				if (pontos != null) {
					g.drawPolyline(pontos[0], pontos[1], pontos[0].length);
				}
			}
		}
	}

	private void clipparReta(Graphics g, int[] xPoints, int[] yPoints) {
		switch (InterfaceGrafica.getInstance().algoClipping()) {
		case LIANG_BARSKY:
			liangBarsky(g, xPoints, yPoints);
			break;
		case COHNEN_SUTHERLAND:
			if (clipping) {
				cohenSutherland(g, xPoints, yPoints);
			} else {
				g.drawPolygon(xPoints, yPoints, xPoints.length);
			}
			break;
		}
	}

	private void cohenSutherland(Graphics g, int[] xPoints, int[] yPoints) {
		System.out
				.println("Fazendo Clipping da reta usando o algoritmo de Cohen-Sutherland");
		TipoClipador cS = new CohenSutherland(xPoints, yPoints, areaDesenhavel);
		cS.clipar(g, clipping);

	}

	private void liangBarsky(Graphics g, int[] xPoints, int[] yPoints) {
		System.out
				.println("Fazendo Clipping da reta usando o algoritmo de Liang Barsky");
		TipoClipador lB = new LiangBarsky(xPoints, yPoints, areaDesenhavel);
		lB.clipar(g, clipping);
	}

	private void clipparPonto(Graphics g, TipoObjeto o,
			ArrayList<CoordenadasHomogeneas> lC) {

		double xNT = lC.get(0).getXD();
		double yNT = lC.get(0).getYD();
		int xT = (int) transformadaViewPortX(xNT);
		int yT = (int) transformadaViewPortY(yNT);
		g.setColor(o.cor());
		if (areaDesenhavel.contains(xT, yT)) {
			g.drawLine(xT, yT, xT, yT);
		} else if (!clipping) {
			g.drawLine(xT, yT, xT, yT);
			System.out.println("Ponto fora da área desenhável");
		}

	}

	private double transformadaViewPortX(double x) {
		// TODO Auto-generated method stub
		return (int) (((x - Janela.getInstance().xMin()) / (Janela
				.getInstance().xMax() - Janela.getInstance().xMin())) * (xMax - xMin));
	}

	private double transformadaViewPortY(double y) {
		return (((1 - (y - Janela.getInstance().yMin())
				/ (Janela.getInstance().yMax() - Janela.getInstance().yMin()))) * (yMax - yMin));
	}

	private int transformadaViewPortZ(double z) {
		// TODO Auto-generated method stub
		return (int) z;
		// (((z - Janela.getInstance().zMin()) / (Janela
		// .getInstance().zMax() - Janela.getInstance().zMin())) * (zMax -
		// zMin));
	}

	public void setClipping(boolean clipping) {
		// TODO Auto-generated method stub
		this.clipping = clipping;
	}

}