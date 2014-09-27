package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlRot implements KeyListener, ActionListener {

	private JTextField graus;
	private JRadioButton centro;
	private JRadioButton origem;
	private JRadioButton pontoQqr;
	private JTextField eX;
	private JTextField eY;

	public CtrlRot(JTextField entryGraus, JRadioButton centro,
			JRadioButton origem, JRadioButton pontoQqr, JTextField entryRotX,
			JTextField entryRotY) {
		// TODO Auto-generated constructor stub
		graus = entryGraus;
		this.centro = centro;
		this.origem = origem;
		this.pontoQqr = pontoQqr;
		this.eX = entryRotX;
		this.eY = entryRotY;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		rotacionar();

	}

	private void rotacionar() {
		int[] idxObjeto = InterfaceGrafica.getInstance().objetoAtivo();
		System.out.println("Rotacionar " + graus.getText() + "º");
		try {
			if (centro.isSelected()) {
				System.out.println("Em torno do seu centro");
				for (int index : idxObjeto) {
					Mundo.getInstance().objetos().get(index)
							.rotacionarSe(Double.parseDouble(graus.getText()));
				}
				InterfaceGrafica.getInstance().exibirObjetos();
			} else {
				if (origem.isSelected()) {
					System.out.println("Em torno da origem");
					for (int index : idxObjeto) {
						Mundo.getInstance()
								.objetos()
								.get(index)
								.rotacionarOrigem(
										Double.parseDouble(graus.getText()));
					}
					InterfaceGrafica.getInstance().exibirObjetos();
				} else {
					if (pontoQqr.isSelected()) {
						System.out.println("Em torno de um ponto ("
								+ eX.getText() + ", " + eY.getText() + ")");
						for (int index : idxObjeto) {
							Mundo.getInstance()
									.objetos()
									.get(index)
									.rotacionarAoRedorPto(
											Double.parseDouble(graus.getText()),
											eX.getText(), eY.getText());
						}
						InterfaceGrafica.getInstance().exibirObjetos();
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			InterfaceGrafica.getInstance().nenhumObjeto();
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10) {
			System.out.println("kik");
			this.rotacionar();
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
