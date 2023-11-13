package terminal;

import java.util.Scanner;

public interface CommandHandler
{
    void handle(String[] parts, Scanner scanner);
}
