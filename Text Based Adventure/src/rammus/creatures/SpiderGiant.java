package rammus.creatures;

import rammus.Main;
import rammus.item.Food;
import rammus.item.Item;


public class SpiderGiant extends Creature{
	
	public SpiderGiant(){
		name = "Giant Spider";
		tags.add(CreatureTag.spider);
		maxHp = 36;
		hp = maxHp;
		dmg = 5;
		xp = 20;
		courage = 8 + (Math.random() * 4);
		if(Math.random() < 0.6){
			this.inv.add(Item.silk);
		}
		if(Math.random() < 0.3){
			this.inv.add(Item.stickyString);
		}
		addBodyPart(Food.meatSpider, 0.5);
		addBodyPart(Food.meatSpider, 0.5);
	}
	 
	public double decideAttackPattern(){
		attack(1, "bit");
		return 1;
	}
	
	public void deathTrigger(){
		for(int i = 0; i < Main.zone.creatures.size(); i++){
			if(Main.zone.creatures.get(i).getClass() == this.getClass()){
				Main.zone.creatures.get(i).courage -= 2;
			}
		}
	}
	
	public void printDescription(){
		System.out.println("A much larger spider, comparable to a moderately sized dog with too many legs.");
	}
}
