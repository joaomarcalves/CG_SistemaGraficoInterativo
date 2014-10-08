package controle;

import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import modelo.CoordenadasHomogeneas;
import modelo.SemiPonto;
import modelo.TipoCoordenadas;
import visao.InterfaceGrafica;

public class ControladorDeObjetos3D extends ControladorDeObjetos {

	public ControladorDeObjetos3D(JFrame frame,
			ArrayList<SemiPonto> listaSemiPontos, JColorChooser colorChooser,
			boolean preenchido) {
		super(frame, listaSemiPontos, colorChooser, preenchido);
		// TODO Auto-generated constructor stub
	}

	protected void criarPoligono() {
		// TODO Auto-generated method stub

		ArrayList<TipoCoordenadas> listCord = new ArrayList<TipoCoordenadas>();
		for (SemiPonto sp : listaSemiPontos) {
			listCord.add(new CoordenadasHomogeneas(sp.gettFx().getText(), sp
					.gettFy().getText(), "1"));
		}
		mundo.incluirObjeto3D(listCord, cor, preenchido);
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	protected void criarReta() {
		// TODO Auto-generated method stub

		SemiPonto sp1 = listaSemiPontos.get(0);
		SemiPonto sp2 = listaSemiPontos.get(1);
		mundo.incluirObjeto3D(new CoordenadasHomogeneas(sp1.gettFx().getText(),
				sp1.gettFy().getText(), sp1.gettFz().getText()),
				new CoordenadasHomogeneas(sp2.gettFx().getText(), sp2.gettFy()
						.getText(), sp2.gettFz().getText()), cor);
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	protected void criarPonto() {
		// TODO Auto-generated method stub
		SemiPonto sp = listaSemiPontos.get(0);

		mundo.incluirObjeto3D(new CoordenadasHomogeneas(sp.gettFx().getText(),
				sp.gettFy().getText(), sp.gettFz().getText()), cor);
		InterfaceGrafica.getInstance().exibirObjetos();
	}

}
