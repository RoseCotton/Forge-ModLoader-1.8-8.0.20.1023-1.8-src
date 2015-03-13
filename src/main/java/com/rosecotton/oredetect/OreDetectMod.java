package com.rosecotton.oredetect;

import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;



@Mod(modid = OreDetectMod.MODID, name = OreDetectMod.MODNAME, version = OreDetectMod.VERSION) 

public class OreDetectMod 
{

	
	  //public static Block brickBlock;
	  //public static Item brickIngot;
	  public static final String MODID = "oredetectmod";
	  public static final String MODNAME = "RoseCotton's OreDetect for 1.8";
	  public static final String VERSION = "1.0";
	  
	  
	  
	  
	  @Instance(value = "oredetectmod")
	  public static OreDetectMod instance = new OreDetectMod();
	  

	  
	  
	  public static class MySidedProxyHolder {
		  @SidedProxy(modId="oredetectmod",clientSide="com.rosecotton.oredetectmod.ClientProxy", serverSide="com.rosecotton.oredetectmod.CommonProxy")
		  public static CommonProxy proxy;
		  }
		  
		  public class CommonProxy {
			  // Common or server stuff here that needs to be overridden on the client
		  }
		  public class ClientProxy extends CommonProxy {
			  // Override common stuff with client specific stuff here
		  }
	  
	 
	  @EventHandler
	  public void preInit(FMLPreInitializationEvent event) 
	  {
	  	//System.out.println("Called method: preInit");
	  	

	  
	  	//blocks
	  	//brickBlock = new BlockBrick();
	  	//LanguageRegistry.addName(brickBlock, "Brick Block");
	  	
	  
	  	//items
	  	//brickIngot = new ItemBrickIngot();
		  //MOVE GAMEREGISTRY TO ITEM CLASS?
	  	//GameRegistry.registerItem(brickIngot, ((ItemBrickIngot) brickIngot).getName());
	  	//LanguageRegistry.addName(brickIngot, "Brick Ingot");
	  }

	  @EventHandler
	  public void init(FMLInitializationEvent event) 
	  {
	  	//System.out.println("Called method: init");
	  
	  	if(event.getSide() == Side.CLIENT)
	  	{
	  		
	  		//RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	  		//renderItem.getItemModelMesher().register(brickIngot, 0, new ModelResourceLocation(Testmod01.MODID + ":" + ((ItemBrickIngot)brickIngot).getName(), "brickIngot"));
	  		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(brickIngot,0, new ModelResourceLocation(Testmod01.MODID + ":" + ((ItemBrickIngot)brickIngot).getName(), "inventory"));

	  		//renderItem.getItemModelMesher().register(Item.getItemFromBlock(brickBlock), 0, new ModelResourceLocation(Testmod01.MODID + ":" + ((BlockBrick)brickBlock).getName(), "brickBlock"));
	  		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(brickBlock),0, new ModelResourceLocation(Testmod01.MODID+":"+ ((BlockBrick)brickBlock).getName(), "inventory"));
	  		
	  		
	  	}
	  	
	  	//recipes
	  	//GameRegistry.addRecipe(new ItemStack(brickBlock), new Object[]{
	  	//"AA ",
	  	//"AA ",
	  	//"   ",
	  	//'A', brickIngot
	  	//});

	  	//GameRegistry.addShapelessRecipe(new ItemStack(brickIngot, 4), new Object[]
	  	//{
	  	//    	brickBlock//new ItemStack(brickBlock, 1, 1)
	  	//});
	  	
	  	//I left off the third parameter in ItemStack because it means damage
	  	//GameRegistry.addSmelting(new ItemStack(brickBlock, 1), new ItemStack(brickIngot, 1), 0.1F);
	  	
	  	
	  }

	  @EventHandler
	  public void postInit(FMLPostInitializationEvent event) 
	  {
	  	System.out.println("Called method: postInit");
	  }
	}












  
  
  


  
 