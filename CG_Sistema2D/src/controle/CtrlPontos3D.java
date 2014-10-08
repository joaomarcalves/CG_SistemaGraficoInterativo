package controle;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
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
import modelo.SemiPonto3D;

public class CtrlPontos3D extends CtrlPontos {

	public CtrlPontos3D(JFrame frameFilho, JTextField entryQuant2,
			JColorChooser corChooser, JCheckBox checkPreenchido) {
		super(frameFilho, entryQuant2, corChooser, checkPreenchido);
		// TODO Auto-generated constructor stub
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
			pnlSec.setLayout(new GridLayout((quant + 1), 4));
			pnlSec.add(new Label("nome"));
			pnlSec.add(new Label("x"));
			pnlSec.add(new Label("y"));
			pnlSec.add(new Label("z"));

			listaSemiPontos = new ArrayList<SemiPonto>();

			for (int i = 1; i <= quant; i++) {
				SemiPonto3D sp = new SemiPonto3D(new JLabel("P" + i),
						new JTextField(5), new JTextField(5), new JTextField(5));
				listaSemiPontos.add(sp);
				pnlSec.add(sp.getNome());
				pnlSec.add(sp.gettFx());
				pnlSec.add(sp.gettFy());
				pnlSec.add(sp.gettFz());
			}
			switch (quant) {
			case 1:
				btCriarObjt = new JButton("Criar Ponto");
				break;
			case 2:
				btCriarObjt = new JButton("Criar Reta");
				break;
			default:
				btCriarObjt = new JButton("Criar Poliedro");
			}

			ControladorDeObjetos ctrlObjetos = new ControladorDeObjetos3D(frame,
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
}
