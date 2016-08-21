/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items.armor;

import com.coner.pixeldungeon.remake.R;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.actors.hero.HeroSubClass;
import com.watabou.pixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

abstract public class ClassArmor extends Armor {
	
	private static final String TXT_LOW_HEALTH   = Game.getVar(R.string.ClassArmor_LowHealt);
	private static final String TXT_NOT_EQUIPPED = Game.getVar(R.string.ClassArmor_NotEquipped);
	
	{
		levelKnown = true;
		cursedKnown = true;
		defaultAction = special();
	}
	
	public ClassArmor() {
		super( 6 );
	}
	
	public static Armor upgrade ( Hero owner, Armor armor ) {

		ClassArmor classArmor;
		if(owner.subClass == HeroSubClass.NONE) {
			classArmor = owner.heroClass.classArmor();
		} else {
			classArmor = owner.subClass.classArmor();
		}

		classArmor.STR = armor.STR;
		classArmor.DR  = armor.DR;
		
		classArmor.inscribe( armor.glyph );
		
		return classArmor;
	}
	
	private static final String ARMOR_STR	= "STR";
	private static final String ARMOR_DR	= "DR";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( ARMOR_STR, STR );
		bundle.put( ARMOR_DR, DR );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		STR = bundle.getInt( ARMOR_STR );
		DR = bundle.getInt( ARMOR_DR );
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (hero.hp() >= 3 && isEquipped( hero )) {
			actions.add( special() );
		}
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		if (action.equals(special())) {
			
			if (hero.hp() < 3) {
				GLog.w( TXT_LOW_HEALTH );
			} else if (!isEquipped( hero )) {
				GLog.w( TXT_NOT_EQUIPPED );
			} else {
				setCurUser(hero);
				doSpecial();
			}
			
		} else {	
			super.execute( hero, action );		
		}
	}
	
	abstract public String special();
	abstract public void doSpecial();
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public int price() {
		return 0;
	}
	
	@Override
	public String desc() {
		return Game.getVar(R.string.ClassArmor_Desc);
	}
}
