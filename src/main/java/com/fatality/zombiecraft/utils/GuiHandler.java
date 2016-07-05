/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	//public static final int GUI_SKILL_BOOK = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	/*	switch (ID) {
			case GUI_SKILL_BOOK:
				return new ContainerSkillBook(player.inventory);
			default:
				break;
		}*/
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		/*switch (ID) {
			case GUI_SKILL_BOOK:
				return new GuiSkillBook(new ContainerSkillBook(player.inventory), player);
			
			default:
				break;
		}*/
		return null;
	}
}
