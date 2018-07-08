package nl.kingdev.testmod.event;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import nl.kingdev.testmod.init.ModBlocks;
import nl.kingdev.testmod.tileentity.TileEntityTest;

import java.awt.*;

public class TestModEventHandler {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent e) {

        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            BlockPos blockPos = Minecraft.getMinecraft().objectMouseOver.getBlockPos();
            Block block = Minecraft.getMinecraft().player.getEntityWorld().getBlockState(blockPos).getBlock();

            if (block == ModBlocks.testBlock) {
                IBlockState state = Minecraft.getMinecraft().player.world.getBlockState(blockPos);
                Minecraft.getMinecraft().player.world.notifyBlockUpdate(blockPos,state, state, 3);

                TileEntity te = Minecraft.getMinecraft().player.getEntityWorld().getTileEntity(blockPos);
                if (te != null && te instanceof TileEntityTest) {
                    TileEntityTest tileEntityTest = (TileEntityTest) te;
                    GlStateManager.pushMatrix();
                    {

                        Minecraft.getMinecraft().fontRenderer.drawString("Count: " + tileEntityTest.getDiamondCount(), 20, 20, new Color(28, 84, 101).getRGB());
                    }
                    GlStateManager.popMatrix();

                }

            }
        }


    }

}
