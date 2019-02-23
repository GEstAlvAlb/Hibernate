package alberto.hibernate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Componentes.JEstado;
import alberto.hibernate.Objetos.Juego;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

public class Vista extends JFrame {

	public JPanel contentPane;
	public DefaultListModel<Juego> mJuego;
	public JEstado jEstado;
	public JTabbedPane tpPaneles;
	public JMenu mEditar;
	public JMenuItem miJuego;
	public JMenuItem miAutor;

	public Vista() {
		setMinimumSize(new Dimension(600, 450));
		setPreferredSize(new Dimension(800, 600));
		setTitle("CRUD Hibernate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 368);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		JMenuItem miSalir = new JMenuItem("Salir");
		mnNewMenu.add(miSalir);

		mEditar = new JMenu("Editar");
		menuBar.add(mEditar);

		miJuego = new JMenuItem("Personajes");
		miJuego.setActionCommand("PANEL_PERSONAJES");
		mEditar.add(miJuego);

		miAutor = new JMenuItem("Armas");
		miAutor.setActionCommand("PANEL_ARMAS");
		mEditar.add(miAutor);

		JMenu mnNewMenu_1 = new JMenu("Opciones");
		menuBar.add(mnNewMenu_1);

		JMenuItem miConfiguracion = new JMenuItem("Configuraci\u00F3n");
		mnNewMenu_1.add(miConfiguracion);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem miAcercaDe = new JMenuItem("Acerca de");
		mnAyuda.add(miAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		jEstado = new JEstado();
		contentPane.add(jEstado, BorderLayout.SOUTH);

		tpPaneles = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tpPaneles, BorderLayout.CENTER);

		mJuego = new DefaultListModel<>();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		setLocationRelativeTo(null);
		setVisible(true);
	}

}
