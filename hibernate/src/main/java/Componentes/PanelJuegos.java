package Componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alberto.hibernate.Modelo;
import alberto.hibernate.Objetos.Juego;



/**
 * Panel principal para gestionar Armas
 */
public class PanelJuegos extends JPanel implements ActionListener, MouseListener {
	public JBotones botonesCrud;
	public JTextField tfNombre;
	public JTextField tfAtaque;
	public JTextField tfDuracion;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2;
	public panelBusqueda<Juego> panelBusqueda;
	
	private Modelo modelo;
	private Accion accion;

	public PanelJuegos(Modelo modelo) {
		this.modelo = modelo;
		setLayout(null);
		
		botonesCrud = new JBotones();
		botonesCrud.setBounds(10, 94, 179, 107);
		add(botonesCrud);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(62, 10, 86, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfAtaque = new JTextField();
		tfAtaque.setBounds(62, 36, 86, 20);
		add(tfAtaque);
		tfAtaque.setColumns(10);
		
		tfDuracion = new JTextField();
		tfDuracion.setBounds(62, 62, 86, 20);
		add(tfDuracion);
		tfDuracion.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 10, 37, 14);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Ataque");
		lblNewLabel_1.setBounds(10, 36, 35, 14);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Duraci\u00F3n");
		lblNewLabel_2.setBounds(10, 62, 42, 14);
		add(lblNewLabel_2);
		
		panelBusqueda = new panelBusqueda<>();
		panelBusqueda.setBounds(208, 124, 258, 150);
		add(panelBusqueda);
		
		botonesCrud.addListeners(this);
		
		inicializar();
	}
	
	private void inicializar() {
		panelBusqueda.inicializar(modelo.getJuegos());
		panelBusqueda.addListener(this);
		modoEdicion(false);
	}
	
	private void modoEdicion(boolean edicion) {
		tfNombre.setEditable(edicion);
		tfAtaque.setEditable(edicion);
		tfDuracion.setEditable(edicion);
		
		botonesCrud.modoEdicion(edicion);
	}
	
	/**
	 * Carga el arma que se le pasa como parámetro, que será la que se haya seleccionado
	 * @param arma
	 */
	private void cargar(Juego juego) {
		tfNombre.setText(juego.getNombre());
		tfAtaque.setText(String.valueOf(juego.getFecha()));
		tfDuracion.setText(String.valueOf(juego.getGenero()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		Juego juego = null;
		switch (actionCommand) {
			case "nuevo":
				tfNombre.setText("");
				tfAtaque.setText("");
				tfDuracion.setText("");
				modoEdicion(true);
				accion = Accion.NUEVO;
				break;
			case "modificar":
				modoEdicion(true);
				accion = Accion.MODIFICAR;
				break;
			case "guardar":
				if (Util.mensajeConfirmacion() == Util.NO)
					return;
				
				String nombre = tfNombre.getText();
				int ataque = Integer.parseInt(tfAtaque.getText());
				int duracion = Integer.parseInt(tfDuracion.getText());
				
				switch (accion) {
					case NUEVO:
						juego = new Arma();
						break;
					case MODIFICAR:
						juego = panelBusqueda.getSeleccionado();
						break;
					default:
						Util.mensajeError("Operación desconocida");
						break;
				}
				juego.setNombre(nombre);
				juego.setAtaque(ataque);
				juego.setDuracion(duracion);
				
				modelo.guardar(juego);
				panelBusqueda.refrescar();
				modoEdicion(false);
				break;
			case "eliminar":
				if (!panelBusqueda.estaSeleccionado())
					return;
				
				if (Util.mensajeConfirmacion() == Util.NO)
					return;
				
				juego = panelBusqueda.getSeleccionado();
				modelo.eliminar(juego);
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
		Arma armaSeleccionada = panelBusqueda.getSeleccionado();
		if (armaSeleccionada == null)
			return;
		
		cargar(armaSeleccionada);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
}