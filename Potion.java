public class Potion extends Object
{
    private int xpos;
    private int ypos;
    private boolean found = false;
    private String type = "P";
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
