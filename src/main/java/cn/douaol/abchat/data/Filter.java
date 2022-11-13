package cn.douaol.abchat.data;

import cn.douaol.abchat.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static List<String> filterWordList = new ArrayList<>();
    public static List<String> ignoreCharacterList = new ArrayList<>();
    public static List<String> whitelist = new ArrayList<>();

    public static void loadFilter() throws IOException {
        File filterFile = new File(Main.instance.getDataFolder(), "filter.yml");
        YamlConfiguration filter = YamlConfiguration.loadConfiguration(filterFile);

        filter.options().copyDefaults(true);
        List<String> filterWordList = new ArrayList<>();
        filterWordList.add("fuck");
        filterWordList.add("example");
        filter.addDefault("filter-words", filterWordList);

        List<String> whitelist = new ArrayList<>();
        whitelist.add("www.example.com");
        filter.addDefault("whitelist", whitelist);
        List<String> ignoreCharacterList = new ArrayList<>();

        ignoreCharacterList.add("\\~");
        ignoreCharacterList.add("\\`");
        ignoreCharacterList.add("\\!");
        ignoreCharacterList.add("\\@");
        ignoreCharacterList.add("\\#");
        ignoreCharacterList.add("\\$");
        ignoreCharacterList.add("\\%");
        ignoreCharacterList.add("\\^");
        ignoreCharacterList.add("\\&");
        ignoreCharacterList.add("\\*");
        ignoreCharacterList.add("\\(");
        ignoreCharacterList.add("\\)");
        ignoreCharacterList.add("\\_");
        ignoreCharacterList.add("\\-");
        ignoreCharacterList.add("\\+");
        ignoreCharacterList.add("\\=");
        ignoreCharacterList.add("\\{");
        ignoreCharacterList.add("\\[");
        ignoreCharacterList.add("\\}");
        ignoreCharacterList.add("\\]");
        ignoreCharacterList.add("\\|");
        ignoreCharacterList.add("\\");
        ignoreCharacterList.add("\\;");
        ignoreCharacterList.add("\\:");
        ignoreCharacterList.add("\"");
        ignoreCharacterList.add("\\'");
        ignoreCharacterList.add("\\.");
        ignoreCharacterList.add("\\<");
        ignoreCharacterList.add("\\>");
        ignoreCharacterList.add("\\,");
        ignoreCharacterList.add("\\/");
        ignoreCharacterList.add("\\?");
        filter.addDefault("ignore-characters", ignoreCharacterList);

        Filter.filterWordList = filter.getStringList("filter-words");
        Filter.ignoreCharacterList = filter.getStringList("ignore-characters");
        Filter.whitelist = filter.getStringList("whitelist");

        filter.save(filterFile);
    }
}
