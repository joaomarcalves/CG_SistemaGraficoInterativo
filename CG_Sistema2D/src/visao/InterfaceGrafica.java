package visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import modelo.Clippins;
import modelo.Curvas;
import modelo.Direcoes;
import modelo.Janela;
import modelo.Mundo;
import modelo.TipoObjeto;
import controle.CtrlBaixo;
import controle.CtrlBaixoTrans;
import controle.CtrlCima;
import controle.CtrlCimaTrans;
import controle.CtrlClipping;
import controle.CtrlCriarCurva;
import controle.CtrlCriarObjt;
import controle.CtrlCriarObjt3D;
import controle.CtrlDir;
import controle.CtrlDirTrans;
import controle.CtrlEsq;
import controle.CtrlEsqTrans;
import controle.CtrlImportar;
import controle.CtrlMais;
import controle.CtrlMaisEscal;
import controle.CtrlMenos;
import controle.CtrlMenosEscal;
import controle.CtrlRot;
import controle.CtrlRotWin;
import controle.CtrlSelecTd;
import controle.CtrlSpfBCubics;
import controle.CtrlTamPasso;
import controle.CtrlTrans3D;

public class InterfaceGrafica extends JFrame implements WindowListener {
	/**
*
*/
	private static final long serialVersionUID = 1L;
	private static InterfaceGrafica instance;
	private JLabel lblObjeto;
	private JLabel lblNav;
	private JLabel lblZoom;
	private JLabel lblVP;
	private JButton btEsq;
	private JButton btCima;
	private JButton btBaixo;
	private JButton btDir;
	private JButton btMais;
	private JButton btMenos;
	private JButton btObjeto;
	private JPanel pnlEsq;
	private JPanel pnlNav;
	private JPanel pnlZoom;
	private List campoDeObjetos;
	private CanvasViewPort canvas;
	private JPanel pnlCentral;
	private CtrlMais ctrlMais;
	private CtrlCriarObjt ctrlCriarObjt;
	private CtrlImportar ctrlImportar;
	private CtrlMenos ctrlMenos;
	private CtrlEsq ctrlEsq;
	private CtrlCima ctrlCima;
	private CtrlBaixo ctrlBaixo;
	private CtrlDir ctrlDir;
	private JLabel lblTrans;
	private JPanel pnlDir;
	private JPanel pnlNavTrans;
	private JButton btEsqTrans;
	private JButton btCimaTrans;
	private JButton btBaixoTrans;
	private JButton btDirTrans;
	private CtrlEsqTrans ctrlEsqTrans;
	private CtrlCimaTrans ctrlCimaTrans;
	private CtrlBaixoTrans ctrlBaixoTrans;
	private CtrlDirTrans ctrlDirTrans;
	private JLabel lblEscal;
	private JButton btMaisEscal;
	private JButton btMenosEscal;
	private CtrlMaisEscal ctrlMaisEscal;
	private CtrlMenosEscal ctrlMenosEscal;
	private JPanel pnlEscal;
	private JButton btImportar;
	private JPanel pnlCriarImportar;
	private JLabel lblRot;
	private CtrlRot ctrlRot;
	private JTextField entryGraus;
	private JTextField entryRotX;
	private JTextField entryRotY;
	private ButtonGroup tipoRot;
	private JRadioButton tipoOrigem;
	private JRadioButton tipoCentro;
	private JRadioButton tipoPonto;
	private JButton btRot;
	private JPanel pnlPto;
	private JLabel lblX;
	private JLabel lblY;
	private JPanel pnlTipo;
	private JTextField entryGrausWin;
	private JLabel lblRotWin;
	private CtrlRotWin ctrlRotWin;
	private JButton btRotWin;
	private JMenuBar menuBarra;
	private JMenu menu;
	private JMenuItem menuTamPasso;
	private JMenu menuAlgClipp;
	private JRadioButtonMenuItem menuLiangBarsky;
	private JRadioButtonMenuItem menuCS;
	private CtrlTamPasso ctrlTamPasso;
	private JMenuItem menuDesativarClipping;
	private CtrlClipping ctrlClipping;
	private CtrlCriarCurva ctrlCriarCurva;
	private JButton btCriarCurva;
	private JMenu menuAlgCurva;
	private JRadioButtonMenuItem menuBezier;
	private JRadioButtonMenuItem menuSpline;
	private JButton btObjeto3D;
	private JButton btSpf3D;
	private CtrlCriarObjt3D ctrlCriarObjt3D;
	private JButton btTrans3D;
	private CtrlTrans3D ctrlNav3D;
	private JRadioButton tipoX;
	private JRadioButton tipoY;
	private JRadioButton tipoZ;
	private JButton btSelecTudo;
	private CtrlSelecTd ctrlSelecTd;
	private CtrlSpfBCubics ctrlSpfBCubics;

	private InterfaceGrafica() {
		// Criar controlador de configuração
		ctrlTamPasso = new CtrlTamPasso();
		ctrlClipping = new CtrlClipping();
		// Criar a barra de menu
		menuBarra = new JMenuBar();
		// Construir o primeiro menu
		menu = new JMenu("Configurações");
		menu.setMnemonic(KeyEvent.VK_C);
		menu.getAccessibleContext().setAccessibleDescription(
				"Opções avançadas de configuração");
		menuBarra.add(menu);
		menuTamPasso = new JMenuItem("Preferencias", KeyEvent.VK_P);
		menuTamPasso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		menuTamPasso.addActionListener(ctrlTamPasso);
		menuAlgClipp = new JMenu("Algorítmo de Clipping");
		menuDesativarClipping = new JMenuItem("Desativar Clipping");
		menuDesativarClipping.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuDesativarClipping.addActionListener(ctrlClipping);
		menuLiangBarsky = new JRadioButtonMenuItem("Liang-Barsky");
		menuLiangBarsky.setSelected(true);
		menuCS = new JRadioButtonMenuItem("Cohen-Sutherland");
		menuAlgCurva = new JMenu("Algorítmo de Curva");
		menuBezier = new JRadioButtonMenuItem("Bézier");
		menuBezier.setSelected(true);
		menuSpline = new JRadioButtonMenuItem("B-Spline");
		ButtonGroup g1 = new ButtonGroup();
		g1.add(menuLiangBarsky);
		g1.add(menuCS);
		ButtonGroup g2 = new ButtonGroup();
		g2.add(menuBezier);
		g2.add(menuSpline);
		menu.add(menuTamPasso);
		menu.addSeparator();
		menu.add(menuAlgClipp);
		menu.add(menuAlgCurva);
		menuAlgClipp.add(menuDesativarClipping);
		menuAlgClipp.addSeparator();
		menuAlgClipp.add(menuLiangBarsky);
		menuAlgClipp.add(menuCS);
		menuAlgCurva.add(menuBezier);
		menuAlgCurva.add(menuSpline);
		// Características da tela principal
		this.setJMenuBar(menuBarra);
		this.addWindowListener(this);
		this.setLayout(new BorderLayout());
		this.setTitle("Sistema 2D Básico");
		this.setSize(1330, 710);
		lblVP = new JLabel("Viewport", SwingConstants.CENTER);
		canvas = new CanvasViewPort();
		canvas.construirViewPort(Mundo.getInstance().objetos());
		campoDeObjetos = new List();
		campoDeObjetos.setMultipleMode(true);
		pnlCentral = new JPanel();
		pnlCentral.add(lblVP);
		pnlCentral.add(canvas);
		criarPainelDeWindow();
		criarPainelDeTransformacao();
		// Inclusão dos 3 paineis principais na tela
		this.add("West", pnlEsq);
		this.add("Center", pnlCentral);
		this.add("East", pnlDir);
		this.setVisible(true);
		// Centralizando a window
		Janela.getInstance().centralizar();
	}

	private void criarPainelDeWindow() {
		// Criação dos campos necessários
		entryGrausWin = new JTextField();
		entryGrausWin.setText("90");
		// Criação dos controladores de eventos
		ctrlNav3D = new CtrlTrans3D();
		ctrlCriarCurva = new CtrlCriarCurva();
		ctrlCriarObjt3D = new CtrlCriarObjt3D();
		ctrlImportar = new CtrlImportar(this);
		ctrlCriarObjt = new CtrlCriarObjt();
		ctrlMais = new CtrlMais();
		ctrlMenos = new CtrlMenos();
		ctrlEsq = new CtrlEsq();
		ctrlCima = new CtrlCima();
		ctrlBaixo = new CtrlBaixo();
		ctrlDir = new CtrlDir();
		ctrlRotWin = new CtrlRotWin(entryGrausWin);
		ctrlSelecTd = new CtrlSelecTd();
		ctrlSpfBCubics = new CtrlSpfBCubics();
		// Criação dos labels
		lblObjeto = new JLabel("Display File", JLabel.CENTER);
		// lblObjeto.setAlignmentX(0);
		lblNav = new JLabel("Navegação da Janela", JLabel.CENTER);
		lblZoom = new JLabel("Zoom da Janela", JLabel.CENTER);
		lblRotWin = new JLabel("Rotacionar a Janela (º)", JLabel.CENTER);
		// Criação dos botões e adição dos controladores
		btObjeto3D = new JButton("Criar objeto 3D");
		btSelecTudo = new JButton("Selecionar tudo");
		btSpf3D = new JButton("Superfície 3D");
		btObjeto = new JButton("Criar objeto 2D");
		btImportar = new JButton("Importar objeto");
		btEsq = new JButton("←");
		btCima = new JButton("↑");
		btBaixo = new JButton("↓");
		btDir = new JButton("→");
		btTrans3D = new JButton("Navegação 3D");
		btMais = new JButton("+");
		btMenos = new JButton("-");
		btRotWin = new JButton("Rotacionar");
		btCriarCurva = new JButton("Criar curva 2D");
		btSpf3D.addActionListener(ctrlSpfBCubics);
		btSpf3D.addKeyListener(ctrlSpfBCubics);
		btSelecTudo.addActionListener(ctrlSelecTd);
		btSelecTudo.addKeyListener(ctrlSelecTd);
		btTrans3D.addActionListener(ctrlNav3D);
		btTrans3D.addKeyListener(ctrlNav3D);
		btObjeto3D.addActionListener(ctrlCriarObjt3D);
		btObjeto3D.addKeyListener(ctrlCriarObjt3D);
		btCriarCurva.addActionListener(ctrlCriarCurva);
		btCriarCurva.addKeyListener(ctrlCriarCurva);
		btRotWin.addActionListener(ctrlRotWin);
		btRotWin.addKeyListener(ctrlRotWin);
		btMais.addActionListener(ctrlMais);
		btMais.addKeyListener(ctrlMais);
		btMenos.addActionListener(ctrlMenos);
		btMenos.addKeyListener(ctrlMenos);
		btObjeto.addActionListener(ctrlCriarObjt);
		btObjeto.addKeyListener(ctrlCriarObjt);
		btImportar.addActionListener(ctrlImportar);
		btImportar.addKeyListener(ctrlImportar);
		btEsq.addActionListener(ctrlEsq);
		btEsq.addKeyListener(ctrlEsq);
		btEsq.setMnemonic(KeyEvent.VK_LEFT);
		btCima.addActionListener(ctrlCima);
		btCima.addKeyListener(ctrlCima);
		btCima.setMnemonic(KeyEvent.VK_UP);
		btBaixo.addActionListener(ctrlBaixo);
		btBaixo.addKeyListener(ctrlBaixo);
		btBaixo.setMnemonic(KeyEvent.VK_DOWN);
		btDir.addActionListener(ctrlDir);
		btDir.addKeyListener(ctrlDir);
		btDir.setMnemonic(KeyEvent.VK_RIGHT);
		// Criação do painel que contém os botões e campos para a manipulação da
		// window e viewport
		pnlEsq = new JPanel();
		pnlCriarImportar = new JPanel();
		pnlCriarImportar.setLayout(new GridLayout(2, 3));
		pnlEsq.setLayout(new GridLayout(10, 1));
		pnlNav = new JPanel();
		pnlNav.add(btEsq);
		pnlNav.add(btCima);
		pnlNav.add(btBaixo);
		pnlNav.add(btDir);
		pnlZoom = new JPanel();
		pnlZoom.setLayout(new GridLayout(1, 2));
		pnlZoom.add(btMais);
		pnlZoom.add(btMenos);
		pnlEsq.add(lblObjeto);
		pnlEsq.add(campoDeObjetos);
		pnlCriarImportar.add(btObjeto3D);
		pnlCriarImportar.add(btSpf3D);
		pnlCriarImportar.add(btObjeto);
		pnlCriarImportar.add(btCriarCurva);
		pnlCriarImportar.add(btImportar);
		pnlCriarImportar.add(btSelecTudo);
		pnlEsq.add(pnlCriarImportar);
		pnlEsq.add(lblNav);
		pnlEsq.add(pnlNav);
		pnlEsq.add(lblZoom);
		pnlEsq.add(pnlZoom);
		pnlEsq.add(lblRotWin);
		pnlEsq.add(entryGrausWin);
		pnlEsq.add(btRotWin);
	}

	private void criarPainelDeTransformacao() {
		// Criação dos campos necessários
		entryGraus = new JTextField(2);
		entryGraus.setText("45");
		entryRotX = new JTextField(2);
		entryRotY = new JTextField(2);
		tipoCentro = new JRadioButton("Centro");
		tipoCentro.setSelected(true);
		tipoOrigem = new JRadioButton("Origem");
		tipoPonto = new JRadioButton("Ponto:");
		tipoX = new JRadioButton("Eixo x");
		tipoY = new JRadioButton("Eixo y");
		tipoZ = new JRadioButton("Eixo z");
		tipoRot = new ButtonGroup();
		tipoRot.add(tipoCentro);
		tipoRot.add(tipoOrigem);
		tipoRot.add(tipoX);
		tipoRot.add(tipoY);
		tipoRot.add(tipoZ);
		tipoRot.add(tipoPonto);
		// Criação dos controladores de eventos
		ctrlEsqTrans = new CtrlEsqTrans();
		ctrlCimaTrans = new CtrlCimaTrans();
		ctrlBaixoTrans = new CtrlBaixoTrans();
		ctrlDirTrans = new CtrlDirTrans();
		ctrlMaisEscal = new CtrlMaisEscal();
		ctrlMenosEscal = new CtrlMenosEscal();
		ctrlRot = new CtrlRot(entryGraus, tipoCentro, tipoOrigem, tipoPonto,
				tipoX, tipoY, tipoZ, entryRotX, entryRotY);
		// Criação dos labels
		lblTrans = new JLabel("Translação do Objeto", JLabel.CENTER);
		lblEscal = new JLabel("Escalonamento do Objeto", JLabel.CENTER);
		lblRot = new JLabel("Rotação do Objeto (º)", JLabel.CENTER);
		lblX = new JLabel("x", JLabel.CENTER);
		lblY = new JLabel("y", JLabel.CENTER);
		// Criação dos botões e adição dos controladores
		btEsqTrans = new JButton("←");
		btCimaTrans = new JButton("↑");
		btBaixoTrans = new JButton("↓");
		btDirTrans = new JButton("→");
		btMaisEscal = new JButton("+");
		btMenosEscal = new JButton("-");
		btRot = new JButton("Rotacionar");
		btEsqTrans.addActionListener(ctrlEsqTrans);
		btEsqTrans.addKeyListener(ctrlEsqTrans);
		btEsqTrans.setMnemonic(KeyEvent.VK_A);
		btCimaTrans.addActionListener(ctrlCimaTrans);
		btCimaTrans.addKeyListener(ctrlCimaTrans);
		btCimaTrans.setMnemonic(KeyEvent.VK_W);
		btBaixoTrans.addActionListener(ctrlBaixoTrans);
		btBaixoTrans.addKeyListener(ctrlBaixoTrans);
		btBaixoTrans.setMnemonic(KeyEvent.VK_S);
		btDirTrans.addActionListener(ctrlDirTrans);
		btDirTrans.addKeyListener(ctrlDirTrans);
		btDirTrans.setMnemonic(KeyEvent.VK_D);
		btMaisEscal.addActionListener(ctrlMaisEscal);
		btMaisEscal.addKeyListener(ctrlMaisEscal);
		btMenosEscal.addActionListener(ctrlMenosEscal);
		btMenosEscal.addKeyListener(ctrlMenosEscal);
		btRot.addActionListener(ctrlRot);
		btRot.addKeyListener(ctrlRot);
		// Criação do painel que contém os botões e campos para a transformação
		// dos objetos
		pnlDir = new JPanel();
		pnlDir.setLayout(new GridLayout(10, 1));
		pnlDir.add(lblTrans);
		pnlNavTrans = new JPanel();
		pnlNavTrans.setLayout(new GridLayout(1, 4));
		pnlNavTrans.add(btEsqTrans);
		pnlNavTrans.add(btCimaTrans);
		pnlNavTrans.add(btBaixoTrans);
		pnlNavTrans.add(btDirTrans);
		pnlDir.add(pnlNavTrans);
		pnlDir.add(btTrans3D);
		pnlDir.add(lblEscal);
		pnlEscal = new JPanel();
		pnlEscal.setLayout(new GridLayout(1, 2));
		pnlEscal.add(btMaisEscal);
		pnlEscal.add(btMenosEscal);
		pnlDir.add(pnlEscal);
		pnlDir.add(lblRot);
		pnlDir.add(entryGraus);
		pnlTipo = new JPanel();
		pnlTipo.setLayout(new GridLayout(2, 3));
		pnlTipo.add(tipoX);
		pnlTipo.add(tipoY);
		pnlTipo.add(tipoZ);
		pnlTipo.add(tipoCentro);
		pnlTipo.add(tipoOrigem);
		pnlTipo.add(tipoPonto);
		pnlDir.add(pnlTipo);
		pnlPto = new JPanel();
		pnlPto.setLayout(new GridLayout(1, 2));
		pnlPto.add(lblX);
		pnlPto.add(entryRotX);
		pnlPto.add(lblY);
		pnlPto.add(entryRotY);
		pnlDir.add(pnlPto);
		pnlDir.add(btRot);
	}

	public static InterfaceGrafica getInstance() {
		if (instance == null)
			instance = new InterfaceGrafica();
		return instance;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void exibirObjetos() {
		// TODO Auto-generated method stub
		int[] selecionados = campoDeObjetos.getSelectedIndexes();
		campoDeObjetos.removeAll();
		for (TipoObjeto o : Mundo.getInstance().objetos()) {
			campoDeObjetos.add(o.nome() + " " + o.coordenadasString());
		}
		for (int i = 0; i < selecionados.length; i++) {
			campoDeObjetos.select(i);
		}
		this.desenharObjetos();
	}

	public void desenharObjetos() {
		// TODO Auto-generated method stub
		canvas.repaint();
	}

	public void diminuirTamanhoJanela() {
		// TODO Auto-generated method stub
		Janela.getInstance().diminuir();
		canvas.repaint();
	}

	public void aumentarTamanhoJanela() {
		// TODO Auto-generated method stub
		Janela.getInstance().aumentar();
		canvas.repaint();
	}

	public void moverJanela(Direcoes dir) {
		// TODO Auto-generated method stub
		Janela j = Janela.getInstance();
		switch (dir) {
		case ESQUERDA:
			j.moverEsq();
			break;
		case CIMA:
			j.moverCima();
			break;
		case BAIXO:
			j.moverBaixo();
			break;
		case DIREITA:
			j.moverDir();
		}
		canvas.repaint();
	}

	public int[] objetoAtivo() {
		// TODO Auto-generated method stub
		return campoDeObjetos.getSelectedIndexes();
	}

	public void selecionarTds() {
		// TODO Auto-generated method stub
		int tam = campoDeObjetos.getItemCount();
		for (int i = 0; i < tam; i++) {
			campoDeObjetos.select(i);
		}
	}

	public void nenhumObjeto() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null,
				"Selecione um objeto na lista de objetos!");
	}

	public Clippins algoClipping() {
		// TODO Auto-generated method stub
		if (menuLiangBarsky.isSelected())
			return Clippins.LIANG_BARSKY;
		else
			return Clippins.COHNEN_SUTHERLAND;
	}

	public Curvas algoCurvas() {
		if (menuBezier.isSelected())
			return Curvas.BEZIER;
		else
			return Curvas.SPLINE;
	}

	public void aplicarClipping(boolean clipping) {
		// TODO Auto-generated method stub
		if (clipping) {
			menuDesativarClipping.setText("Desativar Clipping");
			canvas.setClipping(clipping);
		} else {
			menuDesativarClipping.setText("Ativar Clipping");
			menuLiangBarsky.setEnabled(false);
			menuCS.setEnabled(false);
			canvas.setClipping(clipping);
		}
	}
}