package main.java.utils;

public class Portal
{
    private int x1, x2, y1, y2;

    public Portal(int x1, int y1)
    {
        this.x1 = x1;
        this.y1 = y1;
    }

    public int getF(int x)
    {
        return (x == this.x1 ? this.x2 : this.x1);
    }
    
    public int getC(int y)
    {
        return (y == this.y1 ? this.y2 : this.y1);
    }

    public void setX1(int x)
    {
        this.x1 = x;
    }

    public void setX2(int x)
    {
        this.x2 = x;
    }

    public void setY1(int y)
    {
        this.y1 = y;
    }

    public void setY2(int y)
    {
        this.y2 = y;
    }

}
