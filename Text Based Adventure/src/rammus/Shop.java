package rammus;

import java.util.ArrayList;

import rammus.item.Armour;
import rammus.item.Food;
import rammus.item.Item;
import rammus.item.Potion;

public class Shop {
	
	double markup = 1.5;
	double hagglingValue = 0.9;
	
	public Buyable bottle = new Buyable(Potion.blank, 13);
	public Buyable bread = new Buyable(Food.bread, 8);
	public Buyable butterKnife = new Buyable(Item.butterKnife, 28);
	public Buyable emerald = new Buyable(Item.emerald, 200);
	public Buyable featherSnow = new Buyable(Item.featherSnow, 2);
	public Buyable feather = new Buyable(Item.feather, 2);
	public Buyable featherWarm = new Buyable(Item.featherWarm, 3);
	public Buyable flint = new Buyable(Item.flint, 3.5);
	public Buyable fork = new Buyable(Item.fork, 35);
	public Buyable fungus = new Buyable(Food.fungus, 33);
	public Buyable gbladeBronze = new Buyable(Item.glaiveBladeBronze, 414);
	public Buyable gbladeCopper = new Buyable(Item.glaiveBladeCopper, 178);
	public Buyable gbladeIron = new Buyable(Item.glaiveBladeIron, 601);
	public Buyable gbladeRusted = new Buyable(Item.glaiveBladeRusted, 110);
	public Buyable glaiveBronze = new Buyable(Item.glaiveBronze, 345);
	public Buyable lswordCopper = new Buyable(Item.lswordCopper, 260);
	public Buyable lswordBronze = new Buyable(Item.lswordBronze, 550);
	public Buyable lswordIron = new Buyable(Item.lswordIron, 820);
	public Buyable lswordRusted = new Buyable(Item.lswordRusted, 160);
	public Buyable maceBronze = new Buyable(Item.maceBronze, 398);
	public Buyable maceCopper = new Buyable(Item.maceCopper, 190);
	public Buyable maceIron = new Buyable(Item.maceIron, 576);
	public Buyable mailCopper = new Buyable(Armour.mailCopper, 350);
	public Buyable mushroom = new Buyable(Food.mushroom, 4);
	public Buyable ruby = new Buyable(Item.ruby, 200);
	public Buyable sapphire = new Buyable(Item.sapphire, 200);
	public Buyable silk = new Buyable(Item.silk, 7);
	public Buyable slimeAdhesive = new Buyable(Item.slimeSticky, 8);
	public Buyable slimeExplosive = new Buyable(Item.slimeExplosive, 8);
	public Buyable slimeFiery = new Buyable(Item.slimeFire, 10);
	public Buyable slimeIce = new Buyable(Item.slimeIce, 5.5);
	public Buyable slimePlant = new Buyable(Item.slimePlant, 7);
	public Buyable slimeRock = new Buyable(Item.slimeEarth, 8);
	public Buyable slimeWater = new Buyable(Item.slimeWater, 5);
	public Buyable spearCopper = new Buyable(Item.spearCopper, 142);
	public Buyable spearheadBronze = new Buyable(Item.spearheadBronze, 335);
	public Buyable spearheadCopper = new Buyable(Item.spearheadCopper, 134);
	public Buyable spearheadRusted = new Buyable(Item.spearheadRusted, 96);
	public Buyable spearRusted = new Buyable(Item.spearRusted, 103);
	public Buyable spiceCinnamon = new Buyable(Food.cinnamon, 29);
	public Buyable spiceGinger = new Buyable(Food.ginger, 25);
	public Buyable spiceNutmeg = new Buyable(Food.nutmeg, 27);
	public Buyable spicePepper = new Buyable(Food.pepper, 32);
	public Buyable sswordCopper = new Buyable(Item.sswordCopper, 160);
	public Buyable sswordBronze = new Buyable(Item.sswordBronze, 376);
	public Buyable sswordIron = new Buyable(Item.sswordIron, 530);
	public Buyable sswordRusted = new Buyable(Item.sswordRusted, 126);
	public Buyable stickyString = new Buyable(Item.stickyString, 6.5);
	public Buyable string = new Buyable(Item.string, 4);
	public Buyable tusk = new Buyable(Item.walrusTusk, 32);
	public Buyable vestDingo = new Buyable(Armour.vestFurDingo, 165);
	public Buyable vestBear = new Buyable(Armour.vestFurBear, 250);

	
	ArrayList<Buyable> items = new ArrayList<Buyable>();
	ArrayList<Buyable> all = new ArrayList<Buyable>();
	
	public void addItem(Buyable i, double rarity, double localA){
		i.rarityConstant = rarity;
		i.localAvailability = localA;
		i.calculateValue();
		i.buyValue = (int) Math.round(i.marketValue * markup);
		i.sellValue = (int) Math.round(i.marketValue * hagglingValue);
		items.add(i);
	}
	
	public void addItemToAll(Buyable i, double rarity, double localA){
		i.rarityConstant = rarity;
		i.localAvailability = localA;
		i.calculateValue();
		i.buyValue = (int) Math.round(i.marketValue * markup);
		i.sellValue = (int) Math.round(i.marketValue * hagglingValue);
		all.add(i);
	}
	
	public void printItem(int n){
		System.out.println(n + ": " + items.get(n).item.getName() + " (" + items.get(n).buyValue + " gold)");
	}
	
	public Shop(){//Surely there's a better way than this
		addItemToAll(this.bottle, 0.9, 1);
		addItemToAll(this.bread, 0.9, 1);
		addItemToAll(this.butterKnife, 0.9, 1);
		addItemToAll(this.emerald, 0.8, 1);
		addItemToAll(this.flint, 1, 1);
		addItemToAll(this.featherSnow, 0.7, 1);
		addItemToAll(this.feather, 1, 1);
		addItemToAll(this.featherWarm, 0.7, 1);
		addItemToAll(this.fork, 0.9, 1);
		addItemToAll(this.fungus, 0.8, 1);
		addItemToAll(this.gbladeBronze, 0.8, 1);
		addItemToAll(this.gbladeCopper, 0.8, 1);
		addItemToAll(this.gbladeIron, 0.8, 1);
		addItemToAll(this.gbladeRusted, 0.8, 1);
		addItemToAll(this.glaiveBronze, 0.9, 1);
		addItemToAll(this.lswordCopper, 0.9, 1);
		addItemToAll(this.lswordBronze, 0.9, 1);
		addItemToAll(this.lswordIron, 0.9, 1);
		addItemToAll(this.lswordRusted, 1, 1);
		addItemToAll(this.maceCopper, 0.9, 1);
		addItemToAll(this.maceBronze, 0.9, 1);
		addItemToAll(this.maceIron, 0.8, 1);
		addItemToAll(this.mailCopper, 0.9, 1);
		addItemToAll(this.mushroom, 0.9, 1);
		addItemToAll(this.ruby, 0.8, 1);
		addItemToAll(this.sapphire, 0.8, 1);
		addItemToAll(this.silk, 0.9, 1);
		addItemToAll(this.slimeAdhesive, 0.9, 1);
		addItemToAll(this.slimeExplosive, 0.75, 1);
		addItemToAll(this.slimeFiery, 0.9, 1);
		addItemToAll(this.slimeIce, 0.75, 1);
		addItemToAll(this.slimePlant, 0.9, 1);
		addItemToAll(this.slimeRock, 0.9, 1);
		addItemToAll(this.slimeWater, 0.9, 1);
		addItemToAll(this.spearCopper, 0.9, 1);
		addItemToAll(this.spearheadBronze, 0.8, 1);
		addItemToAll(this.spearheadCopper, 0.8, 1);
		addItemToAll(this.spearheadRusted, 0.8, 1);
		addItemToAll(this.spearRusted, 0.9, 1);
		addItemToAll(this.spiceCinnamon, 0.7, 1.4);
		addItemToAll(this.spiceGinger, 0.7, 1.4);
		addItemToAll(this.spiceNutmeg, 0.7, 1.4);
		addItemToAll(this.spicePepper, 0.7, 1.4);
		addItemToAll(this.sswordCopper, 0.9, 1);
		addItemToAll(this.sswordBronze, 0.9, 1);
		addItemToAll(this.sswordIron, 0.9, 1);
		addItemToAll(this.sswordRusted, 1, 1);
		addItemToAll(this.stickyString, 0.9, 1);
		addItemToAll(this.string, 1, 1);
		addItemToAll(this.tusk, 0.7, 1);
		addItemToAll(this.vestDingo, 0.9, 1);
		addItemToAll(this.vestBear, 0.9, 1);
	}
	
	public int getSalePrice(Item i){
		for(int n = 0; n < all.size(); n++){
			if(all.get(n).item.name.equals(i.name)){
				return all.get(n).sellValue;
			}
		}
		return 0;
	}
}
