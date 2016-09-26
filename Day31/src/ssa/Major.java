package ssa;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("unchecked")
@Entity
@Table(name = "major")
public class Major {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="req_sat")
	private int req_sat;

	public Major() {
		
	}
	
	public Major(String description) {
		super();
		this.description = description;
	}
	
	public Major(String description, int req_sat) {
		super();
		this.description = description;
		this.req_sat = req_sat;
	}
	
	@Override
	public String toString() {
		return (String.format("%4d %-30s %4d", this.id, this.description, this.req_sat));
	}
	
	// Create major and insert to major table
	static void InsertMajor(String description){
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		Major major = new Major(description);
		
		try {
		session.beginTransaction();			
		session.save(major);
		System.out.println("Inserting into <major> table: ");
		System.out.println(major);
		session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();}
	   }
	
	static void UpdateMajorDescription(String oldDesc, String newDesc){
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
		session.beginTransaction();			

		String hql = "update Major set description = :nDesc where description = :oDesc";
		Query query = session.createQuery(hql);
		query.setParameter("nDesc", newDesc);
		query.setParameter("oDesc", oldDesc);
		System.out.printf("Updating major description from %s to %s.\n", oldDesc, newDesc);
		query.executeUpdate();
		
		session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
	static void DeleteMajor(String desc){
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
		session.beginTransaction();			

		String hql = "delete Major where description = :adesc";
		Query query = session.createQuery(hql);
		query.setParameter("adesc", desc);
		query.executeUpdate();
		System.out.printf("Deleting major where description just happens to be %s.\n", desc);
		session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

	static void DisplayAllMajors() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();	
			List<Major> majors = session.createQuery("from Major").list();
			for (Major major:majors) {
				System.out.println(major);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();}
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReq_sat() {
		return req_sat;
	}

	public void setReq_sat(int req_sat) {
		this.req_sat = req_sat;
	}

	
}
