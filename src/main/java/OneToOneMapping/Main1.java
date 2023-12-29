package OneToOneMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.mappingrelations.Laptop;
import com.mappingrelations.Student;

public class Main {

	public static void main(String[] args) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Question.class).addAnnotatedClass(Answer.class);		
		SessionFactory sf = con.buildSessionFactory();	
		
		//creating answer
		Answer answer = new Answer();
		answer.setAnswerId(343);
		answer.setAnswer("Java is programming language");
				
		//Creating question
		Question q1 = new Question();
		q1.setQuestionId(1212);
		q1.setQuestion("What is java?");
		q1.setAnswer(answer);
		
		answer.setQuestion(q1);
		
		//creating answer
		Answer answer1 = new Answer();
		answer1.setAnswerId(344);
		answer1.setAnswer("API to work with objects in Java");
			
		//Creating question
		Question q2 = new Question();
		q2.setQuestionId(242);
		q2.setQuestion("What is collection framework?");
		q2.setAnswer(answer1);
		
		answer1.setQuestion(q2);
		
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		
		session.save(answer);
		session.save(q1);
		session.save(answer1);
		session.save(q2);
		
		tx.commit();
		
		//Fetching the data with the help of Foreign Key
		
		//Since we have already added fkey in Question Table, we are trying to fetch one row having question id=1212 now.
		 
		Question newQ= session.get(Question.class, 1212);
		System.out.println(newQ.getQuestion());
		//We will get the value from question column "What is java"?
		System.out.println(newQ.getAnswer().getAnswer()); 
		// Since we created the object 'answer' of Answer Class inside Question class, and we know that there are two properties inside  
		// the object 'answer' which is created inside Question class (properties/instances are int answerid, String answer), 
		//so in order to print the value of one of the properties (i.e. String answer), we need to fetch the value of answer object first,
		//that we created inside Question class, and again go inside that object to find the corresponding value.
		//That is why we did getAnswer().getAnswer() above.
											
		session.close();
		
	
	}
}
