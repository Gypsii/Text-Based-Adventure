package rammus.creatures;

import java.util.ArrayList;
import java.util.HashMap;

import rammus.Effect;
import rammus.Main;
import rammus.OnHit;
import rammus.item.Armour;
import rammus.item.Item;
import rammus.item.Potion;

public class Creature {
	public int maxHp;
	public int hp;
	public int dmg;
	public int xp = 1;
	public int bluntArmour, pierceArmour, slashArmour, burnArmour, coldArmour, magicArmour = 0;
	public int baseDmg = dmg;
	public double nextActionTime = 0;
	
	public String name;
	public boolean nameIsGeneral = true;
	public boolean alive = true;
	public boolean hostile = true;
	public boolean enraged = false;
	public boolean hatesPlayer = true;
	public boolean stopsZoneExit = hostile;
	public boolean damp = false;
	public int paralysedTurns = 0;
	public double stuckLevel = 0;
	public double courage = 100000;
	public Creature target = null;//Main.player;
	public ArrayList<CreatureTag> tags = new ArrayList<CreatureTag>();
	public ArrayList<CreatureTag> targetTags = new ArrayList<CreatureTag>();
	
	public boolean triggerDamp = false;
	public boolean triggerBurn = false;
	public boolean triggerStick = false;
	public boolean triggerFreeze = false;

	public ArrayList<Item> inv = new ArrayList<Item>();
	public ArrayList<Item> butcherInv = new ArrayList<Item>();
	public HashMap<String, Double> butcherSuccess = new HashMap<String, Double>();
	public Item equipped = new Item("unarmed", 0, 0, 1);
	public Armour armourChest = new Armour("unarmoured", 0, 0, 0, 0, 0, 0);
	
	public ArrayList<Item> shopInv = new ArrayList<Item>();
	public HashMap<String, Integer> prices = new HashMap<String, Integer>();

	
	public Creature(){
		targetTags.add(CreatureTag.player);
	}
	
	/*
	 * Actions
	 */
	
	public double takeTurn(){
		passiveAction();
		if(paralysedTurns == 0){
			if(courage <= 0){
				if(stuckLevel <= 0){
					abscond();
					return 10000;
				}else{
					System.out.println("The " + name + " tried to abscond but failed");
					return 1;
				}
			}else{
				//if(hostile){
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
					
				/*}else{
					restingAction();
				}*/
			}
		}else{
			System.out.println("The " + name + " is paralysed");
			paralysedTurns --;
			if(paralysedTurns == 0){
				stopsZoneExit = true;
			}else if(target == Main.player && alive){
				stopsZoneExit = false;
			}
		}
		Main.printCyan("This is likely a bug, check Creature.takeTurn");
		return 0;
	}
	
	public void findTarget(){
		if(target != Main.player){
			if(hatesPlayer){
				target = Main.player;
			}else{
				boolean targetFound = false;
				for(int i = 0; i < Main.zone.creatures.size(); i++){
					Creature c = Main.zone.creatures.get(i);
					if(c != this && c.alive){
						for(int j = 0; j < targetTags.size(); j++){
							for(int k = 0; k < c.tags.size(); k++){
								if(c.tags.get(k) == targetTags.get(j)){
									target = c;
									targetFound = true;
									break;
								}
							}
							if(targetFound){break;}
						}
						if(targetFound){break;}
					}
				}
				if(!targetFound){
					target = null;
				}
			}
		}
	}
	
	public double decideAttackPattern(){
		if(equipped.dmgType == 2){
			if(equipped.getName().equals("unarmed")){
				attack(2, "slashed");
			}else{
				attack(2, "slashed", "its " + equipped.name);
			}
		}else if(equipped.dmgType == 1){
			if(equipped.getName().equals("unarmed")){
				attack(1, "stabbed");
			}else{
				attack(1, "stabbed", "its " + equipped.name);
			}
		}else{
			if(equipped.getName().equals("unarmed")){
				attack(0, "hit");
			}else{
				attack(0, "hit", "its " + equipped.name);
			}
		}
		return 1;
	}
	
	public void restingAction(){
		
	}
	
	public void passiveAction(){
		
	}
	
	public void abscond(){
		System.out.println("The " + name + " ran away");
		Main.zone.creatures.remove(this);
	}
	
	public void attemptUnstick(){
		if(Math.random() > stuckLevel){
			System.out.println("The " + name + " broke free");
			stuckLevel = 0;
		}else{
			System.out.println("The " + name + " failed to break free");
		}
	}
	
	public void attack(int type, String verb){
		int d = dmg;
		if(type == 2){
			d -= target.armourChest.slashResist;
		}else if(type == 1){
			d -= target.armourChest.pierceResist;
		}else if(type == 0){
			d -= target.armourChest.bluntResist;
		}
		d = Math.max(0, d);
		if(target == Main.player){
			System.out.println("The " + name + " " + verb + " you for " + d + " damage, leaving you on " + Math.max(0, target.hp - d) + " hp");
		}else{
			System.out.println("The " + name + " " + verb + " the " + target.name + " for " + d + " damage, leaving it on " + Math.max(0, target.hp - d) + " hp");
		}
		if(type == 2){
			target.takeSlashDamage(dmg);
			target.aggravateTrigger(this);
		}else if(type == 1){
			target.takePierceDamage(dmg);
			target.aggravateTrigger(this);
		}else if(type == 0){
			target.takeBluntDamage(dmg);
			target.aggravateTrigger(this);
		}
		applyOnHits(equipped.effects);
	}
	
	public void attack(int type, String verb, String weapon){
		int d = dmg;
		if(type == 2){
			d -= target.armourChest.slashResist;
		}else if(type == 1){
			d -= target.armourChest.pierceResist;
		}else if(type == 0){
			d -= target.armourChest.bluntResist;
		}
		d = Math.max(0, d);
		if(target == Main.player){
			System.out.println("The " + name + " " + verb + " you with " + weapon + " for "  + d + " damage, leaving you on " + Math.max(0, target.hp - d) + " hp");
		}else{
			System.out.println("The " + name + " " + verb + " the " + target.name + " with " + weapon + " for " + d + " damage, leaving it on " + Math.max(0, target.hp - d) + " hp");
		}
		if(type == 2){
			target.takeSlashDamage(dmg);
			target.aggravateTrigger(this);
		}else if(type == 1){
			target.takePierceDamage(dmg);
			target.aggravateTrigger(this);
		}else if(type == 0){
			target.takeBluntDamage(dmg);
			target.aggravateTrigger(this);
		}
		applyOnHits(equipped.effects);
	}
	
	public void potionAttack(Potion p){
		target.applyPotionEffects(p);
	}
	
	public void equip(int id){
    	if(inv.get(id).isArmour){
    		bluntArmour -= armourChest.bluntResist;
    		pierceArmour -= armourChest.pierceResist;
    		slashArmour -= armourChest.slashResist;
    		burnArmour -= armourChest.burnResist;
    		coldArmour -= armourChest.coldResist;
    		armourChest = (Armour)inv.get(id);
    		bluntArmour += armourChest.bluntResist;
    		pierceArmour += armourChest.pierceResist;
    		slashArmour += armourChest.slashResist;
    		burnArmour -= armourChest.burnResist;
    		coldArmour -= armourChest.coldResist;
    	}else{
    		equipped = inv.get(id);
        	dmg = Math.max(baseDmg, equipped.dmg);
    	}
    }
	
	public void equip(Item i){
    	if(i.isArmour){
    		bluntArmour -= armourChest.bluntResist;
    		pierceArmour -= armourChest.pierceResist;
    		slashArmour -= armourChest.slashResist;
    		burnArmour -= armourChest.burnResist;
    		coldArmour -= armourChest.coldResist;
    		magicArmour -= armourChest.magicResist;
    		armourChest = (Armour) i;
    		bluntArmour += armourChest.bluntResist;
    		pierceArmour += armourChest.pierceResist;
    		slashArmour += armourChest.slashResist;
    		burnArmour += armourChest.burnResist;
    		coldArmour += armourChest.coldResist;
    		magicArmour += armourChest.magicResist;
    	}else{
    		equipped = i;
        	dmg = Math.max(baseDmg, equipped.dmg);
    	}
    }
	
	/*
	 * Things acting on creature
	 */
	
	public void takeSlashDamage(int d) {
		hp -= Math.max(d - slashArmour, 0);
		//System.out.println("You slash the " + name + " for " + (d - slashArmour) + " damage, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - slashArmour);
		}
	}
	
	public void takeBluntDamage(int d) {
		hp -= Math.max(d - bluntArmour, 0);
		//System.out.println("You hit the " + name + " for " + (d - bluntArmour) + " damage, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - bluntArmour);
		}
	}
	
	public void takePierceDamage(int d) {
		hp -= Math.max(d - pierceArmour, 0);
		//System.out.println("You pierce the " + name + " for " + (d - pierceArmour) + " damage, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - pierceArmour);
		}
	}
	
	public void takeExplosionDamage(int d) {
		hp -= Math.max(d, 0);
		System.out.println("The " + name + " took " + d + " damage from the explosion, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d);
			courage -= 1 + (d / 3);
		}
	}
	
	public void takeBurnDamage(int d) {
		hp -= Math.max(d - burnArmour, 0);
		System.out.println("The " + name + " took " + (d - burnArmour) + " damage from the flames, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - burnArmour);
			courage -= d;
		}
	}
	
	public void takeColdDamage(int d) {
		hp -= Math.max(d - coldArmour, 0);
		System.out.println("The " + name + " took " + (d - coldArmour) + " damage from the cold, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - coldArmour);
		}
	}
	
	public void takeMagicDamage(int d) {
		hp -= Math.max(d, 0);
		System.out.println("The " + name + " took " + d + " damage, leaving it on " + Math.max(0, hp) + " hp");
		if(hp <= 0){
			kill();
		}else{
			damageTrigger(d - coldArmour);
		}
	}
	
	public void dampen(){
		if(!damp){
			System.out.println("The " + name + " became damp");
			name = "damp " + name;
			damp = true;
		}
	}
	
	public void paralyse(int duration){
		paralysedTurns = Math.max(paralysedTurns, duration);
		if(paralysedTurns > 0){
			stopsZoneExit = false;
		}
	}
	
	public void stick(double stickiness){
		if(stuckLevel <= 0){
			System.out.println("The " + name + " became stuck");
		}
		stuckLevel = Math.max(stuckLevel, 0.3);
	}
	
	public void applyPotionEffects(Potion p) {
		double netHpChange = 0;
		double burnDamage = 0;
		double paralasisLevel = 0;
		int paralasisLength = 0;
		double potencyProportion = 1;
		for(int i = 0; i < Potion.EFFECTCOUNT; i++){			
			if(p.effects.get(i).type == Effect.DAMAGE){
				netHpChange -= p.effects.get(i).potency * potencyProportion;
			}else if(p.effects.get(i).type == Effect.HEAL){
				netHpChange += p.effects.get(i).potency * potencyProportion;
			}else if(p.effects.get(i).type == Effect.IGNITE){
				burnDamage += p.effects.get(i).potency * potencyProportion;
			}else if(p.effects.get(i).type == Effect.PARALYSE){
				paralasisLevel += p.effects.get(i).potency * potencyProportion;
			}
			if(i > 0){
				potencyProportion -= 0.25;
			}
		}
		if(netHpChange < 0){
			takeMagicDamage((int)netHpChange);
			if(hp <= 0){
				kill();
			}
		}else{
			hp += netHpChange;
			if(hp > maxHp){
				hp = maxHp;
			}
		}
		if(burnDamage >= 1){
			takeBurnDamage((int)burnDamage);
		}
		if(paralasisLevel >= 1){
			paralasisLength += (int)paralasisLevel;
		}
		if(paralasisLevel % 1 > Math.random()){
			paralasisLength ++;
		}
		paralyse(paralasisLength);
	}
	
	public void kill(){
		if(alive){
			stopsZoneExit = false;
			System.out.println("The " + name + " was killed");
			name = "dead " + name;
			alive = false;
			dropItems();
			deathTrigger();
			if(this.xp > 0){
				Main.player.giveXp(xp);
			}
		}
	}
	
	public void dropItems(){
		for(int i = 0; i < inv.size(); i++){
			Main.zone.addItem(inv.get(i));
			Main.printYellow("The " + name + " dropped " + inv.get(i).prefix + " " + inv.get(i).getName());
		}
		inv.clear();
		for(int i = 0; i < shopInv.size(); i++){
			Main.zone.addItem(shopInv.get(i));
			Main.printYellow("The " + name + " dropped " + shopInv.get(i).prefix + " " + shopInv.get(i).getName());
		}
		shopInv.clear();
	}
	
	public void dropButcherItems(){
		for(int i = 0; i < butcherInv.size(); i++){
			for(int j = 0; j < butcherInv.get(i).count; j++){
				if(Math.random() < butcherSuccess.get(butcherInv.get(i).name)){
					Main.zone.addItem(butcherInv.get(i));
	    			Main.printYellow("The " + name + " dropped " + butcherInv.get(i).prefix + " " + butcherInv.get(i).getName());
				}
			}

		}
		butcherInv.clear();
	}
	
	public void addItem(Item i){
		Item item = i.clone();
    	if(i.isStackable){
    		int loc = findItemLoc(i);
    		if(loc >= 0){
    			inv.get(loc).count += i.count;
    		}else{
            	inv.add(item);
    		}
    	}else{
        	inv.add(item);
    	}
	}
	
	public void addItem(Item i, int count){
		Item item = i.clone();
		item.count = count;
		inv.add(item);
	}
	
	public void removeItem(int i){
		if(equipped == inv.get(i)){
			equip(new Item("unarmed", 3, 0, 1));
		}else if(armourChest == inv.get(i)){
			equip(new Armour("unarmoured", 0, 0, 0, 0, 0, 0));
		}
		inv.remove(i);
	}
	
	public void removeItem(Item i){
		if(equipped == i){
			equip(new Item("unarmed", 3, 0, 1));
		}else if(armourChest == i){
			equip(new Armour("unarmoured", 0, 0, 0, 0, 0, 0));
		}
		inv.remove(i);
	}
	
	public int findItemLoc(Item i){
		for(int x = 0; x < inv.size(); x++){
    		if(inv.get(x).name == i.name){
    			return x;
    		}
    	}
		return -1;
	}
	
	public int findShopItemLoc(Item i){
		for(int x = 0; x < shopInv.size(); x++){
    		if(shopInv.get(x).name == i.name){
    			return x;
    		}
    	}
		return -1;
	}
	
	public void addShopItem(Item i, int p){
		Item item = i.clone();
    	if(i.isStackable){
    		int loc = findShopItemLoc(i);
    		if(loc >= 0){
    			shopInv.get(loc).count += i.count;
    		}else{
            	shopInv.add(item);
    		}
    	}else{
        	shopInv.add(item);
    	}
    	prices.put(i.name, p);
	}
	
	public void addBodyPart(Item i, double successRate){
		Item item = i.clone();
    	if(i.isStackable){
    		int loc = findShopItemLoc(i);
    		if(loc >= 0){
    			butcherInv.get(loc).count += i.count;
    		}else{
    	    	butcherInv.add(item);
    		}
    	}else{
        	butcherInv.add(item);
    	}
    	butcherSuccess.put(i.name, successRate);
	}
	
	public void printInfo(){
		Main.printHorizontalLine();
		System.out.println(name + ":");
		System.out.println("Health: " + Math.max(0, hp) + "/" + maxHp);
		if(bluntArmour > 0){
			System.out.println("Bludgeoning resistance: " + bluntArmour);
		}else if(bluntArmour < 0){
			System.out.println("Bludgeoning vulnerability: " + (0 - bluntArmour));
		}
		if(pierceArmour > 0){
			System.out.println("Piercing resistance: " + pierceArmour);
		}else if(pierceArmour < 0){
			System.out.println("Piercing vulnerability: " + (0 - pierceArmour));
		}
		if(slashArmour > 0){
			System.out.println("Slashing resistance: " + slashArmour);
		}else if(slashArmour < 0){
			System.out.println("Slashing vulnerability: " + (0 - slashArmour));
		}
		if(burnArmour > 0){
			System.out.println("Fire resistance: " + burnArmour);
		}else if(burnArmour < 0){
			System.out.println("Fire vulnerability: " + (0 - burnArmour));
		}
		if(coldArmour > 0){
			System.out.println("Cold resistance: " + coldArmour);
		}else if(coldArmour < 0){
			System.out.println("Cold vulnerability: " + (0 - coldArmour));
		}
		if(magicArmour > 0){
			System.out.println("Magic resistance: " + magicArmour);
		}else if(magicArmour < 0){
			System.out.println("Magic vulnerability: " + (0 - magicArmour));
		}
		System.out.println("");
		if(!equipped.name.equals("unarmed")){
			System.out.println("Weapon: " + equipped.getName());
		}
		System.out.print("Damage: " + dmg);
		if(equipped.dmgType == 2){
			System.out.println(" slashing");
		}else if(equipped.dmgType == 1){
			System.out.println(" piercing");
		}else{
			System.out.println(" bludgeoning");
		}
		System.out.println("");
		printDescription();
		Main.printHorizontalLine();
	}
	
	public void printDescription(){
		
	}
	
	/*
	 * Triggers
	 */
	
	public void damageTrigger(int d){
		d = Math.max(d, 0);
		if(d > maxHp / 4){//Lose 0.5 courage per damage over 25% hp
			courage -= (d - (maxHp / 4)) / 2;
		}
		damageSubtrigger();//Creature specific		
	}
	
	public void damageSubtrigger(){
		
	}
	
	public void aggravateTrigger(Creature c){
		if(c == Main.player && alive){
			hatesPlayer = true;
			stopsZoneExit = true;
		}
		target = c;
		//targetTags.add(c);
	}
	
	public void deathTrigger(){
		
	}
	
	public void itemStealTrigger(){
		
	}
	
	public void applyOnHits(ArrayList<OnHit> onHits){
		for(int i = 0; i < onHits.size(); i++){
			if(onHits.get(i).type == OnHit.BURN){
				target.takeBurnDamage(onHits.get(i).potency);
				if(target == Main.player){
					System.out.println("You were burnt for " + Math.max(0, onHits.get(i).potency - target.burnArmour) + " damage, leaving you on " + Math.max(0, target.hp) + " hp");
				}
			}else if(onHits.get(i).type == OnHit.COLD){
				target.takeColdDamage(onHits.get(i).potency);
				if(target == Main.player){
					System.out.println("You took " + Math.max(0, onHits.get(i).potency - target.burnArmour) + " damage from the cold, leaving you on " + Math.max(0, target.hp) + " hp");
				}
			}else if(onHits.get(i).type == OnHit.MAGIC){
				target.takeBurnDamage(onHits.get(i).potency);
				if(target == Main.player){
					System.out.println("You took " + Math.max(0, onHits.get(i).potency - target.burnArmour) + " magic damage, leaving you on " + Math.max(0, target.hp) + " hp");
				}
			}else if(onHits.get(i).type == OnHit.LIGHTNING){
				target.takeBurnDamage(onHits.get(i).potency);
				if(target == Main.player){
					System.out.println("You took " + Math.max(0, onHits.get(i).potency - target.burnArmour) + " magic damage from the lightning, leaving you on " + Math.max(0, target.hp) + " hp");
				}
			}else if(onHits.get(i).type == OnHit.SELFHEAL){
				if(true){//TODO check for if it was dead before you hit it
					hp += onHits.get(i).potency;
					hp = Math.min(hp, maxHp);
					if(this == Main.player){
						System.out.println("You were healed for " + onHits.get(i).potency + " damage, healing you to " + hp + " hp");
					}else{
						System.out.println("The " + name + " healed for " + onHits.get(i).potency + " damage, healing it to " + Math.min(maxHp, hp) + " hp");
					}
				}
			}else if(onHits.get(i).type == OnHit.SELFDMG){
				takeMagicDamage(onHits.get(i).potency);
				if(this == Main.player){
					System.out.println("The weapon drained your health by " + onHits.get(i).potency + ", leaving you on " + Math.max(hp, 0) + " hp");
				}else{
					System.out.println("The " + name + " took " + onHits.get(i).potency + " damage from its weapon, leaving it on " + Math.min(maxHp, hp) + " hp");
				}
			}else if(onHits.get(i).type == OnHit.FEARSPIDERS){
				Creature c;
				boolean foundSpider = false;
				for(int j = 0; j < Main.zone.creatures.size(); j++){
					c = Main.zone.creatures.get(j);
					if(c.tags.contains(CreatureTag.spider)){
						c.courage -= onHits.get(i).potency * 2;
						foundSpider = true;
					}
				}
				if(foundSpider){
					System.out.println("All spiders lose courage!");
				}
			}
		}
	}
}