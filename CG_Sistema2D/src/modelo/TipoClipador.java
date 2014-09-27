package modelo;

import java.awt.Graphics;

public interface TipoClipador {

	void clipar(Graphics g, boolean clipping);

	int[][] cliparPoligono(Graphics g, boolean clipping);

}
