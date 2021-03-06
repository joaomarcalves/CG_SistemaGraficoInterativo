package controle;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CtrlCriarObjt3D extends CtrlCriarObjt {
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
		CtrlPontos3D ctlPts = new CtrlPontos3D(frame, entryQuant, corChooser,
				checkPreenchido);
		btQuant.addActionListener(ctlPts);
		btQuant.addKeyListener(ctlPts);
	}
}
