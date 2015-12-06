package rammus.creatures.humans;

import rammus.creatures.CreatureTag;
import rammus.item.Armour;
import rammus.item.Food;
import rammus.item.Item;

public class SpiceTrader extends Human{
	
	public void setSpecifics(){
		name = "Spice Trader";
		tags.add(CreatureTag.merchant);
		tags.add(CreatureTag.spiceTrader);
		courage = 10 + (Math.random() * 5);
		value = 15;
		if(value >= 60){
			addItem(Armour.vestFurDingo);
		}else if(value >= 25){
			addItem(Armour.tunicSilk);
		}else{
			addItem(Armour.tunicCotton);	
		}
		equip(0);
		if(Math.random() < 0.9){
			if(value >= 43){
				addItem(Item.sswordBronze);
			}else{
				addItem(Item.sswordCopper);
			}
			equip(1);
		}
		addItem(Item.money, (int)(Math.random() * value + 10));
		
		addShopItem(Food.pepper, 45);
		if(Math.random() < 0.3){
			addShopItem(Food.pepper, 40);
			value += 10;
		}
		if(Math.random() < 0.5){
			addShopItem(Food.cinnamon, 38);
			value += 8;
		}
		if(Math.random() < 0.3){
			addShopItem(Food.cinnamon, 35);
			value += 8;
		}
		if(Math.random() < 0.5){
			addShopItem(Food.nutmeg, 38);
			value += 8;
		}
		if(Math.random() < 0.3){
			addShopItem(Food.nutmeg, 35);
			value += 8;
		}
		if(Math.random() < 0.5){
			addShopItem(Food.ginger, 34);
			value += 6;
		}
		if(Math.random() < 0.3){
			addShopItem(Food.ginger, 30);
			value += 6;
		}
	}
	
	public void printDescription(){
		System.out.println("Sells a variety of exotic spices. Press H to haggle with the trader.");
	}
}
