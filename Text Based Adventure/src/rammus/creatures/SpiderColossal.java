package rammus.creatures;

import rammus.item.Food;
import rammus.item.Item;


public class SpiderColossal extends Creature{
	
	public SpiderColossal(){
		tags.add(CreatureTag.spider);
		name = "Giant Spider";
		maxHp = 135;
		hp = maxHp;
		dmg = 12;
		xp = 20;
		if(Math.random() < 0.4){this.inv.add(Item.silk);}
		if(Math.random() < 0.4){this.inv.add(Item.silk);}
		if(Math.random() < 0.4){this.inv.add(Item.silk);}
		if(Math.random() < 0.5){this.inv.add(Item.stickyString);}
		addBodyPart(Food.meatSpider, 0.7);
		addBodyPart(Food.meatSpider, 0.7);
		addBodyPart(Food.meatSpider, 0.7);
	}
	 
	public double decideAttackPattern(){
		attack(1, "bit");
		return 1;
	}
	
	public void printDescription(){
		System.out.println("An eight-legged behemoth.");
	}
	
}
