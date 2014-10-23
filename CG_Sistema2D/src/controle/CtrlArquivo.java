package controle;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.InterpretadorObj;

public class CtrlArquivo implements KeyListener, ActionListener {

	private JFrame pai;
	private JColorChooser cor;
	private TextField escal;

	public CtrlArquivo(JFrame frame, JColorChooser cor, TextField escal) {
		// TODO Auto-generated constructor stub
		pai = frame;
		this.cor = cor;
		this.escal = escal;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			criarFileChooser();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void criarFileChooser() throws NumberFormatException, IOException {
		pai.dispose();
		final JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("obj file",
				new String[] { "obj" });
		fc.setFileFilter(filter);
		fc.addChoosableFileFilter(filter);
		fc.showOpenDialog(pai);

		InterpretadorObj inter = new InterpretadorObj(fc.getSelectedFile(),
				cor.getColor(), Integer.parseInt(escal.getText()));
		try {
			inter.criarObjetoObj();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 10)
			try {
				criarFileChooser();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

}
