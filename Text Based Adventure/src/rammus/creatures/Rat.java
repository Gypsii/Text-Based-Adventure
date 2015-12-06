package rammus.creatures;

public class Rat extends Creature{
	
	public Rat(){
		name = "Rat";
		maxHp = 15;
		hp = maxHp;
		dmg = 2;
		xp = 6;
		courage = 2 + (Math.random() * 2);
		tags.add(CreatureTag.rodent);
	}
	
	public double decideAttackPattern(){
		attack(1, "bit");
		return 0.7;
	}
	
	public void printDescription(){
		System.out.println("A large rodent.");
	}
}
