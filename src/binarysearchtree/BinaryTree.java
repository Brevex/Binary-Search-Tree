package binarysearchtree;

public class BinaryTree
{
    private Node root;
    private int counter = 0;

    // ----------------------- Constructor -------------------------

    public BinaryTree() { this.root = null; }

    // -------------------- Conventional Methods -------------------

    // find the place where we want to add a new node to keep the tree sorted (recursive method).

    private Node recursiveInsert(Node currentNode, int value, int nodeLevel)
    {
        if (currentNode == null)
        {
            currentNode = new Node(value, nodeLevel);
        }
        else if (value < currentNode.getValue())
        {
            if (currentNode.getLeft() == null || value != currentNode.getLeft().getValue())
            {
                currentNode.setLeft(recursiveInsert(currentNode.getLeft(), value, nodeLevel + 1));
            }
        }
        else if (value > currentNode.getValue())
        {
            if (currentNode.getRight() == null || value != currentNode.getRight().getValue())
            {
                currentNode.setRight(recursiveInsert(currentNode.getRight(), value, nodeLevel + 1));
            }
        }

        updateSubtreeNodeAmount(currentNode);
        return currentNode;
    }

    public void insertElement(int value) { root = recursiveInsert(root, value, 1); }

    // check if the tree contains a specific value (recursive method).

    private Node recursiveSearch(Node currentNode, int value)
    {
        if (currentNode == null) { return null; }

        if (value == currentNode.getValue()) { return currentNode; }

        if (value < currentNode.getValue())
        {
            return recursiveSearch(currentNode.getLeft(), value);
        }
        else
        {
            return recursiveSearch(currentNode.getRight(), value);
        }
    }

    public Node searchElement(int value) { return recursiveSearch(root, value); }

    // find the node to delete from the tree (recursive method).

    private Node recursiveDelete(Node currentNode, int value)
    {
        if (currentNode == null) { return null; }

        if (value == currentNode.getValue())
        {
            if (currentNode.getLeft() == null && currentNode.getRight() == null) { return null; }

            if (currentNode.getLeft() == null) { return currentNode.getRight(); }

            if (currentNode.getRight() == null) { return currentNode.getLeft(); }

            if (currentNode.getLeft() != null && currentNode.getRight() != null)
            {
                int smallestValue = findSmallestValue(currentNode.getRight());

                currentNode.setValue(smallestValue);
                currentNode.setRight(recursiveDelete(currentNode.getRight(), smallestValue));
                return currentNode;
            }
        }

        if (value < currentNode.getValue())
        {
            currentNode.setLeft(recursiveDelete(currentNode.getLeft(), value));
        }
        else if (value > currentNode.getValue())
        {
            currentNode.setRight(recursiveDelete(currentNode.getRight(), value));
        }

        updateSubtreeNodeAmount(currentNode);
        return currentNode;
    }

    public void deleteElement(int value) { root = recursiveDelete(root, value); }

    // --------------------- Specific Methods ----------------------

    // (1) returns the nth element (counting from 1) of the path in ABB order (symmetrical order).

    public int nthElement(int n)
    {
        if (root == null || n <= 0) { return -1; }

        counter = 0;

        return findNthElement(root, n).getValue();
    }

    // (2) returns the position occupied by an element x in an in-order path symmetrical in ABB (counting from 1).

    public int position(int x)
    {
        if (root == null || x <= 0) { return -1; }

        counter = 0;

        return findPositionInOrder(root, x);
    }

    // (3) returns the element that contains the median of the ABB.

    public int median()
    {
        if (root == null) { return -1; }

        int totalElements = countNodes(root);

        if (totalElements % 2 == 0)
        {
            int median1 = nthElement(totalElements / 2);
            int median2 = nthElement((totalElements / 2) + 1);

            return Math.min(median1, median2);
        }
        else
        {
            return nthElement(totalElements / 2);
        }
    }

    // (4) returns the arithmetic mean of the nodes of the tree that x is the root of.

    public double mean(int x)
    {
        if (root == null) { return 0.0; }

        Node rootNode = searchElement(x);

        int sum = calculateSum(rootNode);
        int totalElements = countNodes(rootNode);

        if (totalElements > 0)
        {
            return (double) sum / totalElements;
        }
        else { return 0.0; }
    }

    // (5) return if the tree is full or not

    private boolean isFullTree(Node root)
    {
        if (root == null) { return true; }

        if (root.getLeft() == null && root.getRight() == null)
        {
            return true;
        }

        if (root.getLeft() != null && root.getRight() != null)
        {
            return isFullTree(root.getLeft()) && isFullTree(root.getRight());
        }

        return false;
    }

    public boolean isFull() { return isFullTree(root); }

    // (6) return if the tree is complete or not

    public boolean isComplete()
    {
        if (root == null) { return true; }

        int height = treeHeight(root);
        int totalElements = countNodes(root);

        return (int) Math.pow(2, height - 1) <= totalElements && totalElements <= (int) Math.pow(2, height) - 1;
    }

    // (7) returns a String that contains the traversal of the tree in a pre-order.

    private String preOrder(Node currentNode)
    {
        if (currentNode == null) {  return ""; }

        String result = currentNode.getValue() + " ";
        result += preOrder(currentNode.getLeft());
        result += preOrder(currentNode.getRight());

        return result;
    }

    public String printPreOrder() { return preOrder(root).trim(); }

    // (8) gives the user the option to print the tree in 2 ways.

    public void printTree(int s)
    {
        if (root == null) { return; }

        switch (s)
        {
            case 1:
                printModelOne(root, 1);
                break;

            case 2:
                printModelTwo(root);
                System.out.println(" ");
                break;

            default:
                System.out.println("Invalid value");
                break;
        }
    }

    // ----------------------- Helper Methods -----------------------

    private void updateSubtreeNodeAmount(Node currentNode)
    {
        if (currentNode == null) { return; }

        if (currentNode.getLeft() != null)
        {
            updateSubtreeNodeAmount(currentNode.getLeft());
            currentNode.setSizeLeft(currentNode.getLeft().getSizeLeft() + currentNode.getLeft().getSizeRight() + 1);
        }
        else { currentNode.setSizeLeft(0); }

        if (currentNode.getRight() != null)
        {
            updateSubtreeNodeAmount(currentNode.getRight());
            currentNode.setSizeRight(currentNode.getRight().getSizeLeft() + currentNode.getRight().getSizeRight() + 1);
        }
        else { currentNode.setSizeRight(0); }
    }

    private Node findNthElement(Node currentNode, int n)
    {
        if (currentNode == null) { return null; }

        Node leftResult = findNthElement(currentNode.getLeft(), n);

        if (leftResult != null) { return leftResult; }

        counter++;

        if (counter == n) { return currentNode; }

        return findNthElement(currentNode.getRight(), n);
    }

    private int findPositionInOrder(Node currentNode, int x)
    {
        if (currentNode == null) { return -1; }

        int leftResult = findPositionInOrder(currentNode.getLeft(), x);

        if (leftResult != -1) { return leftResult; }

        counter++;

        if (currentNode.getValue() == x) { return counter; }

        return findPositionInOrder(currentNode.getRight(), x);
    }

    private int findSmallestValue(Node root)
    {
        if (root.getLeft() == null)
        {
            return root.getValue();
        }
        else { return findSmallestValue(root.getLeft()); }
    }

    private int calculateSum(Node currentNode)
    {
        if (currentNode == null) { return 0; }

        int leftSum = calculateSum(currentNode.getLeft());
        int rightSum = calculateSum(currentNode.getRight());

        return leftSum + rightSum + currentNode.getValue();
    }

    private int countNodes(Node currentNode)
    {
        if (currentNode == null) { return 0; }

        return (1 + countNodes(currentNode.getLeft()) + countNodes(currentNode.getRight()));
    }

    private int treeHeight(Node currentNode)
    {
        if (currentNode == null) { return 0; }

        return 1 + Math.max(treeHeight(currentNode.getLeft()), treeHeight(currentNode.getRight()));
    }

    private void printModelOne(Node currentNode, int nodeLevel)
    {
        if (currentNode != null)
        {
            int amountOfSpaces = (nodeLevel * 2) - countValueDigits(currentNode.getValue());
            int amountOfDashes = (treeHeight(root) * 3) - (nodeLevel * 2);

            for (int i = 0; i <= amountOfSpaces; i++)
            {
                System.out.print(" ");
            }

            System.out.print(currentNode.getValue() + "|");

            for (int i = 0; i <= amountOfDashes; i++)
            {
                System.out.print("-");
            }

            System.out.print(" Level: " + nodeLevel);
            System.out.println();

            if (currentNode.getLeft() != null)
            {
                printModelOne(currentNode.getLeft(), nodeLevel + 1);
            }

            if (currentNode.getRight() != null)
            {
                printModelOne(currentNode.getRight(), nodeLevel + 1);
            }
        }
    }

    private void printModelTwo(Node currentNode)
    {
        if (currentNode != null)
        {
            System.out.print(currentNode.getValue());

            if (currentNode.getLeft() != null || currentNode.getRight() != null)
            {
                System.out.print(" (");

                if (currentNode.getLeft() != null)
                {
                    printModelTwo(currentNode.getLeft());
                    System.out.print(") (");
                }

                if (currentNode.getRight() != null)
                {
                    printModelTwo(currentNode.getRight());
                    System.out.print(")");
                }
            }
        }
    }

    private int countValueDigits(int nodeValue)
    {
        int digits = 0;

        while (nodeValue > 0)
        {
            nodeValue = nodeValue / 10;
            digits++;
        }

        return digits;
    }

    // -------------------- Getters and Setters --------------------

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }
}
