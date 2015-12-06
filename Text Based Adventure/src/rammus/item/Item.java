package rammus.item;

import java.util.ArrayList;

import rammus.OnHit;

public class Item {
	public String name;
	public int count = 1;
	public int dmg = 0;
	public double swingTime = 1;
	public int dmgType = 0;
	public String prefix = "a"; 
	public boolean isArmour = false;
	public boolean isPotion = false;
	public boolean isFood = false;
	public boolean isCurrency = false;
	public boolean isStackable = true;
	public String modifier = "";
	public boolean isModifier = false;
	public boolean isIngredient = false;
	public ArrayList<OnHit> effects = new ArrayList<OnHit>();
	
	public Item(String n, boolean isIng){//Item w/o prefix
		name = n;
		isIngredient = isIng;
	}
	
	public Item(String n, boolean isIng, String pref){//Item with prefix
		name = n;
		prefix = pref;
		isIngredient = isIng;
	}
	
	public Item(String n, int damage, int type, double speed){//"Weapon" w/o prefix
		name = n;
		dmg = damage;
		dmgType = type;
		swingTime = speed;
	}
	
	public Item(String n, int damage, int type, double speed, String pref){//"Weapon" with prefix
		name = n;
		dmg = damage;
		prefix = pref;
		dmgType = type;
		swingTime = speed;
	}
	
	public Item(){//Armour, potions, currency etc.
		
	}
	
	public String getName(){//Now redundant
		if(count > 1){
			return name + " (" + count + ")";
		}
		return name;
	}
	
	public Item clone(){
		Item i = new Item(name, dmg, dmgType, swingTime, prefix);
		i.isArmour = this.isArmour;
		i.isModifier = this.isModifier;
		i.modifier = this.modifier;
		i.isIngredient = this.isIngredient;
		i.count = this.count;
		i.effects = this.effects;
		return i;
	}
	public static Item walrusTusk = new Item("Walrus tusk", 7, 1, 1);
	public static Item stick = new Item("Stick", 5, 0, 1.3);
	public static Item butterKnife = new Item("Butter Knife", 6, 2, 0.8);
	public static Item fork = new Item("Fork", 6, 1, 1);
	public static Item icicle = new Item("Icicle", 5, 1, 1, "an");
	public static Item clawGloves = new Item("Claw Gloves", 11, 2, 1, "some");
	
	public static Item glaiveCrude = new Item("Crude Glaive", 8, 2, 1);
	public static Item glaiveRusted = new Item("Rusted Glaive", 10, 2, 1);
	public static Item glaiveCopper = new Item("Copper Glaive", 12, 2, 1);
	public static Item glaiveBronze = new Item("Bronze Glaive", 15, 2, 1);
	public static Item glaiveIron = new Item("Iron Glaive", 18, 2, 1, "an");
	
	public static Item maceRusted = new Item("Rusted Mace", 10, 0, 1.3);
	public static Item maceCopper = new Item("Copper Mace", 11, 0, 1.3);
	public static Item maceBronze = new Item("Bronze Mace", 13, 0, 1.3);
	public static Item maceIron = new Item("Iron Mace", 15, 0, 1.3, "an");
	
	public static Item spearFlint = new Item("Flint Spear", 7, 1, 1);
	public static Item spearFork = new Item("Crude Spear", 7, 1, 1);
	public static Item spearRusted = new Item("Rusted Spear", 8, 1, 1);
	public static Item spearCopper = new Item("Copper Spear", 10, 1, 1);
	public static Item spearBronze = new Item("Bronze Spear", 13, 1, 1);
	public static Item spearIron = new Item("Iron Spear", 16, 1, 1, "an");
	
	public static Item pikeRusted = new Item("Rusted Pike", 10, 1, 1);
	public static Item pikeCopper = new Item("Copper Pike", 12, 1, 1);
	public static Item pikeBronze = new Item("Bronze Pike", 15, 1, 1);
	public static Item pikeIron = new Item("Iron Pike", 18, 1, 1, "an");
	
	public static Item sswordRusted = new Item("Rusted Shortsword", 9, 2, 0.8);
	public static Item sswordCopper = new Item("Copper Shortsword", 11, 2, 0.8);
	public static Item sswordBronze = new Item("Bronze Shortsword", 14, 2, 0.8);
	public static Item sswordIron = new Item("Iron Shortsword", 17, 2, 0.8, "an");
	
	public static Item lswordRusted = new Item("Rusted Longsword", 11, 2, 1);
	public static Item lswordCopper = new Item("Copper Longsword", 13, 2, 1);
	public static Item lswordBronze = new Item("Bronze Longsword", 16, 2, 1);
	public static Item lswordIron = new Item("Iron Longsword", 19, 2, 1, "an");
	
	public static Item spearheadRusted = new Item("Rusted Spearhead", 5, 1, 1);
	public static Item spearheadCopper = new Item("Copper Spearhead", 6, 1, 1);
	public static Item spearheadBronze = new Item("Bronze Spearhead", 7, 1, 1);
	public static Item spearheadIron = new Item("Iron Spearhead", 8, 1, 1, "an");

	public static Item glaiveBladeRusted = new Item("Single Edged Rusted Blade", 6, 2, 0.8);
	public static Item glaiveBladeCopper = new Item("Single Edged Copper Blade", 7, 2, 0.8);
	public static Item glaiveBladeBronze = new Item("Single Edged Bronze Blade", 8, 2, 0.8);
	public static Item glaiveBladeIron = new Item("Single Edged Iron Blade", 9, 2, 0.8);
	
	public static Item lswordDemon = new Item("Demonic Longsword", 17, 2, 1);
	public static Item lswordDemonFlame = new Item("Flaming Demonic Longsword", 17, 2, 1);
	public static Item lswordDemonMagic = new Item("Bound Demonic Longsword", 17, 2, 1);
	public static Item lswordDemonTap = new Item("Sappping Demonic Longsword", 17, 2, 1);
	
	public static Item lswordSlimeFire = new Item("Crystalised Fiery Slime Longsword", 14, 2, 1);
	public static Item lswordSlimeIce = new Item("Crystalised Icy Slime Longsword", 14, 2, 1);
	public static Item lswordSlimeVolatile = new Item("Crystalised Volatile Slime Longsword", 14, 2, 1);

	public static Item spiderbane = new Item("Spiderbane", 7, 2, 0.8);
	
	public static Item shanker = new Item("Shanker", 1337, 1, 0.01);
	
	public static Item baby = new Item("Stolen Baby", false);//Dropped by dingoes
	public static Item featherSnow = new Item("White Feather", true);
	public static Item feather = new Item("Brown Feather", true);
	public static Item featherWarm = new Item("Colourful Feather", true);
	public static Item hideWalrus = new Item("Walrus Hide", false, "some");
	public static Item hideDingo = new Item("Dingo Fur Hide", false, "some");
	public static Item hidePBear = new Item("Polar Bear Fur Hide", false, "some");

	public static Item silk = new Item("Spider Silk", false, "some");
	public static Item string = new Item("String", false, "some");
	public static Item cloth = new Item("Cotton Cloth", false, "some");
	public static Item stickyString = new Item("Sticky String", false, "some");
	public static Item ruby = new Item("Ruby", false);
	public static Item emerald = new Item("Emerald", false);
	public static Item sapphire = new Item("Sapphire", false);
	public static Item flint = new Item("Flint", 5, 2, 0.8, "some");

	public static Item slimeExplosive = new Item("Unstable Slime", true, "some");
	public static Item slimeFire = new Item("Hot Slime", true, "some");
	public static Item slimeIce = new Item("Cold Slime", true, "some");
	public static Item slimeWater = new Item("Dripping Slime", true, "some");
	public static Item slimeSticky = new Item("Adhesive Slime", true, "some");
	public static Item slimeEarth = new Item("Muddy Slime", true, "some");
	public static Item slimePlant = new Item("Photosynthetic Slime", true, "some");
	public static Item slimeSpice = new Item("Flavoursome Slime", true, "some");
	
	public static Item money = new Item("Gold Coin", false);

	//Unobtainable
	public static Item elementalCoreFire = new Item("UNOBTAINABLE ECore Fire", 15, 0, 2.3);
	public static Item elementalCoreWater = new Item("UNOBTAINABLE ECore Water", 15, 0, 2.3);
	public static Item elementalCoreIce = new Item("UNOBTAINABLE ECore Ice", 15, 0, 2.3);
	public static Item elementalCoreEarth = new Item("UNOBTAINABLE ECore Earth", 22, 0, 3);
	public static Item elementalCoreAir = new Item("UNOBTAINABLE ECore Air", 3, 0, 0.5);

	
	public static void doItems() {
		slimeFire.effects.add(new OnHit(OnHit.BURN, 1));
		slimeIce.effects.add(new OnHit(OnHit.COLD, 1));
		icicle.effects.add(new OnHit(OnHit.COLD, 1));
		Food.fungus.effects.add(new OnHit(OnHit.MAGIC, 2));
		spiderbane.effects.add(new OnHit(OnHit.FEARSPIDERS, 2));

		lswordSlimeFire.effects.add(new OnHit(OnHit.BURN, 4));
		lswordSlimeIce.effects.add(new OnHit(OnHit.COLD, 4));
		lswordSlimeVolatile.effects.add(new OnHit(OnHit.BURN, 4));
		
		lswordDemonFlame.effects.add(new OnHit(OnHit.BURN, 4));
		lswordDemonMagic.effects.add(new OnHit(OnHit.MAGIC, 6));
		lswordDemonMagic.effects.add(new OnHit(OnHit.SELFDMG, 3));
		lswordDemonTap.effects.add(new OnHit(OnHit.SELFHEAL, 2));
		
		elementalCoreFire.effects.add(new OnHit(OnHit.BURN, 8));
		elementalCoreIce.effects.add(new OnHit(OnHit.COLD, 8));
		elementalCoreAir.effects.add(new OnHit(OnHit.LIGHTNING, 4));
	}

}
