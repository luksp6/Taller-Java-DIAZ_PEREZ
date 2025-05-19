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

        return buildString(this, "", true);
    }
    
    private String buildString(Node nodo, String prefijo, boolean esIzq)
    {
        if (nodo == null)
            return "";
    
        String s = prefijo;
        if (esIzq)
        {            
            s += "├── ";
            prefijo += "│   ";
        }
        else
        {
            s += "└── ";
            prefijo += "    ";
        }
        s += nodo.getData() + "\n";
        s += buildString(nodo.getLeft(), prefijo, true);
        s += buildString(nodo.getRight(), prefijo, false);
    
        return s;
    }
}