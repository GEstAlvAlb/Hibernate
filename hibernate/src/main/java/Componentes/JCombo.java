package Componentes;

import java.util.List;

import javax.swing.JComboBox;

public class JCombo<T> extends JComboBox<T> {

	private List<T> datos;

	public JCombo() {
		super();
	}

//	  Carga los datos del Combo y lo refresca

	public void inicializar(List<T> datos) {
		this.datos = datos;
		refrescar();
	}

//	  Refresca el listado del combo

	public void refrescar() {
		limpiar();
		listar();
	}

//	  Lista los datos en el combo

	public void listar() {
		if (datos == null)
			return;

		for (T dato : datos)
			addItem(dato);
	}

//	 Elimina los datos listados en el combo

	public void limpiar() {
		removeAllItems();
	}

//	 Devuelve el elemento seleccionado en el combo

	public T getDatoSeleccionado() {
		T dato = (T) getSelectedItem();
		return dato;
	}
}