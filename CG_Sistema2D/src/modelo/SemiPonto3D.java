package modelo;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class SemiPonto3D extends SemiPonto {

	private JTextField tfZ;

	public SemiPonto3D(JLabel label, JTextField tfX, JTextField tfY, JTextField tfZ) {
		super(label, tfX, tfY);
		// TODO Auto-generated constructor stub
		this.tfZ = tfZ;
	}
	
	public JTextField gettFz() {
		return tfZ;
	}

}
