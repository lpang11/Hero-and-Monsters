public class Weapon extends Object
{
    private String type;
    private String name;
    private int min;
    private int max;
    private boolean found = false;
    public Weapon(String n, int mi, int ma)
    {
        type = "W";
        name = n;
        min = mi;
        max = ma;
    }
    
    public String toString()
    {
        return name + " (" + min + "-" + max + " damage)";
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getMin()
    {
        return min;
    }
    
    public int getMax()
    {
        return max;
    }
}
