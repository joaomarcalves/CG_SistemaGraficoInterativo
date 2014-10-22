package modelo;

import java.awt.Color;
import java.util.ArrayList;

public interface TipoObjeto {

	String nome();

	ArrayList<CoordenadasHomogeneas> coordenadas();

	String coordenadasString();

	void moverSe(Direcoes dir);

	void escalonarSe(Escalonamento tipo);

	CoordenadasHomogeneas centro();

	Color cor();

	void rotacionarSe(double graus);

	void rotacionarSe(double parseDouble, Eixo x);

	void rotacionarOrigem(double graus);

	void rotacionarAoRedorPto(double graus, String ptX, String ptY);

	void rotacionarEmCoordWin(double graus, CoordenadasHomogeneas centroWin);

	void moverSeWin(Direcoes dir);

	boolean preenchido();

	void moverSe(double dX, double dY, double dZ);

	void incluirCurvas(ArrayList<TipoObjeto> listaCurvas1,
			ArrayList<TipoObjeto> listaCurvas2);

	ArrayList<TipoObjeto> curva1();

	ArrayList<TipoObjeto> curva2();
}
