import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        boolean play = true;

        // WELCOME MESSAGE
        System.out.println(" __        _______ _     ____ ___  __  __ _____ _ \n"
                + " \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| |\n"
                + "  \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | |\n"
                + "   \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|\n"
                + "    \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_)\n"
                + "                                                  \n"
                + "TO THE LETTER GAME");

        // Main loop to play the game
        while (play) {
            // CREATE A NEW GAME
            Game game = new Game();

            // Loop to play the current word until the game is over.
            while (!game.gameOver()) {
                // DRAW FIGURE
                game.draw();

                // PRINT GUESSED LETTERS
                System.out.println(game.getLetters());

                System.out.println("Enter a letter:");
                // GET GUESS FROM USER
                char guess = scan.next().toLowerCase().charAt(0);

                // CHECK IF LETTER GUESSED ALREADY
                while (game.guessedLetterAlready(guess)) {
                    System.out.println("Enter another letter:");
                    guess = scan.next().toLowerCase().charAt(0);
                }

                // CHECK GUESS
                game.guess(guess);
                System.out.println(game.draw());
            }

            // PLAY AGAIN
            System.out.println("Would you like to play again? (type \"y\" for yes, anything else for no)");
            play = scan.next().equals("y");
        }

        // EXIT MESSAGE
        System.out.println("  ______   _______ _ \n"
                + " | __ ) \\ / / ____| |\n"
                + " |  _ \\\\ V /|  _| | |\n"
                + " | |_) || | | |___|_|\n"
                + " |____/ |_| |_____(_)\n"
                + "                     ");
    }
}
