package me.notkronos.meowhack.module.misc;

import me.notkronos.meowhack.event.events.network.PacketEvent;
import me.notkronos.meowhack.mixin.mixins.accessor.ITextComponentStringAccessor;
import me.notkronos.meowhack.module.Category;
import me.notkronos.meowhack.module.Module;
import me.notkronos.meowhack.setting.Setting;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

import static me.notkronos.meowhack.module.client.HUD.time;

public class ChatTimestamp extends Module {
    public static ChatTimestamp INSTANCE;

    public ChatTimestamp() {
        super("ChatTimestamp", Category.MISC, "Adds timestamps before chat messages", new String[]{});
        INSTANCE = this;
        this.enabled = true;
        this.drawn = false;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.PacketReceiveEvent event) {
        // packet for server chat messages
        if (event.getPacket() instanceof SPacketChat) {

            // get the text
            if (((SPacketChat) event.getPacket()).getChatComponent() instanceof TextComponentString) {

                // the chat message
                TextComponentString component = (TextComponentString) ((SPacketChat) event.getPacket()).getChatComponent();

                // timestamp
                String formattedTime = "";
                formattedTime = new SimpleDateFormat("h:mm a").format(new Date());

                component.getText();// timestamp formatted
                StringBuilder formattedText = new StringBuilder();

                formattedText.append(TextFormatting.GRAY).append("[").append(formattedTime).append("] ").append(TextFormatting.RESET);

                formattedText.append(component.getText());

                // replace the chat message
                ((ITextComponentStringAccessor) component).setText(formattedText.toString());
            }
        }
    }
}
