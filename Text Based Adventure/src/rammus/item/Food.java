package rammus.item;

import java.util.ArrayList;

public class Food extends Item{
	
	public ArrayList<FoodTag> tags = new ArrayList<FoodTag>();
	
	public Food(String n, boolean isIng){
		name = n;
		isFood = true;
	}
	
	public Food(String n, boolean isIng, String pref){
		name = n;
		isFood = true;
		prefix = pref;
	}
	
	public Food clone(){
		Food i = new Food(this.name, this.isIngredient, this.prefix);
		i.isFood = true;
		i.tags = this.tags;
		return i;
	}
	
	public static Food leaf = new Food("Leaf", false);
	
	public static Food egg = new Food("Egg", true, "an");
	
	public static Food mushroom = new Food("Mushroom", true);
	public static Food fungus = new Food("Mystical Fungus", true);
	
	public static Food pepper = new Food("Black Pepper", true, "some");
	public static Food cinnamon = new Food("Cinnamon", true, "some");
	public static Food nutmeg = new Food("Nutmeg", true, "some");
	public static Food ginger = new Food("Ginger", true, "some");
	
	public static Food bread = new Food("Bread", false, "some");
	public static Food cinnamonBun = new Food("Cinnamon Bun", false, "some");
	public static Food sandwichMeatVege = new Food("Magestic Sandwich", false);
	public static Food sandwichMeat = new Food("Meaty Sandwich", false);
	public static Food sandwichVegetable = new Food("Salad Sandwich", false);
	public static Food sandwich = new Food("Sandwich", false);
	
	public static Food meatBird = new Food("Bird Meat", false, "some");
	public static Food meatBear = new Food("Polar Bear Meat", false, "some");
	public static Food meatDingo = new Food("Dingo Meat", false, "some");
	public static Food meatSeal = new Food("Seal Meat", false, "some");
	public static Food meatSpider = new Food("Spider Meat", false, "some");
	public static Food meatTiger = new Food("Tiger Meat", false, "some");
	public static Food meatTurtle = new Food("Turtle Meat", false, "some");
	public static Food meatWalrus = new Food("Walrus Meat", false, "some");
	
	public static Food spicyMeat = new Food("Hot and Spicy Meat", false, "some");
	public static Food meatRoast = new Food("Roast Meat", false, "some");

	public static Food failure = new Food("\"Cooking\"", false, "some");

	
	public static void TagFoods(){
		meatBird.tagAsMeat();
		meatBear.tagAsMeat();
		meatDingo.tagAsMeat();
		meatSeal.tagAsMeat();
		meatSpider.tagAsMeat();
		meatTiger.tagAsMeat();
		meatTurtle.tagAsMeat();
		meatWalrus.tagAsMeat();
		spicyMeat.tagAsMeat();
		
		leaf.tags.add(FoodTag.vegetable);
		leaf.tags.add(FoodTag.filler);

		egg.tags.add(FoodTag.egg);
		egg.tags.add(FoodTag.filler);
		
		mushroom.tags.add(FoodTag.mushroom);
		mushroom.tags.add(FoodTag.filler);
		fungus.tags.add(FoodTag.mushroom);
		
		pepper.tags.add(FoodTag.spice);
		cinnamon.tags.add(FoodTag.spice);
		nutmeg.tags.add(FoodTag.spice);
		ginger.tags.add(FoodTag.spice);
		
		bread.tags.add(FoodTag.bread);
		
	}
	
	private void tagAsMeat(){
		tags.add(FoodTag.meat);
		tags.add(FoodTag.filler);
	}
	
}
