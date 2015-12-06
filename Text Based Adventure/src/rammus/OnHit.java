package rammus;

public class OnHit {

	public static int MAGIC = 0;
	public static int BURN = 1;
	public static int COLD = 2;
	public static int SELFHEAL = 3;
	public static int SELFDMG = 4;
	public static int DEATHEXPLODE = 5;
	public static int FEARSPIDERS = 6;
	public static int LIGHTNING = 7;

	
	public int type;
	public int potency;

	public OnHit(int t, int p){
		type = t;
		potency = p;
	}
	
}
