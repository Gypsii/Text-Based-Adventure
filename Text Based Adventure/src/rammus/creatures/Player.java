package rammus.creatures;

import rammus.Main;
import rammus.item.Armour;
import rammus.item.Item;

public class Player extends Creature{
	public int xp = 0;
    public int lvl = 1;
    
    public Player(){
		tags.add(CreatureTag.human);
		tags.add(CreatureTag.player);
    	dmg = 3;
    	baseDmg = 3;
        maxHp = 50;
        hp = maxHp;
        equipped = new Item("fists", 3, 0, 1);
        armourChest = new Armour("unarmoured", 0, 0, 0, 0, 0, 0);
    }
    
    public void equip(int id){
    	if(inv.get(id).isArmour){
    		bluntArmour -= armourChest.bluntResist;
    		pierceArmour -= armourChest.pierceResist;
    		slashArmour -= armourChest.slashResist;
    		burnArmour -= armourChest.burnResist;
    		coldArmour -= armourChest.coldResist;
    		magicArmour -= armourChest.magicResist;
    		armourChest = (Armour)inv.get(id);
    		bluntArmour += armourChest.bluntResist;
    		pierceArmour += armourChest.pierceResist;
    		slashArmour += armourChest.slashResist;
    		burnArmour += armourChest.burnResist;
    		coldArmour += armourChest.coldResist;
    		magicArmour += armourChest.magicResist;
    		if(!armourChest.getName().equals("unarmoured")){
    			Main.printBlue("You equip the " + armourChest.getName());
    		}
    	}else{
    		equipped = inv.get(id);
        	dmg = Math.max(baseDmg, equipped.dmg);
        	if(!equipped.getName().equals("fists")){
        		Main.printBlue("You equip the " + equipped.getName());
    		}
    	}
    }
    
    public void equip(Item i){
    	if(i.isArmour){
    		bluntArmour -= armourChest.bluntResist;
    		pierceArmour -= armourChest.pierceResist;
    		slashArmour -= armourChest.slashResist;
    		burnArmour -= armourChest.burnResist;
    		coldArmour -= armourChest.coldResist;
    		armourChest = (Armour) i;
    		bluntArmour += armourChest.bluntResist;
    		pierceArmour += armourChest.pierceResist;
    		slashArmour += armourChest.slashResist;
    		burnArmour += armourChest.burnResist;
    		coldArmour += armourChest.coldResist;
    		if(!equipped.name.equals("unarmed")){
    			Main.printBlue("You equip the " + armourChest.getName());
    		}
    	}else{
    		equipped = i;
    		if(!equipped.name.equals("unarmoured")){
    			Main.printBlue("You equip the " + equipped.getName());	
    		}
        	dmg = Math.max(baseDmg, equipped.dmg);
    	}
    }
    
    public void abscond(){
    	System.err.println("ERR: Player absconding. This should not happen. Yell at dev");
    }
    
    
    public void takeBluntDamage(int d) {
		hp -= Math.max(d - bluntArmour, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    
    public void takeSlashDamage(int d) {
		hp -= Math.max(d - slashArmour, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    public void takePierceDamage(int d) {
		hp -= Math.max(d - pierceArmour, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    
    public void takeDamage(int d) {
    	if(d > 0){
    		hp -= Math.max(d, 0);
    	}	
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    
    public void takeBurnDamage(int d) {
    	hp -= Math.max(d - burnArmour, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}   
    
    public void takeColdDamage(int d) {
    	hp -= Math.max(d - coldArmour, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    public void takeExplosionDamage(int d) {
    	hp -= Math.max(d, 0);
		if(hp <= 0){
			System.out.println("RIP. You were killed.");
		}
	}
    
    public void kill(){
		System.out.println("RIP. You were killed.");
	}
    
    public void giveXp(int x) {
		xp += x;
		System.out.println("You gained " + x + " XP (" + xp + "/" + (lvl * 5 + 10) + ")");
		while(xp >= lvl * 5 + 10){
			xp -= lvl * 5 + 10;
			lvl ++;
			maxHp += lvl + 6;
			hp += lvl + 6;
			Main.printPurple("You have reached level " + lvl);
			System.out.println("Your max hp is now " + maxHp);
		}
	}

}
