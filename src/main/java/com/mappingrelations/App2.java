package com.mappingrelations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App2 {

	public static void main(String[] args) {
		
		List<Laptop> laptops = new ArrayList<Laptop>();
				
			Laptop laptop1 = new Laptop();
			 laptop1.setLid(106);
			 laptop1.setLname("docker");
			 laptops.add(laptop1);
			 
				Laptop laptop2 = new Laptop();
				laptop2.setLid(107);
				laptop2.setLname("samsung");
				laptops.add(laptop2);	
		
		Student s = new Student();
		s.setName("Bindu");
		s.setRollno(7);
		s.setMarks(100);
		s.setLaptops(laptops);
		
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		
		SessionFactory sf = con.buildSessionFactory(reg);
			
		Session session =sf.openSession();   
			
		session.beginTransaction(); //Transaction tx = session.beginTransaction();
		
		session.save(laptop1);
		session.save(s);
			
		session.getTransaction().commit(); //tx.commit();
		
			
		}

}

