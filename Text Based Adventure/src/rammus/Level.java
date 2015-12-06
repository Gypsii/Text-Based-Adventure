package rammus;

import java.util.HashMap;
import java.util.Random;

public class Level {
	public HashMap<Integer, Zone> zones = new  HashMap<Integer, Zone>();
	public static int LEVELSIZE = 3;
	public Shop shop;
	public int temp = 100;
	
	
	public Level(int diff){
		Random r = new Random();
		temp += (int)(r.nextGaussian() * 10);
		System.out.println("Temp = " + temp);//TODO this is a debug
		int setX = (int)(Math.random() * (LEVELSIZE - 1)) + 1;
		int setY = (int)(Math.random() * (LEVELSIZE - 1)) + 1;
		for(int w = 0; w <= LEVELSIZE; w++){
			for(int h = 0; h <= LEVELSIZE; h++){
				if((w == LEVELSIZE && h == 0) || (h == LEVELSIZE && w == 0) || (h == setY && w == setX)){
					zones.put(w + (100 * h), Zone.generateSetPiece());
				}else if(w == LEVELSIZE && h == LEVELSIZE){
					zones.put(w + (100 * h), Zone.generateFinalZone(diff, temp));
				}else{
					zones.put(w + (100 * h), Zone.generateZone(diff, temp));
				}
			}
		}
		shop = generateShop();
	}
	
	public static void generateLevel(int d){
		Level level = new Level(d);
		Main.levels.put(Main.levelNum, level);
	}
	
	public static Shop generateShop(){
		Shop s = new Shop();
		s.addItem(s.string, 1, 1);
		s.addItem(s.stickyString, 1, 1);
		if(Math.random() < 0.8){s.addItem(s.bottle, 1, 1);}
		if(Math.random() < 0.8){s.addItem(s.bread, 1, 1);}
		if(Math.random() < 0.8){s.addItem(s.mushroom, 1, 1);}
		if(Math.random() < 0.1){s.addItem(s.fungus, 1, 1);}
		if(Math.random() < 0.4){s.addItem(s.tusk, 1.5, 1);}
		if(Math.random() < 0.4){s.addItem(s.sswordCopper, 1, 1);}
		if(Math.random() < 0.3){s.addItem(s.sswordBronze, 1, 1);}
		if(Math.random() < 0.2){s.addItem(s.sswordIron, 1, 1);}
		if(Math.random() < 0.35){s.addItem(s.maceCopper, 1, 1);}
		if(Math.random() < 0.25){s.addItem(s.maceBronze, 1, 1);}
		if(Math.random() < 0.25){s.addItem(s.lswordCopper, 1, 1);}
		if(Math.random() < 0.15){s.addItem(s.lswordBronze, 1, 1);}
		if(Math.random() < 0.1){s.addItem(s.lswordIron, 1, 1);}
		if(Math.random() < 0.15){s.addItem(s.maceIron, 1, 1);}
		if(Math.random() < 0.3){s.addItem(s.vestDingo, 1, 1);}
		if(Math.random() < 0.2){s.addItem(s.vestBear, 1, 1);}
		if(Math.random() < 0.2){s.addItem(s.gbladeRusted, 1, 1);}
		if(Math.random() < 0.3){s.addItem(s.gbladeCopper, 1, 1);}
		if(Math.random() < 0.2){s.addItem(s.gbladeBronze, 1, 1);}
		if(Math.random() < 0.1){s.addItem(s.gbladeIron, 1, 1);}
		if(Math.random() < 0.1){s.addItem(s.spearheadRusted, 1, 1);}
		if(Math.random() < 0.3){s.addItem(s.spearheadCopper, 1, 1);}
		if(Math.random() < 0.2){s.addItem(s.spearheadBronze, 1, 1);}
	
		//double r = Math.random();
		return s;
	}
	
	public void printLevelDescription(){
		if(temp < 85){
			System.out.println("It is very cold and barren.");
		}else if(temp < 95){
			System.out.println("It is cold.");
		}else if(temp < 105){
			System.out.println("It is temperate.");
		}else if(temp < 115){
			System.out.println("It is warm.");
		}else{
			System.out.println("It is hot.");
		}
	}
}
