package cn.douaol.abchat;

import cn.douaol.abchat.commands.ABChat;
import cn.douaol.abchat.data.Config;
import cn.douaol.abchat.data.Filter;
import cn.douaol.abchat.data.Message;
import cn.douaol.abchat.data.ServerData;
import cn.douaol.abchat.events.AsyncPlayerChat;
import cn.douaol.abchat.events.PlayerJoin;
import cn.douaol.abchat.events.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {
    public static Main instance;

    public void onEnable() {
        instance = this;

        try {initPlugin();} catch (IOException e) {throw new RuntimeException(e);}
    }
    public static void initPlugin() throws IOException {
        Config.loadConfig();
        Filter.loadFilter();
        Message.loadMessage();

        ServerData.checkServer();

        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), instance);

        Bukkit.getPluginCommand("abchat").setExecutor(new ABChat());
    }
}
