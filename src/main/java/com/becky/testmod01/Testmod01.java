package com.becky.testmod01;

//import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
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
//import net.minecraftforge.fml.common.NetworkMod;
//import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.becky.testmod01.ItemBrickIngot;
//import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Testmod01.MODID, name = Testmod01.MODNAME, version = Testmod01.VERSION) 

//FIND OUT WHY I THOUGHT THIS WAS A GOOD IDEA?  1.7 tutorials... :\
//Maybe not used for 1.8...??
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
 



public class Testmod01 
{
    public static Block brickBlock;
    
    public static Item brickIngot;
    
    
    
    //DO WE NEED THIS?  WHAT NEEDS TO BE INCLUDED?  THIS IS FROM 1.8 CODE
    //http://www.wuppy29.com/minecraft/1-8-tutorial/updating-1-7-to-1-8-part-1-setup-mod-file/#sthash.LGfmMFHk.dpbs
    //Testmod01EventHandler handler = new Testmod01EventHandler();

    public static final String MODID = "testmod01";
    public static final String MODNAME = "Becky's First Test Mod 1.8";
    public static final String VERSION = "1.0";
    
    @Instance(value = "testmod01")
    public static Testmod01 instance = new Testmod01();
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(modId="Testmod01",clientSide="com.becky.testmod01.client.ClientProxy", serverSide="com.becky.testmod01.CommonProxy")

    public static CommonProxy proxy;
    
    public class CommonProxy 
    {
    // Common or server stuff here that needs to be overridden on the client
    }
    public class ClientProxy extends CommonProxy 
    {
    // Override common stuff with client specific stuff here
    }
    

    ///START WTIH THE NEXT TWO LINES
    //public final static Block blockBrick = new BlockBrick().setHardness(0.5F).setStepSound(Block.soundTypeGravel)
    //.setBlockName("blockBrick").setCreativeTab(CreativeTabs.tabBlock);
    
   
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	//System.out.println("Called method: preInit");
    	
    	//event handler registry
    	//FMLCommonHandler.instance().bus().register(handler);
    	//MinecraftForge.EVENT_BUS.register(handler);
    
    	//blocks
    	//START WITH THE NEXT TWO LINES
    	brickBlock = new BlockBrick();
    	//GameRegistry.registerBlock(brickBlock, ((BlockBrick) brickBlock).getName());
    	LanguageRegistry.addName(brickBlock, "Slime Brick");
    	
    
    	//items
    	brickIngot = new ItemBrickIngot();
    	
    
    	GameRegistry.registerItem(brickIngot, ((ItemBrickIngot) brickIngot).getName());
    	LanguageRegistry.addName(brickIngot, "Slime Brick Ingot");
    }
 
    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	//System.out.println("Called method: init");
    
    	if(event.getSide() == Side.CLIENT)
    	{
    		
    		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    		renderItem.getItemModelMesher().register(brickIngot, 0, new ModelResourceLocation(Testmod01.MODID + ":" + ((ItemBrickIngot)brickIngot).getName(), "brickIngot"));
    		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(brickIngot,0, new ModelResourceLocation(Testmod01.MODID + ":" + ((ItemBrickIngot)brickIngot).getName(), "inventory"));

    		renderItem.getItemModelMesher().register(Item.getItemFromBlock(brickBlock), 0, new ModelResourceLocation(Testmod01.MODID + ":" + ((BlockBrick)brickBlock).getName(), "brickBlock"));
    		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(brickBlock),0, new ModelResourceLocation(Testmod01.MODID+":"+ ((BlockBrick)brickBlock).getName(), "inventory"));
    		
    		
    	}
    	
    	//recipes
    	GameRegistry.addRecipe(new ItemStack(brickBlock), new Object[]{
    	"AA ",
    	"AA ",
    	"   ",
    	'A', brickIngot
    	});

    	GameRegistry.addShapelessRecipe(new ItemStack(brickIngot, 4), new Object[]
    	{
    	    	brickBlock//new ItemStack(brickBlock, 1, 1)
    	});
    	
    	//I left off the third parameter in ItemStack because it means damage
    	GameRegistry.addSmelting(new ItemStack(brickBlock, 1), new ItemStack(brickIngot, 1), 0.1F);
    	
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	System.out.println("Called method: postInit");
    }
}

