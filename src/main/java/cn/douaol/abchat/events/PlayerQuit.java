package cn.douaol.abchat.events;

import cn.douaol.abchat.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        PlayerData.removePlayer(e.getPlayer());
    }
}
