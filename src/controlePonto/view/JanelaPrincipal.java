package controlePonto.view;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {

	public Container container;
	public HorarioDeTrabalhoView horarioDeTrabalhoView;
	public MarcacoesFeitasView marcacoesFeitasView;
	public AtrasoView atravoView;
	public HoraExtraView horaExtraView; 	

	private static final long serialVersionUID = -8113310048659696964L;

	public JanelaPrincipal() {
		super();
		basicConfigFrame();
		addLayout();
		
		this.horarioDeTrabalhoView = new HorarioDeTrabalhoView(this.container);		
		this.marcacoesFeitasView = new MarcacoesFeitasView(this.container);		
		this.atravoView = new AtrasoView(this.container, this);		
		this.horaExtraView = new HoraExtraView(this.container, this);
	}

	private void addLayout() {
		this.setLayout(new GridLayout(2, 2));
		container = getContentPane();
	}

	private void basicConfigFrame() {
		this.setTitle("Controle de Ponto");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
	}
}
