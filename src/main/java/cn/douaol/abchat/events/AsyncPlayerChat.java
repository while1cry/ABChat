package cn.douaol.abchat.events;

import cn.douaol.abchat.data.ServerData;
import cn.douaol.abchat.libs.ChatLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        if(ServerData.globalMute) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cServer was global muted!");
            return;
        }
        if(ChatLib.needBlock(e.getPlayer(), e.getMessage())) {
            e.setCancelled(true);
        }
    }
}
