/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ZombieCraftCreativeTabs {
	public static final CreativeTabs GENERAL = new CreativeTabs(ModInfo.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			return Items.SKULL;
		}
		
		@Override
		public String getTabLabel() {
			return ModInfo.MOD_ID + ".general";
		}
	};
}