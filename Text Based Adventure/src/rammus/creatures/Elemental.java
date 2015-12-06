package rammus.creatures;

import rammus.item.Item;

public class Elemental extends Creature{
	int type;
	public static int FLAME = 0;
	public static int FROST = 1;
	public static int EARTH = 2;
	public static int WATER = 3;
	public static int STORM = 4;//Its not called AIR because air isnt 5 letters long
	
	public Elemental(int t){
		type = t;
		name = "ERR UNTYPED ELEMENTAL";
		tags.add(CreatureTag.elemental);
		dmg = 7;
		maxHp = 80;
		xp = 47;
		pierceArmour = 2;
		slashArmour = 2;
		bluntArmour = 2;
		magicArmour = 2;
		burnArmour = 2;
		coldArmour = 2;
		if(type == FLAME){
			name = "Fire Elemental";
			burnArmour = 20;
			pierceArmour = 0;
			slashArmour = 0;
			bluntArmour = 0;
			equip(Item.elementalCoreFire);
		}else if(type == FROST){
			name = "Frost Elemental";
			coldArmour = 20;
			burnArmour = -5;
			equip(Item.elementalCoreIce);
		}else if(type == EARTH){
			name = "Earth Elemental";
			bluntArmour = 7;
			slashArmour = 7;
			pierceArmour = 7;
			magicArmour = -5;
			maxHp = 100;
			equip(Item.elementalCoreEarth);
		}else if(type == WATER){
			name = "Water Elemental";
			bluntArmour = 3;
			slashArmour = 3;
			pierceArmour = 3;
			maxHp = 140;
			equip(Item.elementalCoreWater);
		}else if(type == STORM){
			name = "Air Elemental";
			magicArmour = 20;
			maxHp = 68;
			equip(Item.elementalCoreAir);
		}
		hp = maxHp;
	}
	
	public double decideAttackPattern(){
		attack(0, "hit");
		return equipped.swingTime;
	}
	
	public void printDescription(){
		if(type == FLAME){
			System.out.println("");
		}else if(type == FROST){
			System.out.println("");
		}else if(type == WATER){
			System.out.println("");
		}else if(type == EARTH){
			System.out.println("");
		}else if(type == STORM){
			System.out.println("");
		}
	}
	
}
