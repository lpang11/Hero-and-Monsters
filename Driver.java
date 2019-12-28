import java.util.*;
import java.io.*;
public class Driver
{
    public static void main(String[] args)
    {
        int boundary1, boundary2;
        int potioncount = 0;
        boolean game = true;
        boolean bossbattle = false;
        boolean death = false;
        boolean potions = false;
        boolean armor = false;
        boolean sword = false;
        boolean monster = false;
        boolean win = false;
        int move = 0;
        int d = 0, j = 0;
        int damage = 0;
        Scanner kbReader = new Scanner(System.in);
        Hero h = new Hero();
        Potion p1 = new Potion();
        Potion p2 = new Potion();
        TownsPerson t1 = new TownsPerson("My sheep have been taken. My family is gone and I have nothing left except for bronze armor.\nOnly a true hero will receive this.", new Armor("3"), 2);
        TownsPerson t2 = new TownsPerson("All of my livestock has been stolen by the crooks. All I have is this broad sword.", new Weapon("broad sword", 20, 50), 4); 
        Monster m1 = new Monster();
        Monster m2 = new Monster();
        Monster m3 = new Monster();
        Monster m4 = new Monster();
        Monster m5 = new Monster();
        Monster m6 = new Monster();
        Monster boss = new Monster(3, 200, 70);
        boss.setFound();
        
        Object[] list = {p1, p2, t1, t2, m1, m2, m3, m4, m5, m6};

        Object[][]map = new Object[10][10];

        Object[] list1 = {new Potion(), new Potion(), new Potion(), new Potion(), boss};
        
        Object[][] map1 = new Object[10][10];
        
        map[h.getY()][h.getX()] = h;
        while (d < list.length)
        {
            Object obj = list[d];
            boundary1 = (int)(Math.random() * 10);
            boundary2 = (int)(Math.random() * 10);
            if (map[boundary1] [boundary2] == null)
            {
                map[boundary1][boundary2] = obj;
                obj.setY(boundary1);
                obj.setX(boundary2);
                d++;
            }
        }
        
        while (j < list1.length)
        {
            Object obj = list1[j];
            boundary1 = (int)(Math.random() * 10);
            boundary2 = (int)(Math.random() * 10);
            if (map1[boundary1] [boundary2] == null)
            {
                map1[boundary1][boundary2] = obj;
                obj.setY(boundary1);
                obj.setX(boundary2);
                j++;
            }
        }
        
        System.out.println("** Heroes & Monsters **\n\n");
        
        while (true)
        {
            while (game)
            {                
                if (potions && armor && sword && monster)
                {
                    game = false;
                    bossbattle = true;
                    map1[h.getY()][h.getX()] = h;
                    h.changeWeapon(new Weapon("gold-edged sword", 40, 60));
                    System.out.println("\nYou finally find all the items, defeat all the monsters, and help the townspeople rid of the evil in the town.\nBut, as you leave the town to go back home, you hear a voice behind you.");
                    System.out.println("\nYou thought that you could leave without a fight?\n");
                    System.out.println("A towering armored knight approaches you with a double-edged sword in his hand.");
                    System.out.println("You received a " + h.getWeapon().toString() + "!");
                    System.out.println("\nYou must defeat him in order to bring piece to the land once and for all!");
                    break;
                }
                printMap(map);
                while (true)
                {
                    System.out.println("Which direction would you like to go?\nInput w(north), s(south), d(east), or a(west).\n\nYou can always type 'p' to use a potion except in battle.\nYou currently have " + h.count("P") + " potions and your health is " + h.getHealth() + ".");
                    String input = kbReader.next();
                    if (input.equalsIgnoreCase("w") || input.equalsIgnoreCase("a") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("d"))
                    {
                        if (validMove(h, input, map))
                        {                              
                            int prevX = h.getX();
                            int prevY = h.getY();
                            if (input.equalsIgnoreCase("a"))
                            {
                                h.setX(-1);
                                map[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("w"))
                            {
                                h.setY(-1);
                                map[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("s"))
                            {
                                h.setY(1);  
                                map[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("d"))
                            {
                                h.setX(1);
                                map[prevY][prevX] = null;
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("\nYou cannot go this way!\n");
                        }
                    }
                    else if (input.equalsIgnoreCase("p"))
                    {
                        if (h.count("P") > 0)
                        {
                            System.out.println("\n** A potion from your inventory has been used. **\n");
                            h.usePotion();
                        }
                        else
                        {
                            System.out.println("\nYou do not have any potions to use at the moment.\n");
                        }
                    }
                    else
                    {
                        System.out.println("\nYou must enter a valid direction!\n");
                    }
                }
                map[h.getY()][h.getX()] = h;
                for (int x = 0; x < list.length; x++)
                {
                    if (list[x] == null)
                    {
                        continue;
                    }
                    
                    if (interact(list[x], h) && !list[x].getType().equals("H"))
                    {
                        //potion
                        if (list[x].getType().equals("P"))
                        {
                            Potion p = (Potion)list[x];
                            System.out.println("\n** You found a full health potion! It has been stored in your inventory. **");
                            map[p.getY()][p.getX()] = null;
                            list[x] = null;
                            h.addItem(p);
                            potioncount++;
                            if (potioncount >= 2)
                            {
                                potions = true;
                            }
                        }
                        //farmer
                        else if (list[x].getType().equals("T"))
                        {
                            list[x].setFound();
                            TownsPerson t = (TownsPerson)list[x];
                            if (h.getKills() < t.getReq())
                            {
                                System.out.println("\n** You stumble upon a scared villager who cries out to you for help. **");
                                System.out.println("\nTownsperson: " + t.getDialogue());
                                if (h.getKills() <= 0)
                                {
                                    System.out.println("But, you must kill " + t.getReq() + " monsters in order to get this item!\nYou have no kills.");
                                }
                                else if (h.getKills() == 1)
                                {
                                    System.out.println("But, you must kill " + t.getReq() + " monsters in order to get this item!\nYou have " + h.getKills() + " kill.");
                                } 
                                else
                                {   
                                    System.out.println("But, you must kill " + t.getReq() + " monsters in order to get this item!\nYou have " + h.getKills() + " kills.");
                                }
                            }
                            else
                            {
                                h.addItem(t.gift());
                                System.out.println("\n** You received " + t.gift().toString() + " from the villager for killing " + t.getReq() + " monsters! **");
                                System.out.println("\nTownsperson: Thank you for saving our village! You are a true hero.\n");
                                map[t.getY()][t.getX()] = null;
                                list[x] = null;
                                if (t.gift().getType().equals("W"))
                                {
                                    sword = true;
                                    h.changeWeapon(t.gift());
                                }
                                else if (t.gift().getType().equals("A"))
                                {
                                    armor = true;
                                }
                            }
                        }
                        //monster
                        else if (list[x].getType().equals("M"))
                        {
                            Monster m = (Monster)list[x];
                            Weapon w = (Weapon)h.getWeapon();
                            if (!m.getStatus())
                            {
                                System.out.println("\n** You have encountered a monster! **");
                                System.out.println("\nMonster:\n" + m.toString());
                                System.out.println("\nHero:\n" + h.toString());
                                boolean battle = true;
                                while (battle)
                                {
                                    int min = m.getAttack() - 10;
                                    int max = m.getAttack();
                                    int mDmg = (int)(Math.random() * ((max - min) + 1)) + min;
                                    if (mDmg < 0)
                                    {
                                        mDmg = 0;
                                    }

                                    if (h.count("A") >= 1)
                                    {
                                        System.out.println("\nYour armor reduced " + h.getArmor().getReduc() + " of the monster's damage.");
                                        h.attacked(mDmg / h.getArmor().getRed());
                                        System.out.println("\n** The monster inflicted " + (mDmg / h.getArmor().getRed()) + " damage! **");
                                    }
                                    else
                                    {
                                        h.attacked(mDmg);
                                        System.out.println("\n** The monster inflicted " + mDmg + " damage! **");
                                    }
                                    
                                    if (h.getHealth() <= 0)
                                    {
                                        death = true;
                                        game  = false;
                                        battle = false;
                                        break;
                                    }
                                    System.out.println("\nYour health is now " + h.getHealth() + ".");
                                    while (true)
                                    {
                                        System.out.println("\nEnter an action: run or attack");
                                        String input = kbReader.next();
                                        if (input.equalsIgnoreCase("run"))
                                        {
                                            if (m.getSpeed() == 3)
                                            {
                                                System.out.println("\nYou are unable to run away.");
                                                break;
                                            }
                                            else if (m.getSpeed() == 2)
                                            {
                                                if (chance(25))
                                                {
                                                    System.out.println("\nYou successfully ran away!");
                                                    m.setFound();
                                                    battle = false;
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("\nYou are unable to run away.");
                                                    break;
                                                }
                                            }
                                            else if (m.getSpeed() == 1)
                                            {
                                                if (chance(50))
                                                {
                                                    System.out.println("\nYou successfully ran away!");
                                                    m.setFound();
                                                    battle = false;
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("\nYou are unable to run away.");
                                                    break;
                                                }
                                            }
                                            else if (m.getSpeed() == 0)
                                            {
                                                if (chance(75))
                                                {
                                                    System.out.println("\nYou successfully ran away!");
                                                    m.setFound();
                                                    battle = false;
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("\nYou are unable to run away.");
                                                    break;
                                                }
                                            }
                                        }
                                        else if (input.equalsIgnoreCase("attack"))
                                        {
                                            int hDmg = (int)(Math.random() * (w.getMax() - w.getMin() + 1) + w.getMin());
                                            m.attacked(hDmg);
                                            System.out.println("\n** You inflicted " + hDmg + " damage! **");
                                            if (m.getHealth() <= 0)
                                            {
                                                System.out.println("\nYou defeated the monster!");
                                                m.status();
                                                m.setFound();
                                                h.addKills();
                                                if (h.getKills() >= 6)
                                                {
                                                    monster = true;
                                                }
                                                battle = false;
                                                break;
                                            }
                                            else
                                            {
                                                System.out.println("\nThe monster's health is now " + m.getHealth() + ".");
                                                break;
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("\nYou must enter a valid action!");
                                        }
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("\nYou look at the carcass of the monster you killed. You feel no remorse.");
                            }
                        }
                    }
                }
            }

            while (bossbattle)
            {
                if (move >= 5)
                {
                    while (true)
                    {
                        int[]x = {h.getX(), h.getX() + 1, h.getX() - 1};
                        int[]y = {h.getY(), h.getY() + 1, h.getY() - 1};
                        if (chance(50))
                        {
                            int xpos = (int)(Math.random() * x.length);
                            int ypos = (int)(Math.random() * y.length);
                            if (xpos == 0)
                            {
                                ypos = (int)(Math.random() * (y.length)) + 1;
                            }
                            map1[boss.getY()][boss.getX()] = null;
                            if (h.getX() == 0)
                            {
                                if (chance(50))
                                {
                                    boss.setX(h.getX() + 1);   
                                }
                                else
                                {
                                    boss.setX(h.getX());
                                }
                            }
                            else if (h.getX() == map1[0].length - 1)
                            {
                                if (chance(50))
                                {
                                    boss.setX(h.getX() - 1);
                                }
                                else
                                {
                                    boss.setX(h.getX());
                                }
                            }
                            else if (h.getY() == 0)
                            {
                                if (chance(50) && boss.getX() != h.getX())
                                {
                                    boss.setY(h.getY());
                                }
                                else
                                {
                                    boss.setY(h.getY() + 1);
                                }
                            }
                            else if (h.getY() == map1.length - 1)
                            {
                                if (chance(50) && boss.getX() != h.getX())
                                {
                                    boss.setY(h.getY());
                                }
                                else
                                {
                                    boss.setY(h.getY() - 1);
                                }
                            }
                            else
                            {
                                boss.setX(x[xpos]);
                                boss.setY(y[ypos]);
                            }
                            map1[boss.getY()][boss.getX()] = boss;
                            move = 0;
                            System.out.println("\n** The boss has rushed towards you! **");
                            break;
                        } 
                        else
                        {
                            boundary1 = (int)(Math.random() * 10);
                            boundary2 = (int)(Math.random() * 10);
                            if (map1[boundary1] [boundary2] == null)
                            {
                                map1[boss.getY()][boss.getX()] = null;
                                boss.setY(boundary1);
                                boss.setX(boundary2);
                                map1[boundary1][boundary2] = boss;
                                move = 0;                            
                                break;
                            }
                        }
                    }
                }
                printMap(map1);
                while (true)
                {
                    System.out.println("Which direction would you like to go?\nInput w(north), s(south), d(east), or a(west).\n\nYou can always type 'p' to use a potion except in battle.\nYou currently have " + h.count("P") + " potions and your health is " + h.getHealth() + ".");
                    String input = kbReader.next();
                    if (input.equalsIgnoreCase("w") || input.equalsIgnoreCase("a") || input.equalsIgnoreCase("s") || input.equalsIgnoreCase("d"))
                    {
                        if (validMove(h, input, map1))
                        {                              
                            int prevX = h.getX();
                            int prevY = h.getY();
                            move++;
                            if (input.equalsIgnoreCase("a"))
                            {
                                h.setX(-1);
                                map1[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("w"))
                            {
                                h.setY(-1);
                                map1[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("s"))
                            {
                                h.setY(1);  
                                map1[prevY][prevX] = null;
                                break;
                            }
                            else if (input.equalsIgnoreCase("d"))
                            {
                                h.setX(1);
                                map1[prevY][prevX] = null;
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("You cannot go this way!");
                        }
                    }
                    else if (input.equalsIgnoreCase("p") && h.count("P") > 0)
                    {
                        if (h.count("P") > 0)
                        {
                            System.out.println("\n** A potion from your inventory has been used. **\n");
                            h.usePotion();
                        }
                        else
                        {
                            System.out.println("\nYou do not have any potions to use at the moment.\n");
                        }
                    }
                    else
                    {
                        System.out.println("\nYou must enter a valid direction!\n");
                    }
                }
                map1[h.getY()][h.getX()] = h;
                for (int x = 0; x < list1.length; x++)
                {
                    if (list1[x] == null)
                    {
                        continue;
                    }
                    
                    if (interact(list1[x], h) && !list1[x].getType().equals("H"))
                    {
                        //potion
                        if (list1[x].getType().equals("P"))
                        {
                            Potion p = (Potion)list1[x];
                            System.out.println("\n** You found a full health potion! It has been stored in your inventory. **");
                            map1[p.getY()][p.getX()] = null;
                            list1[x] = null;
                            h.addItem(p);
                        }
                        else if (list1[x].getType().equals("M"))
                        {
                            Monster m = (Monster)list1[x];
                            Weapon w = (Weapon)h.getWeapon();
                            if (!m.getStatus())
                            {
                                System.out.println("\n** You have encountered the ultimate boss! **");
                                System.out.println("\nBoss:\n" + m.toString());
                                System.out.println("\nHero:\n" + h.toString());
                                boolean battle = true;
                                while (battle)
                                {
                                    int min = m.getAttack() - 10;
                                    int max = m.getAttack();
                                    int mDmg = (int)(Math.random() * (max - min + 1)) + min;
                                    if (mDmg < 0)
                                    {
                                        mDmg = 0;
                                    }

                                    if (h.count("A") >= 1)
                                    {
                                        System.out.println("\nYour armor reduced " + h.getArmor().getReduc() + " of the boss' damage.");
                                        h.attacked(mDmg / h.getArmor().getRed());
                                        System.out.println("\n** The boss inflicted " + (mDmg / h.getArmor().getRed()) + " damage! **");
                                    }
                                    else
                                    {
                                        h.attacked(mDmg);
                                        System.out.println("\n** The boss inflicted " + mDmg + " damage! **");
                                    }
                                     
                                    if (h.getHealth() <= 0)
                                    {
                                        bossbattle = battle = false;
                                        death = true;
                                        break;
                                    }
                                    
                                    System.out.println("\nYour health is now " + h.getHealth() + ".");
                                    
                                    while (true)
                                    {
                                        System.out.println("\nEnter an action: run or attack");
                                        String input = kbReader.next();
                                        if (input.equalsIgnoreCase("run"))
                                        {
                                            System.out.println("\nYou are unable to run away from the boss.");
                                              
                                        }
                                        else if (input.equalsIgnoreCase("attack"))
                                        {
                                            int hDmg = (int)(Math.random() * (w.getMax() - w.getMin() + 1) + w.getMin());
                                            m.attacked(hDmg);
                                            damage += hDmg;
                                            System.out.println("\n** You inflicted " + hDmg + " damage! **");
                                            if (m.getHealth() <= 0)
                                            {
                                                System.out.println("\nYou defeated the boss once and for all!");
                                                m.status();
                                                bossbattle = battle = false;
                                                win = true;
                                                break;
                                            }
                                            
                                            if (damage >= 50)
                                            {
                                                System.out.println("\nYou stunned the boss long enough to run away!");
                                                battle = false;
                                                break;
                                            }
                                            else
                                            {
                                                System.out.println("\nThe boss' health is now " + m.getHealth() + ".");
                                                break;
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("\nYou must enter a valid action!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }           
            }
            
            if (death)
            {
                System.out.println("\n** You died in battle. **\n\nThe villagers do not mourn your death.");
                death = false;
                break;
            }
            
            if (win)
            {
                System.out.println("\n** You won! **\n\nThe evil knight is finally defeated and the villagers come to congratulate you.\nYou go back to Piddlyshire and do your Calculus homework before the next monster invasion.");
                win = false;
                break;
            }
        }
    }
 
    public static boolean chance(int x)
    {
        if ((int)(Math.random()*101) <= x)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static boolean interact(Object obj, Hero h)
    {
        if (obj == null)
        {
            return false;
        }
        
        if (h.getX() == obj.getX() + 1 || h.getX() == obj.getX() - 1)
        {
            if (h.getY() == obj.getY())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (h.getY() == obj.getY() + 1 || h.getY() == obj.getY() - 1)
        {
            if (h.getX() == obj.getX())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }        
    }
    
    public static void printMap(Object[][]map)
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                if (map[i][j]!= null && map[i][j].found() && !map[i][j].getType().equals("M"))
                {
                    System.out.print("|" + map[i][j].getType() + "|\t");
                }
                else if (map[i][j]!= null && map[i][j].getType().equals("M"))
                {
                    Monster m = (Monster)map[i][j];
                    if (m.getStatus() && m.found())
                    {
                        System.out.print("|X|\t");
                    }
                    else if (!m.getStatus() && m.found())
                    {
                        System.out.print("|" + m.getType() + "|\t");
                    }
                    else
                    {
                        System.out.print("| |\t");
                    }
                }
                else
                {
                    System.out.print("| |\t");
                }
            }
            System.out.println(" ");
        }
    }
        
    public static boolean validMove(Hero h, String dir, Object[][]map)
    {
        if (dir.equalsIgnoreCase("a"))
        {
            if (h.getX() - 1 < 0 || map[h.getY()][h.getX() - 1] != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (dir.equalsIgnoreCase("w"))
        {
            if (h.getY() - 1 < 0  || map[h.getY() - 1][h.getX()] != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (dir.equalsIgnoreCase("s"))
        {
            if (h.getY() + 1 >= map.length  || map[h.getY() + 1][h.getX()] != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (dir.equalsIgnoreCase("d"))
        {
            if (h.getX() + 1 >= map[0].length  || map[h.getY()][h.getX() + 1] != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
}