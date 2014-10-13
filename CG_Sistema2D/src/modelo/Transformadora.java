package modelo;

public class Transformadora {

	private static final double xMax = Janela.getInstance().xMax();
	private static final double xMin = Janela.getInstance().xMin();
	private static final double yMax = Janela.getInstance().yMax();
	private static final double yMin = Janela.getInstance().yMin();

	public double transVPx(double x) {
		// TODO Auto-generated method stub
		return (((x - Janela.getInstance().xMin()) / (Janela.getInstance()
				.xMax() - Janela.getInstance().xMin())) * (xMax - xMin));
	}

	public double transVPy(double y) {
		// TODO Auto-generated method stub
		return (((1 - (y - Janela.getInstance().yMin())
				/ (Janela.getInstance().yMax() - Janela.getInstance().yMin()))) * (yMax - yMin));
	}

}
