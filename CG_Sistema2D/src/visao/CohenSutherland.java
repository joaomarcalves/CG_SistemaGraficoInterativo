package visao;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import modelo.AreaCohenSutherland;
import modelo.TipoArea;
import modelo.TipoClipador;
import modelo.Transformadora;

public class CohenSutherland implements TipoClipador {
	private ArrayList<TipoArea> lA;
	private TipoArea dentro;
	private AreaCohenSutherland esquerda;
	private AreaCohenSutherland direita;
	private AreaCohenSutherland baixo;
	private TipoArea cima;
	private TipoArea esqcima;
	private TipoArea esqbaixo;
	private TipoArea dirbaixo;
	private TipoArea dircima;
	private ArrayList<TipoArea> areaComPonto;
	private double[] xPoints;
	private double[] yPoints;

	public CohenSutherland(double[] xPoints, double[] yPoints,
			Polygon areaDesenhavel) {
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		lA = new ArrayList<TipoArea>();
		/*
		 * A maldição do faraó vai pegar quem mexer nessas áreas!!
		 */
		int[] ca0 = { 0, 0, 0, 0 };
		dentro = new AreaCohenSutherland(ca0, areaDesenhavel);
		lA.add(dentro);
		int[] x1 = { 30, -10000, -10000, 30 };
		int[] y1 = { 30, 30, 650, 650 };
		int[] ca1 = { 0, 0, 0, 1 };
		esquerda = new AreaCohenSutherland(ca1, new Polygon(x1, y1, 4));
		lA.add(esquerda);
		int[] x2 = { 650, 650, 10000, 10000 };
		int[] y2 = { 30, 650, 650, 30 };
		int[] ca2 = { 0, 0, 1, 0 };
		direita = new AreaCohenSutherland(ca2, new Polygon(x2, y2, 4));
		lA.add(direita);
		int[] x3 = { 30, 30, 650, 650 };
		int[] y3 = { -10000, 30, 30, -10000 };
		int[] ca3 = { 0, 1, 0, 0 };
		baixo = new AreaCohenSutherland(ca3, new Polygon(x3, y3, 4));
		lA.add(baixo);
		int[] x4 = { 30, 30, 650, 650 };
		int[] y4 = { 650, 10000, 10000, 650 };
		int[] ca4 = { 1, 0, 0, 0 };
		cima = new AreaCohenSutherland(ca4, new Polygon(x4, y4, 4));
		lA.add(cima);
		int[] xec = { -10000, -10000, 30, 30 };
		int[] yec = { 650, 10000, 10000, 650 };
		int[] caec = { 1, 0, 0, 1 };
		esqcima = new AreaCohenSutherland(caec, new Polygon(xec, yec, 4));
		lA.add(esqcima);
		int[] xeb = { -10000, -10000, 30, 30 };
		int[] yeb = { -10000, 30, 30, -10000 };
		int[] caeb = { 0, 1, 0, 1 };
		esqbaixo = new AreaCohenSutherland(caeb, new Polygon(xeb, yeb, 4));
		lA.add(esqbaixo);
		int[] xdb = { 650, 650, 10000, 10000 };
		int[] ydb = { -10000, 30, 30, -10000 };
		int[] cadb = { 0, 1, 1, 0 };
		dirbaixo = new AreaCohenSutherland(cadb, new Polygon(xdb, ydb, 4));
		lA.add(dirbaixo);
		int[] xdc = { 650, 650, 10000, 10000 };
		int[] ydc = { 650, 10000, 10000, 650 };
		int[] cadc = { 1, 0, 1, 0 };
		dircima = new AreaCohenSutherland(cadc, new Polygon(xdc, ydc, 4));
		lA.add(dircima);
		areaComPonto = new ArrayList<TipoArea>();
	}

	@Override
	public void clipar(Graphics g, boolean clipping) {
		// TODO Auto-generated method stub
		areaComPonto.clear();
		for (TipoArea a : lA) {
			if (a.contemRetaCompleta(xPoints, yPoints)) {
				// A reta está toda na área 'a'
				if (a.centro()) {
					// Se a área 'a' é o centro, então desenha a reta
					int[] xPointsT = new int[xPoints.length];
					int[] yPointsT = new int[yPoints.length];
					for (int k = 0; k < yPointsT.length; k++) {
						xPointsT[k] = (int) new Transformadora()
								.transVPx(xPoints[k]);
						yPointsT[k] = (int) new Transformadora()
								.transVPy(yPoints[k]);
					}
					g.drawPolygon(xPointsT, yPointsT, xPoints.length);
				}
			} else {
				if (a.contemUmPto(xPoints, yPoints)) {
					areaComPonto.add(a);
				}
			}
		}
		if (!areaComPonto.isEmpty()) {
			cliparParcial(g);
		}
	}

	private void cliparParcial(Graphics g) {
		// TODO Auto-generated method stub
		double x1 = 0;
		double y1 = 0;
		double x2 = 0;
		double y2 = 0;
		if (areaComPonto.contains(esquerda)) {
			double m = (yPoints[1] - yPoints[0]) / (xPoints[1] - xPoints[0]);
			x1 = 30;
			y1 = m * (x1 - xPoints[0]) + yPoints[0];
			if (xPoints[1] > xPoints[0]) {
				x2 = xPoints[1];
				y2 = yPoints[1];
			} else {
				x2 = xPoints[0];
				y2 = yPoints[0];
			}
		}
		if (areaComPonto.contains(direita)) {
			double m = (yPoints[1] - yPoints[0]) / (xPoints[1] - xPoints[0]);
			x1 = 650;
			y1 = m * (x1 - xPoints[0]) + yPoints[0];
			if (xPoints[1] > xPoints[0]) {
				x2 = xPoints[0];
				y2 = yPoints[0];
			} else {
				x2 = xPoints[1];
				y2 = yPoints[1];
			}
		}
		if (areaComPonto.contains(cima)) {
			// 1/m
			double m = (xPoints[1] - xPoints[0]) / (yPoints[1] - yPoints[0]);
			y1 = 650;
			x1 = xPoints[0] + m * (y1 - yPoints[0]);
			if (yPoints[1] > yPoints[0]) {
				x2 = xPoints[0];
				y2 = yPoints[0];
			} else {
				x2 = xPoints[1];
				y2 = yPoints[1];
			}
		}
		if (areaComPonto.contains(baixo)) {
			// 1/m
			double m = (xPoints[1] - xPoints[0]) / (yPoints[1] - yPoints[0]);
			y1 = 30;
			x1 = xPoints[0] + m * (y1 - yPoints[0]);
			if (yPoints[1] > yPoints[0]) {
				x2 = xPoints[1];
				y2 = yPoints[1];
			} else {
				x2 = xPoints[0];
				y2 = yPoints[0];
			}
		}
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	@Override
	public double[][] cliparPoligono(Graphics g, boolean clipping) {
		// TODO Auto-generated method stub
		return null;
	}
}