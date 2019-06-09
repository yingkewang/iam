import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String... args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                System.out.format("Input: %s\n", currentLine);
                System.out.format("Output: %s\n", Num2Word.extractNumberToWord(currentLine));
                currentLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
