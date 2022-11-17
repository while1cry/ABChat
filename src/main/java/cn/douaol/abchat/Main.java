package cn.douaol.abchat;

import cn.douaol.abchat.commands.ABChat;
import cn.douaol.abchat.data.*;
import cn.douaol.abchat.events.AsyncPlayerChat;
import cn.douaol.abchat.events.PlayerJoin;
import cn.douaol.abchat.events.PlayerQuit;
import cn.douaol.abchat.libs.Emote;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {
    public static Main instance;
    private static Chat chat = null;
    private static Permission perms = null;

    public void onEnable() {
        instance = this;

        try {initPlugin();} catch (IOException e) {throw new RuntimeException(e);}

        if(ServerData.hasVault) {
            RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
            RegisteredServiceProvider<Chat> rsp2 = getServer().getServicesManager().getRegistration(Chat.class);
            perms = rsp.getProvider();
            chat = rsp2.getProvider();
        }
    }

    public static void initPlugin() throws IOException {
        Config.loadConfig();
        Filter.loadFilter();
        Message.loadMessage();
        Emote.loadEmotes();
        ChatFormat.loadFormat();

        ServerData.checkServer();

        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), instance);

        Bukkit.getPluginCommand("abchat").setExecutor(new ABChat());
    }

    public static Chat getChat() {
        return chat;
    }
    public static Permission getPerms() {
        return perms;
    }
}
