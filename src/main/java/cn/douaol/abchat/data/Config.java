package cn.douaol.abchat.data;

import cn.douaol.abchat.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static boolean blockFilter = false;
    public static boolean blockDomain = false;
    public static boolean blockRepeat = false;
    public static boolean blockAdv = false;
    public static Double chatDelay = 1.00;
    public static Double repeatSimilarity = 85.00;

    public static void loadConfig() throws IOException {
        File configFile = new File(Main.instance.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.options().copyDefaults(true);
        config.addDefault("block-filter", true);
        config.addDefault("block-domain", true);
        config.addDefault("block-repeat", true);
        config.addDefault("block-adv", true);
        config.addDefault("chat-delay", 1.00);
        config.addDefault("repeat-similarity", 85.00);

        blockFilter = config.getBoolean("block-filter");
        blockDomain = config.getBoolean("block-domain");
        blockRepeat = config.getBoolean("block-repeat");
        blockAdv = config.getBoolean("block-adv");
        chatDelay = config.getDouble("chat-delay");
        repeatSimilarity = config.getDouble("repeat-similarity");

        config.save(configFile);
    }
}
