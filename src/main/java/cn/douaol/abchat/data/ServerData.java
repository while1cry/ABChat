package cn.douaol.abchat.data;

import org.bukkit.Bukkit;

public class ServerData {
    public static boolean globalMute = false;
    public static boolean hasPlaceholderAPI = false;
    public static void checkServer() {
        hasPlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }
}