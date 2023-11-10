package binarysearchtree;

public class Node
{
    private int value;
    private Node left;
    private Node right;
    private int sizeLeft;
    private int sizeRight;
    private int level;

    // -------------- Constructor --------------

    public Node(int value, int nodeLevel)
    {
        this.value = value;
        this.left = null;
        this.right = null;
        this.sizeLeft = 0;
        this.sizeRight = 0;
        this.level = 0;
    }

    // ---------- Getters and Setters ----------

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public Node getLeft() { return left; }

    public void setLeft(Node left) { this.left = left; }

    public Node getRight() { return right; }

    public void setRight(Node right) { this.right = right; }

    public int getSizeLeft() { return sizeLeft; }

    public void setSizeLeft(int sizeLeft) { this.sizeLeft = sizeLeft; }

    public int getSizeRight() { return sizeRight; }

    public void setSizeRight(int sizeRight) { this.sizeRight = sizeRight; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }
}
