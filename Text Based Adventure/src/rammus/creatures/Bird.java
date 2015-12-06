package rammus.creatures;

import rammus.Main;
import rammus.item.Food;
import rammus.item.Item;

public class Bird extends Creature{
	public static int SNOW = 0;
	public static int STANDARD = 1;
	public static int TROPICAL = 2;
	
	public int type;
	
	public Bird(int t){
		name = "UNDEFINED BIRD";
		type = t;
		tags.add(CreatureTag.bird);
		maxHp = 8;
		hp = maxHp;
		dmg = 1;
		xp = 3;
		hatesPlayer = false;
		targetTags.remove(CreatureTag.player);
		stopsZoneExit = false;
		courage = 0.75 + (Math.random() * 2);
		
		Item featherType = new Item();
		if(type == 0){
			name = "Snow Bird";
			featherType = Item.featherSnow;
		}else if(type == 1){
			name = "Bird";
			featherType = Item.feather;
		}else if(type == 2){
			name = "Toucan";
			featherType = Item.featherWarm;
		}
		addBodyPart(featherType, 0.6);
		addBodyPart(featherType, 0.6);
		addBodyPart(Food.meatBird, 0.4);
	}
	
	public double takeTurn(){
		if((courage <= 0 && !Main.zone.containsEgg) || courage <= -4){//Will abscond at 0 if no egg, -4 if egg
			if(stuckLevel <= 0){
				abscond();
				return 10000;
			}else{
				System.out.println("The " + name + " tried to fly away but failed");
			}
		}else{
			findTarget();
			if(target != null){
				if(target.alive && (hatesPlayer || target != Main.player)){
					return decideAttackPattern();
				}else{
					restingAction();
					return 1;
				}
			}else{
				restingAction();
				return 1;
			}
		}
		return 0;
	}
	 
	public double decideAttackPattern(){
		attack(1, "pecked");
		return 1;
	}
	
	public void restingAction(){
		if(Math.random() < 0.3){
			System.out.println("The " + name + " squawked");
		}
	}
	
	public void abscond(){
		System.out.println("The " + name + " flew away");
		Main.zone.creatures.remove(this);	
	}
	
	public void itemStealTrigger(){
		courage += 3;
		if(!hostile){
			stopsZoneExit = true;
			hatesPlayer = true;
		}
		if(!enraged){
			stopsZoneExit = true;
			enraged = true;
			System.out.println("The Bird became enraged!");
			dmg += 2;
		}
	}
	
	public void damageSubtrigger(){
		courage -= Math.random();
	}
	
	public void printDescription(){
		if(type == 0){
			System.out.println("A small white bird, protective of its young. Lives in cold climates.");
		}else if(type == 1){
			System.out.println("A small brown bird, protective of its young. Lives in temperate climates.");
		}else if(type == 2){
			System.out.println("A majestic colourful bird, protective of its young. Lives in warm climates.");
		}else{
			System.out.println("A very generic bird with no noticeably defining traits. Lives in bugged games.");
		}
	}
}
