package moe.taswell.rebirthutils.nms.api.nbt;

import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public interface PackagedCompoundTag {
    Object getNMS();

    void write(DataOutput output) throws IOException;

    int sizeInBytes();

    Set<String> getAllKeys();

    boolean contains(String key);

    boolean hasUUID(String key);

    int getSize();

    void putUUID(String key, UUID uuid);

    UUID getUUID(String key);

    void putByte(String key,byte b);

    byte getByte(String key);

    void putShort(String key,short s);

    short getShort(String key);

    void putLong(String key,long l);

    long getLong(String key);

    void putString(String key,String value);

    String getString(String key);

    void putInt(String key,int value);

    int getInt(String key);

    void putBoolean(String key,boolean value);

    boolean getBoolean(String key);

    void putByteArray(String key,byte[] bytes);

    byte[] getByteArray(String key);

    void putLongArray(String key,long[] l);

    long[] getLongArray(String key);

    void putIntArray(String key,int[] l);

    int[] getIntArray(String key);

    void putCompoundTag(String key,PackagedCompoundTag tag);

    PackagedCompoundTag getCompoundTag(String key);

    PackagedCompoundTag merge(PackagedCompoundTag source);
}
