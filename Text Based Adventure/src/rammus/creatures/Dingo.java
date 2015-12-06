package rammus.creatures;

import rammus.Main;
import rammus.item.Food;
import rammus.item.Item;

public class Dingo extends Creature{
	public int type;
	public static int STANDARD = 0;
	public static int SNOW = 1;
	
	public Dingo(int t){
		maxHp = 22;
		hp = maxHp;
		dmg = 2;
		xp = 15;
		courage = 5 + (Math.random() * 3);
		addBodyPart(Item.hideDingo, 0.5);
		if(Math.random() < 0.05){
			this.inv.add(Item.baby);
		}
		addBodyPart(Food.meatDingo, 0.7);
		if(type == STANDARD){
			name = "Dingo";
		}else if(type == SNOW){
			name = "Snow Dingo";
		}
		targetTags.add(CreatureTag.rodent);
	}
	
	public double decideAttackPattern(){
		if(enraged){
			attack(1, "bit");
			return 1;
		}else{
			attack(1, "nipped");
			return 1;
		}
	}
	public void aggravateTrigger(Creature c){
		if(c == Main.player && alive){
			stopsZoneExit = true;
		}
		target = c;
		if(!enraged && alive){
			name = "Enraged " + name;
			enraged = true;
			dmg = 5;
			System.out.println("The Dingo became enraged!");
		}
	}

	public void deathTrigger(){
		for(int i = 0; i < Main.zone.creatures.size(); i++){
			if(Main.zone.creatures.get(i).getClass() == this.getClass()){
				Main.zone.creatures.get(i).courage -= 1.5;
			}
		}
	}
	
	public void printDescription(){
		if(type == STANDARD){
			System.out.println("Filthy dog creature.");
		}else if(type == SNOW){
			System.out.println("Dingo: Snow edition.");
		}
	}
}
