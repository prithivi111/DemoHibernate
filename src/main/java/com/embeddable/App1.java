package com.embeddable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class App1 {

	public static void main(String[] args) {
		
		AlienName an = new AlienName();
		an.setFname("Janak");
		an.setMname("Raj");
		an.setLname("Shrestha");
		
		Alien1 telusko = new Alien1();
		telusko.setAid(105);
		telusko.setAname(an);
		telusko.setColor("red");
		
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien1.class);
	
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		
		
		SessionFactory sf = con.buildSessionFactory(reg);
		
		Session session =sf.openSession();   
		
		Transaction tx = session.beginTransaction();
		
		session.save(telusko); 

		//telusko = (Alien1)session.get(Alien1.class, 101);
		
		tx.commit();
		
		System.out.println(telusko); 
	
		
	}

}
