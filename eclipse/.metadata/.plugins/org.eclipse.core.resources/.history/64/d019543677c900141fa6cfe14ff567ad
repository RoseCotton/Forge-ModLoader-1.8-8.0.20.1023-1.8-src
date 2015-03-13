package com.becky.testmod01;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Set;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;

public class BlockBrick extends Block 
{
	//public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockBrick.EnumType.class);
    private static final String __OBFID = "CL_00003555";
    
	//public static int brickBlockid = 3000;
	public static Material material = Material.rock;
	private final String name = "brickBlock";
    private final String unlocalizedName = "testmod01.MODID"+"_"+name;
	ModelResourceLocation modelresourcelocation = new ModelResourceLocation("testmod01"+":"+name, "inventory");
    public BlockBrick()//int i, Material j) 
    {
        super(Material.rock);
		this.setDefaultState(this.blockState.getBaseState());
        GameRegistry.registerBlock(this, name);
    	this.setUnlocalizedName(Testmod01.MODID + "_" + name);
    	this.setCreativeTab(CreativeTabs.tabBlock);
    	this.setStepSound(soundTypeStone);
        this.setHardness(1.0f);
        this.setResistance(2.0f);
    }   
        //THE INTERNET SAID THIS WAS FIXED BY REINSTALLING EVERYTHING AND SHOULD WORK FINE, 
        //I KINNA TRIED TO REINSTALL BUT DON'T THINK IT WORKED RIGHT OR SOMETHING.
        //this.setHarvestLevel("pickaxe", 0);    
         

    

    
    
    
    //public IBlockState getStateFromMeta(int meta)
    //{
    //    return this.getDefaultState().withProperty(VARIANT, BlockBrick.EnumType.byMetadata(meta));
   // }

   // public int getMetaFromState(IBlockState state)
   // {
    //    return ((BlockBrick.EnumType)state.getValue(VARIANT)).getMetadata();
    //}



  
        public String toString()
        {
            return this.name;
        }

       

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        //static
       // {
        //   BlockBrick.EnumType[] var0 = values();
        //    int var1 = var0.length;

       //     for (int var2 = 0; var2 < var1; ++var2)
        //    {
       //         BlockBrick.EnumType var3 = var0[var2];
       //         META_LOOKUP[var3.getMetadata()] = var3;
        //    }
        //}
    
    
    //I'M TRYING TO MAKE THE BLOCK DROP ITSELF
    //HARD PART IS FINDING THE blockID
    //BlockBrick brickBlock = new BlockBrick();//id,material);
    public int idDropped()//(int i, int k)
    {
            return 0;//(Block)Block.getIdFromBlock((Block)brickBlock);
    }
    
    //DO I NEED THIS??
    //public String getIcon()
    //{
    //	return name;
    //}

}
