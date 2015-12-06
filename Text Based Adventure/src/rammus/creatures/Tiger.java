package rammus.creatures;

import rammus.item.Food;


public class Tiger extends Creature{
	
	public Tiger(){
		name = "Tiger";
		maxHp = 45;
		hp = maxHp;
		dmg = 6;
		xp = 24;
		courage = 8 + (Math.random() * 3);
		addBodyPart(Food.meatTiger, 0.5);
		addBodyPart(Food.meatTiger, 0.5);
	}
	
	public double decideAttackPattern(){
		if(Math.random() < 0.8){
			attack(2, "slashed", "its claws");
		}else{
			attack(1, "bit");
		}
		return 1;
	}
	
	public void printDescription(){
		System.out.println("A large cat with deadly claws. Lives in warm climates.");
	}
}
