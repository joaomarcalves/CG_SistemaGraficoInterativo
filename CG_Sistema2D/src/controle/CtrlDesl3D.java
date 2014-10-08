package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.Direcoes;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlDesl3D implements KeyListener, ActionListener {

	private JFrame framePai;
	private JTextField entryX;
	private JTextField entryZ;
	private JTextField entryY;

	public CtrlDesl3D(JFrame frame, JTextField entryX, JTextField entryY,
			JTextField entryZ) {
		// TODO Auto-generated constructor stub
		this.framePai = frame;
		this.entryX = entryX;
		this.entryY = entryY;
		this.entryZ = entryZ;
	}

	private void deslocar() {
		// TODO Auto-generated method stub
		framePai.dispose();
		double dX = Double.parseDouble(entryX.getText());
		double dY = Double.parseDouble(entryY.getText());
		double dZ = Double.parseDouble(entryZ.getText());
		
		try {
			int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();

			for (int index : idxObjeto) {
				Mundo.getInstance().objetos().get(index)
						.moverSe(dX, dY, dZ);
			}
			InterfaceGrafica.getInstance().exibirObjetos();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			InterfaceGrafica.getInstance().nenhumObjeto();
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		deslocar();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10)
			deslocar();

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
