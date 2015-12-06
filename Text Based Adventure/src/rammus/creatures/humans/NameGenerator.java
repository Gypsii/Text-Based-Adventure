package rammus.creatures.humans;

import java.util.ArrayList;

public class NameGenerator {
	
	static ArrayList<String> firstNames = new ArrayList<String>();
	
	public static void names(){
		firstNames.add("Graham");
		firstNames.add("Nigel");
		firstNames.add("Tyrone");
		firstNames.add("Ainsley");
		firstNames.add("DeShawn");
		firstNames.add("Mohammed");
	}

	public static String generateHumanName(){
		return firstNames.get((int)(Math.random() * firstNames.size()));
	}
	
}
