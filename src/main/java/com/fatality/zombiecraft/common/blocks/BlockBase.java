/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.blocks;

import com.fatality.zombiecraft.utils.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public abstract class BlockBase extends Block {
	
	protected String resourcePath = "";
	protected String groupName = "";
	protected String unlocalizedName = "";
	
	public BlockBase(Material material, String unlocalName, String resourcePath) {
		super(material);
		setBlockUnbreakable();
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
		
		return String.format("tile.%s.%s.%s", ModInfo.MOD_ID, groupName, unlocalizedName);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
}
