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

		
		
			Emp e1= new Emp();
			Emp e2 = new Emp();
			e1.setEid(34); 
			e1.setName("Ram");
			e2.setEid(35);
			e2.setName("Shyam");
			 
			 Project p1 = new Project();
			 Project p2 = new Project(); 
			 p1.setPid(12121);
			 p1.setProjectName("Library Management System");
			 p2.setPid(14214);
			 p2.setProjectName("CHATBOT");

			 List<Project> list2 = new ArrayList<Project>();
			 list2.add(p1);
			 list2.add(p2);			
			 e1.setProjects(list2);
			 e2.setProjects(list2);
			 
			 List<Emp> list1 = new ArrayList<Emp>(); 
			 list1.add(e1);
			 list1.add(e2);
			 p1.setEmployees(list1);
			 p2.setEmployees(list1); 
			
			 Session session = sf.openSession();
			 Transaction tx =session.beginTransaction();			  
			 session.save(e1);
			 session.save(e2); 
			 session.save(p1);
			 session.save(p2);
			 	
		
		// After the data is stored in DB and new table been created
		// So now lets try to fetch the data with the help of foreign key, and I took 34 in
		// this case. The code is as below:
//			
			Emp emp = session.get(Emp.class, 34);
			System.out.println(emp.getName());
		
		// Employee ID and employee Name is easy to fetch by using get method.
		// But for the project names, we need to go inside the List of projects.
		// And we have created different table for project and store the data there.
			
		// Here comes the picture of @Manytomany relations that we mentioned in our Java code while declaring projects.
		// That will let Java know that there are many to many relationship of the table Employee with some other table out there.
		// and java needs to use the Pkey of employee table, (which is used as FKey by Project table in this case), to fetch the
		// required by mapping the PK and FK relation. 
		// Since there is a new table (emp_project_table) being created in the DB which contains the PKeys of both 
		//Employee and Project tables in this case, and as we are searching the data of employee having ID 34 above, first Java will 
		//look inside the newly created table to find any mapping values present inside there. After it finds that there are two 
		//values, it again maps to the project table to fetch the data requested by the user in the Java code.
		//Since the fetching request made by the user is for the Projects name, Java will fetch the project names, but not the projectids.
		//and display the result
			for(Project pp : emp.getProjects()) {
				System.out.println(pp.getProjectName());
		}		
		
		tx.commit();
		session.close();

	}

}
