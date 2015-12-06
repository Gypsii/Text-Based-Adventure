package rammus;

import java.util.ArrayList;
import java.util.Random;

import rammus.creatures.Bird;
import rammus.creatures.Creature;
import rammus.creatures.Dingo;
import rammus.creatures.Fiend;
import rammus.creatures.Mouse;
import rammus.creatures.PolarBear;
import rammus.creatures.Rat;
import rammus.creatures.Seal;
import rammus.creatures.Slime;
import rammus.creatures.Spider;
import rammus.creatures.SpiderGiant;
import rammus.creatures.Tiger;
import rammus.creatures.Turtle;
import rammus.creatures.Walrus;
import rammus.creatures.humans.Hobgoblin;
import rammus.creatures.humans.SealHunter;
import rammus.creatures.humans.SpiceTrader;
import rammus.item.Food;
import rammus.item.Item;

public class Zone {

	public ArrayList<Creature> creatures = new ArrayList<Creature>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<String> descriptors = new ArrayList<String>();

	public int aggroCreatures = 0;
	public boolean containsEgg = false;
	public boolean isFinalZone = false;
	
	public static Zone generateZone(int diff, int temp){
		Random r = new Random();
		temp += r.nextGaussian() * 2;
		Zone zone = new Zone();
		int d = (int)(1.5 * diff + Math.random() * diff);
		int loot = (int)(Math.random() * diff + 0.5);
		int value = 0;
		while(d > value){
			if(Math.random() < 0.2){
				if(d - value >= 25){
					zone.addCreature(new Fiend());
					value += 25;
				}
			}
			if(temp <= 90){
				if(Math.random() < 0.1){
					if(d - value >= 15){
						zone.addCreature(new PolarBear());
						value += 15;
					}
				}
			}
			if(temp >= 106){
				if(Math.random() < 0.12){
					if(d - value >= 12){
						zone.addCreature(new Tiger());
						value += 12;
					}
				}
			}
			if(Math.random() < 0.1){
				if(d - value >= 6){
					zone.addCreature(new SpiderGiant());
					value += 6;
					for(int i = 0; i < 10; i++){
						if(Math.random() < 1 - 0.1 * i && d - value >= 6){
							zone.addCreature(new SpiderGiant());
							value += 6;
						}else{
							break;
						}
					}	
				}
			}
			if(temp >= 105){
				if(Math.random() < 0.1){
					if(d - value >= 7){
						zone.addCreature(new Turtle());
						value += 8;
					}
				}
			}
			if(temp <= 95){
				if(Math.random() < 0.1){
					if(d - value >= 13){
						zone.addCreature(new SealHunter());
						value += 13;
					}
				}
			}
			if(Math.random() < 0.1){
				if(d - value >= 10){
					zone.addCreature(new Hobgoblin());
					value += 10;
				}	
			}
			if(temp <= 110){
				if(Math.random() < 0.1){
					if(d - value >= 5){
						zone.addCreature(new Dingo(Dingo.SNOW));
						value += 5;
					}
				}
			}
			if(Math.random() < 0.025){
				if(d - value >= 26){
					zone.addCreature(new Slime(Slime.FIERY, 4));
					value += 26;
				}else if(d - value >= 7){
					zone.addCreature(new Slime(Slime.FIERY, 2));
					value += 7;
				}else if(d - value >= 4){
					zone.addCreature(new Slime(Slime.FIERY, 1));
					value += 4;
				}			
			}
			if(temp <= 95){
				if(Math.random() < 0.035){
					if(d - value >= 23){
						zone.addCreature(new Slime(Slime.ICY, 4));
						value += 23;
					}else if(d - value >= 5){
						zone.addCreature(new Slime(Slime.ICY, 2));
						value += 5;
					}else if(d - value >= 2){
						zone.addCreature(new Slime(Slime.ICY, 1));
						value += 2;
					}
				}
			}
			if(temp >= 80){
				if(Math.random() < 0.025){
					if(d - value >= 20){
						zone.addCreature(new Slime(Slime.WATERY, 4));
						value += 20;
					}else if(d - value >= 5){
						zone.addCreature(new Slime(Slime.WATERY, 2));
						value += 5;
					}else if(d - value >= 2){
						zone.addCreature(new Slime(Slime.WATERY, 1));
						value += 2;
					}			
				}
			}
			if(Math.random() < 0.01){
				if(d - value >= 20){
					zone.addCreature(new Slime(Slime.STICKY, 4));
					value += 20;
				}else if(d - value >= 5){
					zone.addCreature(new Slime(Slime.STICKY, 2));
					value += 5;
				}else if(d - value >= 2){
					zone.addCreature(new Slime(Slime.STICKY, 1));
					value += 2;
				}	
			}
			if(Math.random() < 0.01){
				if(d - value >= 32){
					zone.addCreature(new Slime(Slime.VOLATILE, 4));
					value += 32;
				}else if(d - value >= 9){
					zone.addCreature(new Slime(Slime.VOLATILE, 2));
					value += 9;
				}else if(d - value >= 5){
					zone.addCreature(new Slime(Slime.VOLATILE, 1));
					value += 5;
				}
			}
			if(Math.random() < 0.01){
				if(d - value >= 28){
					zone.addCreature(new Slime(Slime.EARTHEN, 4));
					value += 28;
				}else if(d - value >= 9){
					zone.addCreature(new Slime(Slime.EARTHEN, 2));
					value += 9;
				}else if(d - value >= 4){
					zone.addCreature(new Slime(Slime.EARTHEN, 1));
					value += 4;
				}
			}
			if(temp >= 103 && temp <= 120){
				if(Math.random() < 0.025){
					if(d - value >= 22){
						zone.addCreature(new Slime(Slime.PLANT, 4));
						value += 22;
					}else if(d - value >= 5){
						zone.addCreature(new Slime(Slime.PLANT, 2));
						value += 5;
					}else if(d - value >= 2){
						zone.addCreature(new Slime(Slime.PLANT, 1));
						value += 2;
					}
				}
			}
			if(temp >= 115){
				if(Math.random() < 0.01){
					if(d - value >= 22){
						zone.addCreature(new Slime(Slime.SPICY, 4));
						value += 22;
					}else if(d - value >= 5){
						zone.addCreature(new Slime(Slime.SPICY, 2));
						value += 5;
					}else if(d - value >= 2){
						zone.addCreature(new Slime(Slime.SPICY, 1));
						value += 2;
					}
				}
			}
			if(Math.random() < 0.10){
				zone.addCreature(new Spider());
				value += 1;
				for(int i = 0; i < 10; i++){
					if(Math.random() < 1 - 0.1 * i && d > value){
						zone.addCreature(new Spider());
						value += 1;
					}else{
						break;
					}
				}	
			}
			if(temp <= 116){
				if(Math.random() < 0.15){
					if(d - value >= 2){
						zone.addCreature(new Rat());
						value += 2;
					}	
				}
			}
			if(temp <= 96){
				if(Math.random() < 0.15){
					if(d - value >= 2){
						zone.addCreature(new Walrus());
						value += 2;
					}	
				}
			}
			if(Math.random() < 0.15){
				value += 1;
			}
		}
		//Passive creatures
		int count = 0;
		while(count < Main.PASSIVECREATURECAP){
			if(temp <= 97 && Math.random() < 0.15 && count < Main.PASSIVECREATURECAP){//These random numbers are low so creatures at the top are not biased towards as heavily.
				zone.addCreature(new Seal());
				count ++;
			}
			if(temp <= 98 && Math.random() < 0.15 && count < Main.PASSIVECREATURECAP){
				zone.addCreature(new Bird(Bird.SNOW));
				if(Math.random() < 0.3){
					zone.addItem(Food.egg);
				}
				count ++;
			}
			if(temp >= 114 && Math.random() < 0.15 && count < Main.PASSIVECREATURECAP){
				zone.addCreature(new Bird(Bird.TROPICAL));
				if(Math.random() < 0.3){
					zone.addItem(Food.egg);
				}
				count ++;
			}
			if(temp <= 118 && temp >= 93 && Math.random() < 0.2 && count < Main.PASSIVECREATURECAP){
				zone.addCreature(new Bird(Bird.STANDARD));
				if(Math.random() < 0.3){
					zone.addItem(Food.egg);
				}
				count ++;
			}
			if(temp <= 118 && temp >= 98 && Math.random() < 0.15 && count < Main.PASSIVECREATURECAP){
				zone.addCreature(new Mouse());
				count ++;
			}
			if(Math.random() < 0.35){
				count ++;
			}
		}
		
		//Loot
		int l = 0;
		while(loot > l){
			double rand = Math.random();
			if(rand < 0.2){
				if(d - l >= 1){
					zone.addItem(Item.stick);
					l += 1;
				}
			}else if(rand < 0.35){
				if(d - l >= 1){
					zone.addItem(Item.flint);
					l += 1;
				}
			}else if(rand < 0.45){
				if(d - l >= 2){
					zone.addItem(Food.mushroom);
					l += 2;
				}
			}else if(rand < 0.6){
				if(d - l >= 5){
					zone.addItem(Food.fungus);
					l += 5;
				}
			}else{
				value ++;
			}	
		}
		return zone;
	}
	
	public static Zone generateSetPiece(){
		Zone zone = new Zone();
		double r = Math.random();
		if(r < 0.1){
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
		}else if(r < 0.2){
			zone.addCreature(new Slime(Slime.FIERY, 1));
			zone.addCreature(new Slime(Slime.WATERY, 1));
			zone.addCreature(new Slime(Slime.PLANT, 1));
			zone.addItem(Item.slimeSticky);
			zone.addItem(Item.slimeFire);
		}else if(r < 0.4){
			zone.addItem(Item.butterKnife);
			zone.addItem(Item.fork);
			Dingo bruce = new Dingo(Dingo.STANDARD);
			bruce.hp = 0;
			bruce.alive = false;
			bruce.hostile = false;
			bruce.stopsZoneExit = false;
			bruce.name = "Roasted Dead Dingo";
			bruce.addBodyPart(Food.meatRoast, 1);
			zone.addCreature(bruce);
		}else if(r < 0.5){
			zone.addCreature(new SpiceTrader());
		}else if(r < 0.7){
			if(Math.random() < 0.4){zone.addItem(Food.mushroom);}
			if(Math.random() < 0.4){zone.addItem(Food.fungus);}
			if(Math.random() < 0.4){zone.addItem(Item.slimeSticky);}
			if(Math.random() < 0.3){zone.addItem(Item.featherSnow);}
			if(Math.random() < 0.3){zone.addItem(Item.feather);}
			if(Math.random() < 0.3){zone.addItem(Item.featherWarm);}
			if(Math.random() < 0.4){zone.addItem(Item.flint);}
			if(Math.random() < 0.4){zone.addItem(Item.stick);}
			if(Math.random() < 0.4){zone.addItem(Item.string);}
			double rand = Math.random();
			if(rand < 0.33){zone.addItem(Item.ruby);}
			else if(rand < 0.67){zone.addItem(Item.emerald);}
			else{zone.addItem(Item.sapphire);}
		}else if(r < 0.9){
			Seal nigel = new Seal();
			nigel.hp = 0;
			nigel.alive = false;
			nigel.name = "Dead Seal";
			zone.addCreature(nigel);
			SpiderGiant dwayne = new SpiderGiant();
			dwayne.hp = 0;
			dwayne.alive = false;
			dwayne.stopsZoneExit = false;
			dwayne.name = "Dead Giant Spider";
			if(Math.random() < 0.6){zone.addCreature(new Bird(Bird.SNOW));}
			if(Math.random() < 0.6){zone.addCreature(new Bird(Bird.SNOW));}
			zone.addCreature(dwayne);
			zone.addItem(Item.spearheadRusted);
			Item i = Item.money.clone();
			i.count += (int)(Math.random() * 10);
			zone.addItem(i);
			zone.descriptors.add("You see the remains of a dead adventurer.");
		}else{
			zone.addCreature(new Walrus());
			zone.addCreature(new Walrus());
			zone.addCreature(new Walrus());
			zone.addCreature(new Walrus());
			if(Math.random() > 0.5){zone.addCreature(new Walrus());}
			if(Math.random() > 0.5){zone.addCreature(new Walrus());}
			if(Math.random() > 0.5){zone.addCreature(new Bird(Bird.SNOW));}
		} 
		return zone;
	}
	
	public static Zone generateFinalZone(int diff, int temp){
		Zone zone = generateZone(diff + 1, temp);
		zone.descriptors.add("This is the final zone. Press z to leave the level. ");
		zone.isFinalZone = true;
		return zone;
		
	}

	public void printDescription(){
		if(descriptors.size() > 0){
			for(int i = 0; i < descriptors.size(); i++){
				System.out.print(descriptors.get(i));
			}
			System.out.println("");
		}
	}
	public void addCreature(Creature c){
		this.creatures.add(c);
		if(c.hostile){
			this.aggroCreatures ++;
		}
		if(Main.zone == this){
			c.nextActionTime = Main.toTakeTurn.peek().nextActionTime - 0.01;//puts the new creature just at the front of the queue.
			Main.toTakeTurn.add(c);
		}
	}
	
	public void addItem(Item i){
		Item item = i.clone();
    	if(i.isStackable){
    		int loc = findItemLoc(i);
    		if(loc >= 0){
    			items.get(loc).count += i.count;
    		}else{
            	items.add(item);
    		}
    	}else{
        	items.add(item);
    	}
		if(i.name == Food.egg.name){
			containsEgg = true;
		}
	}
	
	public int findItemLoc(Item i){
		for(int x = 0; x < items.size(); x++){
    		if(items.get(x).name == i.name){
    			return x;
    		}
    	}
		return -1;
	}
}