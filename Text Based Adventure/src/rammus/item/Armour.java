package rammus.item;

public class Armour extends Item{

	public int bluntResist;
	public int slashResist;
	public int pierceResist;
	public int burnResist;
	public int coldResist;
	public int magicResist;
	
	public Armour(String n, int blunt, int pierce, int slash, int burn, int cold, int magic) {
		name = n;
		bluntResist = blunt;
		pierceResist = pierce;
		slashResist = slash;
		burnResist = burn;
		coldResist = cold;
		magicResist = magic;
		isArmour = true;
		prefix = "a";
	}

	public static Armour tunicSilk = new Armour("Silk Tunic", 0, 1, 0, 0, 0, 0);
	public static Armour tunicCotton = new Armour("Cotton Tunic", 0, 1, 0, 0, 0, 0);
	public static Armour tunicRagged = new Armour("Ragged Tunic", 0, 0, 0, 0, 0, 0);
	public static Armour vestFurDingo = new Armour("Snow Dingo Fur Vest", 0, 1, 0, 0, 1, 0);
	public static Armour vestFurBear = new Armour("Polar Bear Fur Vest", 0, 1, 0, 0, 2, 0);
	public static Armour armourHideWalrus = new Armour("Hide Chestplate", 2, 1, 2, 0, 0, 0);
	public static Armour armourLeather = new Armour("Leather Chestplate", 2, 2, 3, 0, 0, 0);
	public static Armour mailIron = new Armour("Iron Mail Chestpiece", 2, 2, 4, 0, 0, 0);
	public static Armour mailCopper = new Armour("Copper Mail Chestpiece", 1, 1, 3, 0, 0, 0);
	public static Armour mailSteel = new Armour("Steel Mail Chestpiece", 2, 2, 5, 0, 0, 0);
	public static Armour plateIron = new Armour("Iron Plate Chestpiece", 3, 3, 4, 0, 0, 0);
	public static Armour plateCopper = new Armour("Copper Plate Chestpiece", 2, 2, 3, 0, 0, 0);
	public static Armour plateSteel = new Armour("Steel Plate Chestpiece", 5, 4, 5, 0, 0, 0);
	public static Armour plotArmour = new Armour("Magical Wizard Armour", 50, 50, 50, 50, 50, 50);
	
	public Armour clone(){
		Armour i = new Armour(name, bluntResist, pierceResist, slashResist, burnResist, coldResist, magicResist);
		i.isArmour = true;
		return i;
	}
}
