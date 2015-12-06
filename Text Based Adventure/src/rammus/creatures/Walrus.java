package rammus.creatures;

import rammus.Main;
import rammus.item.Food;
import rammus.item.Item;

public class Walrus extends Creature{
	public boolean enraged = false;
	
	public Walrus(){
		name = "Docile Walrus";
		maxHp = 80;
		bluntArmour = 2;
		slashArmour = 1;
		coldArmour = 2;
		hp = maxHp;
		dmg = 2;
		xp = 20;
		addBodyPart(Item.walrusTusk, 0.67);
		addBodyPart(Item.walrusTusk, 0.67);
		addBodyPart(Item.hideWalrus, 0.4);
		addBodyPart(Item.hideWalrus, 0.4);
		addBodyPart(Food.meatWalrus, 0.6);
		addBodyPart(Food.meatWalrus, 0.6);
		hatesPlayer = false;
		targetTags.remove(CreatureTag.player);
		stopsZoneExit = false;
	}
	 
	public double decideAttackPattern(){
		attack(1, "gored", "its tusks");
		return 2.3;
	}
	
	public void aggravateTrigger(Creature c){
		if(c == Main.player && alive){
			hatesPlayer = true;
			stopsZoneExit = true;
		}
		target = c;
		if(!enraged){
			name = "Enraged Walrus";
			enraged = true;
			dmg = 4;
			System.out.println("The Walrus became enraged!");
		}
	}
	
	public void printDescription(){
		System.out.println("A mighty beast with thick hide to protect it from the cold.");
	}

}
