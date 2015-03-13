package com.rosecotton.shelvesmod;


	
	import net.minecraft.client.Minecraft;
	import net.minecraft.client.gui.FontRenderer;
	import net.minecraft.client.renderer.BlockRendererDispatcher;
	import net.minecraft.client.renderer.GlStateManager;
	import net.minecraft.client.renderer.RenderHelper;
	import net.minecraft.client.renderer.Tessellator;
	import net.minecraft.client.renderer.WorldRenderer;
	import net.minecraft.client.renderer.entity.Render;
	import net.minecraft.client.renderer.entity.RenderItem;
	import net.minecraft.client.renderer.entity.RenderManager;
	import net.minecraft.client.renderer.texture.TextureAtlasSprite;
	import net.minecraft.client.renderer.texture.TextureCompass;
	import net.minecraft.client.renderer.texture.TextureMap;
	import net.minecraft.client.resources.model.IBakedModel;
	import net.minecraft.client.resources.model.ModelManager;
	import net.minecraft.client.resources.model.ModelResourceLocation;
	import net.minecraft.entity.Entity;
	import net.minecraft.entity.item.EntityItem;
	import net.minecraft.entity.item.EntityItemFrame;
	import net.minecraft.init.Items;
	import net.minecraft.item.Item;
	import net.minecraft.item.ItemSkull;
	import net.minecraft.item.ItemStack;
	import net.minecraft.util.BlockPos;
	import net.minecraft.util.MathHelper;
	import net.minecraft.util.ResourceLocation;
	import net.minecraft.world.storage.MapData;
	import net.minecraftforge.fml.relauncher.Side;
	import net.minecraftforge.fml.relauncher.SideOnly;
	import org.lwjgl.opengl.GL11;

	@SideOnly(Side.CLIENT)
	public class RenderShelfItems extends Render
	{
		private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
	    private final Minecraft mc = Minecraft.getMinecraft();
	    private final ModelResourceLocation itemFrameModel = new ModelResourceLocation("item_frame", "normal");
	    private final ModelResourceLocation mapModel = new ModelResourceLocation("item_frame", "map");
	    private RenderItem itemRenderer[] = new RenderItem[4];
	    //private static final String __OBFID = "CL_00001002";

	    public RenderShelfItems(RenderManager renderManagerIn, RenderItem renderItemIn[])
	    {
	        super(renderManagerIn);
	        renderManagerIn.entityRenderMap.put(ShelfTitleEntity.class, new RenderShelfItems(renderManager, null));
	        for (int i = 0; i<4; i++)
	        {
	        	this.itemRenderer[i] = renderItemIn[i];
	        }
	    }

	    public void doRender(ShelfTitleEntity entity, double x, double y, double z, float rotationMaybeIn, float partialTicks)
	    {
	        GlStateManager.pushMatrix();
	        BlockPos blockpos = entity.getPos();
	        double d3 = (double)blockpos.getX() - entity.getPos().getX() + x;
	        double d4 = (double)blockpos.getY() - entity.getPos().getY() + y;
	        double d5 = (double)blockpos.getZ() - entity.getPos().getZ() + z;
	        for(int i = 0; i<4; i++)
	        {
		        double d3Add = d3 + (double)(4 +(i%2)*8)/16;
		        double d4Add = d4 + (double)(2 + (i/2)*6)/16;
		        double d5Add = d5 + (double)(13 - (i/2)*8)/16;
		        //GlStateManager.translate(d3 + 0.5D, d4 + 0.5D, d5 + 0.5D);  OLD CODE
		        GlStateManager.translate(d3Add, d4Add, d5Add);
		        //GlStateManager.rotate(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		        //this.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		        BlockRendererDispatcher blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
		        ModelManager modelmanager = blockrendererdispatcher.getBlockModelShapes().getModelManager();
		        IBakedModel ibakedmodel;
	
		        //OLD CODE
		        /*if (entity.getStackInSlot(i) != null && entity.getStackInSlot(i).getItem() == Items.filled_map)
		        {
		            ibakedmodel = modelmanager.getModel(this.mapModel);
		        }
		        else
		        {
		            ibakedmodel = modelmanager.getModel(this.itemFrameModel);
		        }*/
		        if (entity.getStackInSlot(i) != null)
		        {
		        	ibakedmodel = modelmanager.getModel(new ModelResourceLocation(entity.getStackInSlot(i).getUnlocalizedName(),"inventory"));
		        }
		        else
		        {
		        	ibakedmodel = modelmanager.getMissingModel();
		        }
	
		        GlStateManager.pushMatrix();
		        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		        blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(ibakedmodel, 1.0F, 1.0F, 1.0F, 1.0F);
		        GlStateManager.popMatrix();
		        GlStateManager.translate(0.0F, 0.0F, 0.4375F);
		        this.renderItem(entity);
		        GlStateManager.popMatrix();
		        //OLD, OMG I CANNOT MAKE THIS WORK, field_174860_B IS AN ENUMFACING FACING DIRECTION VARIABLE THINGY AND I CAN'T SEEM TO GET IT...
		        //this.func_147914_a(entity, x + (double)((float)entity.field_174860_b.getFrontOffsetX() * 0.3F), y - 0.25D, z + (double)((float)entity.field_174860_b.getFrontOffsetZ() * 0.3F));
		        //this.func_147914_a(entity, x, y, z);  //SCREW IT, WE CAN TWEAK THE POSITIONING LATER
	    	}
	    }

	    protected ResourceLocation getEntityTexture(EntityItemFrame entity)
	    {
	        return null;
	    }

	    private void renderItem(ShelfTitleEntity shelfTitleEntityIn)
	    {
	        ItemStack itemstack[] = new ItemStack[4];
	        for (int i = 0; i<4; i++)
	        {
	        	itemstack[i] = shelfTitleEntityIn.getStackInSlot(i);
	        

		        if (itemstack[i] != null)
		        {
		            EntityItem entityitem = new EntityItem(shelfTitleEntityIn.getWorld(), 0.0D, 0.0D, 0.0D, itemstack[i]);
		            Item item = entityitem.getEntityItem().getItem();
		            
		            //DO I WANT THIS?  WILL IT SCREW UP THE NUMBER OF ITEMS I HAVE STORED IN EACH STACK OR JUST DISPLAY PROPERLY?
		            entityitem.getEntityItem().stackSize = 1;
		            entityitem.hoverStart = 0.0F;
		            GlStateManager.pushMatrix();
		            GlStateManager.disableLighting();
		            
		            //I DON'T DO ROTATION SO I THINK IT'S OK TO LEAVE THIS OUT
		            //int i = shelfTitleEntityIn.getRotation();
	
		            //if (item == Items.filled_map)
		            //{
		            //    i = i % 4 * 2;
		            //}
	
		            //GlStateManager.rotate((float)i * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
	
		            if (item == Items.filled_map)
		            {
		                this.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
		                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		                float f = 0.0078125F;
		                GlStateManager.scale(f, f, f);
		                GlStateManager.translate(-64.0F, -64.0F, 0.0F);
		                MapData mapdata = Items.filled_map.getMapData(entityitem.getEntityItem(), shelfTitleEntityIn.getWorld());
		                GlStateManager.translate(0.0F, 0.0F, -1.0F);
	
		                if (mapdata != null)
		                {
		                    this.mc.entityRenderer.getMapItemRenderer().func_148250_a(mapdata, true);
		                }
		            }
		            else
		            {
		                TextureAtlasSprite textureatlassprite = null;
	
		                if (item == Items.compass)
		                {
		                    textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite(TextureCompass.field_176608_l);
		                    this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
	
		                    if (textureatlassprite instanceof TextureCompass)
		                    {
		                        TextureCompass texturecompass = (TextureCompass)textureatlassprite;
		                        double d0 = texturecompass.currentAngle;
		                        double d1 = texturecompass.angleDelta;
		                        texturecompass.currentAngle = 0.0D;
		                        texturecompass.angleDelta = 0.0D;
		                        
		                        //SCREW COMPASS ACCURACY. THIS IS RIDICULOUS. I CANNOT FIGURE OUT HOW TO GET THE ENUMFACING ROTATION DATA FOR MY SHELFTILEENTITY
		                        //texturecompass.updateCompass(shelfTitleEntityIn.getWorld(), shelfTitleEntityIn.getPos().getX(), shelfTitleEntityIn.getPos().getZ(), (double)MathHelper.wrapAngleTo180_float((float)(180 + shelfTitleEntityIn.field_174860_b.getHorizontalIndex() * 90)), false, true);
		                        texturecompass.currentAngle = d0;
		                        texturecompass.angleDelta = d1;
		                    }
		                    else
		                    {
		                        textureatlassprite = null;
		                    }
		                }
	
		                GlStateManager.scale(0.5F, 0.5F, 0.5F);
	
		                /*if (!this.itemRenderer.shouldRenderItemIn3D(entityitem.getEntityItem()) || item instanceof ItemSkull)
		                {
		                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		                }*/
	
		                GlStateManager.pushAttrib();
		                RenderHelper.enableStandardItemLighting();
		                this.itemRenderer[i].renderItemModel(shelfTitleEntityIn.getStackInSlot(i));
		                RenderHelper.disableStandardItemLighting();
		                GlStateManager.popAttrib();
	
		                if (textureatlassprite != null && textureatlassprite.getFrameCount() > 0)
		                {
		                    textureatlassprite.updateAnimation();
		                }
		            }
	
		            GlStateManager.enableLighting();
		            GlStateManager.popMatrix();
		        }
	        }
	    }

	    //I REALLY DON'T KNOW WHAT THIS IS BUT IF IT'S FOR LABELS OR LIVING THINGS OR WHATEVER, LET'S SKIP IT
	    /*
	    protected void func_147914_a(ShelfTitleEntity shelfTitleEntityIn2, double p_147914_2_, double p_147914_4_, double p_147914_6_)
	    {
	    	for(int i = 0; i<4; i++)
	    	{
		        if (Minecraft.isGuiEnabled() && shelfTitleEntityIn2.getStackInSlot(i) != null && shelfTitleEntityIn2.getStackInSlot(i).hasDisplayName() && this.renderManager.pointedEntity == (shelfTitleEntityIn2.getStackInSlot(i)))
		        {
		            float f = 1.6F;
		            float f1 = 0.016666668F * f;
		            double d3 = shelfTitleEntityIn2.getDistanceSqToEntity(this.renderManager.livingPlayer);
		            float f2 = shelfTitleEntityIn2.isSneaking() ? 32.0F : 64.0F;
	
		            if (d3 < (double)(f2 * f2))
		            {
		                String s = shelfTitleEntityIn2.getStackInSlot(i).getDisplayName();
	
		                if (shelfTitleEntityIn2.isSneaking())
		                {
		                    FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
		                    GlStateManager.pushMatrix();
		                    GlStateManager.translate((float)p_147914_2_ + 0.0F, (float)p_147914_4_ + shelfTitleEntityIn2.height + 0.5F, (float)p_147914_6_);
		                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		                    GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		                    GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		                    GlStateManager.scale(-f1, -f1, f1);
		                    GlStateManager.disableLighting();
		                    GlStateManager.translate(0.0F, 0.25F / f1, 0.0F);
		                    GlStateManager.depthMask(false);
		                    GlStateManager.enableBlend();
		                    GlStateManager.blendFunc(770, 771);
		                    Tessellator tessellator = Tessellator.getInstance();
		                    WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		                    GlStateManager.disableTexture2D();
		                    worldrenderer.startDrawingQuads();
		                    int i = fontrenderer.getStringWidth(s) / 2;
		                    worldrenderer.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
		                    worldrenderer.addVertex((double)(-i - 1), -1.0D, 0.0D);
		                    worldrenderer.addVertex((double)(-i - 1), 8.0D, 0.0D);
		                    worldrenderer.addVertex((double)(i + 1), 8.0D, 0.0D);
		                    worldrenderer.addVertex((double)(i + 1), -1.0D, 0.0D);
		                    tessellator.draw();
		                    GlStateManager.enableTexture2D();
		                    GlStateManager.depthMask(true);
		                    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 553648127);
		                    GlStateManager.enableLighting();
		                    GlStateManager.disableBlend();
		                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		                    GlStateManager.popMatrix();
		                }
		                else
		                {
		                    this.renderLivingLabel(shelfTitleEntityIn2, s, p_147914_2_, p_147914_4_, p_147914_6_, 64);
		                }
		            }
		        }
	    	}
	    }*/

	    protected ResourceLocation getEntityTexture(Entity entity)
	    {
	        return this.getEntityTexture((EntityItemFrame)entity);
	    }

	    protected void renderName(Entity entity, double x, double y, double z)
	    {
	        //this.func_147914_a((EntityItemFrame)entity, x, y, z);
	    }

	    public void doRender(Entity entity, double x, double y, double z, float rotationMaybeIn, float partialTicks)
	    {
	        this.doRender((EntityItemFrame)entity, x, y, z, rotationMaybeIn, partialTicks);
	    }

}
