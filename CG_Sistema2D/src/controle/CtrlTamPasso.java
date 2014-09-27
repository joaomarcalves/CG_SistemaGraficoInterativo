package controle;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CtrlTamPasso implements KeyListener, ActionListener {

	private JFrame frame;
	private JLabel lblTamZoom;
	private JLabel lblTamTrans;
	private JLabel lblTamEscal;
	private JLabel lblTamRot;
	private JTextField entryTamZoom;
	private JTextField entryTamTrans;
	private JTextField entryTamEscal;
	private JTextField entryTamRot;
	private JButton btSalvar;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setarTamanhoDoPasso();

	}

	private void setarTamanhoDoPasso() {
		// TODO Auto-generated method stub
		System.out.println("Obtendo tamanho dos passsos");
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(400, 170);
		frame.setTitle("Configurar tamanho do passo");
		frame.setLayout(new FlowLayout());
		lblTamZoom = new JLabel("Passo do Zoom");
		lblTamTrans = new JLabel("Passo da Translação");
		lblTamEscal = new JLabel("Passo do Escalonamento");
		lblTamRot = new JLabel("Passo da Rotação");
		entryTamZoom = new JTextField("10");
		entryTamTrans = new JTextField("10");
		entryTamEscal = new JTextField("10");
		entryTamRot = new JTextField("10");
		btSalvar = new JButton("Salvar");
		JPanel pnl = new JPanel(new GridLayout(4, 2));
		pnl.add(lblTamZoom);
		pnl.add(entryTamZoom);
		pnl.add(lblTamTrans);
		pnl.add(entryTamTrans);
		pnl.add(lblTamEscal);
		pnl.add(entryTamEscal);
		pnl.add(lblTamRot);
		pnl.add(entryTamRot);
		frame.add(pnl);
		frame.add(btSalvar);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10 || arg0.getKeyCode() == KeyEvent.VK_P) {
			setarTamanhoDoPasso();
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
