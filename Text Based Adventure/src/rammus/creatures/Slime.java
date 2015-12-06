package rammus.creatures;

import java.util.Random;

import rammus.Main;
import rammus.OnHit;
import rammus.item.Food;
import rammus.item.Item;

public class Slime extends Creature{
	int type;
	int size;
	public static int VOLATILE = 0;
	public static int FIERY = 1;
	public static int ICY = 2;
	public static int WATERY = 3;
	public static int STICKY = 4;
	public static int EARTHEN = 5;
	public static int PLANT = 6;
	public static int SPICY = 7;

	public Slime(int t, int s){
		tags.add(CreatureTag.slime);
		size = s;
		dmg = 2;
		maxHp = 12;
		xp = 7;
		pierceArmour = 6;
		slashArmour = 2;
		bluntArmour = -1;
		name = "ERR UNTYPED SLIME";
		type = t;
		Random random = new Random();
		int dropCount = Math.max((int)(random.nextGaussian() + 2 * size), size);//Normal dist w/ μ = 1 + size, σ = 1, no lower than size
		if(type == VOLATILE){
			addItem(Item.slimeExplosive, dropCount);
			name = "Volatile Slime";
			bluntArmour = -3;
			xp = 12;
		}else if(type == FIERY){
			addItem(Item.slimeFire, dropCount);
			name = "Fiery Slime";
			burnArmour = 4;
			xp = 10;
			equipped.effects.add(new OnHit(OnHit.BURN, size * 2));
		}else if(type == ICY){
			addItem(Item.slimeIce, dropCount);
			if(Math.random() < 0.5){
				this.inv.add(Item.icicle);
			}
			name = "Icy Slime";
			bluntArmour = 1;
			slashArmour = 1;
			pierceArmour = 4;
			coldArmour = 5;
			burnArmour = -2;
			equipped.effects.add(new OnHit(OnHit.COLD, size));
		}else if(type == WATERY){
			addItem(Item.slimeWater, dropCount);
			name = "Watery Slime";
			slashArmour = 3;
			burnArmour = 2;
			pierceArmour = 10;
		}else if(type == STICKY){
			addItem(Item.slimeSticky, dropCount);
			name = "Sticky Slime";
			slashArmour = 3;
			bluntArmour = 1;
		}else if(type == EARTHEN){
			addItem(Item.slimeEarth, dropCount);
			name = "Earthen Slime";
			bluntArmour = 6;
			slashArmour = 7;
			pierceArmour = 5;
			coldArmour = 5;
			burnArmour = 5;
		}else if(type == PLANT){
			addItem(Item.slimePlant, dropCount);
			name = "Leafy Slime";
			burnArmour = -1;
			bluntArmour = 0;
			addBodyPart(Food.leaf, 0.3);
			for(int i = 0; i < size * 2; i++){
				addBodyPart(Food.leaf, 0.7);
			}
		}else if(type == SPICY){
			addItem(Item.slimeSpice, dropCount);
			name = "Spicy Slime";
			addBodyPart(Food.nutmeg, 0.2);
			addBodyPart(Food.pepper, 0.3);
			addBodyPart(Food.ginger, 0.2);
			addBodyPart(Food.cinnamon, 0.25);
		}
		if(size == 2){
			dmg = 4;
			maxHp = 42;
			name = "Large " + name;
			xp *= 2.5;
		}else if(size == 4){
			dmg = 8;
			maxHp = 112;
			name = "Huge " + name;
			xp *= 6;
		}else if(size == 8){
			dmg = 16;
			maxHp = 220;
			name = "Colossal " + name;
			xp *= 20;
		}else if(size == 16){
			dmg = 32;
			maxHp = 512;
			name = "Titanic " + name;
			xp *= 50;
		}
		
		hp = maxHp;		
	}
	
	public double decideAttackPattern(){
		if(type == ICY){
			if(Math.random() < 0.5){
				attack(0, "hit");
			}else{
				iceAttack();
			}
		}else{
			attack(0, "hit");
		}
		return 1.2;
	}
	
	public void passiveAction(){
		if(type == PLANT){
			if(hp != maxHp){
				System.out.print("The " + name + " regenerated " + Math.min(size, maxHp - hp) + " hp, leaving it on ");
				hp += size;
				hp = Math.min(hp, maxHp);
				System.out.println(hp + " hp");
			}
		}	
	}
	
	
	public void deathTrigger(){
		if(type == 0){
			explode();
		}
	}
	
	public void explode(){
		int explodeDmg = size * 4;
		System.out.println("The Volatile Slime exploded dealing " + explodeDmg + " damage, leaving you on " + Math.max(0, Main.player.hp - explodeDmg) + " hp");
		Main.player.takeExplosionDamage(explodeDmg);
		for(int i = 0; i < Main.zone.creatures.size(); i++){
			if(Main.zone.creatures.get(i) != this && Main.zone.creatures.get(i).alive){
				Main.zone.creatures.get(i).takeExplosionDamage(explodeDmg);
			}
		}
	}
	
	public void dampen(){
		if(!damp && type == FIERY){
			System.out.println("The " + name + " became damp");
			name = "damp Red Slime";
			damp = true;
		}		
	}
	
	public void takeExplosionDamage(int d) {
		hp -= d;
		System.out.println("The " + name + " took " + d + " damage from the explosion, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
			if(type == FIERY){
				int burnDmg = size * 2;
				System.out.println("The Fiery Slime splattered in a shower of flames dealing " + (burnDmg - Main.player.burnArmour) + " damage, leaving you on " + Math.max(0, Main.player.hp - (burnDmg - Main.player.burnArmour)) + " hp");
				Main.player.takeBurnDamage(burnDmg);
				for(int i = 0; i < Main.zone.creatures.size(); i++){
					if(Main.zone.creatures.get(i) != this && Main.zone.creatures.get(i).alive){
						Main.zone.creatures.get(i).takeBurnDamage(burnDmg);
					}
				}
			}else if(type == WATERY){
				System.out.println("The Watery Slime exploded splashing everything around it");
				for(int i = 0; i < Main.zone.creatures.size(); i++){
					if(Main.zone.creatures.get(i) != this && Main.zone.creatures.get(i).alive){
						Main.zone.creatures.get(i).dampen();;
					}
				}
			}
		}else{
			damageTrigger(d);
		}
	}
	
	public void iceAttack(){
		dmg ++;
		attack(1, "pierced", "an ice shard");
		dmg --;
	}
	
	public void printDescription(){
		System.out.print("A blob of liquid and enzymes.");
		if(type == Slime.FIERY){
			System.out.print(" It is eternally on fire.");
		}else if(type == Slime.ICY){
			System.out.print(" It looks mostly frozen and is covered in sharp ice crystals.");
		}else if(type == Slime.VOLATILE){
			System.out.print(" It is highly unstable.");
		}else if(type == Slime.STICKY){
			System.out.print(" Extremely adhesive.");
		}else if(type == Slime.EARTHEN){
			System.out.print(" It looks like it is partially made of dirt and rocks.");
		}else if(type == Slime.PLANT){
			System.out.print(" It looks like it is largely made up of plants.");
		}
		System.out.println("");
	}
}
