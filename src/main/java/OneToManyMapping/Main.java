package OneToManyMapping;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Question.class).addAnnotatedClass(Answer.class);		
		SessionFactory sf = con.buildSessionFactory();	
				
		//Creating question
		//Question q1 = new Question();
		//q1.setQuestionId(1212);
		//q1.setQuestion("What is java?");

		//creating answer0
		//Answer answer = new Answer();
		//answer.setAnswerId(343);
		//answer.setAnswer("Java is programming language");
	//	answer.setQuestion(q1);
		
		//creating answer1
		//Answer answer1 = new Answer();
		//answer1.setAnswerId(33);
	//	answer1.setAnswer("With the help pf Java we can create softwares");
	//	answer1.setQuestion(q1);
		
		//creating answer2
	//	Answer answer2 = new Answer();
	//	answer2.setAnswerId(363);
	//	answer2.setAnswer("Java has different types of frameworks");
	//	answer2.setQuestion(q1);
		
	//	List<Answer> list = new ArrayList<Answer>();
	//	list.add(answer);
	//	list.add(answer1);
	//	list.add(answer2);
		//since one question can have multiple answers so we gonna add ours answers to question
	//	q1.setAnswers(list);
		
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		
	//	session.save(q1);
	//	session.save(answer);
	//	session.save(answer1);
	//	session.save(answer2);
		
		
		
		//Once we store all the data into the Answer table lets fetch the row now.
		//Since we have the list of objects of answer, we will create an object of question first and use the get method () and session.
		Question q = session.get(Question.class,1212);
		System.out.println(q.getQuestion());  //Here, data is fetched from Question table

		
		//But for printing answers, we have three answers that we saved right.
		//Here, do not look at the Answer table that we created(which added new column called 'answer') to fetch the answers!!
		//We should always look our code while fetching. 
		//As we have already created object of Question above, and our Question class has list of answers' objects! lets print that!
		for (Answer a: q.getAnswers()) {  //as we have no code mentioned above about the list of answers, because we comment out all codes 
			                              //after we finished loading into the DB, we need to use q.getAnswers(), to get the list of answers.
			
			System.out.println(a.getAnswer());  //this getAnswer() gives the value of instance variable 'answer' which is present inside
												//the object 'answer' declared in Question class 
				//Here the data is being fetched from Answer table
		}
		
		tx.commit();
		session.close();
		
	
	}
}

