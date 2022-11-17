package cn.douaol.abchat.data;

import cn.douaol.abchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatFormat {
    public static boolean formatChat = false;
    public static boolean joinMessage = false;
    public static boolean quitMessage = false;
    public static List<String> format = new ArrayList<>();
    public static List<String> jm = new ArrayList<>();
    public static List<String> qm = new ArrayList<>();

    public static void loadFormat() throws IOException {
        Bukkit.getConsoleSender().sendMessage(Config.prefix + "Loading chat-format.yml ...");

        File formatFile = new File(Main.instance.getDataFolder(), "chat-format.yml");
        YamlConfiguration format = YamlConfiguration.loadConfiguration(formatFile);

        format.options().copyDefaults(true);
        format.addDefault("chat-format.enable", true);
        List<String> formatList = new ArrayList<>();
        formatList.add("default: &7<PLAYER>: <MESSAGE>");
        formatList.add("example: <PREFIX> <PLAYER>&f: <MESSAGE>");
        formatList.add("owner: <PREFIX> <PLAYER> <SUFFIX>&f: <MESSAGE>");
        format.addDefault("chat-format.format", formatList);
        format.addDefault("join-message.enable", true);
        List<String> jmList = new ArrayList<>();
        jmList.add("default: &7[&a+&7] &e<PLAYER> has joined!");
        format.addDefault("join-message.message", jmList);
        format.addDefault("quit-message.enable", true);
        List<String> qmList = new ArrayList<>();
        qmList.add("default: &7[&a+&7] &e<PLAYER> has quit!");
        format.addDefault("quit-message.message", qmList);

        format.save(formatFile);

        formatChat = format.getBoolean("chat-format.enable");
        joinMessage = format.getBoolean("join-message.enable");
        quitMessage = format.getBoolean("quit-message.enable");
        ChatFormat.format = format.getStringList("chat-format.format");
        ChatFormat.jm = format.getStringList("join-message.message");
        ChatFormat.qm = format.getStringList("quit-message.message");
    }
}
