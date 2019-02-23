package alberto.hibernate.Objetos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name = "juego")
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "nombre")
	String nombre;
	@Column(name = "fecha")
	String fecha;
	@Column(name = "genero")
	String genero;
	private List<Autor> autores;

	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "juego_autor", joinColumns = { @JoinColumn(name = "id_juego") }, inverseJoinColumns = {
			@JoinColumn(name = "id_arma") })
	public List<Autor> getAutor() {
		return autores;
	}

	public Juego(int id, String nombre, String fecha, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Juego [nombre=" + nombre + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}