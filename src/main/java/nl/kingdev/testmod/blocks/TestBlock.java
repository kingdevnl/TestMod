package nl.kingdev.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nl.kingdev.testmod.init.ModBlocks;
import nl.kingdev.testmod.tileentity.TileEntityTest;

import javax.annotation.Nullable;

public class TestBlock extends Block {

    public TestBlock() {
        super(Material.CLOTH);
        setRegistryName("testblock");
        setUnlocalizedName("testblock");
        setCreativeTab(CreativeTabs.MISC);
        ModBlocks.getModBlocks().add(this);
        setHardness(0.5f);
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityTest) {
            TileEntityTest tileEntityTest = (TileEntityTest) tileEntity;
            ItemStack heldItem = playerIn.getHeldItem(hand);
            if (playerIn.isSneaking()) {
                tileEntityTest.removeDiamond();
                return true;
            }
            if (heldItem.getItem() == Items.AIR) {
                tileEntityTest.removeDiamond();
                return true;
            }

            if (heldItem.getItem() == Items.DIAMOND) {
                if (tileEntityTest.addDiamond()) {
                    heldItem.shrink(1);
                    return true;
                }
            }

        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }


    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        // If it will harvest, delay deletion of the block until after getDrops.
        return willHarvest || super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null && tileEntity instanceof TileEntityTest) {
            TileEntityTest tileEntityTest = (TileEntityTest) tileEntity;
            drops.add(tileEntityTest.getItem());
        }


    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }


    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTest();
    }
}
