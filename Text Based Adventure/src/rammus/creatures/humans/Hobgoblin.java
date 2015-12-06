package rammus.creatures.humans;

import java.util.Random;

import rammus.creatures.Creature;
import rammus.creatures.CreatureTag;
import rammus.item.Armour;
import rammus.item.Food;
import rammus.item.Item;

public class Hobgoblin extends Creature{
	
	public Hobgoblin(){
		//name = NameGenerator.generateHumanName();
		//nameIsGeneral = false;
		name = "Hobgoblin";
		maxHp = 35;
		hp = maxHp;
		baseDmg = 5;
		xp = 25;
		equipped = new Item("fists", 5, 0, 1);
		armourChest = new Armour("unarmoured", 0, 0, 0, 0, 0, 0);
		hatesPlayer = false;
		stopsZoneExit = false;
		tags.add(CreatureTag.hobgoblin);
		setSpecifics();	 
	}
	
	public void setSpecifics(){	
		courage = 12 + (Math.random() * 5);
		double rand = Math.random();
		if(rand < 0.33){
			addItem(Armour.armourLeather);
		}else if(rand < 0.8){
			addItem(Armour.tunicCotton);
		}else{
			addItem(Armour.tunicRagged);
		}
		equip(0);
		
		rand = Math.random();
		if(rand < 0.2){
			addItem(Item.maceRusted);
		}else if(rand < 0.4){
			addItem(Item.pikeRusted);
		}else if(rand < 0.7){
			addItem(Item.sswordRusted);
		}else{
			addItem(Item.glaiveBladeRusted);
		}
		equip(1);
		Random r = new Random();
		addItem(Item.money, Math.max(0, (int)(r.nextGaussian() * 10 + 25)));
		if(Math.random() < 0.3){addItem(Food.mushroom);}
		if(Math.random() < 0.05){addItem(Item.butterKnife);}
		if(Math.random() < 0.2){addItem(Food.bread);}
		if(Math.random() < 0.05){addItem(Food.cinnamonBun);}
		if(Math.random() < 0.05){addItem(Item.butterKnife);}
		if(Math.random() < 0.1){
			rand = Math.random();
			if(rand < 0.33){
				addItem(Item.ruby);
			}else if(rand < 0.67){
				addItem(Item.emerald);
			}else{
				addItem(Item.sapphire);
			}
		}
		targetTags.add(CreatureTag.fiend);
		targetTags.add(CreatureTag.human);
	}
}
