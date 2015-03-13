package com.rosecotton.shelvesmod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import org.lwjgl.opengl.GL11;

public class TileShelfRender extends TileEntitySpecialRenderer {
	// private RenderEntityItem itemRender;
	private RenderManager renderManager;
	private RenderItem renderItem;
	public int jLast = 0;
	
	public TileShelfRender(RenderManager rm, RenderItem ri) {
		renderManager = rm;
		renderItem = ri;
		// itemRender = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem());
		/* {
		 * @Override public int func_177078_a(ItemStack stack) { return
		 * SignedBytes.saturatedCast(Math.min(stack.stackSize /32,15) +1); }
		 * @Override public boolean shouldBob() {return false;}
		 * @Override public boolean shouldSpreadItems() {return false;} }; */
		
	}
	
	public void render(ShelfTitleEntity te, double x, double y, double z, float par6, int par7) {
		// BlockPos blockPos;
		BlockPos blockPos = te.getPos();
		IBlockState myBlockState = this.getWorld().getBlockState(blockPos);
		int facing = ((EnumFacing)myBlockState.getValue(BlockShelf.FACING)).getHorizontalIndex();
		for (int i = 0; i < 4; ++ i) {
			if (te.getStackInSlot(i) != null) {
				
				GL11.glPushMatrix();
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslated(x, y, z);
				
				GL11.glPushMatrix();
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glTranslatef(1.5F, 0.3F, 0.5F); // Y
				GL11.glRotatef(0F, 0F, 1F, 0F);
				GL11.glTranslatef(-0.5F, 0F, 0.5F); // X & Z
				GL11.glRotatef(0F, 0F, 1F, 0F);
				GL11.glTranslated(0D, 0D, 0F);
				
				EntityItem customItem = new EntityItem(te.getWorld(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), te.getStackInSlot(i));
				customItem.hoverStart = 0.0F;
				int j = ((EnumFacing) myBlockState.getValue(BlockShelf.FACING)).getHorizontalIndex();
				
				customItem.posX = te.getPos().getX();
				customItem.posY = te.getPos().getY() + 2;
				customItem.posZ = te.getPos().getZ();
				GL11.glScalef(2F, 2F, 2F);
				if (facing == 0) {
					if (i == 0) GL11.glTranslatef(-0.25F, 0.375F, -0.15F);
					if (i == 1) GL11.glTranslatef(0.25F, 0.375F, -0.15F);
					if (i == 2) GL11.glTranslatef(-0.25F, -0.125F, 0.3F);
					if (i == 3) GL11.glTranslatef(0.25F, -0.125F, 0.3F);
				} else if (facing == 1) {
					if (i == 0) GL11.glTranslatef(0.15F, 0.375F, -0.25F);
					if (i == 1) GL11.glTranslatef(0.15F, 0.375F, 0.25F);
					if (i == 2) GL11.glTranslatef(-0.3F, -0.125F, -0.25F);
					if (i == 3) GL11.glTranslatef(-0.3F, -0.125F, 0.25F);
				} else if (facing == 2) {
					if (i == 0) GL11.glTranslatef(0.25F, 0.375F, 0.15F);
					if (i == 1) GL11.glTranslatef(-0.25F, 0.375F, 0.15F);
					if (i == 2) GL11.glTranslatef(0.25F, -0.125F, -0.3F);
					if (i == 3) GL11.glTranslatef(-0.25F, -0.125F, -0.3F);
				} else if (facing == 3) {
					if (i == 0) GL11.glTranslatef(-0.15F, 0.375F, 0.25F);
					if (i == 1) GL11.glTranslatef(-0.15F, 0.375F, -0.25F);
					if (i == 2) GL11.glTranslatef(0.3F, -0.125F, 0.25F);
					if (i == 3) GL11.glTranslatef(0.3F, -0.125F, -0.25F);
				} else {
					System.out.println(facing); // This probably shouldn't happen
				}
				//int j = ((EnumFacing)myBlockState.getValue(BlockShelf.FACING)).getHorizontalIndex();
				GlStateManager.rotate((float)(j * 90), 0.0F, 1.0F, 0.0F);  //I'M GUESSING j IS GOING TO BE 0, 1, 2, OR 3	
				renderManager.doRenderEntity(customItem, 0, 0, 0, 0, 0, false);
				
				GL11.glPopMatrix();
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glPushMatrix();
				GL11.glTranslatef(0.5F, 1.8F, 0.5F);
				GL11.glRotatef(180F, 1F, 0F, 1F);
				GL11.glPopMatrix();
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glPopMatrix();
			}
		}
	}
	
	@Override public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
		render((ShelfTitleEntity) te, x, y, z, p_180535_8_, p_180535_9_);
	}
}