package rammus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import rammus.crafting.Category;
import rammus.crafting.Crafting;
import rammus.crafting.FoodRecipe;
import rammus.crafting.Recipe;
import rammus.creatures.Bird;
import rammus.creatures.Creature;
import rammus.creatures.Player;
import rammus.creatures.Walrus;
import rammus.creatures.humans.NameGenerator;
import rammus.item.Armour;
import rammus.item.Food;
import rammus.item.Item;
import rammus.item.Potion;

public class Main{
	public static int PASSIVECREATURECAP = 3;
	public static int STARTGOLD = 75;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static boolean isWindows = false;

	static int targetIndex = 0;
	public static int levelNum = 0; 
	public static HashMap<Integer, Level> levels = new  HashMap<Integer, Level>();
	public static Level level;
	public static int levelDiff = 1;
	public static Zone zone;
	public static int position = 0;
	public static int money = STARTGOLD;
	public static Player player = new Player();
	public static Shop startingShop = new Shop();
	static Comparator<Creature> comparator = new TurnComparator();
	static PriorityQueue<Creature> toTakeTurn = new PriorityQueue<Creature>(10, comparator);
	
	public static void main(String[] args) throws IOException{
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		if(osName.startsWith("Windows")){
			isWindows = true;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		printPurple("Dank Meme Adventure v0.4.20");
		Crafting.doRecipes();
		FoodRecipe.doFoodRecipes();
		Food.TagFoods();
		Item.doItems();
		NameGenerator.names();
		System.out.println("Who do you think you are?");
		player.name = br.readLine();
		if(player.name.length() > 20){
			System.out.println("You have a long name.");
		}
		
		position = 0;
		zone = new Zone();
		System.out.println("Before setting out on your adventure you decide to prepare yourself.");
		startingShop.addItem(startingShop.bottle, 1, 1);
		startingShop.addItem(startingShop.bread, 1, 1);
		startingShop.addItem(startingShop.mushroom, 1, 1);
		startingShop.addItem(startingShop.string, 1, 1);
		startingShop.addItem(startingShop.stickyString, 1, 1);
		startingShop.addItem(startingShop.tusk, 2, 1);
		startingShop.addItem(startingShop.butterKnife, 1, 1);
		startingShop.addItem(startingShop.gbladeRusted, 1, 1);
		startingShop.addItem(startingShop.spearheadCopper, 1, 1);
		startingShop.addItem(startingShop.sswordBronze, 1, 1);
		Level.generateLevel(levelDiff);
		level = levels.get(levelNum);
		level.shop = startingShop;
		shop();
		printBlue("You set out on your adventure.");
		level.printLevelDescription();
		enterZone();
		toTakeTurn.add(player);
		
		while(player.hp > 0){
			Creature c = toTakeTurn.remove();
			//printCyan(c.name + "'s action at " + c.nextActionTime);
			if(c == player){
				double time = playerTurn();   //This has to be like this because magic
				player.nextActionTime += time;//Otherwise it breaks
			}else{
				if(c.alive){
					c.nextActionTime += c.takeTurn();
				}else{
					c.nextActionTime += 10000;
				}
			}
			//System.out.println(c.name + "'s next action is at " + c.nextActionTime);
			//System.out.println(toTakeTurn.peek().name + " is on top of the pq");
			toTakeTurn.add(c);
		}
		System.out.println("Game over.");
	}

	public static double playerTurn() throws IOException{//Returns true if action takes full turn
		if(player.paralysedTurns > 0){
			printBlue("You are paralysed");
			int t = player.paralysedTurns;
			player.paralysedTurns = 0;
			return t;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = br.readLine().toLowerCase();
		if(command.equals("dingo stole my baby")){
			zone.addItem(Item.shanker);
			zone.addItem(Armour.plotArmour);
			return 0;
		}
		if(command.equals("hacking")){
			zone.addItem(Item.glaiveIron);
			Walrus graham = new Walrus(){
				public double decideAttackPattern(){
					attack(2, "slashed", "its Claw Gloves");
					return 2.3;
				}
			};
			graham.name = "Graham the Walrus";
			graham.xp = 30;
			graham.dmg = 6;
			graham.inv.add(Item.clawGloves);
			zone.addCreature(graham);
			return 0;
		}
		if(command.equals("a") || command.equals("attack")){
			if(zone.creatures.isEmpty()){
				System.out.println("There is nothing to attack");
				return 0;
			}else if(zone.creatures.size() > targetIndex){
				Creature c = zone.creatures.get(targetIndex);
				if(player.equipped.dmgType == 0){
					printBlue("You hit the " + c.name + " for " + (player.dmg - c.bluntArmour) + " damage, leaving it on " + Math.max(0, c.hp - (player.dmg - c.bluntArmour)) + " hp");
					c.takeBluntDamage(player.dmg);
					c.aggravateTrigger(player);
				}else if(player.equipped.dmgType == 1){
					printBlue("You pierce the " + c.name + " for " + (player.dmg - c.pierceArmour) + " damage, leaving it on " + Math.max(0, c.hp - (player.dmg - c.pierceArmour)) + " hp");
					c.takePierceDamage(player.dmg);
					c.aggravateTrigger(player);
				}else if(player.equipped.dmgType == 2){
					printBlue("You slash the " + c.name + " for " + (player.dmg - c.slashArmour) + " damage, leaving it on " + Math.max(0, c.hp - (player.dmg - c.slashArmour)) + " hp");
					c.takeSlashDamage(player.dmg);
					c.aggravateTrigger(player);
				}
				player.target = c;
				player.applyOnHits(player.equipped.effects);
				return player.equipped.swingTime;
			}else{
				printRed("There is no creature with that ID!");
				return 0;
			}
		}
		if(command.equals("n") || command.equals("north")){
			if(canExitZone()){
				if(position < Level.LEVELSIZE * 100){
					printBlue("You walk North");
					position += 100;
					enterZone();
				}else{
					printRed("You can not walk North");
				}
				
			}else{
				printRed("There are still creatures in this zone");
			}
			return 0;
		}
		if(command.equals("s") || command.equals("south")){
			if(canExitZone()){
				if(position >= 100){
					printBlue("You walk South");
					position -= 100;
					enterZone();
				}else{
					printRed("You can not walk South");
				}
			}else{
				printRed("There are still creatures in this zone");
			}
			return 0;
		}
		if(command.equals("e") || command.equals("east")){
			if(canExitZone()){
				if(position % 100 < Level.LEVELSIZE){
					printBlue("You walk East");
					position += 1;
					enterZone();
				}else{
					printRed("You can not walk East");
				}
			}else{
				printRed("There are still creatures in this zone");
			}
			return 0;
		}
		if(command.equals("w") || command.equals("west")){
			if(canExitZone()){
				if(position % 100 > 0){
					printBlue("You walk West");
					position -= 1;
					enterZone();
				}else{
					printRed("You can not walk West");
				}
			}else{
				printRed("There are still creatures in this zone");
			}
			return 0;
		}
		if(command.equals("l") || command.equals("list") || command.equals("look")){
			viewZone();
			return 0;
		}
		if(command.equals(".") || command.equals("wait")){
			printBlue("You do nothing");
			return 1;
		}
		if(command.equals("k") || command.equals("take")){
			if(zone.items.size() == 0){
				System.out.println("There are no items to be taken.");
				return 0;
			}
			listItems();
			int n = 0;
			try{
				n = Integer.parseInt(br.readLine());
				if(n < zone.items.size() && n >= 0){
					printBlue("You took the " + zone.items.get(n).getName()); 
					if(zone.items.get(n).name == Item.money.name){
						money += zone.items.get(n).count;
						printYellow("You now have " + money + " gold"); 
					}else{
						player.addItem(zone.items.get(n));
					}
					if(zone.items.get(n).name == Food.egg.name){
						triggerBirds();
					}
					zone.items.remove(n);
				}else{
					printRed("There is no item with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}			
			return 0;
		}
		if(command.equals("t") || command.equals("target")){
			listTargets();
			int n = 0;
			try{
				n = Integer.parseInt(br.readLine());
				if(n < zone.creatures.size() && n >= 0){
					targetIndex = n;
				}else{
					printRed("There is no creature with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
			return 0;
		}
		if(command.equals("i") || command.equals("info")){
			if(zone.creatures.size() > targetIndex){
				zone.creatures.get(targetIndex).printInfo();
			}else{
				printRed("There is no creature with that ID!");
			}
			return 0;
		}
		if(command.equals("dingo")){
			printRed("ERROR 420: DINGO NOT FOUND");
			return 0;
		}
		if(command.equals("help") || command.equals("?")){	//Free	gjmorvwxy,+-
			System.out.println("North: n");					//Used	abcdefhiklnpqstz.?
			System.out.println("South: s");
			System.out.println("East: e");
			System.out.println("West: w");
			System.out.println("Attack: a");
			System.out.println("Target enemy: t");
			System.out.println("Take item: k");
			System.out.println("Drop item: d");
			System.out.println("Equip weapon/armour: q");
			System.out.println("Brew Potion: p");
			System.out.println("Use Potion: u");
			System.out.println("Make items: m");
			System.out.println("Cook food: c");
			System.out.println("Eat food: f");
			System.out.println("Butcher target: b");
			System.out.println("Haggle with target: h");
			System.out.println("Look at zone: l");
			System.out.println("Target info: i");
			System.out.println("Wait: .");
			System.out.println("Leave final zone: z");
			return 0;
		}
		if(command.equals("q") || command.equals("equip")){
			listInv();
			int n = 0;
			try{
				n = Integer.parseInt(br.readLine());
				if(n < player.inv.size() && n >= 0){
					player.equip(n);
				}else{
					printRed("There is no item with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
			return 0.2;
		}
		if(command.equals("p") || command.equals("brew") || command.equals("alchemy")){
			System.out.println("Select Potion/Vessel:");
			listInvPotions();
			int potId = 0;
			try{
				potId = Integer.parseInt(br.readLine());
				if(potId < player.inv.size() && potId >= 0){
					if(player.inv.get(potId).isPotion){
						Potion pot = (Potion) player.inv.get(potId);
						System.out.println("Select Ingredient");
						listInvIngredients();
						int ingId = 0;
						try{
							ingId = Integer.parseInt(br.readLine());
							if(ingId < player.inv.size() && ingId >= 0 && potId != ingId){
								Item ing = player.inv.get(ingId);
								if(ing.isIngredient){
									alchemise(pot, ing);
								}else{
									printRed("Selected item is not an ingredient");
								}
							}else{
								printRed("There is no item with that ID!");
							}
						}catch(NumberFormatException nfe){
							printRed("Invalid Format!");
						}
					}else{
						printRed("Selected item is not a potion or empty potion vessel");
					}
				}else{
					printRed("There is no item with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
			return 5;
		}
		if(command.equals("u") || command.equals("potion")){
			listInv();
			int n = 0;
			try{
				n = Integer.parseInt(br.readLine());
				if(n < player.inv.size() && n >= 0){
					if(player.inv.get(n).isPotion){
						Potion potion = (Potion) player.inv.get(n);
						boolean used = false;
						if(zone.creatures.size() > 0){
							System.out.println("Select target:");
							System.out.println("0: Self");
							if(zone.creatures.size() > targetIndex){
								System.out.println("1: Target Creature (" + zone.creatures.get(targetIndex).name + ")");
							}
							try{
								n = Integer.parseInt(br.readLine());
								if(n == 0){
									printBlue("You drank the " + potion.getName());
									player.applyPotionEffects(potion);
									player.addItem(Potion.blank);
									used = true;
								}else if(n == 1 && zone.creatures.size() > targetIndex){
									printBlue("You used the " + potion.getName() + " on the " + zone.creatures.get(targetIndex).name);
									zone.creatures.get(targetIndex).applyPotionEffects(potion);
									used = true;
								}else{
									printRed("There is no command with that ID");
								}
							}catch(NumberFormatException nfe){
								printRed("Invalid Format!");
							}
						}else{
							printBlue("You drank the " + potion.getName());
							player.applyPotionEffects(potion);
							player.addItem(Potion.blank);
							used = true;
						}
						if(used){
							player.removeItem(potion);
						}
					}else{
						printRed("Selected item is not a potion");
					}
				}else{
					printRed("There is no item with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
			return 0.3;
		}
		if(command.equals("m") || command.equals("create") || command.equals("craft") || command.equals("make")){
			Category c = Crafting.all;
			while(true){
				System.out.println("(-1) Exit");
				Crafting.listContents(c);			
				try{
					int n = Integer.parseInt(br.readLine());
					if(n < c.subcategories.size() + c.recipes.size() && n >= 0){
						if(n < c.subcategories.size()){
							c = c.subcategories.get(n);
						}else{
							craft(c.recipes.get(n - c.subcategories.size()));
						}
					}else if(n == -1){
						break;
					}else{
						printRed("There is no recipe or category with that ID!");
					}
				}catch(NumberFormatException nfe){
					printRed("Invalid Format!");
				}
			}
			return 0;
		}
		if(command.equals("d") || command.equals("drop")){
			listInv();
			int n = 0;
			try{
				n = Integer.parseInt(br.readLine());
				if(n < player.inv.size() && n >= 0){
					Item i = player.inv.get(n);
					zone.items.add(i);
					printBlue("You dropped the " + i.getName());
					if(player.equipped == i){
						player.equipped = new Item("unarmed", 3, 0, 1);
					}else if(player.armourChest == i){
						player.bluntArmour -= player.armourChest.bluntResist;
						player.pierceArmour -= player.armourChest.pierceResist;
						player.slashArmour -= player.armourChest.slashResist;
						player.burnArmour -= player.armourChest.burnResist;
						player.coldArmour -= player.armourChest.coldResist;
						player.armourChest = new Armour("unarmoured", 0, 0, 0, 0, 0, 0);
					}
					player.removeItem(n);
					
				}else{
					printRed("There is no item with that ID!");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
			return 0;
		}
		if(command.equals("z")){
			if(zone.isFinalZone){
				if(canExitZone()){
					player.hp = Math.max(player.hp, player.lvl * 4);
					player.hp = Math.min(player.hp, player.maxHp);
					levelNum ++;
					levelDiff ++;
					Level.generateLevel(levelDiff);
					level = levels.get(levelNum);
					shop();
					position = 0;
					zone = level.zones.get(0);
					System.out.println("You arrive in the next level.");
					level.printLevelDescription();
					viewZone();
				}else{
					printRed("There are still creatures in this zone!");
				}
			}else{
				printRed("This is not the final zone!.");	
			}
			return 0;
		}
		if(command.equals("h") || command.equals("haggle")){
			Creature c = zone.creatures.get(targetIndex);
			if(c.shopInv.size() > 0 && !c.hatesPlayer && c.alive){
				while(true){
					System.out.println("You have " + money + " gold");
					System.out.println("-1: Exit");
					for(int i = 0; i < c.shopInv.size(); i++){
						System.out.println(i + ": " + c.shopInv.get(i).name + " (" + c.shopInv.get(i).count + ", " + c.prices.get(c.shopInv.get(i).name) + " gold)");
					}
					int n = 0;
					try{
						n = Integer.parseInt(br.readLine());
						if(n < c.shopInv.size() && n >= 0){
							if(money < c.prices.get(c.shopInv.get(n).name)){
								printRed("You can not afford that item");
							}else{
								printGreen("You bought the " + c.shopInv.get(n).name + " for " + c.prices.get(c.shopInv.get(n).name) + " gold");  
								Item item = c.shopInv.get(n).clone();
								item.count = 1;
								player.addItem(item);
								money -= c.prices.get(c.shopInv.get(n).name);
								for(int j = 0; j < c.inv.size(); j++){
									if(c.inv.get(j).name.equals(Item.money.name)){
										c.inv.get(j).count += c.prices.get(c.shopInv.get(n).name);
										break;
									}
								}
								c.shopInv.get(n).count --;
								if(c.shopInv.get(n).count < 1){
									c.shopInv.remove(n);
								}
							} 
						}else if(n == -1){
							break;
						}else{
							printRed("There is no item with that ID!");

						}
					}catch(NumberFormatException nfe){
						printRed("Invalid Format!");
					}
				} 			
			}else{
				printRed("The target has nothing to sell");	
			}
			return 0;
		}
		
		if(command.equals("b") || command.equals("butcher")){
			if(zone.creatures.size() > targetIndex){
				if(!zone.creatures.get(targetIndex).alive){
					printBlue("You butcher the " + zone.creatures.get(targetIndex).name);
					zone.creatures.get(targetIndex).dropButcherItems();
					return 3;
				}else{
					printRed("This creature is still alive!");
					return 0;
				}
			}else{
				printRed("There is no creature with that ID!");
				return 0;
			}	
		}
		
		if(command.equals("c") || command.equals("bake") || command.equals("cook")){
			ArrayList<Food> foods = new ArrayList<Food>();
			Boolean finishingRecipe;
			while(true){
				listInvFoods();
				System.out.println(player.inv.size() + ": Finish");
				try{
					int n = Integer.parseInt(br.readLine());
					if(n < player.inv.size() && n >= 0){
						if(player.inv.get(n).isFood){
							foods.add((Food)player.inv.get(n));
						}else{
							printRed("This can not be used as a food.");
						}
					}else if(n == -1){
						finishingRecipe = false;
						break;
					}else if(n == player.inv.size()){
						finishingRecipe = true;
						break;
					}else{
						printRed("There is no recipe or category with that ID!");
					}
				}catch(NumberFormatException nfe){
					printRed("Invalid Format!");
				}
			}
			if(finishingRecipe){
				Food result = Food.failure;
				for(int i = 0; i < FoodRecipe.recipes.size(); i++){
					if(FoodRecipe.recipes.get(i).check(foods)){
						result = FoodRecipe.recipes.get(i).product;
						break;
					}
				}
				printBlue("You made " + result.prefix + " " + result.name);
				return 10;
			}
			return 0;
		}
		
		printRed("Invalid Command");
		return 0;
	}
	
	public static void doTurnList(){
		toTakeTurn.clear();
		player.nextActionTime = -0.01;
		//toTakeTurn.add(player);
		for(int i = 0; i < zone.creatures.size(); i++){
			zone.creatures.get(i).nextActionTime = 0;
			toTakeTurn.add(zone.creatures.get(i));
		}
	}
	
	public static void enterZone(){
		System.out.println(position / 100 + ", " + position % 100);
		zone = level.zones.get(position);
		viewZone();
		targetIndex = 0;
		doTurnList();
	}
	
	public static boolean canExitZone(){		
		for(int i = 0; i < zone.creatures.size(); i++){
			if(zone.creatures.get(i).stopsZoneExit){
				return false;
			}
		}
		return true;		
	}
	
	public static void listTargets(){
		for(int i = 0; i < zone.creatures.size(); i++){
			if(zone.creatures.get(i).alive){
				System.out.println(i + ": " + zone.creatures.get(i).name + " (" + zone.creatures.get(i).hp + " hp)");
			}else{
				System.out.println(i + ": " + zone.creatures.get(i).name + " (dead)");
			}
		}
	}
	
	public static void listItems(){
		for(int i = 0; i < zone.items.size(); i++){
			System.out.println(i + ": " + zone.items.get(i).getName());		
		}
	}
	
	public static void listInv(){
		for(int i = 0; i < player.inv.size(); i++){
			System.out.println(i + ": " + player.inv.get(i).getName());		
		}
	}
	
	public static void listInvFoods(){
		for(int i = 0; i < player.inv.size(); i++){
			System.out.print(i + ": ");
			if(player.inv.get(i).isFood){
				System.out.print(ANSI_GREEN);
			}
			System.out.println(player.inv.get(i).getName() + ANSI_RESET);
		}
	}
	
	public static void listInvIngredients(){
		for(int i = 0; i < player.inv.size(); i++){
			System.out.print(i + ": ");
			if(player.inv.get(i).isIngredient){
				System.out.print(ANSI_GREEN);
			}
			System.out.println(player.inv.get(i).getName() + ANSI_RESET);
		}
	}
	
	public static void listInvPotions(){
		for(int i = 0; i < player.inv.size(); i++){
			System.out.print(i + ": ");
			if(player.inv.get(i).isPotion){
				System.out.print(ANSI_GREEN);
			}
			System.out.println(player.inv.get(i).getName() + ANSI_RESET);
		}
	}
	
	public static void viewZone(){
		listCreatures();
		if(zone.items.size() > 0){
			System.out.print("Items: " + zone.items.get(0).prefix + " " +  zone.items.get(0).getName());
			for(int i = 1; i < zone.items.size() - 1; i++){
				System.out.print(", " + zone.items.get(i).prefix + " " +  zone.items.get(i).getName());
			}
			if(zone.items.size() > 1){
				System.out.println(" and " + zone.items.get(zone.items.size() - 1).prefix + " " +  zone.items.get(zone.items.size() - 1).getName());
			}else{
				System.out.println("");
			}
		}
		zone.printDescription();
	}
	
	public static void listCreatures(){
		if(zone.creatures.size() > 0){
			if(zone.creatures.get(0).nameIsGeneral){
				System.out.print("You see a " + zone.creatures.get(0).name);
			}else{
				System.out.print("You see " + zone.creatures.get(0).name);
			}
			for(int i = 1; i < zone.creatures.size() - 1; i++){
				if(zone.creatures.get(i).nameIsGeneral){
					System.out.print(", a " + zone.creatures.get(i).name);
				}else{
					System.out.print(", " + zone.creatures.get(i).name);
				}			
			}
			if(zone.creatures.size() > 1){
				if(zone.creatures.get(zone.creatures.size() - 1).nameIsGeneral){
					System.out.println(" and a " + zone.creatures.get(zone.creatures.size() - 1).name);
				}else{
					System.out.println(" and " + zone.creatures.get(zone.creatures.size() - 1).name);
				}
			}else{
				System.out.println("");
			}	
		}
	}
	public static void alchemise(Potion pot, Item ing){
		if(pot.count > 1){
			Item clone = pot.clone();
			clone.count --;
			player.addItem(clone);
			pot.count = 1;
		}
		pot.addIngredient(ing);
		ing.count --;
		if(ing.count < 1){
			player.removeItem(ing);
		}
	}
	
	public static boolean canAfford(Recipe r, boolean printReason){
		for(int i = 0; i < r.components.size(); i++){
			int loc = player.findItemLoc(r.components.get(i));
			if(loc == -1){
				if(printReason){
					printRed("You lack the necessary " + r.components.get(i).name);
				}
				return false;
			}else if(r.componentCount.get(i) > player.inv.get(loc).count){
				if(printReason){
					printRed("You lack the necessary " + r.components.get(i).name);
				}
				return false;
			}
		}
		return true;
	}
	
	public static void craft(Recipe r){
		if(r.product != null){
			if(canAfford(r, true)){
				for(int i = 0; i < r.components.size(); i++){
					int loc = player.findItemLoc(r.components.get(i));
					player.inv.get(loc).count -= r.componentCount.get(i);
					if(player.inv.get(loc).count < 1){
						player.removeItem(loc);
					}
				}
				player.addItem(r.product);
				printGreen("Crafting of " + r.product.name + " successful!");
			}
		}
	}
	
	public static void triggerBirds(){
		for(int i = 0; i < zone.creatures.size(); i++){
			if(zone.creatures.get(i).getClass() == new Bird(0).getClass()){
				if(zone.creatures.get(i).alive){
					zone.creatures.get(i).itemStealTrigger();
				}
			}
		}
		zone.containsEgg = false;
		for(int i = 0; i < zone.items.size(); i++){
			if(zone.items.get(i).name == Food.egg.name){
				zone.containsEgg = true;
			}
		}
	}
	
	public static void shop() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		printBlue("You arrive at a shop.");
		Shop s = level.shop;
		while(true){
			int n = 0;
			System.out.println("0: Buy");
			System.out.println("1: Sell");
			System.out.println("2: Exit");
			try{
				n = Integer.parseInt(br.readLine());
				if(n == 0){//buying
					while(true){
						System.out.println("You have " + money + " gold");
						System.out.println("-1: Close");
						for(int i = 0; i < s.items.size(); i++){
							s.printItem(i);
						}
						try{
							n = Integer.parseInt(br.readLine());
							if(n < s.items.size() && n >= 0){
								if(money < s.items.get(n).buyValue){
									printRed("You can not afford that item");
								}else{
									printBlue("You bought the " + s.items.get(n).item.getName() + " for " + s.items.get(n).buyValue + " gold");  
									player.addItem(s.items.get(n).item);
									money -= s.items.get(n).buyValue;
								}  	
							}else if(n == -1){
								break;
							}else{
								printRed("There is no item with that ID!");
							}
						}catch(NumberFormatException nfe){
							printRed("Invalid Format!");
						}
					}
				}

				else if(n == 1){//Selling
					while(true){
						System.out.println("You have " + money + " gold");
						System.out.println("-1: Close");
						for(int i = 0; i < player.inv.size(); i++){
							System.out.println(i + ": " + player.inv.get(i).getName() + " (" + s.getSalePrice(player.inv.get(i)) + " gold)");		
						}
						try{
							n = Integer.parseInt(br.readLine());
							if(n < player.inv.size() && n >= 0){
								printBlue("You sold the " + player.inv.get(n).getName() + " for " + s.getSalePrice(player.inv.get(n)) + " gold");
								money += s.getSalePrice(player.inv.get(n));
								player.inv.get(n).count -= 1;
								if(player.inv.get(n).count < 1){
									player.removeItem(n);
								}
							}else if(n == -1){
								break;
							}else{
								printRed("There is no item with that ID!");
							}
						}catch(NumberFormatException nfe){
							printRed("Invalid Format!");
						}
					}
				}else if(n == 2){
					break;
				}else{
					printRed("That number is not a command");
				}
			}catch(NumberFormatException nfe){
				printRed("Invalid Format!");
			}
		}	
	}
	
	public static void printRed(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_RED + message + ANSI_RESET);
		}
	}
	
	public static void printBlue(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_BLUE + message + ANSI_RESET);
		}	
	}
	
	public static void printYellow(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_YELLOW + message + ANSI_RESET);
		}
	}
	
	public static void printPurple(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_PURPLE + message + ANSI_RESET);
		}	}
	
	public static void printCyan(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_CYAN + message + ANSI_RESET);
		}
	}
	
	public static void printGreen(String message){
		if(isWindows){
			System.out.println(message);
		}else{
			System.out.println(ANSI_GREEN + message + ANSI_RESET);
		}
	}
	
	public static void printHorizontalLine(){
		for(int i = 0; i < 60; i++){
			System.out.print("-");
		}
		System.out.println("");
	}	
}