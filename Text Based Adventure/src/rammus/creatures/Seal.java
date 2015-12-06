package rammus.creatures;

import rammus.item.Food;



public class Seal extends Creature{
	
	public Seal(){
		name = "Seal";
		tags.add(CreatureTag.seal);
		maxHp = 20;
		bluntArmour = 2;
		hp = maxHp;
		dmg = 2;
		xp = 5;
		hatesPlayer = false;
		targetTags.remove(CreatureTag.player);
		stopsZoneExit = false;
		addBodyPart(Food.meatSeal, 0.5);
	}
	 
	public double decideAttackPattern(){
		attack(0, "hit");
		return 1;
	}
	
	public void printDescription(){
		System.out.println("A harmless seal.");
	}
}
