package view;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;




import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JRadioButton;

import java.awt.Font;

public class View {

	private JFrame frmProgramacinEvolutiva;
	private JPanel panelTorneo;
	private JLabel labelTorneo;
	private JTabbedPane grafPanel;
	private int numberofgrafs=0;
	private Button lanzaButton;
	private Button relanzaButton;
	private Button eliminaButton;
	private JRadioButton elitismoRadio;
	private JRadioButton IFRadio;
	private JComboBox<String> seleccionCB;
	private JComboBox<String> mutationCB;
	private JComboBox<String> inicializacionCB;
	private JComboBox<String> bloatingCB;
	private JComboBox<String> calcTamCB;
	private JTextField poblacionTextField;
	private JTextField iteracionesTextField;
	private JTextField crucesTextField;
	private JTextField mutacionTextField;
	private JTextField semillaTextField;
	private JTextField mejorTextField;
	private JTextField entreTextField;
	private JTextField profundidadTextField;
	private JLabel lblMejorAbsoluto;
	private JLabel mejorAbsolutoLabel;
	private JLabel lblProfundidadMax;
	private JLabel calcTam;
	private JTextField cromosomaFinal;

	/**
	 * Launch the application.
	 */
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public View() {
		initialize();
		frmProgramacinEvolutiva.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProgramacinEvolutiva = new JFrame();
		frmProgramacinEvolutiva.setBackground(Color.WHITE);
		frmProgramacinEvolutiva.setTitle("Programaci\u00F3n evolutiva");
		frmProgramacinEvolutiva.getContentPane().setPreferredSize(new Dimension(600, 400));
		frmProgramacinEvolutiva.setBounds(100, 100, 11500, 695);
		frmProgramacinEvolutiva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 1362, 640);
		panel.setPreferredSize(new Dimension(40, 40));
		panel.setMaximumSize(new Dimension(50000, 50000));

		JLabel lblPoblacion = new JLabel("Poblacion");
		lblPoblacion.setBounds(10, 99, 102, 14);

		poblacionTextField = new JTextField();
		poblacionTextField.setText("50");
		poblacionTextField.setBounds(122, 96, 189, 20);
		poblacionTextField.setColumns(10);

		JLabel lblIteraciones = new JLabel("Iteraciones");
		lblIteraciones.setBounds(10, 190, 83, 14);

		iteracionesTextField = new JTextField();
		iteracionesTextField.setText("100");
		iteracionesTextField.setBounds(122, 187, 189, 20);
		iteracionesTextField.setColumns(10);

		JLabel lblCruces = new JLabel("% Cruces");
		lblCruces.setBounds(10, 215, 83, 14);

		crucesTextField = new JTextField();
		crucesTextField.setText("70");
		crucesTextField.setBounds(122, 212, 189, 20);
		crucesTextField.setColumns(10);

		JLabel lblMutation = new JLabel("% Mutaci\u00F3n");
		lblMutation.setBounds(10, 271, 83, 14);

		mutacionTextField = new JTextField();
		mutacionTextField.setText("10");
		mutacionTextField.setBounds(122, 268, 189, 20);
		mutacionTextField.setColumns(10);

		JLabel lblSemilla = new JLabel("Semilla");
		lblSemilla.setBounds(10, 296, 68, 14);

		semillaTextField = new JTextField();
		semillaTextField.setText("0");
		semillaTextField.setBounds(122, 293, 189, 20);
		semillaTextField.setColumns(10);

		seleccionCB = new JComboBox<String>();
		seleccionCB.setBounds(122, 318, 189, 20);
		seleccionCB.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Torneo Probabil\u00EDstico", "Torneo Determin\u00EDstico",
				"Universal Estoc\u00E1stico", "Ruleta" }));
		seleccionCB.setSelectedIndex(3);

		JLabel lblSeleccion = new JLabel("Seleccion ");
		lblSeleccion.setBounds(11, 321, 82, 14);

		labelTorneo = new JLabel("Torneo Prob.");
		labelTorneo.setOpaque(true);
		labelTorneo.setBounds(10, 340, 83, 14);

		panelTorneo = new JPanel();
		panelTorneo.setOpaque(false);
		panelTorneo.setBounds(10, 349, 301, 63);
		frmProgramacinEvolutiva.getContentPane().setLayout(null);
		panelTorneo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTorneo.setLayout(null);

		JLabel lblmejor = new JLabel("%Mejor");
		lblmejor.setBounds(10, 15, 46, 14);
		panelTorneo.add(lblmejor);

		JLabel lblNewLabel = new JLabel("Entre");
		lblNewLabel.setBounds(10, 38, 70, 14);
		panelTorneo.add(lblNewLabel);

		mejorTextField = new JTextField();
		mejorTextField.setText("60.0");
		mejorTextField.setBounds(112, 12, 179, 20);
		panelTorneo.add(mejorTextField);
		mejorTextField.setColumns(10);

		entreTextField = new JTextField();
		entreTextField.setText("3");
		entreTextField.setBounds(112, 35, 179, 20);
		panelTorneo.add(entreTextField);
		entreTextField.setColumns(10);

		lanzaButton = new Button("Lanza una copia");
		lanzaButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		lanzaButton.setBounds(38, 531, 273, 31);

		relanzaButton = new Button("Relanza este AG");
		relanzaButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		relanzaButton.setBounds(38, 568, 273, 34);
		relanzaButton.setVisible(false);

		eliminaButton = new Button("Elimina este AG");
		eliminaButton.setFont(new Font("Dialog", Font.PLAIN, 17));
		eliminaButton.setBounds(38, 608, 273, 32);
		panel.setLayout(null);
		panel.add(lblPoblacion);
		panel.add(poblacionTextField);
		panel.add(lblIteraciones);
		panel.add(iteracionesTextField);
		panel.add(lblCruces);
		panel.add(crucesTextField);
		panel.add(lblMutation);
		panel.add(mutacionTextField);
		panel.add(lblSemilla);
		panel.add(semillaTextField);
		panel.add(seleccionCB);
		panel.add(lblSeleccion);
		panel.add(labelTorneo);
		panel.add(panelTorneo);
		panel.add(lanzaButton);
		panel.add(relanzaButton);
		panel.add(eliminaButton);
		frmProgramacinEvolutiva.getContentPane().add(panel);

		JLabel lblElitismo = new JLabel("Elitismo");
		lblElitismo.setBounds(10, 48, 102, 14);
		panel.add(lblElitismo);

		elitismoRadio = new JRadioButton("");
		elitismoRadio.setBounds(189, 48, 38, 23);
		panel.add(elitismoRadio);

		grafPanel = new JTabbedPane(JTabbedPane.TOP);
		grafPanel.setBounds(438, 53, 886, 509);
		panel.add(grafPanel);
		
		JLabel lblMutacin = new JLabel("Mutaci\u00F3n");
		lblMutacin.setBounds(10, 240, 80, 14);
		panel.add(lblMutacin);
		
		mutationCB = new JComboBox<String>();
		mutationCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Terminal simple", "Funcional simple", "Mutacion de arbol", "Permutacion"}));
		mutationCB.setSelectedIndex(1);
		mutationCB.setBounds(122, 237, 189, 20);
		panel.add(mutationCB);
						
		lblMejorAbsoluto = new JLabel("Mejor absoluto");
		lblMejorAbsoluto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMejorAbsoluto.setForeground(Color.GREEN);
		lblMejorAbsoluto.setBounds(10, 481, 180, 53);
		panel.add(lblMejorAbsoluto);
		
		mejorAbsolutoLabel = new JLabel("0");
		mejorAbsolutoLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		mejorAbsolutoLabel.setForeground(Color.GREEN);
		mejorAbsolutoLabel.setBounds(209, 485, 169, 47);
		panel.add(mejorAbsolutoLabel);
		
		JLabel lblPrcticadanielReyes = new JLabel("PR\u00C1CTICA-2-DANIEL REYES & RA\u00DAL GIL");
		lblPrcticadanielReyes.setForeground(Color.RED);
		lblPrcticadanielReyes.setFont(new Font("Courier New", Font.BOLD, 15));
		lblPrcticadanielReyes.setBounds(10, 11, 368, 26);
		panel.add(lblPrcticadanielReyes);
		
		JLabel lblInicializacin = new JLabel("Inicializaci\u00F3n");
		lblInicializacin.setBounds(10, 74, 102, 14);
		panel.add(lblInicializacin);
		
		inicializacionCB= new JComboBox<String>();
		inicializacionCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Inicializacion completa", "Inicializacion creciente", "Ramped and half"}));
		inicializacionCB.setSelectedIndex(2);
		inicializacionCB.setBounds(122, 71, 189, 20);
		panel.add(inicializacionCB);
		
		JLabel lblBloating = new JLabel("Bloating");
		lblBloating.setBounds(10, 423, 83, 14);
		panel.add(lblBloating);
		
		bloatingCB = new JComboBox<String>();
		bloatingCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Metodo Tarpeian", "Penalizacion bien fundamentada"}));
		bloatingCB.setBounds(122, 420, 189, 20);
		panel.add(bloatingCB);
		
		lblProfundidadMax = new JLabel("Profundidad max");
		lblProfundidadMax.setBounds(10, 124, 102, 14);
		panel.add(lblProfundidadMax);
		
		profundidadTextField = new JTextField();
		profundidadTextField.setText("6");
		profundidadTextField.setBounds(122, 121, 190, 20);
		panel.add(profundidadTextField);
		profundidadTextField.setColumns(10);
		
		JLabel lblUsarIf = new JLabel("Usar IF");
		lblUsarIf.setBounds(10, 149, 83, 14);
		panel.add(lblUsarIf);
		
		IFRadio = new JRadioButton("");
		IFRadio.setBounds(189, 148, 109, 23);
		panel.add(IFRadio);
		
		JLabel lblMejorCromosoma = new JLabel("Mejor Cromosoma");
		lblMejorCromosoma.setForeground(Color.GREEN);
		lblMejorCromosoma.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMejorCromosoma.setBounds(438, 559, 258, 31);
		panel.add(lblMejorCromosoma);
		
		cromosomaFinal = new JTextField();
		cromosomaFinal.setBounds(438, 596, 886, 20);
		panel.add(cromosomaFinal);
		cromosomaFinal.setColumns(10);
		
		calcTam = new JLabel("C\u00E1lculo tam.");
		calcTam.setBounds(10, 448, 83, 14);
		panel.add(calcTam);
		
		calcTamCB = new JComboBox<String>();
		calcTamCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Profundidad", "Num. nodos"}));
		calcTamCB.setBounds(122, 445, 189, 20);
		panel.add(calcTamCB);
		
		/*
		double[] mediait = { 4, 3, 7, 9, 10, 6, 5 };
		double[] mejorit = { 10, 11.3, 12.4, 6, 11, 20, 25 };
		double[] mejorabsoluto = { 10, 11.3, 12.4, 12.4, 12.4, 20, 25 };
		addGraphPanel(mediait, mejorit,mejorabsoluto);
		*/
	}

	public void addGraphPanel(double[] mediait, int[] mejorit,int[] mejorabsoluto,String best) {
		JFreeChart chart = ChartFactory.createXYLineChart("AG", // chart title
				"Iteracion", // x axis label
				"Fitness", // y axis label
				createDataset(mediait, mejorit,mejorabsoluto), // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		ChartPanel plot = new ChartPanel(chart);
		mejorAbsolutoLabel.setText(Integer.toString(mejorabsoluto[mejorabsoluto.length-1]));
		cromosomaFinal.setText(best);
		numberofgrafs++;
		grafPanel.addTab( "AG"+numberofgrafs, null, plot, "");
		grafPanel.setSelectedIndex(numberofgrafs-1);
	}

	
	public void borrarGrafActivo(){
		if(grafPanel.getSelectedIndex()>=0){
			grafPanel.removeTabAt(grafPanel.getSelectedIndex());
			numberofgrafs--;
		}
	}
	
	
	private XYDataset createDataset(double[] mediait, int[] mejorit,int[] mejorabsoluto) {

		XYSeries series1 = new XYSeries("Media");
		for (int i = 0; i < mediait.length; i++) {
			series1.add(i + 1, mediait[i]);
		}

		XYSeries series2 = new XYSeries("Mejor");
		for (int i = 0; i < mejorit.length; i++) {
			series2.add(i + 1, mejorit[i]);
		}
		
		XYSeries series3 = new XYSeries("Mejor Absoluto");
		for (int i = 0; i < mejorabsoluto.length; i++) {
			series3.add(i + 1, mejorabsoluto[i]);
		}

		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		return dataset;
	}

	public void setVisibilityTorneo(boolean visible) {
		panelTorneo.setVisible(visible);
		labelTorneo.setVisible(visible);
	}
	
	public int getPoblacionText() {
		return Integer.parseInt(poblacionTextField.getText());
	}
	
	public int getProfundidadText() {
		return Integer.parseInt(profundidadTextField.getText());
	}

	public int getIteracionesText() {
		return Integer.parseInt(iteracionesTextField.getText());
	}

	public Double getCrucesText() {
		return Double.parseDouble(crucesTextField.getText()) / 100;
	}

	public Double getMutacionText() {
		return Double.parseDouble(mutacionTextField.getText()) / 100;
	}

	public int getSemillaText() {
		return Integer.parseInt(semillaTextField.getText());
	}

	public Double getMejorTextField() {
		return Double.parseDouble(mejorTextField.getText()) / 100;
	}

	public int getEntreTextField() {
		return Integer.parseInt(entreTextField.getText());
	}
	
	public String getInitializationTextField() {
		return (String) inicializacionCB.getSelectedItem();
	}

	public boolean getElitismoButton() {
		return elitismoRadio.isSelected();
	}
	
	public boolean getIFButton() {
		return IFRadio.isSelected();
	}

	public int getSeleccionCBIndex() {
		return seleccionCB.getSelectedIndex();
	}
	
	public int getMutationCBIndex() {
		return mutationCB.getSelectedIndex();
	}
	
	public int getBloatingCBIndex() {
		return bloatingCB.getSelectedIndex();
	}
	
	public int getCalcTamCBIndex() {
		return calcTamCB.getSelectedIndex();
	}

	public void setSeleccionCBListener(ActionListener a) {
		seleccionCB.addActionListener(a);
	}
	
	public void setLanzaButtonListener(ActionListener a) {
		lanzaButton.addActionListener(a);
	}

	public void setRelanzaButtonListener(ActionListener a) {
		relanzaButton.addActionListener(a);
	}

	public void setEliminaButtonListener(ActionListener a) {
		eliminaButton.addActionListener(a);
	}
	
	public void setVisibilityRelanza(boolean b) {
		relanzaButton.setVisible(b);
		
	}
	
}