package controle;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import visao.InterfaceGrafica;

public class CtrlImportar implements ActionListener, KeyListener {

	private JFrame frame;
	private Label lblCor;
	private Label lblEscal;
	private TextField entryEscal;
	private JColorChooser corChooser;
	private Button btImport;
	private Label lblProcurar;
	private CtrlArquivo ctrlArq;

	public CtrlImportar(InterfaceGrafica interfaceGrafica) {
		// TODO Auto-generated constructor stub
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
		frame = new JFrame();
		frame.setTitle("Importar Objeto");
		frame.setSize(700, 410);
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		corChooser = new JColorChooser(Color.WHITE);
		entryEscal = new TextField(4);
		entryEscal.setText("1");
		ctrlArq = new CtrlArquivo(frame, corChooser, entryEscal);
		lblCor = new Label("Selecione a cor do seu objeto");
		lblEscal = new Label("Deseja já escalonar seu objeto?");
		lblProcurar = new Label("Selecione um arquivo");
		btImport = new Button("Procurar...");
		btImport.addActionListener(ctrlArq);
		btImport.addKeyListener(ctrlArq);
		frame.add(lblCor);
		frame.add(corChooser);
		frame.add(lblEscal);
		frame.add(entryEscal);
		frame.add(lblProcurar);
		frame.add(btImport);
	}
}
