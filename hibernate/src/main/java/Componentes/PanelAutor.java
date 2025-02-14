package Componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alberto.hibernate.Controlador.Accion;
import alberto.hibernate.Modelo;
import alberto.hibernate.Util;
import alberto.hibernate.Objetos.Autor;;


/**
 * Panel para la gestión de los personajes de la aplicación
 */
public class PanelAutor extends JPanel implements ActionListener, MouseListener {
	public JBotones botonesCrud;
	public JTextField tfNombre;
	public JTextField tfEspecialidad;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public PanelBusqueda<Autor> panelBusqueda;
	public PanelAnadirJuego panelAnadirJuego;
	
	private Modelo modelo;
	private Accion accion;

	/**
	 * Create the panel.
	 */
	public PanelAutor(Modelo modelo) {
		this.modelo = modelo;
		setLayout(null);
		
		botonesCrud = new JBotones();
		botonesCrud.btEliminar.setActionCommand("eliminar");
		botonesCrud.btCancelar.setActionCommand("cancelar");
		botonesCrud.btGuardar.setActionCommand("guardar");
		botonesCrud.btModificar.setActionCommand("modificar");
		botonesCrud.btNuevo.setActionCommand("nuevo");
		botonesCrud.setBounds(10, 120, 185, 116);
		add(botonesCrud);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(74, 11, 86, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfEspecialidad = new JTextField();
		tfEspecialidad.setBounds(74, 37, 86, 20);
		add(tfEspecialidad);
		tfEspecialidad.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 10, 37, 14);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Especialidad");
		lblNewLabel_1.setBounds(10, 36, 54, 14);
		add(lblNewLabel_1);
		
		panelBusqueda = new PanelBusqueda<>();
		panelBusqueda.setBounds(245, 139, 258, 150);
		add(panelBusqueda);
		
		panelAnadirJuego = new PanelAnadirJuego();
		panelAnadirJuego.setBounds(205, 11, 258, 116);
		add(panelAnadirJuego);
		
		inicializar();
	}
	
	private void inicializar() {
		botonesCrud.addListeners(this);
		panelBusqueda.addListener(this);
		
		Modelo modelo = new Modelo();
		panelBusqueda.inicializar(modelo.getAutor());
		modoEdicion(false);
	}
	
	private void modoEdicion(boolean edicion) {
		tfNombre.setEditable(edicion);
		tfEspecialidad.setEditable(edicion);
		
		botonesCrud.modoEdicion(edicion);
	}
	
	private void cargar(Autor autor) {
		tfNombre.setText(autor.getNombre());
		tfEspecialidad.setText(autor.getEspecialidad());
		panelAnadirJuego.anadirArmas(autor.getJuegos());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		Autor autor = null;
		switch (actionCommand) {
			case "nuevo":
				tfNombre.setText("");
				tfEspecialidad.setText("");;
				accion = Accion.NUEVO;
				modoEdicion(true);
				break;
			case "modificar":
				accion = Accion.MODIFICAR;
				modoEdicion(true);
				break;
			case "guardar":
				if (Util.mensajeConfirmacion() == Util.NO)
					return;
				
				String nombre = tfNombre.getText();
				String especialidad = tfEspecialidad.getText();
				
				switch (accion) {
					case NUEVO:
						autor = new Autor();
						break;
					case MODIFICAR:
						autor = panelBusqueda.getSeleccionado();
						break;
				}
				autor.setNombre(nombre);
				autor.setEspecialidad(especialidad);
				autor.getJuegos().addAll(panelAnadirJuego.getListadoArmas());
				
				modelo.guardar(autor);
				panelBusqueda.refrescar();
				break;
			case "eliminar":
				if (!panelBusqueda.estaSeleccionado())
					return;
				
				if (Util.mensajeConfirmacion() == Util.NO)
					return;
				
				autor = panelBusqueda.getSeleccionado();
				modelo.eliminar(autor);
				panelBusqueda.inicializar(modelo.getJuegos());
				break;
			case "cancelar":
				modoEdicion(false);
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		Autor autor = panelBusqueda.getSeleccionado();
		if (autor == null)
			return;
		
		cargar(autor);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}