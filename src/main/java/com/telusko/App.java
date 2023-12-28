package com.telusko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class App {

	public static void main(String[] args) {
		
		Alien telusko = new Alien(); //or Alien telusko = null;
		//telusko.setAid(103);
		telusko.setAname("ramesh");
		telusko.setColor("red");
		
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
	
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		//StandardServiceRegistryBuilder() is a class introduced in Hibernate 4, where are ServiceRegistryBuilder() was there
		//prior to Hibernate 4.If we are using Hibernate 4 or later, it is recommended to use StandardServiceRegistryBuilder()
		// and the change the method name buildServiceRegistry() to build().
		//ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		
		SessionFactory sf = con.buildSessionFactory(reg);
		
		Session session =sf.openSession();   //Session interface belongs to hibernate framework, 
		                                     //that is why we need to add dependency of hibernate in pom.xml to access Session keyword.
		
		Transaction tx = session.beginTransaction();
		
		session.save(telusko);  //save method belongs to Session interface
		
		//Here we are inserting the object values into telusko with the help of using get() method that belongs to session,
		//where we have to mention the class name, and values we want to fectch.
		//We mentioned class name and primarykey value in this case.
		//And we are are also typecasting becasue get() method gives the objects of Object Class, so we need to convert it into
		//Alien class.
		//Also, if you are fetching object, you must not forget to comment out the session.save(telusko),
		//becuase we are not storing any object at the moment.
		
		//telusko = (Alien)session.get(Alien.class, 101);
		
		tx.commit();
		
		System.out.println(telusko); 
		//This print gave the null values in the console before we fetched the objects from the database !
		//But after we inserted the fetched the object's values into telusko(above), 
		//we will be able to see the object's values in the console.
		
		
	}

}
