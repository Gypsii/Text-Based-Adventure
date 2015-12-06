package rammus.creatures;

import rammus.item.Food;
import rammus.item.Item;

public class PolarBear extends Creature{
	
	public PolarBear(){
		name = "Polar Bear";
		maxHp = 60;
		hp = maxHp;
		dmg = 8;
		xp = 50;
		addBodyPart(Item.hidePBear, 0.3);
		addBodyPart(Item.hidePBear, 0.3);
		addBodyPart(Food.meatBear, 0.6);
		addBodyPart(Food.meatBear, 0.6);
	}
	 
	public double decideAttackPattern(){
		if(Math.random() < 0.6){
			attack(2, "slashed");
			return 0.9;
		}else{
			attack(1, "bit");
			return 1.2;
		}
	}
	
}
