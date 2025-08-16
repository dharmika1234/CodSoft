import java.util.Random;
import java.util.Scanner;

public class RandomNumberGuessGame {

    public static void main(String[] args) {
        guessNumber();
    }

    public static void guessNumber() {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        int choice = 1;
        while (choice == 1) {
            Random ref = new Random();
            int random = ref.nextInt(101);
            int attempts = 0;
            int guess = -1;

            System.out.println("Enter your guess number in the range of 1-100");

            while (attempts < 6 && guess != random)
            {
                guess = sc.nextInt();
                attempts++;

                while (guess < 1 || guess > 100) {
                    System.out.println("Invalid! Enter your guess in the range of 1-100");
                    guess = sc.nextInt();
                }

                if (guess == random) {
                    System.out.println("You won in " + attempts + " attempts! The number is: " + random);
                    score++;
                    break;
                } else if (Math.abs(random - guess) <= 5) {
                    System.out.println("So close! Think near to the number.");
                } else if (guess < random) {
                    System.out.println("Think higher.");
                } else {
                    System.out.println("Think lesser.");
                }
            }

            if (guess != random) {
                System.out.println("You have used all chances. The number was: " + random);
            }

            System.out.println("Your current score: " + score);
            System.out.println("Press 1 to play again.");
            System.out.println("Press 0 to exit.");

            choice = sc.nextInt();
        }

        System.out.println("Thanks for playing! Your final score was: " + score);
        sc.close();
    }
}
