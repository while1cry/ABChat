package cn.douaol.abchat.data;

import org.bukkit.Bukkit;

public class ServerData {
    public static boolean globalMute = false;
    public static boolean hasPlaceholderAPI = false;
    public static boolean hasVault = false;
    public static boolean hasTextComponent = false;
    public static void checkServer() {
        hasPlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        hasVault = Bukkit.getPluginManager().getPlugin("Vault") != null;
        try {
            Class.forName("net.md_5.bungee.chat.TextComponentSerializer");
            Bukkit.getConsoleSender().sendMessage(Config.prefix + "§aFound package net.md_5.bungee.chat! regex is available!");
            hasTextComponent = true;
        } catch (ClassNotFoundException ignore) {
            Bukkit.getConsoleSender().sendMessage(Config.prefix + "§cCannot find package net.md_5.bungee.chat! Json Components is not available!");
        }
    }
}