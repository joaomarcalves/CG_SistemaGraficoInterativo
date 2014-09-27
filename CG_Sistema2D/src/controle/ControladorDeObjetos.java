package controle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import modelo.CoordenadasHomogeneas;
import modelo.Mundo;
import modelo.SemiPonto;
import modelo.TipoCoordenadas;
import modelo.TipoMundo;
import visao.InterfaceGrafica;

public class ControladorDeObjetos implements ActionListener, KeyListener {

	private ArrayList<SemiPonto> listaSemiPontos;
	private JFrame framePai;

	private TipoMundo mundo;
	private Color cor;
	private boolean preenchido;

	public ControladorDeObjetos(JFrame frame,
			ArrayList<SemiPonto> listaSemiPontos, JColorChooser colorChooser,
			boolean preenchido) {
		// TODO Auto-generated constructor stub
		mundo = Mundo.getInstance();
		this.listaSemiPontos = listaSemiPontos;
		framePai = frame;
		cor = colorChooser.getColor();
		this.preenchido = preenchido;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		criarMetaObjeto();
	}

	private void criarMetaObjeto() {
		framePai.dispose();
		switch (listaSemiPontos.size()) {
		case 1:
			this.criarPonto();
			break;
		case 2:
			this.criarReta();
			break;
		default:
			this.criarPoligono();
		}
	}

	private void criarPoligono() {
		// TODO Auto-generated method stub
		ArrayList<TipoCoordenadas> listCord = new ArrayList<TipoCoordenadas>();
		for (SemiPonto sp : listaSemiPontos) {
			listCord.add(new CoordenadasHomogeneas(sp.gettFx().getText(), sp
					.gettFy().getText(), "1"));
		}
		mundo.incluirObjeto(listCord, cor, preenchido);
		InterfaceGrafica.getInstance().exibirObjetos();

	}

	private void criarReta() {
		// TODO Auto-generated method stub
		SemiPonto sp1 = listaSemiPontos.get(0);
		SemiPonto sp2 = listaSemiPontos.get(1);
		mundo.incluirObjeto(new CoordenadasHomogeneas(sp1.gettFx().getText(),
				sp1.gettFy().getText(), "1"), new CoordenadasHomogeneas(sp2
				.gettFx().getText(), sp2.gettFy().getText(), "1"), cor);
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	private void criarPonto() {
		// TODO Auto-generated method stub
		SemiPonto sp = listaSemiPontos.get(0);
		mundo.incluirObjeto(new CoordenadasHomogeneas(sp.gettFx().getText(), sp
				.gettFy().getText(), "1"), cor);
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			criarMetaObjeto();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
