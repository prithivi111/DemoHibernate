package OneToOneMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClass {
	public static void main(String[] args) {
	
		//creating question
	Question question = new Question();
	question.setQuestionId(1212);
	question.setQuestion("What is Java?");	
		
		//creating answer
	Answer answer = new Answer();
	answer.setAnswerId(343);
	answer.setAnswer("Java is programing language");
	answer.setQuestion(question);
	
	question.setAnswer(answer);
	
		//creating new question
	Question q2 = new Question();
	q2.setQuestionId(242);
	q2.setQuestion("What is Collection Framewrok?");
	
		//creating new question
	Answer answer1 = new Answer();
	answer1.setAnswerId(344);
	answer1.setAnswer("API to work");
	answer1.setQuestion(q2);
	
	q2.setAnswer(answer1);	
	
	Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Question.class).addAnnotatedClass(Answer.class);;
	SessionFactory sf = con.buildSessionFactory();
	Session ss = sf.openSession();
	
	Transaction tx = ss.beginTransaction();
	
	ss.save(answer);
	ss.save(answer1);
	ss.save(question);
	ss.save(q2);
	
	
	//lets fetch
	
	Question newQ = new Question();
	newQ= (Question)ss.get(Question.class, 1212);
	System.out.println(newQ.getQuestion());
	System.out.println(newQ.getQuestionId());
	System.out.println(newQ.getAnswer().getAnswer());
	
	tx.commit();
	
	
	}
}
