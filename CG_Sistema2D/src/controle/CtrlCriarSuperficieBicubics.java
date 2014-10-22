package controle;

import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;

import visao.InterfaceGrafica;
import modelo.Mundo;
import modelo.SemiPonto;

public class CtrlCriarSuperficieBicubics extends CtrlCriarSpline {

	private ArrayList<ArrayList<SemiPonto>> curvasCtrl;

	public CtrlCriarSuperficieBicubics(JFrame framePai,
			ArrayList<ArrayList<SemiPonto>> curvasControl,
			JColorChooser corChooser) {
		super(framePai, null, corChooser);
		// TODO Auto-generated constructor stub
		this.curvasCtrl = curvasControl;
	}

	protected void criarBCubics() {
		framePai.dispose();
		Mundo.getInstance().incluirSuperficieBicubica(curvasCtrl, corChooser.getColor());
		InterfaceGrafica.getInstance().exibirObjetos();
	}
}
