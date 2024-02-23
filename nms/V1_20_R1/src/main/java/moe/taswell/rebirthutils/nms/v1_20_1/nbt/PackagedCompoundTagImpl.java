package moe.taswell.rebirthutils.nms.v1_20_1.nbt;

import moe.taswell.rebirthutils.nms.api.nbt.PackagedCompoundTag;
import net.minecraft.nbt.CompoundTag;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class PackagedCompoundTagImpl implements PackagedCompoundTag {
    public final CompoundTag internal;

    public PackagedCompoundTagImpl(CompoundTag internal) {
        this.internal = internal;
    }

    @Override
    public Object getNMS() {
        return this.internal;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        this.internal.write(output);
    }

    @Override
    public int sizeInBytes() {
        return this.internal.sizeInBytes();
    }

    @Override
    public Set<String> getAllKeys() {
        return this.internal.getAllKeys();
    }

    @Override
    public boolean contains(String key) {
        return this.internal.contains(key);
    }

    @Override
    public boolean hasUUID(String key) {
        return this.internal.hasUUID(key);
    }

    @Override
    public int getSize() {
        return this.internal.size();
    }

    @Override
    public void putUUID(String key, UUID uuid) {
        this.internal.putUUID(key,uuid);
    }

    @Override
    public UUID getUUID(String key) {
        return this.internal.getUUID(key);
    }

    @Override
    public void putByte(String key, byte b) {
        this.internal.putByte(key,b);
    }

    @Override
    public byte getByte(String key) {
        return this.internal.getByte(key);
    }

    @Override
    public void putShort(String key, short s) {
        this.internal.putShort(key,s);
    }

    @Override
    public short getShort(String key) {
        return this.internal.getShort(key);
    }

    @Override
    public void putLong(String key, long l) {
        this.internal.putLong(key,l);
    }

    @Override
    public long getLong(String key) {
        return this.internal.getLong(key);
    }

    @Override
    public void putString(String key, String value) {
        this.internal.putString(key, value);
    }

    @Override
    public String getString(String key) {
        return this.internal.getString(key);
    }

    @Override
    public void putInt(String key, int value) {
        this.internal.putInt(key, value);
    }

    @Override
    public int getInt(String key) {
        return this.internal.getInt(key);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        this.internal.putBoolean(key,value);
    }

    @Override
    public boolean getBoolean(String key) {
        return this.internal.getBoolean(key);
    }

    @Override
    public void putByteArray(String key, byte[] bytes) {
        this.internal.putByteArray(key,bytes);
    }

    @Override
    public byte[] getByteArray(String key) {
        return this.internal.getByteArray(key);
    }

    @Override
    public void putLongArray(String key, long[] l) {
        this.internal.putLongArray(key,l);
    }

    @Override
    public long[] getLongArray(String key) {
        return this.internal.getLongArray(key);
    }

    @Override
    public void putIntArray(String key, int[] l) {
        this.internal.putIntArray(key,l);
    }

    @Override
    public int[] getIntArray(String key) {
        return this.internal.getIntArray(key);
    }

    @Override
    public void putCompoundTag(String key, PackagedCompoundTag tag) {
        this.internal.put(key, ((PackagedCompoundTagImpl) tag).internal);
    }

    @Override
    public PackagedCompoundTag getCompoundTag(String key) {
        return new PackagedCompoundTagImpl(this.internal.getCompound(key));
    }

    @Override
    public PackagedCompoundTag merge(PackagedCompoundTag source) {
        final CompoundTag cast = ((PackagedCompoundTagImpl) source).internal;
        return new PackagedCompoundTagImpl(this.internal.merge(cast));
    }

    @Override
    public boolean equals(Object in){
        if (in instanceof PackagedCompoundTag packagedCompoundTag){
            return ((PackagedCompoundTagImpl) packagedCompoundTag).internal == this.internal || ((PackagedCompoundTagImpl) packagedCompoundTag).internal.equals(this.internal);
        }

        return super.equals(in);
    }
}
