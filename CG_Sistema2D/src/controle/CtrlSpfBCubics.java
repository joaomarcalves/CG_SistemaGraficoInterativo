package controle;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class CtrlSpfBCubics implements ActionListener, KeyListener {

	private JFrame frame;
	private CtrlSpline ctrlSpline;
	private JButton btCurva;

	private void criarSpfBCubics() {
		// TODO Auto-generated method stub
		System.out.println("Criando Superfície Bicubica B-Spline");

		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(420, 120);
		frame.setTitle("Criar Curva");
		frame.add(new JLabel(
				"Quantos pontos de controle e quantas curvas você quer?"));
		JSlider m = new JSlider(3, 18, 5);
		m.setMajorTickSpacing(3);
		m.setMinorTickSpacing(1);
		m.setPaintTicks(true);
		m.setPaintLabels(true);
		ctrlSpline = new CtrlSplineBicubics(m, frame);
		btCurva = new JButton("Criar B-Spline");
		btCurva.addActionListener(ctrlSpline);
		frame.add(m);
		frame.add(btCurva);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==10){
			criarSpfBCubics();
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		criarSpfBCubics();

	}

}
