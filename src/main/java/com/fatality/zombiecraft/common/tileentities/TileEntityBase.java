/**
 * Created by Fatality
 */

package com.fatality.zombiecraft.common.tileentities;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity implements ITickable {
	
	private String customName;
	
	public void setCustomeName(String name) {
		this.customName = name;
	}
	
	public boolean hasCustomName() {
		return (this.customName != null) && (this.customName.length() > 0);
	}
	
	
	@Override
	public void tick() {
		
	}
}
