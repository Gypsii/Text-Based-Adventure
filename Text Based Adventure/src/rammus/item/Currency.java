package rammus.item;

public class Currency extends Item{
	
	public Currency(String n) {
		name = n;
		isCurrency = true;
		prefix = "a";
	}
	
	public static Currency money = new Currency("gold");
}
