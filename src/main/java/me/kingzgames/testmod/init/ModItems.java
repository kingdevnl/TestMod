package me.kingzgames.testmod.init;

import me.kingzgames.testmod.items.TestItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ModItems {

    private static ArrayList<Item> modItems = new ArrayList<>();

    public static TestItem testItem = new TestItem();

    public static void registerRenders() {
        for (Item i : getModItems()) {
            registerRender(i);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
    }

    public static ArrayList<Item> getModItems() {
        return modItems;
    }
}
