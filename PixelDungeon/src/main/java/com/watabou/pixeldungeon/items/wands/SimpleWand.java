package com.watabou.pixeldungeon.items.wands;

import com.coner.android.util.TrackedRuntimeException;
import com.watabou.utils.Random;

public abstract class SimpleWand extends Wand {
	
	@SuppressWarnings("rawtypes")
	private static Class[] variants = {	WandOfAmok.class, 
		WandOfAvalanche.class, 
		WandOfDisintegration.class, 
		WandOfFirebolt.class, 
		WandOfLightning.class, 
		WandOfMagicMissile.class, 
		WandOfPoison.class, 
		WandOfRegrowth.class, 
		WandOfSlowness.class};
	
	static public SimpleWand createRandomSimpleWand() {
		try {
			return (SimpleWand) Random.element(variants).newInstance();
		} catch (Exception e) {
			throw new TrackedRuntimeException(e);
		}
	}
	
	
}
