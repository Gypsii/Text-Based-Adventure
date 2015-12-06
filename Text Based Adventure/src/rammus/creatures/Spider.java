package rammus.creatures;

import rammus.Main;
import rammus.item.Food;
import rammus.item.Item;


public class Spider extends Creature{
	
	public Spider(){
		name = "Spider";
		tags.add(CreatureTag.spider);
		maxHp = 10;
		hp = maxHp;
		dmg = 2;
		xp = 6;
		courage = 3 + (Math.random() * 4);
		if(Math.random() < 0.45){
			this.inv.add(Item.silk);
		}
		if(Math.random() < 0.15){
			this.inv.add(Item.stickyString);
		}
		addBodyPart(Food.meatSpider, 0.3);
	}
	 
	public double decideAttackPattern(){
		attack(1, "bit");
		return 1;
	}
	
	public void deathTrigger(){
		for(int i = 0; i < Main.zone.creatures.size(); i++){
			if(Main.zone.creatures.get(i).getClass() == this.getClass()){
				Main.zone.creatures.get(i).courage -= 1.5;
			}
		}
	}
	
	public void printDescription(){
		System.out.println("A small, cowardly pack creature that drops silk and sticky string. Has an excess of legs.");
	}
}