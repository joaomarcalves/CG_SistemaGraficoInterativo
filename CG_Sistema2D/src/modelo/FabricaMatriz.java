package modelo;

import Jama.Matrix;

public class FabricaMatriz {

	public Matrix matrizTranslação(double dX, double dY, double dZ) {
		// TODO Auto-generated method stub
		double[] l1 = { 1.0, 0.0, 0.0, 0.0 };
		double[] l2 = { 0.0, 1.0, 0.0, 0.0 };
		double[] l3 = { 0.0, 0.0, 1.0, 0.0 };
		double[] l4 = { dX, dY, dZ, 1.0 };
		double[][] m = { l1, l2, l3, l4 };
		Matrix t = new Matrix(m);
		return t;
	}

	public Matrix matrizEscalonamento(double sX, double sY, double sZ) {
		// TODO Auto-generated method stub
		double[] l1 = { sX, 0.0, 0.0, 0.0 };
		double[] l2 = { 0.0, sY, 0.0, 0.0 };
		double[] l3 = { 0.0, 0.0, sZ, 0.0 };
		double[] l4 = { 0.0, 0.0, 0.0, 1.0 };
		double[][] m = { l1, l2, l3, l4 };
		Matrix s = new Matrix(m);
		return s;
	}

	public Matrix matrizRotaçãoX(double angulo) {
		double cos = Math.cos(Math.toRadians(angulo));
		double sen = Math.sin(Math.toRadians(angulo));

		double[] l1 = { 1.0, 0.0, 0.0, 0.0 };
		double[] l2 = { 0.0, cos, sen, 0.0 };
		double[] l3 = { 0.0, -sen, cos, 0.0 };
		double[] l4 = { 0.0, 0.0, 0.0, 1.0 };
		double[][] m = { l1, l2, l3, l4 };
		Matrix rx = new Matrix(m);
		return rx;
	}

	public Matrix matrizRotaçãoY(double angulo) {
		double cos = Math.cos(Math.toRadians(angulo));
		double sen = Math.sin(Math.toRadians(angulo));

		double[] l1 = { cos, 0.0, -sen, 0.0 };
		double[] l2 = { 0.0, 1.0, 0.0, 0.0 };
		double[] l3 = { sen, 0.0, cos, 0.0 };
		double[] l4 = { 0.0, 0.0, 0.0, 1.0 };
		double[][] m = { l1, l2, l3, l4 };
		Matrix ry = new Matrix(m);
		return ry;
	}

	public Matrix matrizRotaçãoZ(double angulo) {
		double cos = Math.cos(Math.toRadians(angulo));
		double sen = Math.sin(Math.toRadians(angulo));

		double[] l1 = { cos, sen, 0.0, 0.0 };
		double[] l2 = { -sen, cos, 0.0, 0.0 };
		double[] l3 = { 0.0, 0.0, 1.0, 0.0 };
		double[] l4 = { 0.0, 0.0, 0.0, 1.0 };
		double[][] m = { l1, l2, l3, l4 };
		Matrix rz = new Matrix(m);
		return rz;
	}
}
