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
		Session session = sf.openSession();
		Transaction tx= session.beginTransaction();
		
		//Creating question
		Question q1 = new Question();
		q1.setQuestionId(1212);
		q1.setQuestion("What is java?");

		//creating answer0
		Answer answer = new Answer();
		answer.setAnswerId(343);
		answer.setAnswer("Java is programming language");
		answer.setQuestion(q1);
		
		//creating answer1
		Answer answer1 = new Answer();
		answer1.setAnswerId(33);
		answer1.setAnswer("With the help pf Java we can create softwares");
		answer1.setQuestion(q1);
		
		//creating answer2
		Answer answer2 = new Answer();
		answer2.setAnswerId(363);
		answer2.setAnswer("Java has different types of frameworks");
		answer2.setQuestion(q1);
		
		List<Answer> list = new ArrayList<Answer>();
		list.add(answer);
		list.add(answer1);
		list.add(answer2);
		//since one question can have multiple answers so we gonna add ours answers to question
		q1.setAnswers(list);
		
		session.save(q1);
		session.save(answer);
		session.save(answer1);
		session.save(answer2);
			
//		//Once we store all the data into the Answer table lets fetch the row now.
//		//Since we have the list of objects of answer, we will create an object of question first and use the get method () and session.
		Question q = new Question();
		q = session.get(Question.class,1212);
		System.out.println(q.getQuestion());  //Here, data is fetched from Question table
//
//		
//		//But for printing answers, we have three answers that we saved right, and they are saved in the answer table right?
		//So how can we fetch the answers from the answer table then? 
		//Here, comes the role of newly created colum(question_question_id) in the answer table and the use of annotation 
		//such as @onetomany that we wrote in Question entity that helps in mapping the columns and fetch the data.
		for (Answer a: q.getAnswers()) {  
			//This q.getAnswers() will trigger the answertable of DB and maps to the answer column only.
			//It is because we have mentioned @Onetomany in the Question Class, and since the PK of question class has become
			//the FK of the answer table, Java will understand to map the two tables by using PK-FK relations, and fetch the 
			//data from the answer column only.
			System.out.println(a.getAnswer());  		
			}
		
		tx.commit();
		session.close();
		
	
	}
}

