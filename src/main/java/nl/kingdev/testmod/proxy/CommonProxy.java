package nl.kingdev.testmod.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nl.kingdev.testmod.TestMod;
import nl.kingdev.testmod.tileentity.TileEntityTest;

public class CommonProxy {

    public void onPreInit(FMLPreInitializationEvent e) {

        GameRegistry.registerTileEntity(TileEntityTest.class, TestMod.modID+"TileEntityTest");
    }

    public void onInit(FMLInitializationEvent e) {

    }

    public void onPostInit(FMLPostInitializationEvent e) {

    }
}
