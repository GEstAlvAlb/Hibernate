package alberto.hibernate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.JarURLConnection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Componentes.PanelAutor;
import Componentes.PanelJuegos;
import alberto.hibernate.Objetos.Autor;
import alberto.hibernate.Objetos.Juego;

public class Controlador implements ActionListener, ListSelectionListener, FocusListener, KeyListener {

	private Vista vista;
	private Modelo modelo;

	public enum Accion {
		NUEVO, MODIFICAR, GUARDAR, ELIMINAR, PANEL_JUEGOS, PANEL_AUTORES
	}

	private Accion accion;
	private Juego juegoActual;
	private Autor autorActual;

	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;

		vista.jEstado.setMensajeError("Juegos");
		addListeners();
		cargarDatos();
	}

	private void cargarDatos() {
		List<Juego> juegos=modelo.getJuegos();
		vista.mJuego.removeAllElements();
		for(Juego juego:juegos)
			vista.mJuego.addElement(juego);
		
	}

	private void addListeners() {
		vista.miAutor.addActionListener(this);
		vista.miJuego.addActionListener(this);

	}

	private void nuevoJuego() {
		modoEdicion(true);
	}

	private void modificarJuego() {
		// TODO Auto-generated method stub

	}

	private void modoEdicion(boolean edicion) {

	}

	private void guardarJuego() {

	}

	private void eliminarJuego() {
		if (JOptionPane.showConfirmDialog(null, "Estas seguro", "modificar",
				JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			return;
		modelo.eliminar(juegoActual);
		cargarDatos();
	}

	public void actionPerformed(ActionEvent event) {
		
		Accion accion=Accion.valueOf(event.getActionCommand());
		switch (accion) {
		case NUEVO:
			nuevoJuego();
			
			break;
		case MODIFICAR:
			modificarJuego();
			break;
		case GUARDAR:
			guardarJuego();
			break;
		case ELIMINAR:
			eliminarJuego();
			break;
		case PANEL_JUEGOS:
			PanelJuegos panelJuegos=new PanelJuegos(modelo);
			vista.tpPaneles.addTab("Juegos",panelJuegos);
			break;
		case PANEL_AUTORES:
			PanelAutor panelAutor=new PanelAutor(modelo);
			vista.tpPaneles.addTab("Autores",panelAutor);
			break;
			
		default:
			break;
		}
		
	}
	
	private void comprobarNombre() {
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		comprobarNombre();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		comprobarNombre();

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
