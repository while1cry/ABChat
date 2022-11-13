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
    public static List<String> domainList = new ArrayList<>();
    public static List<String> advList = new ArrayList<>();

    public static void loadFilter() throws IOException {
        File filterFile = new File(Main.instance.getDataFolder(), "filter.yml");
        YamlConfiguration filter = YamlConfiguration.loadConfiguration(filterFile);

        filter.options().copyDefaults(true);
        List<String> filterWordList = new ArrayList<>();
        filterWordList.add("fuck");
        filterWordList.add("bitch");
        filterWordList.add("nigger");
        filterWordList.add("niger");
        filterWordList.add("negro");
        filterWordList.add("shit");
        filterWordList.add("idiot");
        filterWordList.add("sex");
        filterWordList.add("nmsl");
        filterWordList.add("nm$l");
        filterWordList.add("cnm");
        filterWordList.add("傻逼");
        filterWordList.add("你妈死了");
        filterWordList.add("废物");
        filterWordList.add("狗叫");
        filterWordList.add("吠");
        filterWordList.add("操你妈");
        filterWordList.add("测你码");
        filterWordList.add("测你们码");
        filterWordList.add("开户");
        filterWordList.add("开盒");
        filterWordList.add("身份证");
        filterWordList.add("电话");
        filterWordList.add("手机号");
        filterWordList.add("群号");
        filterWordList.add("进群");
        filterWordList.add("加群");
        filterWordList.add("傻狗");
        filterWordList.add("鸡巴");
        filterWordList.add("几把");
        filterWordList.add("倪哥");
        filterWordList.add("台湾");
        filterWordList.add("蔡英文");
        filterWordList.add("大陆");
        filterWordList.add("习近平");
        filterWordList.add("毛泽东");
        filter.addDefault("filter-words", filterWordList);

        List<String> domainList = new ArrayList<>();
        domainList.add("com");
        domainList.add("net");
        domainList.add("ltd");
        domainList.add("cn");
        domainList.add("cc");
        domainList.add("co");
        domainList.add("store");
        domainList.add("online");
        domainList.add("vip");
        domainList.add("top");
        domainList.add("club");
        filter.addDefault("domain-name", domainList);

        List<String> advList = new ArrayList<>();
        advList.add("Other");
        advList.add("Server");
        filter.addDefault("adv", advList);

        List<String> whitelist = new ArrayList<>();
        whitelist.add("www.example.com");
        whitelist.add("GG");
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
        Filter.domainList = filter.getStringList("domain-name");
        Filter.advList = filter.getStringList("adv");

        filter.save(filterFile);
    }
}
