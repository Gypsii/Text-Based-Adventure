package rammus.crafting;

import java.util.ArrayList;

import rammus.item.Food;
import rammus.item.FoodTag;

public class FoodRecipe {
	
	public static ArrayList<FoodRecipe> recipes = new ArrayList<FoodRecipe>();
	
	public ArrayList<FoodTag> components = new ArrayList<FoodTag>();
	public ArrayList<FoodTag> badComponents = new ArrayList<FoodTag>();
	public Food product;
	
	public static FoodRecipe cinnamonBun = new FoodRecipe(Food.cinnamonBun);
	public static FoodRecipe spicyMeat = new FoodRecipe(Food.spicyMeat);
	public static FoodRecipe sandwichMeatVege = new FoodRecipe(Food.sandwichMeatVege);
	public static FoodRecipe sandwichMeatMushroom = new FoodRecipe(Food.sandwichMeatVege);
	public static FoodRecipe sandwichSalad = new FoodRecipe(Food.sandwichVegetable);
	public static FoodRecipe sandwichMeat = new FoodRecipe(Food.sandwichMeat);
	public static FoodRecipe sandwich = new FoodRecipe(Food.sandwich);
	public static FoodRecipe roastedMeat = new FoodRecipe(Food.meatRoast);
	public static FoodRecipe failure = new FoodRecipe(Food.failure);
	
	public static void doFoodRecipes(){
			cinnamonBun.forbidAll();
			cinnamonBun.addComponent(FoodTag.bread);
			cinnamonBun.addComponent(FoodTag.spice);
			
			spicyMeat.forbidAll();
			spicyMeat.addComponent(FoodTag.spice);
			spicyMeat.addComponent(FoodTag.meat);
			
			roastedMeat.addComponent(FoodTag.meat);
		
			sandwichMeatVege.addComponent(FoodTag.bread);
			sandwichMeatVege.addComponent(FoodTag.meat);
			sandwichMeatVege.addComponent(FoodTag.vegetable);
			
			sandwichMeatMushroom.addComponent(FoodTag.bread);
			sandwichMeatMushroom.addComponent(FoodTag.meat);
			sandwichMeatMushroom.addComponent(FoodTag.mushroom);

			sandwichSalad.addComponent(FoodTag.bread);
			sandwichSalad.addComponent(FoodTag.vegetable);
			
			sandwichMeat.addComponent(FoodTag.bread);
			sandwichMeat.addComponent(FoodTag.meat);
			
			sandwich.addComponent(FoodTag.bread);
			sandwich.addComponent(FoodTag.filler);
	}
	
	public FoodRecipe(Food i){
		product = i;
		recipes.add(this);
	}
	
	public void addComponent(FoodTag tag){
		components.add(tag);
		badComponents.remove(tag);
	}
	
	public void addForbiddenComponent(FoodTag tag){
		badComponents.add(tag);
	}
	
	public void forbidAll(){
		badComponents.add(FoodTag.mushroom);
		badComponents.add(FoodTag.slime);
		badComponents.add(FoodTag.meat);
		badComponents.add(FoodTag.vegetable);
		badComponents.add(FoodTag.fruit);
		badComponents.add(FoodTag.spice);
		badComponents.add(FoodTag.bread);
		badComponents.add(FoodTag.egg);
	}
	
	public void removeForbiddenComponent(FoodTag tag){
		badComponents.remove(tag);
	}
	
	public boolean check(ArrayList<Food> foods){
		//System.out.println("Checking " + product.name);
		for(int i = 0; i < badComponents.size(); i++){
			//System.out.println("Checking for bad food " + i);
			for(int j = 0; j < foods.size(); j++){
				if(foods.get(j).tags.contains(badComponents.get(i))){
					return false;
				}
			}
		}
		//System.out.println("Components = " + components.size());
		for(int i = 0; i < components.size(); i++){
			//System.out.println("Checking for food " + i);
			boolean satisfied = false;
			for(int j = 0; j < foods.size(); j++){
				if(foods.get(j).tags.contains(components.get(i))){
					//System.out.println("Found " + i);
					satisfied = true;
					break;
				}
			}
			if(!satisfied){
				//System.out.println(product.name + "failed");
				return false;
			}
		}
		return true;
	}
}
