package modelo;

public interface TipoArea {

	boolean contemRetaCompleta(int[] xPoints, int[] yPoints);

	boolean centro();

	boolean contemUmPto(int[] xPoints, int[] yPoints);

	int[] codigo();

	String nome();

}