public class Armor extends Object
{
    private int xpos;
    private int ypos;
    private String type;
    private String reduction;
    private boolean found = false;
    
    public Armor(String r)
    {
        type = "A";
        reduction = r;
    }
    
    public String toString()
    {
        return "Armor (reduces attack damage by " + "1/" + reduction + ")";
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getRed()
    {
        return Integer.parseInt(reduction);
    }
    
    public String getReduc()
    {
        return "1/" + reduction;
    }
}
