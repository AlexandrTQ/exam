package alexandr.userinput;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class ConsoleInput implements UserInput {

    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }
}
