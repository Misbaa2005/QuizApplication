import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question 
{
    private String questionText;
    private List<String> options;
    int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) 
    {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() 
    {
        return questionText;
    }

    public List<String> getOptions()
    {
        return options;
    }

    public boolean isCorrectAnswer(int userAnswerIndex) 
    {
        return userAnswerIndex == correctAnswerIndex;
    }
}

class Quiz
{
    private List<Question> questions;
    private int score;

    public Quiz(List<Question> questions)
    {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() 
    {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) 
        {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) 
            {
                System.out.println((j + 1) + ": " + options.get(j));
            }

            int userAnswer = -1;
            while (userAnswer < 0 || userAnswer >= options.size()) 
            {
                System.out.print("Your answer (1-" + options.size() + "): ");
                String input = scanner.nextLine();

                try {
                    userAnswer = Integer.parseInt(input) - 1; // Convert to 0-based index
                    if (userAnswer < 0 || userAnswer >= options.size()){
                        System.out.println("Invalid choice. Please select a number between 1 and " + options.size() + ".");
                    }
                } catch (NumberFormatException e) 
                {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            if (question.isCorrectAnswer(userAnswer)) 
            {
                System.out.println("Correct!");
                score++;
            }
             else 
            {
                System.out.println("Wrong! The correct answer was: " + options.get(question.correctAnswerIndex));
            }
        }
        displayScore();
    }

    public void displayScore() 
    {
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}

public class QuizApplication 
{
    public static void main(String[] args) 
    {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Jupiter", "Saturn"), 1));
        questions.add(new Question("What is the largest ocean on Earth?", List.of("Atlantic", "Indian", "Arctic", "Pacific"), 3));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", List.of("Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"), 1));
        questions.add(new Question("What is the chemical symbol for water?", List.of("O2", "H2O", "CO2", "NaCl"), 1));

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
