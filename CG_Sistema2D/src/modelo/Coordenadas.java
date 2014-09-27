package modelo;

public abstract class Coordenadas implements TipoCoordenadas {

	protected double cX;
	protected double cY;

	public Coordenadas(String cX, String cY) {
		// TODO Auto-generated constructor stub
		this.cX = Double.parseDouble(cX);
		this.cY = Double.parseDouble(cY);
	}
	
	public Coordenadas(double cX, double cY) {
		// TODO Auto-generated constructor stub
		this.cX = cX;
		this.cY = cY;
	}

	@Override
	public double getXD() {
		// TODO Auto-generated method stub
		return cX;
	}

	@Override
	public double getYD() {
		// TODO Auto-generated method stub
		return cY;
	}

	@Override
	public void setX(double d) {
		// TODO Auto-generated method stub
		cX = d;
	}

	@Override
	public void setY(double d) {
		// TODO Auto-generated method stub
		cY = d;
	}

}
