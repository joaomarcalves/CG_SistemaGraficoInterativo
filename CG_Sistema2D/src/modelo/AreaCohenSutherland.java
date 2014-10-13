package modelo;

import java.awt.Polygon;

public class AreaCohenSutherland implements TipoArea {

	private int[] codigo;
	private Polygon area;

	public AreaCohenSutherland(int[] codigo, Polygon area) {
		// TODO Auto-generated constructor stub
		this.codigo = codigo;
		this.area = area;
	}

	@Override
	public boolean contemRetaCompleta(double[] xPoints, double[] yPoints) {
		// TODO Auto-generated method stub
		for (int i = 0; i < yPoints.length; i++) {
			if(!(area.contains(xPoints[i], yPoints[i]))){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean centro() {
		for (int i = 0; i < codigo.length; i++) {
			if(codigo[i]!=0)
				return false;
		}
		return true;
	}

	@Override
	public boolean contemUmPto(double[] xPoints, double[] yPoints) {
		// TODO Auto-generated method stub
		for (int i = 0; i < yPoints.length; i++) {
			if(area.contains(xPoints[i], yPoints[i])){
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] codigo() {
		// TODO Auto-generated method stub
		return codigo;
	}

	@Override
	public String nome() {
		// TODO Auto-generated method stub
		String n = "";
		for (int i = 0; i < codigo.length; i++) {
			n = n+codigo[i];
		}
		return n;
	}

	/*
	 * (30,650) -------- (650, 650)
	 *   |                     |
	 *   |                     |
	 *   |                     |
	 *   |                     |
	 *  (30,30) --------- (650, 30)
	 */
	
}
