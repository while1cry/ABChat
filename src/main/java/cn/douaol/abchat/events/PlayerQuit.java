package cn.douaol.abchat.events;

import cn.douaol.abchat.Main;
import cn.douaol.abchat.data.ChatFormat;
import cn.douaol.abchat.data.PlayerData;
import cn.douaol.abchat.data.ServerData;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Locale;
import java.util.Objects;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerData.removePlayer(e.getPlayer());
        if (ChatFormat.quitMessage) {
            for (String qm : ChatFormat.qm) {
                String group = qm.split(": ")[0];
                String group2 = Main.chat.getPrimaryGroup(e.getPlayer());
                if (Objects.equals(group2.toLowerCase(Locale.ROOT), group.toLowerCase(Locale.ROOT))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(qm.split(": ")[1]);
                    for (int i = 2; i < qm.split(": ").length; i++) {
                        sb.append(": ").append(qm.split(": ")[i]);
                    }
                    String message = sb.toString();
                    message = message.replaceAll("<PREFIX>", Main.chat.getPlayerPrefix(e.getPlayer()));
                    message = message.replaceAll("<PLAYER>", e.getPlayer().getName());
                    message = message.replaceAll("<SUFFIX>", Main.chat.getPlayerSuffix(e.getPlayer()));
                    if (ServerData.hasPlaceholderAPI) {
                        message = PlaceholderAPI.setPlaceholders(e.getPlayer(), message);
                    }
                    message = message.replaceAll("&", "§");
                    e.setQuitMessage(message);
                }
            }
        }
    }
}
