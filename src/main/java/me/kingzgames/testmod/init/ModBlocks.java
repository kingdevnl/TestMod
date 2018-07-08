package me.kingzgames.testmod.init;

import me.kingzgames.testmod.blocks.TestBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ModBlocks {


    private static ArrayList<Block> modBlocks = new ArrayList<>();

    public static TestBlock testBlock = new TestBlock();


    public static void registerRenders() {
        for (Block i : getModBlocks()) {
            registerRender(i);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block i) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(i), 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
    }

    public static ArrayList<Block> getModBlocks() {
        return modBlocks;
    }
}
