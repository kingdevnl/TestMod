package me.kingzgames.testmod.blocks;

import me.kingzgames.testmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TestBlock extends Block {

    public TestBlock() {
        super(Material.ROCK);
        setRegistryName("testblock");
        setUnlocalizedName("testblock");
        setCreativeTab(CreativeTabs.MISC);
        ModBlocks.getModBlocks().add(this);
    }
}
