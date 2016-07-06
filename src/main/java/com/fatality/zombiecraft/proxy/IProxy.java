/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
	
	void registerBlocks();
	
	void registerItems();
	
	void registerGUIs();
	
	void registerRenderers();
	
	void registerEvents();
	
	void registerMessages();
	
	void registerCapabilities();
	
	void initConfig(FMLPreInitializationEvent event);
}
