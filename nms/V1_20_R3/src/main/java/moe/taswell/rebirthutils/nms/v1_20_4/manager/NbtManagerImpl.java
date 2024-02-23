package moe.taswell.rebirthutils.nms.v1_20_4.manager;

import moe.taswell.rebirthutils.nms.api.manager.NbtManager;
import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;
import moe.taswell.rebirthutils.nms.v1_20_4.nbt.PackagedCompoundTagImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

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
        return new PackagedCompoundTagImpl(NbtIo.readCompressed(bytes));
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
}
