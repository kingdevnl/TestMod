package me.kingzgames.testmod.proxy;

import me.kingzgames.testmod.init.ModBlocks;
import me.kingzgames.testmod.init.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent e) {
        super.onPreInit(e);
        ModItems.registerRenders();
        ModBlocks.registerRenders();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onInit(FMLInitializationEvent e) {
        super.onInit(e);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent e) {
        super.onPostInit(e);
    }

    @SubscribeEvent
    public void onModelReg(ModelRegistryEvent e) {
        System.out.println("ClientProxy.onModelReg");
    }


}
