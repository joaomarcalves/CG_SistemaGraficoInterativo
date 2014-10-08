package visao;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import modelo.Janela;
import modelo.TipoClipador;
import modelo.TipoCoordenadas;
import modelo.TipoCoordenadasNormalizada;
import modelo.TipoObjeto;

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
			ArrayList<TipoCoordenadasNormalizada> lC = o.coordenadas();
			g.setColor(o.cor());
			int[] xPoints = new int[lC.size()];
			int[] yPoints = new int[lC.size()];
			int[] zPoints = new int[lC.size()];
			for (TipoCoordenadas c : lC) {
				xPoints[lC.indexOf(c)] = (int) transformadaViewPortX(c.getXD());
				yPoints[lC.indexOf(c)] = (int) transformadaViewPortY(c.getYD());
				zPoints[lC.indexOf(c)] = (int) transformadaViewPortZ(c.getYD());
			}

			if (o.nome().startsWith("P")) {
				if (o.getClass().getSimpleName().equals("Ponto")) {
					clipparPonto(g, o, lC);
				} else
					clipparPonto3D(g, o, lC);
			} else if (o.nome().startsWith("R")) {
				clipparReta(g, xPoints, yPoints);
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

	private void clipparPonto3D(Graphics g, TipoObjeto o,
			ArrayList<TipoCoordenadasNormalizada> lC) {
		// TODO Auto-generated method stub
		double xNT = lC.get(0).getXD();
		double yNT = lC.get(0).getYD();
		double zNT = lC.get(0).getZD();
		int xT = (int) transformadaViewPortX(xNT);
		int yT = (int) transformadaViewPortY(yNT);
		int zT = (int) transformadaViewPortZ(zNT);

		g.setColor(o.cor());

		System.out.println("x " + xT);
		System.out.println("y " + yT);
		System.out.println("z " + zT);

		g.drawLine(xT, yT, xT, yT);
		/*
		 * if (areaDesenhavel.contains(xT, yT)) { g.drawLine(xT, yT, xT, yT); }
		 * else if (!clipping) { g.drawLine(xT, yT, xT, yT);
		 * System.out.println("Ponto fora da área desenhável"); }
		 */
	}

	private int transformadaViewPortZ(double z) {
		// TODO Auto-generated method stub
		return (int) (((z - Janela.getInstance().xMin()) / (Janela
				.getInstance().xMax() - Janela.getInstance().xMin())) * (zMax - zMin));
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
			ArrayList<TipoCoordenadasNormalizada> lC) {

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

	public void setClipping(boolean clipping) {
		// TODO Auto-generated method stub
		this.clipping = clipping;
	}

}