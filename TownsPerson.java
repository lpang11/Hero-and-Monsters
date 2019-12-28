public class TownsPerson extends Object
{
    private int xpos;
    private int ypos;
    private int req;
    private String dialogue;
    private Object gift;
    private String type;
    private boolean found = false;
    
    public TownsPerson(String d, Object g, int r)
    {
        dialogue = d;
        gift = g;
        req = r;
        type = "T";
    }
    
    public String getDialogue()
    {
        return dialogue;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getX()
    {
        return xpos;
    }
    
    public int getY()
    {
        return ypos;
    }
    
    public void setX(int x)
    {
        xpos = x;
    }
    
    public void setY(int y)
    {
        ypos = y;
    }
    
    public int getReq()
    {
        return req;
    }
    
    public Object gift()
    {
        return gift;
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
