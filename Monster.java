public class Monster extends Object
{
    private int xpos;
    private int ypos;
    private int speed;
    private int health;
    private int attack;
    private boolean found = false;
    private boolean status = false;
    private String type;
    public Monster()
    {
        speed = (int)(Math.random() * 4);
        health = (int)(Math.random() * 100);
        attack = (int)(Math.random() * 30);
        type = "M";
    }
        
    public Monster(int s, int h, int a)
    {
        speed = s;
        health = h;
        attack = a;
        type = "M";
    }
    
    public String toString()
    {
        return "Health: " + health + " Attack: " + attack + " Speed: " + speed;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public int getHealth()
    {
        return health;
    }
    
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
    
    public int getAttack()
    {
        return attack;
    }
    
    public boolean found()
    {
        return found;
    }    
    
    public void setX(int x)
    {
        xpos = x;
    }
    
    public void setY(int y)
    {
        ypos = y;
    }
    
    public void attacked(int x)
    {
        health -= x;
    }
    
    public void setFound()
    {
        found = true;
    }
    
    public void status()
    {
        status = true;
    }
    
    public boolean getStatus()
    {
        return status;
    }
}
