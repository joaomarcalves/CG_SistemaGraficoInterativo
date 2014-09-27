package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visao.InterfaceGrafica;

public class CtrlMais implements ActionListener, KeyListener {

	public CtrlMais() {
		// TODO Auto-generated constructor stub
		// canvas.setM
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.diminuirTamanhoDaJanela();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if ((e.getKeyCode() == 10)||(e.getKeyCode() == 61)) {
			diminuirTamanhoDaJanela();
		}

	}

	private void diminuirTamanhoDaJanela() {
		// TODO Auto-generated method stub
		InterfaceGrafica.getInstance().diminuirTamanhoJanela();

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
