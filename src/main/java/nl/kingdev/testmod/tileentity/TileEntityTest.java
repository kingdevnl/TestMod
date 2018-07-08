package nl.kingdev.testmod.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTest extends TileEntity {

    private int diamondCount = 0;

    public boolean addDiamond() {

        if(diamondCount < 8) {
            diamondCount++;
            return true;
        }
        return false;

    }
    public void removeDiamond() {
        if(diamondCount > 0) {
            diamondCount--;
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), new ItemStack(Items.DIAMOND)));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);
        compound.setInteger("diamondCount", diamondCount);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        diamondCount = compound.getInteger("diamondCount");
    }
}
