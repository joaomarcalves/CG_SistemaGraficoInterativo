package controle;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.SemiPonto;

public class CtrlPontos implements ActionListener, KeyListener {

	protected JFrame framePai;
	protected JFrame frame;
	protected int quant;
	protected JTextField entryQuant;
	protected JPanel pnlPrincipal;
	protected JPanel pnlSec;
	protected ArrayList<SemiPonto> listaSemiPontos;
	protected JButton btCriarObjt;
	protected JColorChooser colorChooser;
	protected JCheckBox checkPreenchido;

	public CtrlPontos(JFrame frameFilho, JTextField entryQuant2,
			JColorChooser corChooser, JCheckBox checkPreenchido) {
		// TODO Auto-generated constructor stub
		this.framePai = frameFilho;
		this.entryQuant = entryQuant2;
		this.colorChooser = corChooser;
		this.checkPreenchido = checkPreenchido;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		obterPontos();
	}

	protected void obterPontos() {

		try {
			framePai.dispose();
			quant = Integer.parseInt(entryQuant.getText());

			frame = new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(400, 200);
			frame.setTitle("Criar Objeto");

			pnlPrincipal = new JPanel();
			pnlPrincipal.setLayout(new GridLayout(2, 1));

			pnlSec = new JPanel();
			pnlSec.setLayout(new GridLayout((quant + 1), 3));
			pnlSec.add(new Label("nome"));
			pnlSec.add(new Label("x"));
			pnlSec.add(new Label("y"));

			listaSemiPontos = new ArrayList<SemiPonto>();

			for (int i = 1; i <= quant; i++) {
				SemiPonto sp = new SemiPonto(new JLabel("P" + i),
						new JTextField(5), new JTextField(5));
				listaSemiPontos.add(sp);

				pnlSec.add(sp.getNome());
				pnlSec.add(sp.gettFx());
				pnlSec.add(sp.gettFy());
			}
			switch (quant) {
			case 1:
				btCriarObjt = new JButton("Criar Ponto");
				break;
			case 2:
				btCriarObjt = new JButton("Criar Reta");
				break;
			default:
				btCriarObjt = new JButton("Criar Polígno");
			}

			ControladorDeObjetos ctrlObjetos = new ControladorDeObjetos(frame,
					listaSemiPontos, colorChooser, checkPreenchido.isSelected());
			btCriarObjt.addActionListener(ctrlObjetos);
			btCriarObjt.addKeyListener(ctrlObjetos);
			pnlPrincipal.add(pnlSec);
			frame.add(pnlPrincipal);
			frame.add(btCriarObjt);
			frame.setVisible(true);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Número de pontos invalido!");
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
