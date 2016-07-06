/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.blocks;

import com.fatality.zombiecraft.common.blocks.building.BlockWoodBarrier;
import com.fatality.zombiecraft.common.blocks.tiles.BlockBarricade;
import com.fatality.zombiecraft.common.blocks.tiles.BlockGameBlock;
import com.fatality.zombiecraft.common.items.tiles.ItemBarricade;
import com.fatality.zombiecraft.utils.Register;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public enum Blocks {
	BLOCK_BARRICADE(BlockBarricade.class),
	BLOCK_WOOD_BARRIER(BlockWoodBarrier.class),
	BLOCK_GAME_BLOCK(BlockGameBlock.class),;
	
	private final Class<? extends BlockBase> blockClass;
	private final Class<? extends ItemBlock> itemBlockClass;
	private Block block;
	
	Blocks(Class<? extends BlockBase> blockClass) {
		this(blockClass, ItemBlock.class);
	}
	
	Blocks(Class<? extends BlockBase> blockClass, Class<? extends ItemBlock> itemBlockClass) {
		this.blockClass = blockClass;
		this.itemBlockClass = itemBlockClass;
	}
	
	public static void registerBlocks() {
		for (Blocks block : Blocks.values()) {
			block.register();
		}
	}
	
	public ItemStack getStack() {
		return new ItemStack(block);
	}
	
	public ItemStack getStack(int size) {
		return new ItemStack(block, size);
	}
	
	public ItemStack getStack(int size, int meta) {
		return new ItemStack(block, size, meta);
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	private void register() {
		block = Register.registerBlock(blockClass, itemBlockClass);
	}
}
