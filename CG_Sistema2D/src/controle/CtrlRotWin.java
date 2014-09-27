package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import visao.InterfaceGrafica;

import modelo.Janela;

public class CtrlRotWin implements ActionListener, KeyListener {

	private JTextField graus;

	public CtrlRotWin(JTextField entryGraus) {
		// TODO Auto-generated constructor stub
		graus = entryGraus;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10)
			rotacionar();

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
		rotacionar();

	}

	private void rotacionar() {
		// TODO Auto-generated method stub
		System.out.println("Rotacionar janela " + graus.getText()+"º");
		Janela.getInstance().rotacionar(Double.parseDouble(graus.getText()));
		InterfaceGrafica.getInstance().exibirObjetos();
	}

}
