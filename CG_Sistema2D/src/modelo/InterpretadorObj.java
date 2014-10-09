package modelo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import visao.InterfaceGrafica;

public class InterpretadorObj {

	private File file;
	private Color cor;
	private int escal;

	public InterpretadorObj(File selectedFile, Color cor, int escal) throws IOException {
		// TODO Auto-generated constructor stub
		file = selectedFile;
		this.cor = cor;
		this.escal = escal;
	}

	public void criarObjetoObj() throws IOException {
		ArrayList<CoordenadasHomogeneas> listaCoords = new ArrayList<CoordenadasHomogeneas>();
		ArrayList<CoordenadasHomogeneas> subListaCoords = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String linha;
		while ((linha = br.readLine()) != null) {
			String[] campos = linha.split(" ");
			String tipo = campos[0];
			CoordenadasHomogeneas coord;

			if (tipo.equals("v")) {
				coord = new CoordenadasHomogeneas(
						(escal * Double.parseDouble(campos[1])),
						escal * Double.parseDouble(campos[2]),
						escal * Double.parseDouble(campos[3]));
				listaCoords.add(coord);
			}

			if (tipo.equals("f")) {
				subListaCoords = new ArrayList<CoordenadasHomogeneas>();
				for (int i = 1; i < campos.length; i++) {
					subListaCoords.add(listaCoords.get(Integer.parseInt((campos[i].split("/")[0])) - 1));
				}
				Mundo.getInstance().incluirObjeto(subListaCoords, cor, false);
			}
		}
		br.close();
		InterfaceGrafica.getInstance().exibirObjetos();
	}

}
