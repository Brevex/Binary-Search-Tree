package terminal;

import binarysearchtree.BinaryTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandPrompt
{
    private final BinaryTree tree;

    // ----------------------- Constructor -------------------------

    public CommandPrompt(BinaryTree tree) { this.tree = tree; }

    // ------------------------- Methods ---------------------------

    public void processCommand(String userInput, Scanner scanner)
    {
        String[] parts = userInput.split(" ");
        String command = parts[0];

        Map <String, CommandHandler> commandHandlers = new HashMap<>();

        commandHandlers.put("insert", this::executeInsertCommand);
        commandHandlers.put("remove", this::executeRemoveCommand);
        commandHandlers.put("print", this::executePrintCommand);
        commandHandlers.put("nth", this::executeNthElementCommand);
        commandHandlers.put("position", this::executePositionCommand);
        commandHandlers.put("median", this::executeMedianCommand);
        commandHandlers.put("mean", this::executeMeanCommand);
        commandHandlers.put("full", this::executeIsFullCommand);
        commandHandlers.put("complete", this::executeIsCompleteCommand);
        commandHandlers.put("preorder", this::executePreOrderCommand);
        commandHandlers.put("search", this::executeSearchCommand);

        CommandHandler handler = commandHandlers.get(command);

        if (handler != null)
        {
            handler.handle(parts, scanner);
        }
        else
        {
            System.out.println("\nUnknown command: " + command);
            System.out.println(" ");
        }
    }

    private void executeInsertCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value for insertion.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (!tree.insertElement(value))
        {
            System.out.println("\nElement " + value + " already exists in the tree.");
        }
        else
        {
            System.out.println("\nElement " + value + " inserted into the tree.");
        }

        System.out.println(" ");
    }

    private void executeRemoveCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value for removal.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (!tree.deleteElement(value))
        {
            System.out.println("\nElement " + value + " does not exist in the tree.");
        }
        else
        {
            System.out.println("\nElement " + value + " removed from the tree.");
        }

        System.out.println(" ");
    }

    private void executePrintCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value to print model.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println(" ");
        tree.printTree(value);
        System.out.println(" ");
    }

    private void executeNthElementCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value to find position.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("\nNth element: " + tree.nthElement(value));
        System.out.println(" ");
    }

    private void executePositionCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value to find position.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("\nPosition occupied by the element " + value + ": " + tree.position(value));
        System.out.println(" ");
    }

    private void executeMedianCommand(String[] parts, Scanner scanner)
    {
        System.out.println("\nTree median: " + tree.median());
        System.out.println(" ");
    }

    private void executeMeanCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value to calculate mean.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("\nArithmetic mean of the nodes of the tree: " + tree.mean(value));
        System.out.println(" ");
    }

    private void executeIsFullCommand(String[] parts, Scanner scanner)
    {
        System.out.println("\nIs this tree full? " + tree.isFull());
        System.out.println(" ");
    }

    private void executeIsCompleteCommand(String[] parts, Scanner scanner)
    {
        System.out.println("\nIs this tree complete? " + tree.isComplete());
        System.out.println(" ");
    }

    private void executePreOrderCommand(String[] parts, Scanner scanner)
    {
        System.out.println("\nTree in pre order: " + tree.printPreOrder());
        System.out.println(" ");
    }

    private void executeSearchCommand(String[] parts, Scanner scanner)
    {
        if (parts.length < 2)
        {
            System.out.println("\nError: Missing value for search.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (tree.searchElement(value) != null)
        {
            System.out.println("\nDoes element " + value + " exist? true.");
        }
        else
        {
            System.out.println("\nDoes element " + value + " exist? false.");
        }

        System.out.println(" ");
    }
}
