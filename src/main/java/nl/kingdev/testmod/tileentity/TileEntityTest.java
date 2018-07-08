package nl.kingdev.testmod.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import nl.kingdev.testmod.init.ModBlocks;

public class TileEntityTest extends TileEntity {

    public int diamondCount = 0;

    public boolean addDiamond() {

        if(diamondCount > 0 && diamondCount <= 5) {
            diamondCount++;
            this.markDirty();
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state,3);
            return true;
        }

        return false;

    }

    public void removeDiamond() {
        if (diamondCount > 0) {

            if (!world.isRemote) {
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1 + world.rand.nextInt(1), pos.getZ(), new ItemStack(Items.DIAMOND)));
                diamondCount--;
                markDirty();
                IBlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state,3);

            }

        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.writeUpdateTag(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.readUpdateTag(compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.getNbtCompound();
        readUpdateTag(compound);
    }


    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tag = super.getUpdateTag();

        writeUpdateTag(tag);

        return tag;
    }

    public ItemStack getItem() {
        ItemStack stack = new ItemStack(ModBlocks.testBlock);
        NBTTagCompound tileEntityData = new NBTTagCompound();
        writeToNBT(tileEntityData);
        tileEntityData.removeTag("x");
        tileEntityData.removeTag("y");
        tileEntityData.removeTag("z");

        NBTTagCompound stackTag = new NBTTagCompound();
        stackTag.setTag("BlockEntityTag", tileEntityData);
        stack.setTagCompound(stackTag);
        return stack;
    }

    public void writeUpdateTag(NBTTagCompound tag) {
        tag.setInteger("diamondCount", this.diamondCount);
        System.out.println("TileEntityTest.writeUpdateTag "+ this.diamondCount);
    }

    public void readUpdateTag(NBTTagCompound tag) {
        this.diamondCount = tag.getInteger("diamondCount");
        System.out.println("TileEntityTest.readUpdateTag " + this.diamondCount);
    }


    public String getDiamondCount() {

        return String.valueOf(diamondCount);
    }
}
