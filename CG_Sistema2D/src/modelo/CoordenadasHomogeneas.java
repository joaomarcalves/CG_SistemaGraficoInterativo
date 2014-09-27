package modelo;

public class CoordenadasHomogeneas extends Coordenadas implements
		TipoCoordenadas {
	private double cZ;

	public CoordenadasHomogeneas(double ccX, double ccY, double ccZ) {
		// TODO Auto-generated constructor stub
		super(ccX, ccY);
		cZ = ccZ;
	}

	public CoordenadasHomogeneas(String cX, String cY, String cZ) {
		super(cX, cY);
		// TODO Auto-generated constructor stub
		this.cZ = Double.parseDouble(cZ);
	}

	public double getZD() {
		return cZ;
	}

	@Override
	public void setZ(double d) {
		// TODO Auto-generated method stub
		cZ = d;
	}
}
