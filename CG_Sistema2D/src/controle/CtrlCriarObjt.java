package controle;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CtrlCriarObjt implements ActionListener, KeyListener {

	protected JFrame frame;
	protected JLabel lblQuant;
	protected JTextField entryQuant;
	protected JButton btQuant;
	protected JLabel lblCor;
	protected JColorChooser corChooser;
	protected JCheckBox checkPreenchido;
	protected JLabel lblPreench;

	public CtrlCriarObjt() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		obterPontos();
	}

	protected void obterPontos() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(650, 400);
		frame.setTitle("Criar Objeto");
		frame.setLayout(new FlowLayout());
		lblQuant = new JLabel("Quantos pontos tem seu objeto?");
		lblCor = new JLabel("Selecione a cor do seu objeto:");
		lblPreench = new JLabel("Preenchimento de polígonos");
		corChooser = new JColorChooser(Color.WHITE);
		entryQuant = new JTextField(10);
		btQuant = new JButton("OK");
		checkPreenchido = new JCheckBox();
		frame.add(lblQuant);
		frame.add(entryQuant);
		frame.add(lblCor);
		frame.add(corChooser);
		frame.add(btQuant);
		frame.add(checkPreenchido);
		frame.add(lblPreench);
		CtrlPontos ctlPts = new CtrlPontos(frame, entryQuant, corChooser, checkPreenchido);
		btQuant.addActionListener(ctlPts);
		btQuant.addKeyListener(ctlPts);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			obterPontos();
		}
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
