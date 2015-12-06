package rammus.crafting;

import rammus.Main;
import rammus.item.Armour;
import rammus.item.Item;

public class Crafting {

	//Indented for readablilty. Indented categories are subcategories of the indent higher than them.
	public static Category all = new Category ("Craft:");
	
	public static Category weapons = new Category("Weapons");
		public static Category polearms = new Category("Polearms");
			public static Category glaives = new Category("Glaives");
				public static Recipe glaiveCrude = new Recipe(Item.glaiveCrude, Item.stick, Item.butterKnife, Item.stickyString);
				public static Recipe glaiveRusted = new Recipe(Item.glaiveRusted, Item.stick, Item.glaiveBladeRusted, Item.stickyString);
				public static Recipe glaiveCopper = new Recipe(Item.glaiveCopper, Item.stick, Item.glaiveBladeCopper, Item.stickyString);
				public static Recipe glaiveBronze = new Recipe(Item.glaiveBronze, Item.stick, Item.glaiveBladeBronze, Item.stickyString);
				public static Recipe glaiveIron = new Recipe(Item.glaiveIron, Item.stick, Item.glaiveBladeIron, Item.stickyString);
			public static Category spears = new Category("Spears");
				public static Recipe spearFlint = new Recipe(Item.spearFlint, Item.stick, Item.flint, Item.stickyString);
				public static Recipe spearCrude = new Recipe(Item.spearFork, Item.stick, Item.fork, Item.stickyString);
				public static Recipe spearRusted = new Recipe(Item.spearRusted, Item.stick, Item.spearheadRusted, Item.stickyString);
				public static Recipe spearCopper = new Recipe(Item.spearCopper, Item.stick, Item.spearheadCopper, Item.stickyString);
				public static Recipe spearBronze = new Recipe(Item.spearBronze, Item.stick, Item.spearheadBronze, Item.stickyString);
				public static Recipe spearIron = new Recipe(Item.spearIron, Item.stick, Item.spearheadIron, Item.stickyString);
		public static Category swords = new Category("Swords");
		public static Category axes = new Category("Axes");

	public static Category components = new Category("Components");
		public static Category stringsRopes = new Category("Strings, cords and ropes");
			public static Recipe adhesiveString = new Recipe(Item.stickyString, Item.silk, Item.slimeSticky);
			public static Recipe adhesiveString2 = new Recipe(Item.stickyString, Item.string, Item.slimeSticky);
		public static Category cloth = new Category("Cloth");
			public static Recipe clothCotton = new Recipe(Item.cloth, Item.string, 3);
		
	public static Category armourSuper = new Category("Armour/Clothing");
		public static Category clothing = new Category("Clothing");
			public static Recipe armourSilk = new Recipe(Armour.tunicSilk, Item.silk, 4);
			public static Recipe armourCotton = new Recipe(Armour.tunicCotton, Item.cloth, 2);
			public static Recipe vestDingo = new Recipe(Armour.vestFurDingo, Item.hideDingo, Item.cloth);
			public static Recipe vestPBear = new Recipe(Armour.vestFurBear, Item.hidePBear, Item.cloth, Item.silk);
		public static Category armour = new Category("Armour");
			public static Recipe armourHide = new Recipe(Armour.armourHideWalrus, Item.hideWalrus, Armour.tunicCotton, Item.string);

	
	public static void doRecipes(){
		//Also indented for readablilty
		all.addSubcategory(weapons);
			weapons.addSubcategory(polearms);
				polearms.addSubcategory(glaives);
					glaives.addRecipe(glaiveCrude);
					glaives.addRecipe(glaiveRusted);
					glaives.addRecipe(glaiveCopper);
					glaives.addRecipe(glaiveBronze);
					glaives.addRecipe(glaiveIron);
				polearms.addSubcategory(spears);
					spears.addRecipe(spearFlint);
					spears.addRecipe(spearCrude);
					spears.addRecipe(spearRusted);
					spears.addRecipe(spearCopper);
					spears.addRecipe(spearBronze);
					spears.addRecipe(spearIron);
			weapons.addSubcategory(swords);
			weapons.addSubcategory(axes);
		all.addSubcategory(components);
			components.addSubcategory(stringsRopes);
				stringsRopes.addRecipe(adhesiveString);
				stringsRopes.addRecipe(adhesiveString2);
			components.addSubcategory(cloth);
				cloth.addRecipe(clothCotton);
		all.addSubcategory(armourSuper);
			armourSuper.addSubcategory(clothing);
				clothing.addRecipe(armourSilk);
				clothing.addRecipe(armourCotton);
				clothing.addRecipe(vestDingo);
				clothing.addRecipe(vestPBear);
			armourSuper.addSubcategory(armour);
				armour.addRecipe(armourHide);
		
	}
	
	public static void listContents(Category c){
		int i = 0;
		if(!c.subcategories.isEmpty()){
			for(i = 0; i < c.subcategories.size(); i++){
				System.out.println("(" + i + ") " + c.subcategories.get(i).name);
			}			
		}
		if(!c.recipes.isEmpty()){
			System.out.println("Recipes:");
			for(int j = 0; j < c.recipes.size(); j++){
				Recipe r = c.recipes.get(j);
				System.out.print("(" + (j + i) + ") ");
				if(Main.canAfford(r, false)){
					System.out.print(Main.ANSI_GREEN);
				}
				System.out.print(r.product.name + " (" + r.components.get(0).name);
				if(r.componentCount.get(0) > 1){
					System.out.print(" (" + r.componentCount.get(0) + ")");
				}
				if(r.components.size() > 1){
					for(int k = 1; k < r.components.size(); k++){
						System.out.print(", " + r.components.get(k).name);
						if(r.componentCount.get(k) > 1){
							System.out.print(" (" + r.componentCount.get(k) + ")");
						}
					}
				}		
				System.out.println(")" + Main.ANSI_RESET);
			}			
		}
	}
	
}
