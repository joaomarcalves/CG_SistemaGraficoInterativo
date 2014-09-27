package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import visao.InterfaceGrafica;

public class CtrlClipping implements KeyListener, ActionListener {

	private boolean clipping;

	public CtrlClipping() {
		// TODO Auto-generated constructor stub
		clipping = true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		aplicarClipping();
	}

	private void aplicarClipping() {
		clipping = !clipping;
		InterfaceGrafica.getInstance().aplicarClipping(clipping);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==10 || arg0.getKeyCode()==KeyEvent.VK_I){
			aplicarClipping();
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
