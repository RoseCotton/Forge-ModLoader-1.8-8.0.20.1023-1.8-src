package com.becky.testmod01;


import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import com.google.common.collect.Sets;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
 
public class ItemBrickIngot extends Item
{
	private final String name = "brickIngot";
	ModelResourceLocation modelresourcelocation = new ModelResourceLocation("brickIngot", "inventory");
	
	public ItemBrickIngot()
	{
		super();
		//GameRegistry.registerItem(this, name);
		setUnlocalizedName(Testmod01.MODID + "_" + name);
		//setTextureName(Testmod01.MODID + ":" + name)
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		
	}
	public String getIcon()
	{
		return name;
	}
	public String getName()
	{
	return name;
	}
}