package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Direcoes;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlDirTrans implements KeyListener, ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		moverObjetoDir();

	}

	private void moverObjetoDir() {
		try {
			int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();

			for (int index : idxObjeto) {
				Mundo.getInstance().objetos().get(index)
						.moverSe(Direcoes.DIREITA);
			}

			InterfaceGrafica.getInstance().exibirObjetos();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			InterfaceGrafica.getInstance().nenhumObjeto();
			ex.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			moverObjetoDir();
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
