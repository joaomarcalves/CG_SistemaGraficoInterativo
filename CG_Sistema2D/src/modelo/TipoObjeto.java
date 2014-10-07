package modelo;

import java.awt.Color;
import java.util.ArrayList;

public interface TipoObjeto {

	String nome();

	ArrayList<TipoCoordenadasNormalizada> coordenadas();

	String coordenadasString();

	void moverSe(Direcoes dir);

	void escalonarSe(Escalonamento tipo);

	TipoCoordenadas centro();

	Color cor();

	void rotacionarSe(double graus);

	void rotacionarOrigem(double graus);

	void rotacionarAoRedorPto(double graus, String ptX, String ptY);

	void rotacionarEmCoordWin(double graus, TipoCoordenadas centroWin);

	void moverSeWin(Direcoes dir);

	boolean preenchido();

	void moverSe(double dX, double dY, double dZ);
}
