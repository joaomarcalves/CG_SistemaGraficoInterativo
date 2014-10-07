package modelo;

import java.awt.Color;
import java.util.ArrayList;

public interface TipoMundo {

	ArrayList<TipoObjeto> objetos();

	void incluirObjeto(TipoCoordenadas coordenadasHomogeneas, Color cor);

	void incluirObjeto(ArrayList<TipoCoordenadas> listCord, Color cor,
			boolean preenchido);

	int[] dimensao();

	void incluirObjeto(TipoCoordenadas c1, TipoCoordenadas c2, Color cor);

	void incluirCurvaBezier(ArrayList<TipoCoordenadas> listCord, Color cor,
			String numPontos);

	void incluirCurvaSpline(ArrayList<SemiPonto> listCord, Color color);

	void incluirObjeto3D(CoordenadasHomogeneas coordenadasHomogeneas, Color cor);

	void incluirObjeto3D(CoordenadasHomogeneas c1, CoordenadasHomogeneas c2,
			Color cor);

	void incluirObjeto3D(ArrayList<TipoCoordenadas> listCord, Color cor,
			boolean preenchido);

}
