/*

  ------ Developed by Breno Barbosa & Marcos Vinícius ------
 Basic Data Structure II course project – Binary Search Tree.
                          - UFRN -

*/

import binarysearchtree.BinaryTree;
import terminal.CommandPrompt;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TreeProgram
{
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);

        tree.insertElement(32);
        tree.insertElement(13);
        tree.insertElement(5);
        tree.insertElement(41);
        tree.insertElement(20);
        tree.insertElement(60);

        Path filePath = Path.of("src/testFile.txt");

        if (Files.exists(Paths.get(filePath.toUri())))
        {
            executeCommandsFromFile(String.valueOf(filePath), tree);
        }
        else
        {
            System.out.println("\n Input file not found. Entering manual mode.");
            System.out.println("\n If the test file exists, check if its name is 'testFile'.");
            System.out.println(" ");

            startManualCommandMode(tree, scanner);
        }
    }

    // Automatic reading - if a test file exists, it executes commands automatically.

    private static void executeCommandsFromFile(String filePath, BinaryTree tree)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            CommandPrompt commandPrompt = new CommandPrompt(tree);

            while ((line = br.readLine()) != null)
            {
                System.out.println("Executing command: " + line);
                commandPrompt.processCommand(line, null);
            }
        }
        catch (IOException e)
        {
            Logger.getLogger(TreeProgram.class.getName()).log(Level.SEVERE, "Error.", e);
        }
    }

    // Non-automatic reading – if there is no test file, start manual command mode.

    private static void startManualCommandMode(BinaryTree tree, Scanner scanner)
    {
        CommandPrompt commandPrompt = new CommandPrompt(tree);

        while (true)
        {
            System.out.print(">> ");

            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("EXIT"))
            {
                System.out.println(" ");
                System.out.println("Program closed.");
                break;
            }

            commandPrompt.processCommand(userInput, scanner);
        }
        scanner.close();
    }
}
