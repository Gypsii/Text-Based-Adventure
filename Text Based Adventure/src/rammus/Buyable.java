package rammus;

import rammus.item.Item;

public class Buyable {
	Item item;
	double rarityConstant = 1;
	double localAvailability = 1;
	double baseValue;
	double marketValue;
	int buyValue;
	int sellValue;
	
	public Buyable(Item i, double v){
		item = i;
		baseValue = v;
		calculateValue();
	}
	
	public void calculateValue(){
		marketValue = baseValue / rarityConstant * localAvailability; 
	}
}
