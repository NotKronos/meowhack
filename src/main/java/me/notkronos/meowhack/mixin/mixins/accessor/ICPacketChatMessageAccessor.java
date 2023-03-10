package me.notkronos.meowhack.mixin.mixins.accessor;

import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketChatMessage.class)
public interface ICPacketChatMessageAccessor {

    @Accessor("message")
    void setMessage(String message);
}
