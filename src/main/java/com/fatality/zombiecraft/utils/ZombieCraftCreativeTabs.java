/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.utils;

import com.fatality.zombiecraft.common.blocks.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ZombieCraftCreativeTabs {
	public static final CreativeTabs MISC = new CreativeTabs(ModInfo.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			return com.fatality.zombiecraft.common.items.Items.ITEM_HELPER.getItem();
		}
		
		@Override
		public String getTabLabel() {
			return ModInfo.MOD_ID + ".misc";
		}
	};
	
	public static final CreativeTabs BUILDING = new CreativeTabs(ModInfo.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			return Blocks.BLOCK_WOOD_BARRIER.getStack().getItem();
		}
		
		@Override
		public String getTabLabel() {
			return ModInfo.MOD_ID + ".building";
		}
	};
	
	public static final CreativeTabs TILES = new CreativeTabs(ModInfo.MOD_ID) {
		@Override
		public Item getTabIconItem() {
			return com.fatality.zombiecraft.common.items.Items.ITEM_BLOCK_BARRICADE.getItem();
		}
		
		@Override
		public String getTabLabel() {
			return ModInfo.MOD_ID + ".tiles";
		}
	};
}