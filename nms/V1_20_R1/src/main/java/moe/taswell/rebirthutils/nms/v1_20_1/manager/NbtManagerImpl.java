package moe.taswell.rebirthutils.nms.v1_20_1.manager;

import moe.taswell.rebirthutils.nms.api.manager.NbtManager;
import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;
import moe.taswell.rebirthutils.nms.v1_20_1.nbt.PackagedCompoundTagImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.item.Items;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.lang.reflect.Constructor;

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
        return new PackagedCompoundTagImpl(NbtIo.readCompressed(((InputStream) bytes)));
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
        NbtIo.write(((PackagedCompoundTagImpl) tag).internal,output); //There is no such method in 1.20.1,so call write tag anyway
    }

    @Override
    public void writeCompressed(PackagedCompoundTag tag, DataOutput output) throws IOException {
        NbtIo.writeCompressed(((PackagedCompoundTagImpl) tag).internal, ((OutputStream) output));
    }

    @Override
    public PackagedCompoundTag getTagOfItem(ItemStack itemStack) {
        final CompoundTag got = CraftItemStack.unwrap(itemStack).getTag();

        if (got == null){
            return null;
        }

        return new PackagedCompoundTagImpl(got);
    }

    @Override
    public void setTagOfItem(ItemStack itemStack, PackagedCompoundTag tag) {
        CraftItemStack.unwrap(itemStack).setTag(((PackagedCompoundTagImpl) tag).internal);
    }

    @Override
    public Object toNms(ItemStack bukkit){
        return CraftItemStack.unwrap(bukkit);
    }

    @Override
    public ItemStack fromNms(Object nms){
        return CraftItemStack.asCraftMirror((net.minecraft.world.item.ItemStack) nms);
    }
}
