package modelo;

import java.awt.Color;
import java.util.ArrayList;

public interface TipoMundo {

	ArrayList<TipoObjeto> objetos();

	void incluirObjeto(TipoCoordenadas coordenadasHomogeneas, Color cor);

	void incluirObjeto(ArrayList<TipoCoordenadas> listCord, Color cor,
			boolean preenchido);

	int[] dimensao();

	void incluirObjeto(TipoCoordenadas coordenadas,
			TipoCoordenadas coordenadas2, Color cor);

	void incluirObjeto(ArrayList<TipoCoordenadas> listCord, Color cor,
			String numPontos);

}
