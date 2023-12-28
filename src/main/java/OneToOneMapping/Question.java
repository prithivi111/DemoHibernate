package OneToOneMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Question {
		
		 @Id
		 @Column(name="question_id")
		private int questionId;
		private String question;
		
		@OneToOne   //The reason to write this is--> pick the PKey column, not all columns from Answer table
		@JoinColumn(name="answer_id")  //The picked PKey column is given the same name here to avoid confusion. 
		private Answer answer;
		

		public Question() {
			super();
		}
		
		public Answer getAnswer() {
			return answer;
		}

		public void setAnswer(Answer answer) {
			this.answer = answer;
		}
		
		public int getQuestionId() {
			return questionId;
		}

		public void setQuestionId(int questionId) {
			this.questionId = questionId;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public Question(int questionId, String question, Answer answer) {
			super();
			this.questionId = questionId;
			this.question = question;
			this.answer = answer;
		}




	
		
		
		
		
}
