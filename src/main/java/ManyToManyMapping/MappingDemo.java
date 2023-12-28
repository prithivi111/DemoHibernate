package ManyToManyMapping;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MappingDemo {
	public static void main(String[] args) {
		Configuration con = new Configuration().configure().addAnnotatedClass(Project.class)
				.addAnnotatedClass(Emp.class);
		SessionFactory sf = con.buildSessionFactory();

		
		/*
		 * Emp e1= new Emp();
		 * Emp e2 = new Emp();
		 * e1.setEid(34); 
		 * e1.setName("Ram");
		 *  e2.setEid(35);
			 * e2.setName("Shyam");
			 * 
			 * Project p1 = new Project();
			 * Project p2 = new Project();
			 * 
			 * p1.setPid(12121);
			 * p1.setProjectName("Library Management System");
			 * 
			 * p2.setPid(14214);
			 * p2.setProjectName("CHATBOT");
			 * 
		
			 * 
			 * List<Emp> list1 = new ArrayList<Emp>(); 
			 * List<Project> list2 = newArrayList<Project>();
			 * list1.add(e1); list1.add(e2);
			 * list2.add(p1);
			 * list2.add(p2);
			 * e1.setProjects(list2);
			 * e2.setProjects(list2);
			 * p1.setEmployees(list1);
			 * p2.setEmployees(list1); 
			 * 
			 * session.save(e1);
			 * session.save(e2); 
			 * session.save(p1);
			 * session.save(p2);
			 */
	
		
		Session session = sf.openSession();
		Transaction tx =session.beginTransaction();
		// After the data is stored in DB and new table been created
		// So now lets try to fetch the data with the help of foreign key, and I took 34 in
		// this case. The code is as below:
			
			Emp emp = session.get(Emp.class, 34);
			System.out.println(emp.getName());
			System.out.println(emp);
		
		// Employee ID and employee Name is easy to fetch by using get method.
		// But for the project names, we need to go inside the List of projects.
		// And we need to iterate to find the individual projects as below
		
			for(Project pp : emp.getProjects()) {
				System.out.println(pp.getProjectName());
			}	
		
		//Inside for loop, the record of 34 is fetched and if there are multiple projects inside, 
		//it will display the multiple project names associated.
		
		
		tx.commit();
		session.close();

	}

}
