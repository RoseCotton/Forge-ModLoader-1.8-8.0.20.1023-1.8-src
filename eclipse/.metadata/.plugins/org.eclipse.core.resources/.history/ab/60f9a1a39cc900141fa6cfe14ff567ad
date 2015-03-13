package com.rosecotton.shelvesmod;

import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import com.google.common.primitives.SignedBytes;

public class TileShelfRender extends TileEntitySpecialRenderer {
    private RenderEntityItem itemRender;
    TileShelfRender() 
    {
        itemRender = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(),Minecraft.getMinecraft().getRenderItem()) ;
       /* {
            @Override
            public int func_177078_a(ItemStack stack) {
                return SignedBytes.saturatedCast(Math.min(stack.stackSize /32,15) +1);
            }
            @Override
            public boolean shouldBob() {return false;}
            @Override
            public boolean shouldSpreadItems() {return false;}
        };*/
    }
    public void render(ShelfTitleEntity te, double x, double y, double z, float par6, int par7) {
    	//BlockPos blockPos;
    	BlockPos blockPos = te.getPos();
    	IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
        for(int i = 0; i < 4; ++i) {
            if(te.getStackInSlot(i) != null) {

                EntityItem customItem = new EntityItem(te.getWorld());
                customItem.hoverStart = 0.0F;
                customItem.setEntityItemStack(te.getStackInSlot(i));
                GlStateManager.pushMatrix();
                int j = ((EnumFacing)myBlockState.getValue(BlockBed.FACING)).getHorizontalIndex();
                GlStateManager.rotate((float)(j * 90), 0.0F, 1.0F, 0.0F);  //I'M GUESSING j IS GOING TO BE 0, 1, 2, OR 3	
                double xAdd;
		        double yAdd;
		        double zAdd;
                if ( j == 0)
                {
                    xAdd = x + (double)(13 - (i/2)*8)/16;
    		        yAdd = y + (double)(2 + (i/2)*6)/16-1.0d;
    		        zAdd = z + (double)(4 +(i%2)*8)/16;
                }
                else if (j == 1)
                {
                    xAdd = x + (double)(13 - (i/2)*8)/16;
    		        yAdd = y + (double)(2 + (i/2)*6)/16-1.0d;
    		        zAdd = z + (double)(4 +(i%2)*8)/16;
                }
                else if (j == 2)
                {
                    xAdd = x + (double)(13 - (i/2)*8)/16;
    		        yAdd = y + (double)(2 + (i/2)*6)/16-1.0d;
    		        zAdd = z + (double)(4 +(i%2)*8)/16;
                }
                else
                {
                    xAdd = x + (double)(13 - (i/2)*8)/16;
    		        yAdd = y + (double)(2 + (i/2)*6)/16-1.0d;
    		        zAdd = z + (double)(4 +(i%2)*8)/16;
                }
                //double xAdd = x + (double)(13 - (i/2)*8)/16;
		        //double yAdd = y + (double)(2 + (i/2)*6)/16-1.0d;
		        //double zAdd = z + (double)(4 +(i%2)*8)/16;
                GlStateManager.translate((float)xAdd,(float)yAdd,(float)zAdd);
                GlStateManager.translate(0.5F,0.7F,0.5F);
                itemRender.doRender(customItem,0,0,0,0,0);
                GlStateManager.scale(0.7F,0.7F,0.7F);
                GlStateManager.popMatrix();
            }
        }
    }
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
        render((ShelfTitleEntity)te,x,y,z,p_180535_8_,p_180535_9_);
    }
}
