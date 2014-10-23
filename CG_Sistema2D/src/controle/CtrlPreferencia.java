package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.Janela;
import visao.InterfaceGrafica;

public class CtrlPreferencia implements ActionListener, KeyListener {

	private JTextField entryCOPd;
	private JTextField entryCOPy;
	private JTextField entryCOPx;
	private JTextField entryTamZoom;
	private JTextField entryTamTrans;
	private JTextField entryTamRot;
	private JTextField entryTamEscal;
	private JFrame framePai;

	public CtrlPreferencia(JFrame frame, JTextField entryTamEscal,
			JTextField entryTamRot, JTextField entryTamTrans,
			JTextField entryTamZoom, JTextField entryCOPx,
			JTextField entryCOPy, JTextField entryCOPd) {
		// TODO Auto-generated constructor stub
		framePai = frame;
		this.entryTamEscal = entryTamEscal;
		this.entryTamRot = entryTamRot;
		this.entryTamTrans = entryTamTrans;
		this.entryTamZoom = entryTamZoom;
		this.entryCOPx = entryCOPx;
		this.entryCOPy = entryCOPy;
		this.entryCOPd = entryCOPd;
	}

	private void setarPreferencias() {
		// TODO Auto-generated method stub
		framePai.dispose();
		Janela.getInstance().setCOP(Double.parseDouble(entryCOPx.getText()),
				Double.parseDouble(entryCOPy.getText()),
				Double.parseDouble(entryCOPd.getText()));
		InterfaceGrafica.getInstance().exibirObjetos();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10)
			setarPreferencias();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setarPreferencias();
	}

}
