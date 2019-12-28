public class Object
{
    private int xpos;
    private int ypos;
    private String type;
    private boolean found = false; 
    public int getX()
    {
        return xpos;
    }
    public int getY()
    {
        return ypos;
    }
    public String getType()
    {
        return type;
    }
    public void setX(int x)
    {
        xpos = x;
    }
    public void setY(int y)
    {
        ypos = y;
    }
    public boolean found()
    {
        return found;
    }
    public void setFound()
    {
        found = true;
    }
}
