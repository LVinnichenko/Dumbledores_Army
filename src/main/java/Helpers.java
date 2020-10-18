import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helpers {
    public static String getText() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        return text.readLine();
    }
}