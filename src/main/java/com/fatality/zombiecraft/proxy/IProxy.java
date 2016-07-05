/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.proxy;

public interface IProxy {
	
	void registerBlocks();
	
	void registerItems();
	
	void registerGUIs();
	
	void registerRenderers();
	
	void registerEvents();
	
	void registerMessages();
	
	void registerCapabilities();
}
