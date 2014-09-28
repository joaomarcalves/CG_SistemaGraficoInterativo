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
	private Polygon areaDesenhavel;
	private boolean clipping;

	public CanvasViewPort(ArrayList<TipoObjeto> objetos) {
		// TODO Auto-generated constructor stub
		clipping = true;
		xMax = Janela.getInstance().xMax();
		yMax = Janela.getInstance().yMax();
		xMin = Janela.getInstance().xMin();
		yMin = Janela.getInstance().yMin();
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
			for (TipoCoordenadas c : lC) {
				xPoints[lC.indexOf(c)] = (int) transformadaViewPortX(c.getXD());
				yPoints[lC.indexOf(c)] = (int) transformadaViewPortY(c.getYD());
			}

			if (o.nome().startsWith("P")) {
				clipparPonto(g, o, lC);
			} else if (o.nome().startsWith("R")) {
				clipparReta(g, xPoints, yPoints);
			} else if (o.nome().startsWith("O")) {
				if (clipping) {
					clipparPoligono(g, xPoints, yPoints);
				} else {
					g.drawPolygon(xPoints, yPoints, xPoints.length);
					if (o.preenchido()) {
						g.fillPolygon(xPoints, yPoints, xPoints.length);
					}
				}
			} else if (clipping){
				clipparCurvas(g, xPoints, yPoints);
			}else{
				g.drawPolyline(xPoints, yPoints, xPoints.length);
			}
			/*
			 * switch (lC.size()) { case PONTO: clipparPonto(g, o, lC); break;
			 * case RETA: clipparReta(g, xPoints, yPoints); break; default: //
			 * if (clipping) { // clipparPoligonos(g, xPoints, yPoints); // }
			 * else { g.drawPolygon(xPoints, yPoints, xPoints.length); // if
			 * (o.preenchido()) { // g.fillPolygon(xPoints, yPoints,
			 * xPoints.length); // } // } }
			 */
		}
	}

	private void clipparPoligono(Graphics g, int[] xPoints, int[] yPoints) {
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
		// int[] newX = new int[segmentosX.size()];
		// int[] newY = new int[segmentosY.size()];

		for (int k = 0; k < yPoints.length; k++) {
			for (int i = 0; i < segmentosX.size(); i++) {
				TipoClipador lB = new LiangBarsky(segmentosX.get(i),
						segmentosY.get(i), areaDesenhavel);
				pontos = lB.cliparPoligono(g, clipping);
				if (pontos != null) {
					g.drawPolygon(pontos[0], pontos[1], pontos[0].length); //
					// newX[i] = pontos[0][0];
					// newY[i] = pontos[1][0];
					// g.drawPolygon(newX, newY, newX.length);
				}
			}
		}
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
		// int[] newX = new int[segmentosX.size()];
		// int[] newY = new int[segmentosY.size()];

		for (int k = 0; k < yPoints.length; k++) {
			for (int i = 0; i < segmentosX.size(); i++) {
				TipoClipador lB = new LiangBarsky(segmentosX.get(i),
						segmentosY.get(i), areaDesenhavel);
				pontos = lB.cliparPoligono(g, clipping);
				if (pontos != null) {
					g.drawPolygon(pontos[0], pontos[1], pontos[0].length); //
					// newX[i] = pontos[0][0];
					// newY[i] = pontos[1][0];
					// g.drawPolygon(newX, newY, newX.length);
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