package controle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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

public class CtrlSplineBicubics extends CtrlSpline implements ActionListener,
		KeyListener {

	private JPanel pnlControle2;

	public CtrlSplineBicubics(JSlider m, JFrame frame) {
		super(m, frame);
		// TODO Auto-generated constructor stub
	}

	protected void criarSpline() {
		int nPontos = m.getValue();
		framePai.dispose();
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(420 + (55 * nPontos), (480 + (20 * nPontos)));
		frame.setTitle("Criar Curva B-Spline");
		btSpline = new JButton("Criar B-Spline");

		pnlPrincipal = new JPanel();
		pnlControle2 = new JPanel();
		pnlControle2.setLayout(new GridLayout(1, nPontos));
		corChooser = new JColorChooser(Color.WHITE);

		ArrayList<SemiPonto> ptsCtrl = new ArrayList<SemiPonto>();
		ArrayList<ArrayList<SemiPonto>> curvasCtrl = new ArrayList<ArrayList<SemiPonto>>();
		for (int i = 0; i < nPontos; i++) {
			pnlControle = new JPanel();
			pnlControle.setLayout(new GridLayout(nPontos + 1, 3));
			for (int j = 0; j < nPontos; j++) {
				JLabel lblM = new JLabel("Ponto de controle " + (i + 1));
				pnlControle.add(lblM);
				JTextField pXm = new JTextField(1);
				JTextField pYm = new JTextField(1);
				ptsCtrl.add(new SemiPonto(lblM, pXm, pYm));
				pnlControle.add(pXm);
				pnlControle.add(pYm);
			}
			curvasCtrl.add(ptsCtrl);
			pnlControle2.add(pnlControle);
		}
		ctrlCriarSpline = new CtrlCriarSuperficieBicubics(frame, curvasCtrl, corChooser);
		btSpline.addActionListener(ctrlCriarSpline);
		btSpline.addKeyListener(ctrlCriarSpline);
		frame.add(pnlControle2);
		frame.add(corChooser);
		frame.add(btSpline);
		frame.setVisible(true);
	}

}
