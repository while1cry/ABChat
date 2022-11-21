package cn.douaol.abchat.misc;

import cn.douaol.abchat.data.*;
import cn.douaol.abchat.libs.Emote;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Load {
    public static void load() throws IOException {
        Config.loadConfig();
        Filter.loadFilter();
        Message.loadMessage();
        Emote.loadEmotes();
        ChatFormat.loadFormat();
        loadPlayers();
    }
    public static void loadPlayers() {
        PlayerData.playerDataMap.clear();
        for(Player player : Bukkit.getOnlinePlayers()) {
            PlayerData.addPlayer(player);
        }
    }
}
