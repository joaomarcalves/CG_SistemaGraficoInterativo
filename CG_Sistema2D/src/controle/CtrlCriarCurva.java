package controle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Curvas;
import visao.InterfaceGrafica;

public class CtrlCriarCurva implements KeyListener, ActionListener {

	private JFrame frame;
	private JPanel pnlControle;
	private JTextField pX1;
	private JTextField pX2;
	private JTextField pX3;
	private JTextField pX4;
	private JTextField pY1;
	private JTextField pY2;
	private JTextField pY3;
	private JTextField pY4;
	private JPanel pnlPrincipal;
	private JTextField numPontos;
	private JButton btCurva;
	private CtrlBezier ctrlBezier;
	private JColorChooser corChooser;


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		criarCurva();
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==10)
			criarCurva();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	private void criarCurva() {
		// TODO Auto-generated method stub
		if(InterfaceGrafica.getInstance().algoCurvas().equals(Curvas.BEZIER)){
			curvaBezier();
		}
	}


	private void curvaBezier() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(650, 475);
		frame.setTitle("Criar Objeto");

		pnlPrincipal = new JPanel();
		
		pnlControle = new JPanel();
		pnlControle.setLayout(new GridLayout(5, 3));
		
		corChooser = new JColorChooser(Color.WHITE);

		pX1 = new JTextField();
		pY1 = new JTextField();
		pX2 = new JTextField();
		pY2 = new JTextField();
		pX3 = new JTextField();
		pY3 = new JTextField();
		pX4 = new JTextField();
		pY4 = new JTextField();
		numPontos = new JTextField("25");

		pnlControle.add(new JLabel("Ponto de controle 1"));
		pnlControle.add(pX1);
		pnlControle.add(pY1);
		pnlControle.add(new JLabel("Ponto de controle 2"));
		pnlControle.add(pX2);
		pnlControle.add(pY2);
		pnlControle.add(new JLabel("Ponto de controle 3"));
		pnlControle.add(pX3);
		pnlControle.add(pY3);
		pnlControle.add(new JLabel("Ponto de controle 4"));
		pnlControle.add(pX4);
		pnlControle.add(pY4);
		pnlControle.add(new JLabel("Pontos na curva"));
		pnlControle.add(numPontos);
		
		ctrlBezier = new CtrlBezier(frame, pX1, pX2, pX3, pX4, pX1, pX2, pX3, pX4, pY1, pY2, pY3, pY4, numPontos, corChooser);
		btCurva = new JButton("Criar");
		btCurva.addActionListener(ctrlBezier);
		btCurva.addKeyListener(ctrlBezier);
		
		pnlPrincipal.add(pnlControle);
		//pnlPrincipal.add(btCurva);
		
		frame.add(pnlControle);
		frame.add(corChooser);
		frame.add(btCurva);
		frame.setVisible(true);
		

	}

}
