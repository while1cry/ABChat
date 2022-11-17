package cn.douaol.abchat.events;

import cn.douaol.abchat.data.Config;
import cn.douaol.abchat.data.Message;
import cn.douaol.abchat.data.ServerData;
import cn.douaol.abchat.libs.ChatLib;
import cn.douaol.abchat.libs.Emote;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class AsyncPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) throws IOException {
        if(ServerData.globalMute && !e.getPlayer().hasPermission("abchat.bypass.globalmute")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatLib.translateMessage(e.getPlayer(), Message.blockGlobalMute));
            return;
        }
        if(ChatLib.needBlock(e.getPlayer(), e.getMessage())) {
            e.setCancelled(true);
        }
        if(! e.isCancelled() && Config.emote) {
            e.setMessage(Emote.translateEmote(e.getPlayer(), e.getMessage()));
        }
    }
}
