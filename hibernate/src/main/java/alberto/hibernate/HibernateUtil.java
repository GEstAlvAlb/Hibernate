package alberto.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import alberto.hibernate.Objetos.Autor;
import alberto.hibernate.Objetos.Juego;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;

	public static void builSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		configuration.addAnnotatedClass(Autor.class);
		configuration.addAnnotatedClass(Juego.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	}

	public static void closeSessionFactory() {
		if (session != null)
			session.close();
		if (sessionFactory != null)
			sessionFactory.close();
	}

	public static Session getCurrenSession() {

		if ((session == null) || (!session.isOpen()))
			openSession();

		return session;
	}

	public static void openSession() {
		session = sessionFactory.openSession();
	}

}
