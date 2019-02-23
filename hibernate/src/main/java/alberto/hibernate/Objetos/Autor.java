package alberto.hibernate.Objetos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Table(name="autor")

public class Autor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="nombre")
	String nombre;
	
	List <Juego> juegos;
	@ManyToMany(cascade=CascadeType.DETACH,mappedBy="autor")
	public List<Juego>getJuegos(){return juegos;}
	
	
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
	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}
	
	
	
	
}