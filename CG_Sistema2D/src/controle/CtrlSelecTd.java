package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visao.InterfaceGrafica;
import modelo.Mundo;

public class CtrlSelecTd implements KeyListener, ActionListener {

	private void selecTd() {
		// TODO Auto-generated method stub
		System.out.println("Selecinar tudo");
		InterfaceGrafica.getInstance().selecionarTds();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		selecTd();

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==10){
			selecTd();
		}

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
