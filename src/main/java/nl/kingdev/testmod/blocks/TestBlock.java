package nl.kingdev.testmod.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.kingdev.testmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import nl.kingdev.testmod.tileentity.TileEntityTest;

import javax.annotation.Nullable;

public class TestBlock extends Block implements ITileEntityProvider {

    public TestBlock() {
        super(Material.ROCK);
        setRegistryName("testblock");
        setUnlocalizedName("testblock");
        setCreativeTab(CreativeTabs.MISC);
        ModBlocks.getModBlocks().add(this);
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileEntityTest) {
            TileEntityTest tileEntityTest = (TileEntityTest) tileEntity;
            ItemStack heldItem = playerIn.getHeldItem(hand);
            if(playerIn.isSneaking()) {
                tileEntityTest.removeDiamond();
            }

            if(heldItem.getItem() == Items.DIAMOND) {
                if(tileEntityTest.addDiamond()) {
                    heldItem.shrink(1);
                    return true;
                }
            }

        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEntityTest();
    }
}
