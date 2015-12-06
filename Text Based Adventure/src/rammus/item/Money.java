package rammus.item;

import java.util.ArrayList;

public class Money {
	public ArrayList<Currency> list = new ArrayList<Currency>();
	
	public void add(Item toAdd){
		Currency c = (Currency) toAdd;
		int loc = -1;
		for(int i = 0; i < list.size(); i++){
			if(c.name.equals(list.get(i))){
				loc = i;
				break;
			}
		}
		if(loc == -1){
			list.add(c);
		}else{
			list.get(loc).count += c.count;
		}
	}
	
	public void decrement(Currency c, int decr){
		int loc = -1;
		for(int i = 0; i < list.size(); i++){
			if(c.name.equals(list.get(i))){
				loc = i;
				break;
			}
		}
		if(loc == -1){
			System.err.println("ERR: Can not afford. This should not happen. Yell at dev");
		}else{
			list.get(loc).count -= decr;
			if(list.get(loc).count < 1){
				list.remove(loc);
			}
		}
	}
	
	public boolean canAfford(Currency c, int decr){
		int loc = -1;
		for(int i = 0; i < list.size(); i++){
			if(c.name.equals(list.get(i))){
				loc = i;
				break;
			}
		}
		if(loc == -1){
			return false;
		}else{
			if(list.get(loc).count < decr){
				return false;
			}
		}
		return true;
	}
}
