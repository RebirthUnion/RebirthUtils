package moe.taswell.rebirthutils.nms.api.buffer;


import io.netty.buffer.ByteBuf;

public interface PackagedFriendlyByteBuf{
    void writeUtf(String string);

    String readUtf(String string);

    void writeNbt(Object nbt);

    Object readNbt();

    byte[] readByteArray();

    void writeByteArray(byte[] bytes);

    Object readItemStack();

    void writeItemStack(Object itemStack);

    int readVarInt();

    void writeVarInt(int i);

    long readVarLong();

    void writeVarLong(long l);

    ByteBuf getAsNettyBuffer();
}