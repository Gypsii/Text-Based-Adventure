package rammus.creatures.humans;

import java.util.Random;

import rammus.creatures.Creature;
import rammus.creatures.CreatureTag;
import rammus.item.Armour;
import rammus.item.Item;


public class Human extends Creature{
	int value;
	
	public Human(){
		//name = NameGenerator.generateHumanName();
		//nameIsGeneral = false;
		name = "Human";
		tags.add(CreatureTag.human);
		maxHp = 50;
		hp = maxHp;
		baseDmg = 3;
		xp = 0;
		equipped = new Item("fists", 3, 0, 1);
		armourChest = new Armour("unarmoured", 0, 0, 0, 0, 0, 0);
		hatesPlayer = false;
		targetTags.remove(CreatureTag.player);
		stopsZoneExit = false;
		setSpecifics();	 
	}
	
	public void setSpecifics(){
		courage = 10 + (Math.random() * 5);
		Random r = new Random();	
		value = Math.max(0, (int) (10 + Math.abs(r.nextGaussian()) * 25)); 
		if(value >= 50){
			addItem(Armour.tunicSilk);
		}else{
			addItem(Armour.tunicCotton);
		}
		equip(0);
		if(Math.random() < 0.4){
			if(value >= 43){
				addItem(Item.sswordBronze);
			}else{
				addItem(Item.sswordCopper);
			}
			equip(1);
		}
		addItem(Item.money, (int)(Math.random() * value + 5));
	}
	
	public void damageSubtrigger(){
		if(!hostile){
			hostile = true;
			stopsZoneExit = true;
		}
	}
	
}
