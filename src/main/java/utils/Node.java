package main.java.utils;

public class Node
{
    private int data;
    private Node left;
    private Node right;

    public Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public Node(int data, Node left, Node right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData()
    {
        return this.data;
    }

    public Node getLeft()
    {
        return this.left;
    }

    public Node getRight()
    {
        return this.right;
    }

    public void setData(int data)
    {
        this.data = data;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }

    public String toString()
    {
        String s = Integer.toString(this.data) + " ";
        if (this.left != null)
            s += "(" + this.left.getData() + ") ";
        if (this.right != null)
            s += "(" + this.right.getData() + ") ";
        if (this.left != null)
            s += this.left.toString();
        if (this.right != null)
            s += this.right.toString();
        return s + "\n";
    }
}