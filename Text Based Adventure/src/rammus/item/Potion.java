package rammus.item;

import java.util.ArrayList;

import rammus.Effect;

public class Potion extends Item{

	public ArrayList<Effect> effects = new ArrayList<Effect>();
	public static int EFFECTCOUNT = 5;
	
	public Potion(String n) {
		name = n;
		isPotion = true;
		isStackable = false;
		prefix = "some";
		for(int i = 0; i < EFFECTCOUNT; i++){
			effects.add(new Effect(Effect.NONE, 0, -100));
		}
	}

	public static Potion blank = new Potion("Bottle of Water");
	
	@SuppressWarnings("unchecked")
	public Potion clone(){
		Potion i = new Potion(name);
		i.isArmour = false;
		i.isModifier = this.isModifier;
		i.modifier = this.modifier;
		i.effects = (ArrayList<Effect>) this.effects.clone();//hope this doesn't fuck stuff up
		return i;
	}
	
	public void addIngredient(Item ing){
		Effect e;
		if(ing.name == Food.mushroom.name){
			e = new Effect(Effect.HEAL, 3, 10);
			addEffect(e);
		}else if(ing.name == Food.egg.name){
			e = new Effect(Effect.HEAL, 4, 15);
			addEffect(e);
		}else if(ing.name == Food.pepper.name){
			e = new Effect(Effect.NONE, 0, 0);
			addEffect(e);
		}else if(ing.name == Item.slimeFire.name){
			e = new Effect(Effect.IGNITE, 2, 12);
			addEffect(e);
		}else if(ing.name == Item.slimeIce.name){
			e = new Effect(Effect.DAMAGE, 6, 6);
			addEffect(e);
			e = new Effect(Effect.HEAL, 4, 12);
			addEffect(e);
		}else if(ing.name == Item.slimeExplosive.name){
			e = new Effect(Effect.PARALYSE, 2, 4);
			addEffect(e);
		}else if(ing.name == Item.slimeWater.name){
			e = new Effect(Effect.NONE, 0, 30);//This should have the effect of reducing the effective potency of the other 4 effects
			addEffect(e);
		}else if(ing.name == Item.featherSnow.name){
			e = new Effect(Effect.NONE, 0, 0);
			addEffect(e);
		}else if(ing.name == Food.fungus.name){
			e = new Effect(Effect.DAMAGE, 5, 16);
			addEffect(e);
		}
	}
	
	public void addEffect(Effect e){
		for(int i = 0; i < EFFECTCOUNT; i++){
			if(e.priority >= effects.get(i).priority){
				for(int j = EFFECTCOUNT - 1; j > i; j--){
					effects.set(j, effects.get(j - 1));
				}
				effects.set(i, e);
				this.name = "Mystical Potion";
				break;
			}
		}
	}
	
}
