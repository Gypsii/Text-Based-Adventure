package rammus.crafting;

import java.util.ArrayList;

import rammus.item.Item;

public class Recipe {

	public ArrayList<Item> components = new ArrayList<Item>();
	public Item product;
	public ArrayList<Integer> componentCount = new ArrayList<Integer>();
	
	public Recipe(Item i, Item c1){
		product = i;
		components.add(c1);
	}
	
	public Recipe(Item i, Item c1, int c1Count){
		product = i;
		addComponent(c1, c1Count);
	}
	
	public Recipe(Item i, Item c1, Item c2){
		product = i;
		addComponent(c1);
		addComponent(c2);
	}
	
	public Recipe(Item i, Item c1, Item c2, Item c3){
		product = i;
		addComponent(c1);
		addComponent(c2);
		addComponent(c3);
	}
	
	public Recipe(Item i, Item c1, Item c2, Item c3, Item c4){
		product = i;
		addComponent(c1);
		addComponent(c2);
		addComponent(c3);
		addComponent(c4);

	}
	
	public Recipe(Item i, Item c1, Item c2, Item c3, Item c4, Item c5){//These are the best practice I'm telling you now
		product = i;
		addComponent(c1);
		addComponent(c2);
		addComponent(c3);
		addComponent(c4);
		addComponent(c5);
	}
	
	public void addComponent(Item i){
		components.add(i);
		componentCount.add(1);
	}
	
	public void addComponent(Item i, int count){
		components.add(i);
		componentCount.add(count);
	}
	
	public static Recipe undefined = new Recipe(null, null);
	
}
