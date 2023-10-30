package binarysearchtree;

public class BinaryTree
{
    private Node root;

    // -------------- Constructor --------------

    public BinaryTree() { this.root = null; }

    // -------- Conventional Operations --------

    // find the place where we want to add a new node to keep the tree sorted (recursive method).

    private Node recursiveInsert(Node currentNode, int value)
    {
        // reached the appropriate position to insert the new node.

        if (currentNode == null) { return new Node(value); }

        /*
        New value is smaller : places in the left subtree.
        New value is greater : places in the right subtree.
        Else : value already exists.
        */

        if (value < currentNode.getValue())
        {
            currentNode.setLeft(recursiveInsert(currentNode.getLeft(), value));
        }
        else if (value > currentNode.getValue())
        {
            currentNode.setRight(recursiveInsert(currentNode.getRight(), value));
        }
        else { return currentNode; }

        return currentNode;
    }

    // uses the recursive function to add an element to the tree.

    public void insertElement(int value) { root = recursiveInsert(root, value); }

    // check if the tree contains a specific value (recursive method).

    private boolean recursiveSearch(Node currentNode, int value)
    {
        // searching for the value by comparing it to the value in the current node.

        if (currentNode == null) { return false; }

        if (value == currentNode.getValue()) { return true; }

        if (value < currentNode.getValue())
        {
            return recursiveSearch(currentNode.getLeft(), value);
        }
        else { return recursiveSearch(currentNode.getRight(), value); }
    }

    // uses the recursive function to search for an element.

    public boolean searchElement(int value) { return recursiveSearch(root, value); }

    // find the smallest node in the right subtree of the soon-to-be-deleted node (recursive method).

    private int findSmallestValue(Node root)
    {
        if (root.getLeft() == null)
        {
            return root.getValue();
        }
        else { return findSmallestValue(root.getLeft()); }
    }

    // find the node to delete from the tree (recursive method).

    private Node recursiveDelete(Node currentNode, int value)
    {
        // Value not found, nothing to delete.

        if (currentNode == null) { return null; }

        // Node to delete found, handle the three cases:

        if (value == currentNode.getValue())
        {
            // Case 1: Node has no children.

            if (currentNode.getLeft() == null && currentNode.getRight() == null) { return null; }

            // Case 2: Node has one child (left child).

            if (currentNode.getLeft() == null) { return currentNode.getRight(); }

            // Case 2: Node has one child (right child).

            if (currentNode.getRight() == null) { return currentNode.getLeft(); }

            // Case 3: Node has two children, find the smallest value in the right subtree.

            if (currentNode.getLeft() != null && currentNode.getRight() != null)
            {
                int smallestValue = findSmallestValue(currentNode.getRight());

                // Replace with the smallest value.

                currentNode.setValue(smallestValue);

                // Delete the smallest node in the right subtree.

                currentNode.setRight(recursiveDelete(currentNode.getRight(), smallestValue));

                return currentNode;
            }
        }

        /*
        Value is smaller : search in the left subtree.
        Value is greater : search in the right subtree.
        */

        if (value < currentNode.getValue())
        {
            currentNode.setLeft(recursiveDelete(currentNode.getLeft(), value));
        }
        else
        {
            currentNode.setRight(recursiveDelete(currentNode.getRight(), value));
        }

        return currentNode;
    }

    // uses the recursive function to delete an element

    public void deleteElement(int value) { root = recursiveDelete(root, value); }

    // ---------- Specific Operations ----------



    // ---------- Getters and Setters ----------

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }
}
