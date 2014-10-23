package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Direcoes;
import visao.InterfaceGrafica;

public class CtrlBaixo implements ActionListener, KeyListener {

	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10){
			moverBaixo();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		moverBaixo();
	}

	private void moverBaixo() {
		// TODO Auto-generated method stub
		InterfaceGrafica.getInstance().moverJanela(Direcoes.BAIXO);

	}

}
