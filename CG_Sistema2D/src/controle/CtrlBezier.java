package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.CoordenadasHomogeneas;
import modelo.Mundo;
import visao.InterfaceGrafica;

public class CtrlBezier implements KeyListener, ActionListener {

	private JTextField pX1;
	private JTextField pX2;
	private JTextField pX3;
	private JTextField pX4;
	private JTextField pY1;
	private JTextField pY2;
	private JTextField pY3;
	private JTextField pY4;
	private JTextField numPontos;
	private JColorChooser corChooser;
	private JFrame framePai;

	public CtrlBezier(JFrame frame, JTextField pX1, JTextField pX2,
			JTextField pX3, JTextField pX4, JTextField pX12, JTextField pX22,
			JTextField pX32, JTextField pX42, JTextField pY1, JTextField pY2,
			JTextField pY3, JTextField pY4, JTextField numPontos,
			JColorChooser corChooser) {
		// TODO Auto-generated constructor stub
		framePai = frame;
		this.pX1 = pX1;
		this.pX2 = pX2;
		this.pX3 = pX3;
		this.pX4 = pX4;
		this.pY1 = pY1;
		this.pY2 = pY2;
		this.pY3 = pY3;
		this.pY4 = pY4;
		this.numPontos = numPontos;
		this.corChooser = corChooser;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		framePai.dispose();
		ArrayList<CoordenadasHomogeneas> listCord = new ArrayList<CoordenadasHomogeneas>();
		listCord.add(new CoordenadasHomogeneas(pX1.getText(), pY1.getText(),
				"1"));
		listCord.add(new CoordenadasHomogeneas(pX2.getText(), pY2.getText(),
				"1"));
		listCord.add(new CoordenadasHomogeneas(pX3.getText(), pY3.getText(),
				"1"));
		listCord.add(new CoordenadasHomogeneas(pX4.getText(), pY4.getText(),
				"1"));
		System.out.println();

		Mundo.getInstance().incluirCurvaBezier(listCord, corChooser.getColor(),
				numPontos.getText());

		InterfaceGrafica.getInstance().exibirObjetos();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

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
