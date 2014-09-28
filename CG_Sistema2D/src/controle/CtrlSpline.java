package controle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import modelo.SemiPonto;

public class CtrlSpline implements ActionListener, KeyListener {

	private JSlider m;
	private JFrame framePai;
	private JFrame frame;
	private JPanel pnlPrincipal;
	private JPanel pnlControle;
	private JColorChooser corChooser;
	private JButton btSpline;
	private CtrlCriarSpline ctrlCriarSpline;

	public CtrlSpline(JSlider m, JFrame frame) {
		// TODO Auto-generated constructor stub
		framePai = frame;
		this.m = m;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

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
		int nPontos = m.getValue();
		framePai.dispose();
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(420, (480 + (20 * nPontos)));
		frame.setTitle("Criar Curva B-Spline");
		btSpline = new JButton("Criar B-Spline");

		pnlPrincipal = new JPanel();
		pnlControle = new JPanel();
		pnlControle.setLayout(new GridLayout(nPontos + 1, 3));
		corChooser = new JColorChooser(Color.WHITE);

		ArrayList<SemiPonto> ptsCtrl = new ArrayList<SemiPonto>();
		for (int i = 0; i < nPontos; i++) {
			JLabel lblM = new JLabel("Ponto de controle " + (i + 1));
			pnlControle.add(lblM);
			JTextField pXm = new JTextField();
			JTextField pYm = new JTextField();
			ptsCtrl.add(new SemiPonto(lblM, pXm, pYm));
			pnlControle.add(pXm);
			pnlControle.add(pYm);
		}
		ctrlCriarSpline = new CtrlCriarSpline(frame, ptsCtrl, corChooser);
		btSpline.addActionListener(ctrlCriarSpline);
		btSpline.addKeyListener(ctrlCriarSpline);
		frame.add(pnlControle);
		frame.add(corChooser);
		frame.add(btSpline);
		frame.setVisible(true);
	}

}
