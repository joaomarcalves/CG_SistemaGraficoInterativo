package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Direcoes;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlEsqTrans implements ActionListener, KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10) {
			moverObjetoEsq();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		moverObjetoEsq();

	}

	private void moverObjetoEsq() {
		// TipoObjeto objeto = null;
		try {

			int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();

			for (int index : idxObjeto) {
				Mundo.getInstance().objetos().get(index)
						.moverSe(Direcoes.ESQUERDA);
			}

			// objeto = Mundo.getInstance().objetos().get(idxObjeto);
			// objeto.moverSe(Direcoes.ESQUERDA);
			InterfaceGrafica.getInstance().exibirObjetos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			InterfaceGrafica.getInstance().nenhumObjeto();
			e.printStackTrace();
		}
	}

}
