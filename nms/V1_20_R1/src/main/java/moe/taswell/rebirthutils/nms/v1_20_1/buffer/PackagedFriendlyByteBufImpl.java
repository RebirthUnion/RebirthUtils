package moe.taswell.rebirthutils.nms.v1_20_1.buffer;

import io.netty.buffer.ByteBuf;
import moe.taswell.rebirthutils.nms.api.buffer.PackagedFriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Field;

public class PackagedFriendlyByteBufImpl implements PackagedFriendlyByteBuf {
    private final FriendlyByteBuf internal;

    public PackagedFriendlyByteBufImpl(FriendlyByteBuf internal) {
        this.internal = internal;
    }

    @Override
    public void writeUtf(String string) {
        this.internal.writeUtf(string);
    }

    @Override
    public String readUtf(String string) {
        return this.internal.readUtf();
    }

    @Override
    public void writeNbt(Object nbt) {
        this.internal.writeNbt((CompoundTag) nbt);
    }

    @Override
    public Object readNbt() {
        return this.internal.readNbt();
    }

    @Override
    public byte[] readByteArray() {
        return this.internal.readByteArray();
    }

    @Override
    public void writeByteArray(byte[] bytes) {
        this.internal.writeByteArray(bytes);
    }

    @Override
    public Object readItemStack() {
        return this.internal.readItem();
    }

    @Override
    public void writeItemStack(Object itemStack) {
        this.internal.writeItem((ItemStack) itemStack);
    }

    @Override
    public int readVarInt() {
        return this.internal.readVarInt();
    }

    @Override
    public void writeVarInt(int i) {
        this.internal.writeVarInt(i);
    }

    @Override
    public long readVarLong() {
        return this.internal.readVarLong();
    }

    @Override
    public void writeVarLong(long l) {
        this.internal.writeVarLong(l);
    }

    @Override
    public ByteBuf getAsNettyBuffer() {
        try {
            for (Field field : this.internal.getClass().getDeclaredFields()){
                field.setAccessible(true);

                final Class<?> type = field.getType();
                if (type.equals(ByteBuf.class)){
                    return (ByteBuf) field.get(this.internal);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return null;
    }
}