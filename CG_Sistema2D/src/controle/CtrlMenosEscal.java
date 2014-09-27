package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.Escalonamento;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlMenosEscal implements KeyListener, ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		escalonarMenos();
	}

	private void escalonarMenos() {
		try {
			int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();

			for (int index : idxObjeto) {
				Mundo.getInstance().objetos().get(index)
						.escalonarSe(Escalonamento.DIMINUIR);
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
			escalonarMenos();
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
