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
	private int quantPts3D;
	private int quantRts3D;
	private int quantPol3D;
	private int quantSpf;

	private Mundo() {
		quantSpf = 0;
		quantPts = 0;
		quantRts = 0;
		quantPol = 0;
		quantPts3D = 0;
		quantRts3D = 0;
		quantPol3D = 0;
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
	public void incluirObjeto(CoordenadasHomogeneas coordenadas, Color cor) {
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
	public void incluirObjeto(CoordenadasHomogeneas coordenadas,
			CoordenadasHomogeneas coordenadas2, Color cor) {
		// TODO Auto-generated method stub
		quantRts++;
		displayFile.add(Objeto.criarReta(("R" + quantRts), coordenadas,
				coordenadas2, cor));

	}

	@Override
	public void incluirObjeto(ArrayList<CoordenadasHomogeneas> listCord,
			Color cor, boolean preenchido) {
		// TODO Auto-generated method stub
		quantPol++;
		displayFile.add(Objeto.criarPoligono(("O" + quantPol), listCord, cor,
				preenchido));
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
	public void incluirCurvaBezier(ArrayList<CoordenadasHomogeneas> listCord,
			Color cor, String numPontos) {
		// TODO Auto-generated method stub
		quantCurv++;
		displayFile.add(Objeto.criarCurvaBezier(("C" + quantCurv), listCord,
				cor, numPontos));

	}

	public void incluirCurvaSpline(ArrayList<SemiPonto> listCord, Color cor) {
		// TODO Auto-generated method stub
		quantCurv++;
		displayFile.add(Objeto.criarCurvaSpline(("C" + quantCurv), listCord,
				cor));
	}

	@Override
	public void incluirObjeto3D(CoordenadasHomogeneas coordenadasHomogeneas,
			Color cor) {
		// TODO Auto-generated method stub
		quantPts3D++;
		displayFile.add(Objeto3D.criarPonto(("P3D" + quantPts3D),
				coordenadasHomogeneas, cor));
	}

	@Override
	public void incluirObjeto3D(CoordenadasHomogeneas c1,
			CoordenadasHomogeneas c2, Color cor) {
		// TODO Auto-generated method stub
		quantRts3D++;
		displayFile.add(Objeto3D.criarReta(("R3D" + quantRts3D), c1, c2, cor));

	}

	@Override
	public void incluirObjeto3D(ArrayList<CoordenadasHomogeneas> listCord,
			Color cor, boolean preenchido) {
		// TODO Auto-generated method stub
		quantPol3D++;
		displayFile.add(Objeto3D.criarPoligono(("O3D" + quantPol3D), listCord,
				cor, preenchido));

	}

	@Override
	public void incluirSuperficieBicubica(
			ArrayList<ArrayList<SemiPonto>> curvasCtrl, Color cor) {
		// TODO Auto-generated method stub
		quantSpf++;
		displayFile.add(Objeto3D.criarSpfSpline(("S" + quantSpf), curvasCtrl,
				cor));

	}

	@Override
	public void incluirPoliedro(
			ArrayList<ArrayList<CoordenadasHomogeneas>> listaDeFaces,
			Color cor, boolean preenchido) {
		// TODO Auto-generated method stub
		for (ArrayList<CoordenadasHomogeneas> f : listaDeFaces) {
			quantPol3D++;
			displayFile.add(Objeto3D.criarPoligono(("O3D" + quantPol3D), f,
					cor, preenchido));
		}

	}
}
