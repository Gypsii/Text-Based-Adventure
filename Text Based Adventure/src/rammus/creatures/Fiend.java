package rammus.creatures;

import rammus.item.Item;



public class Fiend extends Creature{
	
	public Fiend(){
		name = "Demonic Fiend";
		tags.add(CreatureTag.fiend);
		maxHp = 72;
		hp = maxHp;
		dmg = 8;
		xp = 65;
		bluntArmour = 3;
		slashArmour = 3;
		pierceArmour = 1;
		burnArmour = 20;
		double rand = Math.random();
		if(rand < 0.25){
			addItem(Item.lswordDemon);
		}else if(rand < 0.5){
			addItem(Item.lswordDemonTap);
		}else if(rand < 0.75){
			addItem(Item.lswordDemonFlame);
		}else{
			addItem(Item.lswordDemonMagic);
		}
		equip(0);
		if(Math.random() < 0.45){
			addItem(Item.ruby);
		}
		targetTags.add(CreatureTag.hobgoblin);
		targetTags.add(CreatureTag.human);
	}
	
	public void printDescription(){
		System.out.println("A vile hellfiend wielding a cursed demonic sword.");
	}
}