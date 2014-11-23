package quiz.app.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import quiz.app.domain.Question;
import quiz.app.domain.QuestionOption;

@ManagedBean(name = "qAppBean")
@ViewScoped
public class QuizAppMbean {
	private static final List<Question> sampleQuestions = new ArrayList<Question>();

	private QuestionOption selectedOption;

	public QuestionOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(QuestionOption selectedOption) {
		this.selectedOption = selectedOption;
	}

	public List<Question> getSamplequestions() {
		return sampleQuestions;
	}

	private boolean showRes;
	private int totalCorrAnswer;

	public int getTotalCorrAnswer() {
		return totalCorrAnswer;
	}

	public void setTotalCorrAnswer(int totalCorrAnswer) {
		this.totalCorrAnswer = totalCorrAnswer;
	}

	public boolean isShowRes() {
		return showRes;
	}

	public void setShowRes(boolean showRes) {
		this.showRes = showRes;
	}

	int counter = 0;

	public void moveToNextQuestion() {
		if (selectedOption.isAnswer()) {
			totalCorrAnswer++;
		}
		if (counter < sampleQuestions.size()) {
			counter++;

		}

		if (counter >= sampleQuestions.size()) {
			System.out.println("show dialog");

			RequestContext.getCurrentInstance().execute(
					"PF('resultButton').jq.click();");
			return;

		}
		RequestContext
				.getCurrentInstance()
				.execute(
						"PF('dataList').getPaginator().setPage(PF('dataList').getPaginator().cfg.page+1)");

	}

	static {
		List<QuestionOption> q1Options = new ArrayList<QuestionOption>();

		q1Options.add(new QuestionOption("Cardiff", false));
		q1Options.add(new QuestionOption("London", true));
		q1Options.add(new QuestionOption("Edinburgh", false));
		q1Options.add(new QuestionOption("Glasgow", false));
		Question q1 = new Question(1, "Capital of England ?", q1Options, null);
		sampleQuestions.add(q1);

		List<QuestionOption> q2Options = new ArrayList<QuestionOption>();
		q2Options.add(new QuestionOption("XXXXX", false));
		q2Options.add(new QuestionOption("David Cameron", true));
		q2Options.add(new QuestionOption("Ed Miliband", false));
		q2Options.add(new QuestionOption("John", false));
		Question q2 = new Question(1, "PM of UK ?", q2Options, null);
		sampleQuestions.add(q2);
	}

}
