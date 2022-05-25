import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Provides ability to read a file.
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;

    // Stores all the available words. Automatically filled using the
    // loadDictionary() method.
    private final ArrayList<String> dictionary = new ArrayList<String>();
    // Stores the user's progress. To begin it's loaded with all underscores
    // using the CreateTemplate() method.
    private final ArrayList<Character> letters = new ArrayList<Character>();
    // Stores the user's previous guesses.
    private final ArrayList<Character> previousGuesses = new ArrayList<Character>();

    // Maximum number of attempts.
    private final int maxAttempts = 6;
    // Current attempt. Increases by 1 if the user guesses an incorrect letter.
    private int currentAttempt = 0;
    // Stores the word to be guessed.
    private final String word;

    // Constructor
    public Game() throws IOException {
        // Loads ArrayList dictionary with all words from dictionary.txt.
        loadDictionary();

        // ASSIGN RANDOM WORD
        word = getRandomWord();

        // Loads ArrayList letters with all underscores, one per letter from the random word.
        createTemplate();
    }

    public String getLetters() {
        // GET LETTERS
        StringBuilder sb = new StringBuilder();
        for (Character letter : letters) {
            sb.append(letter).append(" ");
        }
        return sb.toString();
    }

    public String getLettersNoSpaces() {
        // GET LETTERS NO SPACES
        return getLetters().replace(" ", "");
    }

    public String getWord() {
        // GET WORD
        return word;
    }

    public String getRandomWord() {
        // GET RANDOM WORD
        int randomIndex = (int) (Math.random() * dictionary.size());
        return dictionary.get(randomIndex);
    }

    public boolean guessedLetterAlready(char g) {
        // GUESSED LETTER ALREADY
        return previousGuesses.contains(g);
    }

    public boolean guess(char g) {
        // GUESS
        previousGuesses.add(g);

        if (!word.contains(String.valueOf(g))) {
            currentAttempt++;
            return false;
        }

        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) == g)
                letters.set(i, g);

        return true;
    }

    public String draw() {
        // DRAW
        //  0
        // /|\
        // / \
        switch (currentAttempt) {
            case 1:
                return " 0 \n";
            case 2:
                return " 0 \n | \n";
            case 3:
                return " 0 \n/| \n";
            case 4:
                return " 0 \n/|\\\n";
            case 5:
                return " 0 \n/|\\\n/  ";
            case 6:
                return " 0 \n/|\\\n/ \\";
            default:
                return "";
        }
    }

    public boolean gameOver() {
        // GAME OVER
        boolean hasGuessedWord = !letters.contains('_');
        boolean hasRanOutOfGuesses = currentAttempt >= maxAttempts;
        boolean gameOver = hasGuessedWord || hasRanOutOfGuesses;

        if (gameOver) {
            String message;
            if (hasGuessedWord) {
                message = "You won! ";
            } else {
                message = "You lost! ";
            }
            message += "The correct word was " + word + ".";
            System.out.println(message);
        }

        return gameOver;
    }

    // Loads ArrayList letters with all underscores, one per letter from the random word.
    public void createTemplate() {
        for (int i = 0; i < word.length(); i++) letters.add('_');
    }

    // Loads dictionary.txt to the ArrayList dictionary.
    public void loadDictionary() throws FileNotFoundException {
        try {
            // String basePath = System.getProperty("user.dir") + "/src/main/java/";
            File file = new File( /* basePath + */ "dictionary.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) dictionary.add(reader.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
