package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import modelo.Mundo;
import modelo.SemiPonto;
import visao.InterfaceGrafica;

public class CtrlCriarSpline implements KeyListener, ActionListener {

	protected JFrame framePai;
	protected ArrayList<SemiPonto> ptsCtrl;
	protected JColorChooser corChooser;

	public CtrlCriarSpline(JFrame framePai, ArrayList<SemiPonto> ptsCtrl,
			JColorChooser corChooser) {
		// TODO Auto-generated constructor stub
		this.framePai = framePai;
		this.ptsCtrl = ptsCtrl;
		this.corChooser = corChooser;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		criarBCubics();
	}

	protected void criarBCubics() {
		framePai.dispose();
		Mundo.getInstance().incluirCurvaSpline(ptsCtrl, corChooser.getColor());
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
