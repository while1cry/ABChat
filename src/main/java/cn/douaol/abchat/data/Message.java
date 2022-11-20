package cn.douaol.abchat.data;

import cn.douaol.abchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class Message {
    public static String noPermission;
    public static String blockFilter;
    public static String blockDelay;
    public static String blockRepeat;
    public static String blockDomain;
    public static String blockAdv;
    public static String blockGlobalMute;
    public static String blockSingleFilter;
    public static String blockUnicode;
    public static String cmdFilterAdd;
    public static String cmdFilterRemove;
    public static String cmdFilterNotFound;
    public static String cmdReload;
    public static String cmdGlobalMute;

    public static void loadMessage() throws IOException {
        Bukkit.getConsoleSender().sendMessage(Config.prefix + "§eLoading message_" + Config.language + ".yml ...");

        File messageFile = new File(Main.instance.getDataFolder() + "/messages", "message_" + Config.language + ".yml");
        YamlConfiguration message = YamlConfiguration.loadConfiguration(messageFile);

        message.options().copyDefaults(true);
        if (Objects.equals(Config.language.toLowerCase(Locale.ROOT), "zh_cn") || Objects.equals(Config.language.toLowerCase(Locale.ROOT), "cn") || Objects.equals(Config.language.toLowerCase(Locale.ROOT), "tw")) {
            message.addDefault("no-permission", "&c你没有这么做的权限!");
            message.addDefault("block.filter", "&c你的发言中似乎包含敏感词!已自动屏蔽!");
            message.addDefault("block.delay", "&7你的发言速度过快!已自动屏蔽!");
            message.addDefault("block.repeat", "&c你的发言似乎重复了!已自动屏蔽!");
            message.addDefault("block.domain", "&c你的发言似乎包含了一些网址!已自动屏蔽!");
            message.addDefault("block.adv", "&c你的发言可能是广告!已自动屏蔽!");
            message.addDefault("block.globalmute", "&c服务器已开启全局禁言!");
            message.addDefault("block.singlefilter", "&c看样子你不能输入: ");
            message.addDefault("block.unicode", "&c你似乎输入了Unicode字符!已自动屏蔽!");
            message.addDefault("command.filter.add", "&a屏蔽词添加成功!");
            message.addDefault("command.filter.remove", "&a屏蔽词删除成功!");
            message.addDefault("command.filter.not-found", "&c未找到该屏蔽词: ");
            message.addDefault("command.reload", "&a插件重载成功");
            message.addDefault("command.globalmute", "&a服务器全局禁言已设置为: ");
            message.addDefault("log.blocked", "&a服务器全局禁言已设置为: ");
        } else {
            message.addDefault("no-permission", "&cYou have no permission to do that!");
            message.addDefault("block.filter", "&cYour message has some words not allowed there!");
            message.addDefault("block.delay", "&7Please speak slowly!");
            message.addDefault("block.repeat", "&cYour message may be repeated!");
            message.addDefault("block.domain", "&cYour message has some domain-name!");
            message.addDefault("block.adv", "&cYour message seems like adv.!");
            message.addDefault("block.globalmute", "&cServer global mute is enabled!");
            message.addDefault("block.singlefilter", "&cYou cannot type: ");
            message.addDefault("block.unicode", "&cYou cannot type Unicode!");
            message.addDefault("command.filter.add", "&aFilter word added successfully!");
            message.addDefault("command.filter.remove", "&aFilter word removed successfully!");
            message.addDefault("command.filter.not-found", "&cCannot find the word: ");
            message.addDefault("command.reload", "&aPlugin has been reloaded.");
            message.addDefault("command.globalmute", "&aServer global mute mode toggle to: ");
        }

        message.save(messageFile);

        noPermission = message.getString("no-permission");
        blockFilter = message.getString("block.filter");
        blockDelay = message.getString("block.delay");
        blockRepeat = message.getString("block.repeat");
        blockDomain = message.getString("block.domain");
        blockAdv = message.getString("block.adv");
        blockGlobalMute = message.getString("block.globalmute");
        blockSingleFilter = message.getString("block.singlefilter");
        blockUnicode = message.getString("block.unicode");
        cmdFilterAdd = message.getString("command.filter.add");
        cmdFilterRemove = message.getString("command.filter.remove");
        cmdFilterNotFound = message.getString("command.filter.not-found");
        cmdReload = message.getString("command.reload");
        cmdGlobalMute = message.getString("command.globalmute");
    }
}
