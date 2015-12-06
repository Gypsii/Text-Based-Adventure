package rammus.creatures.humans;

import java.util.Random;

import rammus.creatures.CreatureTag;
import rammus.item.Armour;
import rammus.item.Food;
import rammus.item.Item;

public class SealHunter extends Human{
	
	public void setSpecifics(){
		tags.add(CreatureTag.sealHunter);
		name = "Seal Hunter";
		courage = 12 + (Math.random() * 5);
		Random r = new Random();	
		value = Math.max(0, (int) (10 + Math.abs(r.nextGaussian()) * 25));
		if(value >= 55){
			addItem(Armour.vestFurBear);
		}else if(value >= 20){
			addItem(Armour.vestFurDingo);
		}else{
			addItem(Armour.tunicCotton);	
		}
		equip(0);
		if(value >= 40){
			addItem(Item.spearCopper);
		}else{
			addItem(Item.spearRusted);
		}
		equip(1);
		addItem(Item.money, (int)(Math.random() * value + 5));
		if(Math.random() < 0.3){addItem(Food.meatSeal);}
		targetTags.add(CreatureTag.seal);
		targetTags.add(CreatureTag.walrus);
	}
	public void printDescription(){
		System.out.println("Lives in icy lands, hunts seals and walruses.");
	}
}
