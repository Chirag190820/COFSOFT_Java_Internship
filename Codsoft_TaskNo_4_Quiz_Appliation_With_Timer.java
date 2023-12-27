package CODSOFT_ALL_TASKS;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Codsoft_TaskNo_4_Quiz_Appliation_With_Timer {
    private static final int QUESTION_COUNT = 3; // Number of questions
    private static final int TIME_LIMIT_PER_QUESTION = 15; // Time limit for each question in seconds
    private static int score = 0;
    private static int correctAnswers = 0;
    private static int incorrectAnswers = 0;

    private static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private static Question[] quizQuestions = {
        new Question("What is the capital of France?",
                new String[]{"London", "Paris", "Berlin", "Rome"}, 1),
        new Question("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1),
        new Question("What is the largest mammal?",
                new String[]{"Elephant", "Whale", "Giraffe", "Hippopotamus"}, 1)
        // Add more questions with options and correct answers here
    };

    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (final Question current : quizQuestions) {
            System.out.println("Question " + (correctAnswers + incorrectAnswers + 1) + ": " + current.question);

            for (int j = 0; j < current.options.length; j++) {
                System.out.println((j + 1) + ". " + current.options[j]);
            }

            int userChoice = -1;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("Time's up for question " + (correctAnswers + incorrectAnswers + 1) + "!");
                    synchronized (scanner) {
                        scanner.notify();
                    }
                }
            }, TIME_LIMIT_PER_QUESTION * 1000);

            synchronized (scanner) {
                try {
                    System.out.print("Enter your choice (1-" + current.options.length + "): ");
                    if (scanner.hasNextInt()) {
                        userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (userChoice >= 1 && userChoice <= current.options.length) {
                            timer.cancel();
                        }
                    } else {
                        scanner.nextLine(); // Consume invalid input
                    }
                    if (userChoice != -1 && userChoice - 1 == current.correctAnswerIndex) {
                        System.out.println("Correct!");
                        score++;
                        correctAnswers++;
                    } else if (userChoice != -1) {
                        System.out.println("Incorrect!");
                        incorrectAnswers++;
                    } else {
                        System.out.println("No answer submitted for question " + (correctAnswers + incorrectAnswers + 1));
                        incorrectAnswers++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        displayResult();
    }

    private static void displayResult() {
        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + QUESTION_COUNT);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
    }
}
