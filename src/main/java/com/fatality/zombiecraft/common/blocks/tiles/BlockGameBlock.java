/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.blocks.tiles;

import com.fatality.zombiecraft.common.blocks.BlockBase;
import com.fatality.zombiecraft.common.blocks.BlockTileBase;
import com.fatality.zombiecraft.common.tileentities.tiles.TileEntityGameBlock;
import com.fatality.zombiecraft.utils.ZombieCraftCreativeTabs;
import net.minecraft.block.material.Material;

public class BlockGameBlock extends BlockTileBase {
	public BlockGameBlock() {
		super(Material.ROCK, "gameblock", "tiles", TileEntityGameBlock.class);
		setCreativeTab(ZombieCraftCreativeTabs.MISC);
	}
}
