/**
 * Created by Fatality
 */

package com.fatality.zombiecraft;

import com.fatality.zombiecraft.proxy.IProxy;
import com.fatality.zombiecraft.utils.ModInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION)
public class ZombieCraft {
	
	@Mod.Instance(ModInfo.MOD_ID)
	public static ZombieCraft instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		proxy.registerBlocks();
		proxy.registerItems();
		proxy.registerGUIs();
		proxy.registerRenderers();
		
		proxy.registerMessages();
		proxy.registerCapabilities();
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerEvents();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
