package moe.taswell.rebirthutils.nms.v1_20_2.manager;

import moe.taswell.rebirthutils.nms.api.manager.NbtManager;
import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;
import moe.taswell.rebirthutils.nms.v1_20_2.nbt.PackagedCompoundTagImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import org.bukkit.craftbukkit.v1_20_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.*;

public class NbtManagerImpl implements NbtManager {
    @Override
    public PackagedCompoundTag wrapNewNbt(Object nmsNbt) {
        return new PackagedCompoundTagImpl(((CompoundTag) nmsNbt));
    }

    @Override
    public PackagedCompoundTag createNew() {
        return new PackagedCompoundTagImpl(new CompoundTag());
    }

    @Override
    public PackagedCompoundTag read(DataInput bytes) throws IOException {
        return new PackagedCompoundTagImpl(NbtIo.read(bytes));
    }

    @Override
    public PackagedCompoundTag readCompressed(DataInput bytes) throws IOException {
        return new PackagedCompoundTagImpl(NbtIo.readCompressed((InputStream) bytes));
    }

    @Override
    public void writeUnnamed(PackagedCompoundTag tag, DataOutput output) throws IOException {
        NbtIo.writeUnnamedTag(((PackagedCompoundTagImpl) tag).internal,output);
    }

    @Override
    public void write(PackagedCompoundTag tag, DataOutput output) throws IOException {
        NbtIo.write(((PackagedCompoundTagImpl) tag).internal,output);
    }

    @Override
    public void writeAnyTag(PackagedCompoundTag tag, DataOutput output) throws IOException {
        NbtIo.writeAnyTag(((PackagedCompoundTagImpl) tag).internal,output);
    }

    @Override
    public void writeCompressed(PackagedCompoundTag tag, DataOutput output) throws IOException {
        NbtIo.writeCompressed(((PackagedCompoundTagImpl) tag).internal, ((OutputStream) output));
    }

    @Override
    public PackagedCompoundTag getTagOfItem(ItemStack itemStack) {
        return new PackagedCompoundTagImpl(((CraftItemStack) itemStack).handle.getTag());
    }

    @Override
    public void setTagOfItem(ItemStack itemStack, PackagedCompoundTag tag) {
        ((CraftItemStack) itemStack).handle.setTag(((PackagedCompoundTagImpl) tag).internal);
    }
}
