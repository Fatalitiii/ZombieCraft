/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.proxy;

import com.fatality.zombiecraft.ZombieCraft;
import com.fatality.zombiecraft.common.blocks.Blocks;
import com.fatality.zombiecraft.common.items.Items;
import com.fatality.zombiecraft.utils.GuiHandler;
import com.fatality.zombiecraft.utils.ModInfo;
import com.fatality.zombiecraft.utils.constants;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.io.File;

public class CommonProxy implements IProxy {
	
	public static SimpleNetworkWrapper network;
	
	@Override
	public void registerBlocks() {
		Blocks.registerBlocks();
	}
	
	@Override
	public void registerItems() {
		Items.registerItems();
	}
	
	@Override
	public void registerGUIs() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ZombieCraft.instance, new GuiHandler());
	}
	
	@Override
	public void registerRenderers() {
		
	}
	
	@Override
	public void registerEvents() {
		
	}
	
	@Override
	public void registerMessages() {
		int messageCounter = 0;
		network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MESSENGER);
	}
	
	@Override
	public void registerCapabilities() {
	}
	
	@Override
	public void initConfig(FMLPreInitializationEvent event) {
		constants.zombiecraftFolder = new File(event.getModConfigurationDirectory().getParent(), "/zombiecraft");
		constants.mapFolder = new File(constants.zombiecraftFolder,"/maps");
		
		if(!constants.zombiecraftFolder.exists() || !constants.mapFolder.exists()){
			constants.zombiecraftFolder.mkdir();
			constants.mapFolder.mkdir();
		}
	}
	
	
}