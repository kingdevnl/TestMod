package nl.kingdev.testmod;

import nl.kingdev.testmod.init.ModBlocks;
import nl.kingdev.testmod.init.ModItems;
import nl.kingdev.testmod.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = TestMod.modName, modid = TestMod.modID, version = TestMod.modVersion)
public class TestMod {

    //Reference
    public static final String modID = "testmod";
    public static final String modName = "TestMod";
    public static final String modVersion = "0.1";


    @SidedProxy(
            modId = TestMod.modID,
            clientSide = "nl.kingdev.testmod.proxy.ClientProxy",
            serverSide = "nl.kingdev.testmod.proxy.ServerProxy")
    private static CommonProxy proxy;


    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new TestModEventHandler());
        proxy.onPreInit(e);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {

        proxy.onInit(e);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent e) {

        proxy.onPostInit(e);
    }


    @Mod.EventBusSubscriber
    public class TestModEventHandler {

        @SubscribeEvent
        public void onItemReg(RegistryEvent.Register<Item> event) {
            for (Item item : ModItems.getModItems()) {
                event.getRegistry().register(item);
            }
            for (Block b : ModBlocks.getModBlocks()) {
                event.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
            }
        }

        @SubscribeEvent
        public void onBlockReg(RegistryEvent.Register<Block> event) {
            event.getRegistry().register(ModBlocks.testBlock);

//
//            for (Block block : ModBlocks.getModBlocks()) {
//                System.out.println("TestModEventHandler.onBlockReg " + block.getRegistryName());
//                event.getRegistry().register(block);
//            }
        }

    }


}
