package alberto.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mysql.cj.Session.SessionEventListener;

import alberto.hibernate.Objetos.Autor;
import alberto.hibernate.Objetos.Juego;

/**
 * Hello world!
 *
 */
public class Modelo 
{
	public Modelo() {
		conectar();
	}
	@Override
	public void finalize() {
		desconectar();
	}

	private void desconectar() {
		HibernateUtil.closeSessionFactory();
		
	}
	private void conectar() {
		HibernateUtil.builSessionFactory();
		
	}
	
	public void guardar(Juego juego) {
		Session sesion=HibernateUtil.getCurrenSession();
		sesion.beginTransaction();
		sesion.save(juego);
		
		for(Autor autor:juego.getAutor()) {
			autor.setJuegos(juego);
			sesion.save(autor);
			
		}
		sesion.getTransaction().commit();
		sesion.close();
		
	}
	
	public void eliminar(Juego juego) {
		Session sesion=HibernateUtil.getCurrenSession();
		sesion.beginTransaction();
		sesion.delete(juego);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public boolean ExisteJuego(String nombre) {
		Session sesion=HibernateUtil.getCurrenSession();
		Query<Juego> query=sesion.createQuery("FROM Juego WHERE nombre=:nombre");
		query.setParameter("nombre", nombre);
		Juego juego=query.uniqueResult();
		
		return (juego!=null);
		
		
	}
	public List<Juego> getJuegos(String busqueda){
		Session session=HibernateUtil.getCurrenSession();
		ArrayList<Juego> juegos=(ArrayList<Juego>) session.createQuery("FROM Juego").list();
		return juegos;
		
	}
	
	public void guardar(Autor autor) {
		Session sesion=HibernateUtil.getCurrenSession();
		sesion.beginTransaction();
		sesion.save(autor);
		sesion.getTransaction().commit();
		sesion.close();
		
	}
	
	public void elinar(Autor autor) {
		Session session=HibernateUtil.getCurrenSession();
		session.beginTransaction();
		session.delete(autor);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public List<Autor> getAutor() {
		Session sesion=HibernateUtil.getCurrenSession();
		ArrayList<Autor>autores=(ArrayList<Autor>) sesion.createQuery("FROM Autor").list();
		return autores;
	}
	public List<Juego> getJuegos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
