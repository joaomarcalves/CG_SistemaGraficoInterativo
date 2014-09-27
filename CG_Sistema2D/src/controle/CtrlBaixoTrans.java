package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Direcoes;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlBaixoTrans implements ActionListener, KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			moverObjetoBaixo();
		}

	}

	private void moverObjetoBaixo() {
		try {
			int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();
			for (int index : idxObjeto) {
				System.out.println("Transladando o objeto "+Mundo.getInstance().objetos().get(index).nome()+" para baixo");
				Mundo.getInstance().objetos().get(index)
						.moverSe(Direcoes.BAIXO);
			}
			InterfaceGrafica.getInstance().exibirObjetos();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			InterfaceGrafica.getInstance().nenhumObjeto();
			ex.printStackTrace();
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
		moverObjetoBaixo();
	}

}
