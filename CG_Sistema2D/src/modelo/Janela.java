package modelo;

import java.util.ArrayList;

public class Janela {
	private static Janela instance;
	private int xMax;
	private int yMax;
	private int zMax;
	private int xMin;
	private int yMin;
	private int zMin;
	private double anguloAtual;
	private Reta vetorVertical;
	private Reta vetorHorizontal;

	private Janela() {
		int[] dim = Mundo.getInstance().dimensao();
		xMax = dim[0];
		yMax = dim[1];
		zMax = dim[2];
		xMin = 0;
		yMin = 0;
		zMin = 0;
		anguloAtual = 0;
		criarVetoresReferencia();
	}

	public static Janela getInstance() {
		if (instance == null)
			instance = new Janela();
		return instance;
	}

	public CoordenadasHomogeneas centroWin() {
		double mediaX = (xMax + xMin) / 2;
		double mediaY = (yMax + yMin) / 2;
		// double mediaZ = (zMax+zMin)/2;
		return new CoordenadasHomogeneas(mediaX, mediaY, 1);
	}

	public void diminuir() {
		// TODO Auto-generated method stub
		xMax -= 5;
		yMax -= 5;
		zMax -= 5;
		xMin += 5;
		yMin += 5;
		zMin += 5;
	}

	public double xMax() {
		// TODO Auto-generated method stub
		return xMax;
	}

	public double xMin() {
		// TODO Auto-generated method stub
		return xMin;
	}

	public double yMin() {
		// TODO Auto-generated method stub
		return yMin;
	}

	public double yMax() {
		// TODO Auto-generated method stub
		return yMax;
	}

	public double zMin() {
		return zMin;
	}

	public double zMax() {
		return zMax;
	}

	public void aumentar() {
		// TODO Auto-generated method stub
		xMax += 5;
		yMax += 5;
		zMax += 5;
		xMin -= 5;
		yMin -= 5;
		zMin -= 5;
	}

	public void moverEsq() {
		// TODO Auto-generated method stub
		ArrayList<TipoObjeto> objts = Mundo.getInstance().objetos();
		for (TipoObjeto obj : objts) {

			obj.moverSeWin(Direcoes.DIREITA);
		}

	}

	public void moverCima() {
		// TODO Auto-generated method stub
		ArrayList<TipoObjeto> objts = Mundo.getInstance().objetos();
		for (TipoObjeto obj : objts) {

			obj.moverSeWin(Direcoes.BAIXO);
		}
	}

	public void moverBaixo() {
		// TODO Auto-generated method stub
		ArrayList<TipoObjeto> objts = Mundo.getInstance().objetos();
		for (TipoObjeto obj : objts) {

			obj.moverSeWin(Direcoes.CIMA);
		}
	}

	public void moverDir() {
		// TODO Auto-generated method stub
		ArrayList<TipoObjeto> objts = Mundo.getInstance().objetos();
		for (TipoObjeto obj : objts) {

			obj.moverSeWin(Direcoes.ESQUERDA);
		}
	}

	public void centralizar() {
		// TODO Auto-generated method stub
		xMin -= 350;
		xMax -= 350;
		yMin -= 350;
		yMax -= 350;
		zMax -= 350;
		zMin -= 350;
	}

	public void rotacionar(double graus) {
		// TODO Auto-generated method stub
		anguloAtual += graus;
		ArrayList<TipoObjeto> objts = Mundo.getInstance().objetos();
		for (TipoObjeto obj : objts) {
			obj.rotacionarEmCoordWin(graus, centroWin());
		}
		criarVetoresReferencia();
	}

	private void criarVetoresReferencia() {
		// Criando um vetor de deslocamento para cima
		ArrayList<CoordenadasHomogeneas> lV = new ArrayList<CoordenadasHomogeneas>();
		lV.add(new CoordenadasHomogeneas(0, 0, 1));
		lV.add(new CoordenadasHomogeneas(0, 10, 1));
		vetorVertical = new Reta("vetor vertical", lV, null);
		
		ArrayList<CoordenadasHomogeneas> lH = new ArrayList<CoordenadasHomogeneas>();
		lH.add(new CoordenadasHomogeneas(0, 0, 1));
		lH.add(new CoordenadasHomogeneas(10, 0, 1));
		vetorHorizontal = new Reta("vetor horizontal", lH, null);

		// Rotacionar o vetor de deslocamento no angulo da window
		vetorVertical.rotacionarAoRedorPto(anguloAtual, "0", "0");
		vetorHorizontal.rotacionarAoRedorPto(anguloAtual, "0", "0");
	}
	
	public Reta getVetorVertical(){
		return vetorVertical;
	}
	
	public Reta getVetorHorizontal(){
		return vetorHorizontal;
	}

	public double anguloAtual() {
		return anguloAtual;
	}
}
