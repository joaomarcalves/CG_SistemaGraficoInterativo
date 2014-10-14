package visao;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;

import modelo.TipoClipador;
import modelo.Transformadora;

public class LiangBarsky implements TipoClipador {

	private double[] xPoints;
	private double[] yPoints;

	double p1;
	double p2;
	double p3;
	double p4;

	double q1;
	double q2;
	double q3;
	double q4;

	private double[] pk;
	private double[] qk;
	private ArrayList<Double> r1k = new ArrayList<Double>();
	private ArrayList<Double> r2k = new ArrayList<Double>();
	private Polygon areaDesenhavel;

	public LiangBarsky(double[] xPoints, double[] yPoints,
			Polygon areaDesenhavel) {

		this.areaDesenhavel = areaDesenhavel;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		p1 = xPoints[0] - xPoints[1];
		p2 = xPoints[1] - xPoints[0];
		p3 = yPoints[0] - yPoints[1];
		p4 = yPoints[1] - yPoints[0];
		double[] pk = { p1, p2, p3, p4 };
		this.pk = pk;
		q1 = (xPoints[0] - areaDesenhavel.getBounds().getMinX());
		q2 = (areaDesenhavel.getBounds().getMaxX() - xPoints[0]);
		q3 = (yPoints[0] - areaDesenhavel.getBounds().getMinY());
		q4 = (areaDesenhavel.getBounds().getMaxY() - yPoints[0]);
		double[] qk = { q1, q2, q3, q4 };
		this.qk = qk;

	}

	@Override
	public void clipar(Graphics g, boolean clipping) {
		// TODO Auto-generated method stub
		if ((xPoints[0] == xPoints[1])) {
			if (xPoints[0] >= areaDesenhavel.getBounds().getMinY()
					&& (xPoints[0] <= areaDesenhavel.getBounds().getMaxY())) {
				desenha(g, clipping);
			}
		} else if ((yPoints[0] == yPoints[1])) {
			if (yPoints[0] >= areaDesenhavel.getBounds().getMinX()
					&& (yPoints[0] <= areaDesenhavel.getBounds().getMaxX())) {
				desenha(g, clipping);
			}
		} else if ((xPoints[0] != xPoints[1]) || (yPoints[0] != yPoints[1])) {
			desenha(g, clipping);
		}
	}

	private void desenha(Graphics g, boolean clipping) {
		for (int i = 0; i < 4; i++) {
			if (pk[i] < 0) {
				r1k.add(qk[i] / pk[i]);
			} else if (pk[i] > 0) {
				r2k.add(qk[i] / pk[i]);
			}
		}

		double zeta1 = Math.max(0, Collections.max(r1k));
		double zeta2 = Math.min(1, Collections.min(r2k));

		int nPoints = xPoints.length;
		if ((zeta1 < zeta2) && clipping) {
			if (zeta1 > 0 && zeta2 < 1) {
				double x1 = xPoints[0] + zeta2 * p2;
				double y1 = yPoints[0] + zeta2 * p4;
				xPoints[1] = (int) x1;
				yPoints[1] = (int) y1;
				double x0 = xPoints[0] + zeta1 * p2;
				double y0 = yPoints[0] + zeta1 * p4;
				xPoints[0] = (int) x0;
				yPoints[0] = (int) y0;
			}
			if (zeta1 > 0 && zeta2 >= 1) {
				double x = xPoints[0] + zeta1 * p2;
				double y = yPoints[0] + zeta1 * p4;
				xPoints[0] = (int) x;
				yPoints[0] = (int) y;
			}
			if (zeta1 <= 0 && zeta2 < 1) {
				double x = xPoints[0] + zeta2 * p2;
				double y = yPoints[0] + zeta2 * p4;
				xPoints[1] = (int) x;
				yPoints[1] = (int) y;
			}

			int[] xPointsT = new int[2];
			xPointsT[0] = (int) new Transformadora().transVPx(xPoints[0]);
			xPointsT[1] = (int) new Transformadora().transVPx(xPoints[1]);
			int[] yPointsT = new int[2];
			yPointsT[0] = (int) new Transformadora().transVPy(yPoints[0]);
			yPointsT[1] = (int) new Transformadora().transVPy(yPoints[1]);

			g.drawPolygon(xPointsT, yPointsT, nPoints);
		} else if (!clipping) {
			int[] xPointsT = new int[2];
			xPointsT[0] = (int) new Transformadora().transVPx(xPoints[0]);
			xPointsT[1] = (int) new Transformadora().transVPx(xPoints[1]);
			int[] yPointsT = new int[2];
			yPointsT[0] = (int) new Transformadora().transVPy(yPoints[0]);
			yPointsT[1] = (int) new Transformadora().transVPy(yPoints[1]);

			g.drawPolygon(xPointsT, yPointsT, nPoints);
		}
	}

	@Override
	public double[][] cliparPoligono(Graphics g, boolean clipping) {
		// TODO Auto-generated method stub
		if ((xPoints[0] == xPoints[1])) {
			if (xPoints[0] >= areaDesenhavel.getBounds().getMinY()
					&& (xPoints[0] <= areaDesenhavel.getBounds().getMaxY())) {
				return desenhaPol(g, clipping);
			}
		} else if ((yPoints[0] == yPoints[1])) {
			if (yPoints[0] >= areaDesenhavel.getBounds().getMinX()
					&& (yPoints[0] <= areaDesenhavel.getBounds().getMaxX())) {
				return desenhaPol(g, clipping);
			}
		} else if ((xPoints[0] != xPoints[1]) || (yPoints[0] != yPoints[1])) {
			return desenhaPol(g, clipping);
		}
		return null;
	}

	private double[][] desenhaPol(Graphics g, boolean clipping) {
		for (int i = 0; i < 4; i++) {
			if (pk[i] < 0) {
				r1k.add(qk[i] / pk[i]);
			} else if (pk[i] > 0) {
				r2k.add(qk[i] / pk[i]);
			}
		}
		r1k.add(0.0);
		double zeta1 = Collections.max(r1k);
		r2k.add(1.0);
		double zeta2 = Collections.min(r2k);

		int nPoints = xPoints.length;
		if ((zeta1 < zeta2) && clipping) {
			if (zeta1 > 0 && zeta2 >= 1) {
				double x = xPoints[0] + zeta1 * p2;
				double y = yPoints[0] + zeta1 * p4;
				xPoints[0] = (int) x;
				yPoints[0] = (int) y;
			}
			if (zeta1 <= 0 && zeta2 < 1) {
				double x = xPoints[0] + zeta2 * p2;
				double y = yPoints[0] + zeta2 * p4;
				xPoints[1] = (int) x;
				yPoints[1] = (int) y;
			}
			if (zeta1 > 0 && zeta2 < 1) {
				double x1 = xPoints[0] + zeta2 * p2;
				double y1 = yPoints[0] + zeta2 * p4;
				xPoints[1] = (int) x1;
				yPoints[1] = (int) y1;
				double x0 = xPoints[0] + zeta1 * p2;
				double y0 = yPoints[0] + zeta1 * p4;
				xPoints[0] = (int) x0;
				yPoints[0] = (int) y0;
			}
			// g.drawPolygon(xPoints, yPoints, nPoints);
			double[][] pontos = { xPoints, yPoints };
			return pontos;
		} else if (!clipping) {

			int[] xPointsT = new int[xPoints.length];
			int[] yPointsT = new int[yPoints.length];

			for (int i = 0; i < xPointsT.length; i++) {
				xPointsT[i] = (int) new Transformadora().transVPx(xPoints[i]);
				yPointsT[i] = (int) new Transformadora().transVPy(yPoints[i]);
			}

			g.drawPolygon(xPointsT, yPointsT, nPoints);
		}
		return null;
	}

}
