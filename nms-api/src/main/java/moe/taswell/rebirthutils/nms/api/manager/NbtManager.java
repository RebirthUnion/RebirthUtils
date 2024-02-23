package moe.taswell.rebirthutils.nms.api.manager;

import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface NbtManager {
    PackagedCompoundTag wrapNewNbt(Object nmsNbt);

    PackagedCompoundTag createNew();

    PackagedCompoundTag read(DataInput bytes) throws IOException;

    PackagedCompoundTag readCompressed(DataInput bytes) throws IOException;

    void writeUnnamed(PackagedCompoundTag tag,DataOutput output) throws IOException;

    void write(PackagedCompoundTag tag,DataOutput output) throws IOException;

    void writeAnyTag(PackagedCompoundTag tag,DataOutput output) throws IOException;

    void writeCompressed(PackagedCompoundTag tag, DataOutput output) throws IOException;
}
