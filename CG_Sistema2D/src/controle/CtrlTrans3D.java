package controle;

import java.awt.Component;
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

public class CtrlTrans3D implements KeyListener, ActionListener {

	private JFrame frame;
	private JLabel lblDeslocamento;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblZ;
	private JButton btDeslocar;
	private JPanel pnlPrincipal;
	private JTextField entryX;
	private JTextField entryY;
	private JTextField entryZ;
	private CtrlDesl3D ctrlDesl3D;

	private void getDistanciaEixos() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setTitle("Navegação 3D");
		frame.setSize(200, 140);
		frame.setLayout(new FlowLayout());

		lblDeslocamento = new JLabel("Deslocar");
		lblX = new JLabel("X");
		lblY = new JLabel("Y");
		lblZ = new JLabel("Z");
		entryX = new JTextField();
		entryY = new JTextField();
		entryZ = new JTextField();

		btDeslocar = new JButton("Deslocar");

		pnlPrincipal = new JPanel(new GridLayout(4,2));
		Component c = new Component() {
		};
		pnlPrincipal.add(c);
		pnlPrincipal.add(lblDeslocamento);
		pnlPrincipal.add(lblX);
		pnlPrincipal.add(entryX);
		pnlPrincipal.add(lblY);
		pnlPrincipal.add(entryY);
		pnlPrincipal.add(lblZ);
		pnlPrincipal.add(entryZ);

		ctrlDesl3D = new CtrlDesl3D(frame, entryX, entryY, entryZ);
		btDeslocar.addKeyListener(ctrlDesl3D);
		btDeslocar.addActionListener(ctrlDesl3D);
		
		frame.add(pnlPrincipal);
		frame.add(btDeslocar);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		getDistanciaEixos();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10)
			getDistanciaEixos();
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
