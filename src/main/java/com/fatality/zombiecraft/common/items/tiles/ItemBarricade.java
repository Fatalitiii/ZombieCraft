/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.items.tiles;

import com.fatality.zombiecraft.common.blocks.Blocks;
import com.fatality.zombiecraft.common.blocks.building.BlockWoodBarrier;
import com.fatality.zombiecraft.common.blocks.tiles.BlockBarricade;
import com.fatality.zombiecraft.common.items.ItemBase;
import com.fatality.zombiecraft.utils.ZombieCraftCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBarricade extends ItemBase {
	
	public ItemBarricade() {
		super("barricade", "tiles");
		setCreativeTab(ZombieCraftCreativeTabs.TILES);
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up().up())) {
			EnumFacing enumfacing = EnumFacing.fromAngle((double) playerIn.rotationYaw);
			worldIn.setBlockState(pos.up(), Blocks.BLOCK_WOOD_BARRIER.getBlock().getDefaultState().withProperty(BlockWoodBarrier.FACING, enumfacing));
			worldIn.setBlockState(pos.up().up(), Blocks.BLOCK_BARRICADE.getBlock().getDefaultState().withProperty(BlockBarricade.FACING, enumfacing));
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
