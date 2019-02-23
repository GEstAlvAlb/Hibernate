package Componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import alberto.hibernate.Modelo;
import alberto.hibernate.Objetos.Juego;



public class PanelAnadirJuego extends JPanel implements ActionListener {
	public JPanel panel;
	public JCombo<Juego> cbJuegos;
	public JButton btAnadir;
	public JScrollPane scrollPane;
	public JList<Juego> lJuegos;
	public DefaultListModel<Juego> modeloLista;
	public JButton btEliminar;

	public PanelAnadirJuego() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		cbJuegos = new JCombo<>();
		cbJuegos.setPreferredSize(new Dimension(100, 20));
		panel.add(cbJuegos);
		
		btAnadir = new JButton("+");
		panel.add(btAnadir);
		
		btEliminar = new JButton("-");
		panel.add(btEliminar);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		lJuegos = new JList<>();
		scrollPane.setViewportView(lJuegos);
		modeloLista = new DefaultListModel<>();
		lJuegos.setModel(modeloLista);

		inicializar();
	}
	
	private void inicializar() {
		Modelo modelo = new Modelo();
//		List<Juego> juego = modelo.getArmasLibres();
//		cbJuegos.inicializar(juego);
		
		btAnadir.addActionListener(this);
		btEliminar.addActionListener(this);
	}
	
	public List<Juego> getListadoArmas() {
		return Collections.list(modeloLista.elements());
	}
	
	public void anadirArmas(List<Juego> juegos) {
		modeloLista.removeAllElements();
		for (Juego juego : juegos)
			modeloLista.addElement(juego);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
		case "+":
			Juego juegoSeleccionado = cbJuegos.getDatoSeleccionado();
			if (juegoSeleccionado == null)
				return;
			
			if (modeloLista.contains(juegoSeleccionado))
				return;
			
			modeloLista.addElement(juegoSeleccionado);
			break;
		case "-":
			if (lJuegos.getSelectedIndex() == -1)
				return; 
			
			cbJuegos.addItem(modeloLista.remove(lJuegos.getSelectedIndex()));
			break;
		}
	}

}