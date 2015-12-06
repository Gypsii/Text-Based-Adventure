package rammus.creatures;

import rammus.item.Food;



public class Turtle extends Creature{
	
	public Turtle(){
		name = "Snapping Turtle";
		maxHp = 31;
		hp = maxHp;
		dmg = 5;
		xp = 16;
		bluntArmour = 2;
		slashArmour = 8;
		pierceArmour = 8;
		courage = 8 + (Math.random() * 4);
		if(Math.random() < 0.45){
			//this.inv.add(Item.silk);
		}
		addBodyPart(Food.meatTurtle, 0.3);
		addBodyPart(Food.meatTurtle, 0.3);
	}
	 
	public double decideAttackPattern(){
		attack(1, "bit");
		return 1;
	}
	
	public void printDescription(){
		System.out.println("A tough creature with a thick shell and a powerful bite");
	}
	
}