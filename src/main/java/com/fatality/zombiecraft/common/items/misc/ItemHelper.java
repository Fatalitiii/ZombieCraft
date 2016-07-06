/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.items.misc;

import com.fatality.zombiecraft.common.items.ItemBase;
import com.fatality.zombiecraft.common.tileentities.TileEntityBase;
import com.fatality.zombiecraft.common.tileentities.tiles.TileEntityGameBlock;
import com.fatality.zombiecraft.utils.ZombieCraftCreativeTabs;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemHelper extends ItemBase {
	public ItemHelper() {
		super("helper", "misc");
		setCreativeTab(ZombieCraftCreativeTabs.MISC);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		if (worldIn.isRemote) {
			if (player.isSneaking()) {
				if (stack.getTagCompound() == null) {
					stack.setTagCompound(new NBTTagCompound());
				}
				int mode = 0;
				if (stack.getTagCompound().hasKey("mode")) {
					NBTTagCompound modeNBT = (NBTTagCompound) stack.getTagCompound().getTag("mode");
					
					mode = modeNBT.getInteger("mode");
					
					mode = (mode == 1) ? 0 : 1;
					player.addChatMessage(new TextComponentString(String.format("Switching mode to [%s%s%s]",
							ChatFormatting.GREEN, (mode == 0) ? "Linking" : "Switching", ChatFormatting.WHITE
					)));
				}
				
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("mode", mode);
				stack.getTagCompound().setTag("mode", nbt);
			}
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}
	
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		int mode = 0;
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		if (stack.getTagCompound().hasKey("mode")) {
			NBTTagCompound modeNBT = (NBTTagCompound) stack.getTagCompound().getTag("mode");
			
			mode = modeNBT.getInteger("mode");
		}
		if (mode == 0) {
			if ((world.getTileEntity(pos) instanceof TileEntityBase)) {
				if (!player.isSneaking()) {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setInteger("dim", player.dimension);
					nbt.setInteger("posX", pos.getX());
					nbt.setInteger("posY", pos.getY());
					nbt.setInteger("posZ", pos.getZ());
					
					if (!(world.getTileEntity(pos) instanceof TileEntityGameBlock)) {
						stack.getTagCompound().setTag("linking", nbt);
						if (stack.getTagCompound().hasKey("gameblock")) {
							NBTTagCompound gbNBT = (NBTTagCompound) stack.getTagCompound().getTag("gameblock");
							int dim = gbNBT.getInteger("dim");
							int posX = gbNBT.getInteger("posX");
							int posY = gbNBT.getInteger("posY");
							int posZ = gbNBT.getInteger("posZ");
							player.addChatMessage(new TextComponentString(String.format("Linking %s%s%s at [%s%s, %s, %s%s] to %sGameBlock%s at [%s%s, %s, %s%s]",
									ChatFormatting.AQUA,
									world.getBlockState(pos).getBlock().getLocalizedName(),
									ChatFormatting.WHITE,
									ChatFormatting.GOLD,
									pos.getX(), pos.getY(), pos.getZ(),
									ChatFormatting.WHITE,
									ChatFormatting.RED,
									ChatFormatting.WHITE,
									ChatFormatting.GOLD,
									posX, posY, posZ,
									ChatFormatting.WHITE
							)));
						}
					} else {
						stack.getTagCompound().setTag("gameblock", nbt);
					}
				}
			}
		} else {
			if ((world.getTileEntity(pos) instanceof TileEntityGameBlock)) {
				
			} else {
				
				
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("dim", player.dimension);
				nbt.setInteger("posX", pos.getX());
				nbt.setInteger("posY", pos.getY());
				nbt.setInteger("posZ", pos.getZ());
				int position;
				if (!player.isSneaking()) {
					stack.getTagCompound().setTag("coord1", nbt);
					position = 1;
				} else {
					stack.getTagCompound().setTag("coord2", nbt);
					position = 2;
				}
				
				player.addChatMessage(new TextComponentString(String.format("%sPosition %s%s set to [%s%s, %s, %s%s]",
						ChatFormatting.RED,
						position,
						ChatFormatting.WHITE,
						ChatFormatting.GOLD,
						pos.getX(), pos.getY(), pos.getZ(),
						ChatFormatting.WHITE
				)));
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (stack.getTagCompound() != null) {
			tooltip.add("");
			if (stack.getTagCompound().hasKey("mode")) {
				NBTTagCompound modeNBT = (NBTTagCompound) stack.getTagCompound().getTag("mode");
				int mode = modeNBT.getInteger("mode");
				tooltip.add(String.format("%sMode %s: %s%s", ChatFormatting.GOLD, ChatFormatting.WHITE, ChatFormatting.AQUA, (mode == 0) ? "Linking" : "Selecting"));
				if (mode == 0) {
					if (stack.getTagCompound().hasKey("gameblock")) {
						tooltip.add("");
						NBTTagCompound gbNBT = (NBTTagCompound) stack.getTagCompound().getTag("gameblock");
						int dim = gbNBT.getInteger("dim");
						int posX = gbNBT.getInteger("posX");
						int posY = gbNBT.getInteger("posY");
						int posZ = gbNBT.getInteger("posZ");
						tooltip.add(String.format("%sGameBlock%s at [%s%s, %s, %s%s]",
								ChatFormatting.RED,
								ChatFormatting.WHITE,
								ChatFormatting.GOLD,
								posX, posY, posZ,
								ChatFormatting.WHITE
						));
					}
				} else {
					if (stack.getTagCompound().hasKey("coord1")) {
						tooltip.add("");
						NBTTagCompound gbNBT = (NBTTagCompound) stack.getTagCompound().getTag("coord1");
						int dim = gbNBT.getInteger("dim");
						int posX = gbNBT.getInteger("posX");
						int posY = gbNBT.getInteger("posY");
						int posZ = gbNBT.getInteger("posZ");
						tooltip.add(String.format("%sPosition 1%s set [%s%s, %s, %s%s]",
								ChatFormatting.RED,
								ChatFormatting.WHITE,
								ChatFormatting.GOLD,
								posX, posY, posZ,
								ChatFormatting.WHITE
						));
					}
					
					if (stack.getTagCompound().hasKey("coord2")) {
						tooltip.add("");
						NBTTagCompound gbNBT = (NBTTagCompound) stack.getTagCompound().getTag("coord2");
						int dim = gbNBT.getInteger("dim");
						int posX = gbNBT.getInteger("posX");
						int posY = gbNBT.getInteger("posY");
						int posZ = gbNBT.getInteger("posZ");
						tooltip.add(String.format("%sPosition 2%s set [%s%s, %s, %s%s]",
								ChatFormatting.RED,
								ChatFormatting.WHITE,
								ChatFormatting.GOLD,
								posX, posY, posZ,
								ChatFormatting.WHITE
						));
					}
				}
			} else {
				tooltip.add(String.format("%sMode %s: %s%s", ChatFormatting.GOLD, ChatFormatting.WHITE, ChatFormatting.AQUA, "Linking"));
			}
		}
	}
}
