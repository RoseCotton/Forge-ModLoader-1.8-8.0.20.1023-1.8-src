package com.rosecotton.shelvesmod;

//import BlockStairs;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class BlockShelf extends BlockContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyEnum SECTION = PropertyEnum.create("section", BlockShelf.EnumShape.class);
    
    private final Random rand = new Random();
   // private static final String __OBFID = "CL_00000214";
	private final String name = "shelfBlock";
	ModelResourceLocation modelresourcelocation = new ModelResourceLocation("shelvesmod"+":"+name, "inventory");
    
    protected BlockShelf(int type)
    {
        super(Material.wood);
        //TEH FOLLOWIONG LINE MIGHT HAVE BEEN USEFUL BUT MAYBE CAUSED AN ERROR AT RUNTIME, TRY AGAIN
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        //this.chestType = type;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.useNeighborBrightness = true;
		this.setDefaultState(this.blockState.getBaseState());
        GameRegistry.registerBlock(this, name);
    	this.setUnlocalizedName(ShelvesMod.MODID + "_" + name);
    	this.setBlockBounds(1/16.0F, 0, 1/16.0F, 15/16.0F, 8/16.0F, 15/16.0F);
    }

    public String toString()
    {
        return this.name;
    }
    public String getName()
    {
        return this.name;
    }

    // used by the renderer to control lighting and visibility of other blocks.
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    public boolean isOpaqueCube() {
      return false;
    }

    // used by the renderer to control lighting and visibility of other blocks, also by
    // (eg) wall or fence to control whether the fence joins itself to this block
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    public boolean isFullCube() {
      return false;
    }
    
    // this function returns the correct item type corresponding to the colour of our block;
    // i.e. when a sign is broken, it will drop the correct item.
    @Override
    public int damageDropped(IBlockState state)
    {
      //EnumColour enumColour = (EnumColour)state.getValue(PROPERTYCOLOUR);
      //return this.getMetadata();//enumColour.getMetadata();
    	return 0;
    }
    

    
    
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos.north()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (worldIn.getBlockState(pos.south()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (worldIn.getBlockState(pos.west()).getBlock() == this)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (worldIn.getBlockState(pos.east()).getBlock() == this)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        //this.checkForSurroundingChests(worldIn, pos, state);
        Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

        while (iterator.hasNext())
        {
            EnumFacing enumfacing = (EnumFacing)iterator.next();
            BlockPos blockpos1 = pos.offset(enumfacing);
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

        }
    }
@Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase player)
    {
        return this.getDefaultState().withProperty(FACING, player.getHorizontalFacing());
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3).getOpposite();
        state = state.withProperty(FACING, enumfacing);
        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = this == worldIn.getBlockState(blockpos1).getBlock();
        boolean flag1 = this == worldIn.getBlockState(blockpos2).getBlock();
        boolean flag2 = this == worldIn.getBlockState(blockpos3).getBlock();
        boolean flag3 = this == worldIn.getBlockState(blockpos4).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3)
        {
            worldIn.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.X && (flag || flag1))
        {
            if (flag)
            {
                worldIn.setBlockState(blockpos1, state, 3);
            }
            else
            {
                worldIn.setBlockState(blockpos2, state, 3);
            }

            worldIn.setBlockState(pos, state, 3);
        }
        else if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3))
        {
            if (flag2)
            {
                worldIn.setBlockState(blockpos3, state, 3);
            }
            else
            {
                worldIn.setBlockState(blockpos4, state, 3);
            }

            worldIn.setBlockState(pos, state, 3);
        }

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityChest)
            {
                ((TileEntityChest)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }
    
    public IBlockState correctFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        EnumFacing enumfacing = null;
        Iterator iterator = EnumFacing.Plane.HORIZONTAL.iterator();

        while (iterator.hasNext())
        {
            EnumFacing enumfacing1 = (EnumFacing)iterator.next();
            IBlockState iblockstate1 = worldIn.getBlockState(pos.offset(enumfacing1));

            if (iblockstate1.getBlock() == this)
            {
                return state;
            }

            if (iblockstate1.getBlock().isFullBlock())
            {
                if (enumfacing != null)
                {
                    enumfacing = null;
                    break;
                }

                enumfacing = enumfacing1;
            }
        }

        if (enumfacing != null)
        {
            return state.withProperty(FACING, enumfacing.getOpposite());
        }
        else
        {
            EnumFacing enumfacing2 = (EnumFacing)state.getValue(FACING);

            if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
            {
                enumfacing2 = enumfacing2.getOpposite();
            }

            if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
            {
                enumfacing2 = enumfacing2.rotateY();
            }

            if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock().isFullBlock())
            {
                enumfacing2 = enumfacing2.getOpposite();
            }

            return state.withProperty(FACING, enumfacing2);
        }
    }





    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.breakBlock(worldIn, pos, state);
    }
    
   /* 
	// This is where you can do something when the block is broken. In this case drop the inventory's contents
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		IInventory inventory = worldIn.getTileEntity(pos) instanceof IInventory ? (IInventory)worldIn.getTileEntity(pos) : null;

		if (inventory != null){
			// For each slot in the inventory
			for (int i = 0; i < inventory.getSizeInventory(); i++){
				// If the slot is not empty
				if (inventory.getStackInSlot(i) != null)
				{
					// Create a new entity item with the item stack in the slot
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));

					// Apply some random motion to the item
					float multiplier = 0.1f;
					float motionX = worldIn.rand.nextFloat() - 0.5f;
					float motionY = worldIn.rand.nextFloat() - 0.5f;
					float motionZ = worldIn.rand.nextFloat() - 0.5f;

					item.motionX = motionX * multiplier;
					item.motionY = motionY * multiplier;
					item.motionZ = motionZ * multiplier;

					// Spawn the item in the world
					worldIn.spawnEntityInWorld(item);
				}
			}

			// Clear the inventory so nothing else (such as another mod) can do anything with the items
			inventory.clear();
		}

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}*/



	// Called when the block is right clicked
	// In this block it is used to open the blocks gui when right clicked by a player
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Uses the gui handler registered to your mod to open the gui for the given gui id
		// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;
 

		playerIn.openGui(ShelvesMod.instance, GuiHandlerShelf.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
	   	System.out.println("Called method: BlockShelf.onBlockActivated");
		return true;
	}
@Override
   public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new ShelfTitleEntity();
    }



    private boolean isBlocked(World worldIn, BlockPos pos)
    {
        return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
    }

    private boolean isBelowSolidBlock(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.up()).getBlock().isNormalCube();
    }




    private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos)
    {
        Iterator iterator = worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            Entity entity = (Entity)iterator.next();
            entityocelot = (EntityOcelot)entity;
        }
        while (!entityocelot.isSitting());

        return true;
    }

    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
      EnumFacing facing = (EnumFacing)state.getValue(FACING);

      int facingbits = facing.getHorizontalIndex();
      return facingbits;// | colourbits;
    }

    // necessary to define which properties your blocks use
    // will also affect the variants listed in the blockstates model file
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING,SECTION});
    }

    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
    public static enum EnumShape implements IStringSerializable
    {
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

        private final String name;

        private static final String __OBFID = "CL_00003061";

        private EnumShape(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }

    }
    public TileEntity createTileEntity(World world, int metadata)
    {
       return new ShelfTitleEntity();
    }
    
    @Override
    public int getRenderType()
    {
        return 3;
    }
    
}