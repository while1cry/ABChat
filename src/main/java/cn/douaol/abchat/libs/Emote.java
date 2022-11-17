package cn.douaol.abchat.libs;

import cn.douaol.abchat.Main;
import cn.douaol.abchat.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Emote {
    public static List<String> emotes = new ArrayList<>();
    public static String translateEmote(Player player, String message) {
        for(String emo : emotes) {
            emo = emo.replaceAll("//", "/♢");
            String perm = emo.split("/")[0];
            String placeholder = emo.split("/")[1];
            String out = emo.split("/")[2];
            out = out.replaceAll("&", "§");
            out = out.replaceAll("♢", "/");
            StringBuilder sb = new StringBuilder();
            sb.append("(?i)").append(placeholder);
            if(player.hasPermission(perm) && message.toLowerCase(Locale.ROOT).contains(placeholder.toLowerCase(Locale.ROOT))) {
                message = message.replaceAll(sb.toString(), out);
            }
        }
        return message;
    }
    public static void loadEmotes() throws IOException {
        Bukkit.getConsoleSender().sendMessage(Config.prefix + "Loading emote.yml ...");

        File emoteFile = new File(Main.instance.getDataFolder(), "emote.yml");
        YamlConfiguration emote = YamlConfiguration.loadConfiguration(emoteFile);

        emote.options().copyDefaults(true);

        List<String> emoteList = new ArrayList<>();
        emoteList.add("emote.love/<3/&c❤&f");
        emoteList.add("emote.angry/:angry:/&d[○･｀Д´･ ○]&f");
        emoteList.add("emote.goldenGG/GG/&6GG&f");
        emoteList.add("emote.goldenGG/GoodGame/&6GoodGame&f");
        emoteList.add("emote.lol/lol/&b&nLOL&r&f");
        emote.addDefault("emotes", emoteList);

        emote.save(emoteFile);

        emotes = emote.getStringList("emotes");
    }
}
