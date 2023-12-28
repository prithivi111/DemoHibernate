package OneToManyMapping;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Question {
		
		 @Id
		 @Column(name="question_id")
		private int questionId;
		
		private String question;
		
		@OneToMany(mappedBy ="question")
		private List<Answer> answers;
		

		public Question() {
			super();
		}
		
		
		public List<Answer> getAnswers() {
			return answers;
		}


		public void setAnswers(List<Answer> answers) {
			this.answers = answers;
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


		public Question(int questionId, String question, List<Answer> answers) {
			super();
			this.questionId = questionId;
			this.question = question;
			this.answers = answers;
		}

		




	
		
		
		
		
}
