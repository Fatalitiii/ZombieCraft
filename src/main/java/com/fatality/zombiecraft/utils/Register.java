/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.utils;

import com.fatality.zombiecraft.common.blocks.BlockBase;
import com.fatality.zombiecraft.common.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Register {
	
	public static Block registerBlock(Class<? extends Block> blockClass, Class<? extends ItemBlock> itemClass) {
		try {
			Block block = blockClass.getConstructor().newInstance();
			ItemBlock itemBlock = itemClass.getConstructor(Block.class).newInstance(block);
			String name = ((BlockBase) block).getName();
			
			block.setRegistryName(ModInfo.MOD_ID, name);
			block.setUnlocalizedName(name);
			itemBlock.setRegistryName(block.getRegistryName());
			
			GameRegistry.register(block);
			GameRegistry.register(itemBlock);
			
			if (FMLCommonHandler.instance().getSide().isClient()) {
				registerBlockRender((BlockBase) block);
			}
			
			return block;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Item registerItem(Class<? extends Item> itemClass) {
		try {
			Item item = itemClass.getConstructor().newInstance();
			String name = ((ItemBase) item).getName();
			
			item.setRegistryName(ModInfo.MOD_ID, name);
			item.setUnlocalizedName(name);
			
			GameRegistry.register(item);
			
			if (FMLCommonHandler.instance().getSide().isClient()) {
				registerItemRenderer((ItemBase) item);
			}
			
			return item;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerBlockRender(BlockBase block) {
		final String resourcePath = String.format("%s:%s", ModInfo.MOD_ID, block.getResourcePath());
		
		ModelLoader.setCustomStateMapper(block, new DefaultStateMapper() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
			}
		});
		
		List<ItemStack> subBlocks = new ArrayList<ItemStack>();
		block.getSubBlocks(Item.getItemFromBlock(block), null, subBlocks);
		
		for (ItemStack itemStack : subBlocks) {
			IBlockState blockState = block.getStateFromMeta(itemStack.getItemDamage());
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), itemStack.getItemDamage(), new ModelResourceLocation(resourcePath, getKeys(blockState.getProperties())));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerItemRenderer(ItemBase item) {
		List<ItemStack> subBlocks = new ArrayList<ItemStack>();
		item.getSubItems(item, null, subBlocks);
		int i = 0;
		for (ItemStack itemStack : subBlocks) {
			String addon = "";
			if (subBlocks.size() > 1) {
				String split = "[.]";
				String[] pathBroken = subBlocks.get(i).getUnlocalizedName().split(split);
				addon = "_" + pathBroken[pathBroken.length - 1];
			}
			final String resourcePath = String.format("%s:%s%s", ModInfo.MOD_ID, item.getResourcePath(), addon);
			ModelLoader.setCustomModelResourceLocation(item, itemStack.getItemDamage(), new ModelResourceLocation(resourcePath, "inventory"));
			i++;
		}
	}
	
	public static String getKeys(Map<IProperty<?>, Comparable<?>> values) {
		String propertyString = "";
		for (Map.Entry<IProperty<?>, Comparable<?>> json : values.entrySet()) {
			IProperty<?> key = (IProperty) json.getKey();
			propertyString = String.format("%s=%s", key.getName(), getValue(key, (Comparable) json.getValue()));
		}
		return propertyString;
	}
	
	private static <T extends Comparable<T>> String getValue(IProperty<T> variants, Comparable<?> value) {
		return variants.getName((T) value);
	}
	
}