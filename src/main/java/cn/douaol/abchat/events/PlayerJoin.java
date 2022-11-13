package cn.douaol.abchat.events;

import cn.douaol.abchat.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerData.addPlayer(e.getPlayer());
    }
}
