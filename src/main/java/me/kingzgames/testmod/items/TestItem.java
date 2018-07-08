package me.kingzgames.testmod.items;

import me.kingzgames.testmod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TestItem extends Item {

    public TestItem() {
        setRegistryName("testitem");
        setUnlocalizedName("testitem");
        setCreativeTab(CreativeTabs.MISC);
        ModItems.getModItems().add(this);
    }


}
