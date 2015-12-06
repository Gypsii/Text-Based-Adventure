package rammus.creatures;


public class Mouse extends Creature{

	public Mouse(){
		name = "Field Mouse";
		tags.add(CreatureTag.bird);
		maxHp = 5;
		hp = maxHp;
		dmg = 1;
		xp = 2;
		hatesPlayer = false;
		targetTags.remove(CreatureTag.player);
		stopsZoneExit = false;
		courage = (Math.random() * 2);
		tags.add(CreatureTag.rodent);
	}
	 
	public double decideAttackPattern(){
		attack(2, "scratched");
		return 0.6;
	}
	
	public void restingAction(){
		if(Math.random() < 0.1){
			System.out.println("The " + name + " squeaked");
		}
	}
	
	public void damageSubtrigger(){
		courage -= Math.random();
	}
	
	public void printDescription(){
		System.out.println("A tiny brown mouse");
	}
}
