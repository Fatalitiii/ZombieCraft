/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.blocks;

import com.fatality.zombiecraft.utils.ModInfo;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class BlockTileBase extends BlockBase implements ITileEntityProvider {
	
	@Nonnull
	private Class<? extends TileEntity> tileEntityClass;
	
	public BlockTileBase(Material material, String unlocalName, String resource, final Class<? extends TileEntity> clazz) {
		super(material, unlocalName, resource);
		this.setTileEntity(clazz);
	}
	
	protected void setTileEntity(final Class<? extends TileEntity> clazz) {
		this.tileEntityClass = clazz;
		this.isBlockContainer = true;
		
		String tileName = "tileentity." + ModInfo.MOD_ID + "." + clazz.getSimpleName();
		GameRegistry.registerTileEntity(this.tileEntityClass, tileName);
	}
	
	public Class<? extends TileEntity> getTileEntityClass() {
		return this.tileEntityClass;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		try {
			return this.tileEntityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to create a new instance " + this.tileEntityClass, ex);
		}
	}
	
}
