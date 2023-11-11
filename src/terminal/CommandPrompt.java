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

        commandHandlers.put("INSIRA", this::execInsert);
        commandHandlers.put("REMOVA", this::execRemove);
        commandHandlers.put("IMPRIMA", this::execPrint);
        commandHandlers.put("ENESIMO", this::execNthElement);
        commandHandlers.put("POSICAO", this::execPosition);
        commandHandlers.put("MEDIANA", this::execMedian);
        commandHandlers.put("MEDIA", this::execMean);
        commandHandlers.put("CHEIA", this::execIsFull);
        commandHandlers.put("COMPLETA", this::execIsComplete);
        commandHandlers.put("PREORDEM", this::execPreOrder);
        commandHandlers.put("BUSCAR", this::execSearch);

        CommandHandler handler = commandHandlers.get(command);

        if (handler != null)
        {
            handler.handle(parts, scanner);
        }
        else
        {
            System.out.println(" ");
            System.out.println("Unknown command: " + command);
            System.out.println(" ");
        }
    }

    private void execInsert(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value for insertion.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (tree.searchElement(value) != null)
        {
            System.out.println("Element " + value + " already exists in the tree.");
        }
        else
        {
            tree.insertElement(value);

            System.out.println("Element " + value + " inserted into the tree.");
        }

        System.out.println(" ");
    }

    private void execRemove(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value for removal.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (tree.searchElement(value) != null)
        {
            tree.deleteElement(value);

            System.out.println("Element " + value + " removed from the tree.");
        }
        else
        {
            System.out.println("Element " + value + " does not exist in the tree.");
        }

        System.out.println(" ");
    }

    private void execNthElement(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value to find position.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("Nth element: " + tree.nthElement(value));
        System.out.println(" ");
    }

    private void execPosition(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value to find position.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("Position occupied by the element " + value + ": " + tree.position(value));
        System.out.println(" ");
    }

    private void execMedian(String[] parts, Scanner scanner)
    {
        System.out.println(" ");
        System.out.println("Tree median: " + tree.median());
        System.out.println(" ");
    }

    private void execMean(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value to calculate mean.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        System.out.println("Arithmetic mean of the nodes of the tree: " + tree.mean(value));
        System.out.println(" ");
    }

    private void execIsFull(String[] parts, Scanner scanner)
    {
        System.out.println(" ");
        System.out.println("Is this tree full? " + tree.isFull());
        System.out.println(" ");
    }

    private void execIsComplete(String[] parts, Scanner scanner)
    {
        System.out.println(" ");
        System.out.println("Is this tree complete? " + tree.isComplete());
        System.out.println(" ");
    }

    private void execPreOrder(String[] parts, Scanner scanner)
    {
        System.out.println(" ");
        System.out.println("Tree in pre order: " + tree.printPreOrder());
        System.out.println(" ");
    }

    private void execPrint(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value to print model.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        tree.printTree(value);
        System.out.println(" ");
    }

    private void execSearch(String[] parts, Scanner scanner)
    {
        System.out.println(" ");

        if (parts.length < 2)
        {
            System.out.println("Error: Missing value for search.");
            System.out.println(" ");
            return;
        }

        int value = Integer.parseInt(parts[1]);

        if (tree.searchElement(value) != null)
        {
            System.out.println("Does element " + value + " exist? true.");
        }
        else
        {
            System.out.println("Does element " + value + " exist? false.");
        }

        System.out.println(" ");
    }

    private interface CommandHandler { void handle(String[] parts, Scanner scanner); }
}
