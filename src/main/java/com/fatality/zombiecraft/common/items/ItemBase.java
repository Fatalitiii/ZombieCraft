/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.items;

import com.fatality.zombiecraft.utils.ModInfo;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
	
	protected String resourcePath = "";
	protected String groupName = "";
	protected String unlocalizedName = "";
	
	public ItemBase(String unlocalName, String resourcePath) {
		this.groupName = resourcePath;
		this.unlocalizedName = unlocalName;
		this.resourcePath = String.format("%s/%s", resourcePath, unlocalName);
	}
	
	public String getName() {
		return unlocalizedName;
	}
	
	public String getResourcePath() {
		return resourcePath;
	}
	
	@Override
	public String getUnlocalizedName() {
		String unlocalizedName = super.getUnlocalizedName();
		unlocalizedName = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
		return String.format("item.%s.%s.%s", ModInfo.MOD_ID, groupName, unlocalizedName);
	}
}