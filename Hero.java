import java.io.*;
import java.util.*;
public class Hero extends Object
{
    private int xpos;
    private int ypos;
    private int health;
    private int kills;
    private boolean found = true;
    private Weapon weapon = new Weapon("dagger", 10, 30);
    private String type;
    private ArrayList<Object> inventory = new ArrayList<Object>();
    
    public Hero()
    {
        xpos = 0;
        ypos = 9;
        health = 100;
        kills = 0;
        type = "H";
        inventory = new ArrayList<Object>();
        inventory.add(weapon);
    }
    
    public String toString()
    {
        return "Health: " + health + " Attack: " + weapon.getMin() + "-" + weapon.getMax() + " Weapon: " + weapon.getName();
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
    
    public int getHealth()
    {
        return health;
    }
    
    public int getKills()
    {
        return kills;
    }   
    
    public Object getWeapon()
    {
        return weapon;
    }  
    
    public Armor getArmor()
    {
        Armor armor = new Armor("");
        for (Object obj: inventory)
        {
            if (obj.getType().equals("A"))
            {
                armor = (Armor)obj;
                break;
            }
        }
        return armor;
    }   
    
    public void changeWeapon(Object sword)
    {
        weapon = (Weapon)sword;
    }

    public void addKills()
    {
        kills++;
    }    
    
    public void setX(int x)
    {
        xpos = xpos + x;
    }
    
    public void setY(int y)
    {
        ypos = ypos + y;
    }

    public void addItem(Object x)
    {
        inventory.add(x);
    } 
    
    public void usePotion()
    {
        health = 100;
        for (Object obj: inventory)
        {
            if (obj.getType().equals("P"))
            {
                inventory.remove(obj);
                break;
            }
        }
    }
    
    public void attacked(double damage)
    {
        health -= damage;
    }
        
    public int count(String type)
    {
        int sum = 0;
        for (Object obj: inventory)
        {
            if (obj == null)
            {
                continue;
            }
            else if (obj.getType().equals(type))
            {
                sum++;
            }
        }
        return sum;
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