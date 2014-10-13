package modelo;

import java.awt.Graphics;

public interface TipoClipador {

	void clipar(Graphics g, boolean clipping);

	double[][] cliparPoligono(Graphics g, boolean clipping);

}
