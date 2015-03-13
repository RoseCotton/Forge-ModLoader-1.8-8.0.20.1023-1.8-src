package com.rosecotton.shelvesmod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.rosecotton.shelvesmod.BlockShelf;
//import com.becky.testmod01.Testmod01;

@Mod(modid = ShelvesMod.MODID, name = ShelvesMod.MODNAME, version = ShelvesMod.VERSION) 

public class ShelvesMod 
{
	
	  public static Block shelfBlock;
	  //public static Item brickIngot;
	  public static final String MODID = "shelvesmod";
	  public static final String MODNAME = "RoseCotton's Shelves Mod for 1.8";
	  public static final String VERSION = "1.0";
	  public static int type;
	 
	  @Instance(value = "shelvesmod")
	  public static ShelvesMod instance = new ShelvesMod();
	  
	  public static class MySidedProxyHolder {
		  @SidedProxy(modId="shelvesmod",clientSide="com.rosecotton.shelvesmod.ClientProxy", serverSide="com.rosecotton.shelvesmod.CommonProxy")
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

	  	//blocks
	  	shelfBlock = new BlockShelf(type);
	  	LanguageRegistry.addName(shelfBlock, "Shelf Block");
	  	
	  	GameRegistry.registerTileEntity(ShelfTitleEntity.class, "shelfTitleEntity");
	  	//was com.rosecotton.shelvesmod.ShelfTitleEntity.class
	  	
	  	
	  	//items
	  	//brickIngot = new ItemBrickIngot();
		  //MOVE GAMEREGISTRY TO ITEM CLASS?
	  	//GameRegistry.registerItem(brickIngot, ((ItemBrickIngot) brickIngot).getName());
	  	//LanguageRegistry.addName(brickIngot, "Brick Ingot");
		NetworkRegistry.INSTANCE.registerGuiHandler(ShelvesMod.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerShelf(), GuiHandlerShelf.getGuiID());
	  }

	  @EventHandler
	  public void init(FMLInitializationEvent event) 
	  {
	  	//System.out.println("Called method: init");
	  
	  	if(event.getSide() == Side.CLIENT)
	  	{
	  		
	  		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

	  		renderItem.getItemModelMesher().register(Item.getItemFromBlock(shelfBlock), 0, new ModelResourceLocation(ShelvesMod.MODID + ":" + ((BlockShelf) shelfBlock).getName(), "shelfBlock"));
	  		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(shelfBlock),0, new ModelResourceLocation(ShelvesMod.MODID+":"+ "shelfBlock", "inventory"));
	  		
	  	    //final int DEFAULT_ITEM_SUBTYPE = 0;
	  	    //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockSimple, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);

	  	    ClientRegistry.bindTileEntitySpecialRenderer(ShelfTitleEntity.class, new TileShelfRender());
	  	}
	    GameRegistry.registerTileEntity(ShelfTitleEntity.class, "Shelf_tesr_te");
	  	
	  	//recipes
	  	GameRegistry.addRecipe(new ItemStack(shelfBlock), new Object[]{
	  	"   ",
	  	"  A",
	  	" AA",
	  	'A', Items.stick //getItemFromBlock(Blocks.planks)
	  	});
	  }
	    @EventHandler
	    public void postInit(FMLPostInitializationEvent event) 
	    {
	    	System.out.println("Called method: postInit");
	    }
	}