package cn.douaol.abchat.events;

import cn.douaol.abchat.Main;
import cn.douaol.abchat.data.ChatFormat;
import cn.douaol.abchat.data.Config;
import cn.douaol.abchat.data.Message;
import cn.douaol.abchat.data.ServerData;
import cn.douaol.abchat.libs.ChatLib;
import cn.douaol.abchat.libs.Emote;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AsyncPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        if (ServerData.globalMute && !e.getPlayer().hasPermission("abchat.bypass.globalmute")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatLib.translateMessage(e.getPlayer(), Message.blockGlobalMute));
            return;
        }
        if (ChatLib.needBlock(e.getPlayer(), e.getMessage())) {
            e.setCancelled(true);
            return;
        }
        if (Config.emote) {
            e.setMessage(Emote.translateEmote(e.getPlayer(), e.getMessage()));
        }
        if (ChatFormat.formatChat) {
            if (!ServerData.hasVault) {
                return;
            }
            e.setCancelled(true);
            List<String> format = ChatFormat.format;
            for (String fs : format) {
                String group = fs.split(": ")[0];
                String group2 = Main.getChat().getPrimaryGroup(e.getPlayer());
                if (Objects.equals(group2.toLowerCase(Locale.ROOT), group.toLowerCase(Locale.ROOT))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(fs.split(": ")[1]);
                    for (int i = 2; i < fs.split(": ").length; i++) {
                        sb.append(": ").append(fs.split(": ")[i]);
                    }
                    String message = sb.toString();
                    message = message.replaceAll("<PREFIX>", Main.getChat().getPlayerPrefix(e.getPlayer()));
                    message = message.replaceAll("<PLAYER>", e.getPlayer().getName());
                    message = message.replaceAll("<SUFFIX>", Main.getChat().getPlayerSuffix(e.getPlayer()));
                    if (ServerData.hasPlaceholderAPI) {
                        message = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    }
                    message = message.replaceAll("&", "§");
                    message = message.replaceAll("<MESSAGE>", e.getMessage());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(message);
                    }
                    Bukkit.getConsoleSender().sendMessage(message);
                }
            }
        }
    }
}
