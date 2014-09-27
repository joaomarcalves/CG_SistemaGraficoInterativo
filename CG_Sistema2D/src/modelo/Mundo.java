package modelo;

import java.awt.Color;
import java.util.ArrayList;

public class Mundo implements TipoMundo {

	private static TipoMundo instance;
	private ArrayList<TipoObjeto> displayFile = new ArrayList<TipoObjeto>();
	private int quantPts;
	private int quantRts;
	private int quantPol;
	private int xMax;
	private int yMax;
	private int zMax;
	private int quantCurv;

	private Mundo() {
		quantPts = 0;
		quantRts = 0;
		quantPol = 0;
		xMax = 680;
		yMax = 680;
		zMax = 680;
	}

	public static TipoMundo getInstance() {
		if (instance == null)
			instance = new Mundo();
		return instance;
	}

	@Override
	public void incluirObjeto(TipoCoordenadas coordenadas, Color cor) {
		// TODO Auto-generated method stub
		quantPts++;
		displayFile.add(Objeto.criarPonto(("P" + quantPts), coordenadas, cor));
	}

	@Override
	public ArrayList<TipoObjeto> objetos() {
		// TODO Auto-generated method stub
		return displayFile;
	}

	@Override
	public void incluirObjeto(TipoCoordenadas coordenadas,
			TipoCoordenadas coordenadas2, Color cor) {
		// TODO Auto-generated method stub
		quantRts++;
		displayFile.add(Objeto.criarReta(("R" + quantRts), coordenadas,
				coordenadas2, cor));

	}

	@Override
	public void incluirObjeto(ArrayList<TipoCoordenadas> listCord, Color cor,
			boolean preenchido) {
		// TODO Auto-generated method stub
		quantPol++;
		displayFile.add(Objeto.criarPoligono(("O" + quantPol), listCord, cor, preenchido));
	}

	@Override
	public int[] dimensao() {
		// TODO Auto-generated method stub
		int[] dimensao = new int[3];
		dimensao[0] = xMax;
		dimensao[1] = yMax;
		dimensao[2] = zMax;
		return dimensao;
	}

	@Override
	public void incluirCurvaBezier(ArrayList<TipoCoordenadas> listCord, Color cor,
			String numPontos) {
		// TODO Auto-generated method stub
		quantCurv++;
		displayFile.add(Objeto.criarCurvaBezier(("C"+quantCurv), listCord, cor, numPontos));
		
	}
	public void incluirCurvaSpline(ArrayList<TipoCoordenadas> listCord, Color cor,
			String numPontos) {
		// TODO Auto-generated method stub
		quantCurv++;
		displayFile.add(Objeto.criarCurvaSpline(("C"+quantCurv), listCord, cor, numPontos));
		
	}
}
