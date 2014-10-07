package modelo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class SemiPonto {
	protected JLabel nome;
	protected JTextField tFx;
	protected JTextField tFy;
	private JTextField tFz;

	public SemiPonto(JLabel label, JTextField tfX, JTextField tfY) {
		// TODO Auto-generated constructor stub
		nome = label;
		tFx = tfX;
		tFy = tfY;
	}

	public JLabel getNome() {
		return nome;
	}

	public void setNome(JLabel nome) {
		this.nome = nome;
	}

	public JTextField gettFx() {
		return tFx;
	}

	public void settFx(JTextField tFx) {
		this.tFx = tFx;
	}

	public JTextField gettFy() {
		return tFy;
	}

	public void settFy(JTextField tFy) {
		this.tFy = tFy;
	}

	public JTextField gettFz() {
		// TODO Auto-generated method stub
		return this.tFz;
	}
}
