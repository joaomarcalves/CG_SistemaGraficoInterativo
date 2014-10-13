package modelo;

public interface TipoArea {

	boolean centro();

	boolean contemUmPto(double[] xPoints, double[] yPoints);

	int[] codigo();

	String nome();

	boolean contemRetaCompleta(double[] xPoints, double[] yPoints);

}