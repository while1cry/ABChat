package cn.douaol.abchat.commands;

import cn.douaol.abchat.Main;
import cn.douaol.abchat.data.Config;
import cn.douaol.abchat.data.Filter;
import cn.douaol.abchat.data.Message;
import cn.douaol.abchat.data.ServerData;
import cn.douaol.abchat.libs.ChatLib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ABChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player playerSender;
        if(!(sender instanceof Player)) {
            playerSender = Bukkit.getPlayer("");
        } else {
            playerSender = (Player) sender;
        }
        if(! sender.hasPermission("abchat.use")) {
            sender.sendMessage(ChatLib.translateMessage(playerSender, Message.noPermission));
            return false;
        }
        if(args.length > 0) {
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "filter":
                    if(args.length > 2) {
                        if(args[1].toLowerCase(Locale.ROOT).equals("add")) {
                            File filterFile = new File(Main.instance.getDataFolder(), "filter.yml");
                            YamlConfiguration filter = YamlConfiguration.loadConfiguration(filterFile);
                            List<String> filters = filter.getStringList("filter-words");
                            filters.add(args[2]);
                            filter.set("filter-words", filters);
                            try {
                                filter.save(filterFile);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                Filter.loadFilter();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            sender.sendMessage(ChatLib.translateMessage(playerSender, Message.cmdFilterAdd));
                            return false;
                        }
                        if(args[1].toLowerCase(Locale.ROOT).equals("remove")) {
                            File filterFile = new File(Main.instance.getDataFolder(), "filter.yml");
                            YamlConfiguration filter = YamlConfiguration.loadConfiguration(filterFile);
                            List<String> filters = filter.getStringList("filter-words");
                            for(int i = 0; i <= filters.size(); i++) {
                                if(filters.get(i).toLowerCase(Locale.ROOT).equals(args[2].toLowerCase(Locale.ROOT))) {
                                    filters.remove(i);
                                    try {
                                        Filter.loadFilter();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    sender.sendMessage(ChatLib.translateMessage(playerSender, Message.cmdFilterRemove));
                                    return false;
                                }
                            }
                            sender.sendMessage(ChatLib.translateMessage(playerSender, Message.cmdFilterNotFound) + args[2]);
                            return false;
                        }
                    }
                case "reload":
                    try {
                        Config.loadConfig();
                        Filter.loadFilter();
                        Message.loadMessage();
                        sender.sendMessage(ChatLib.translateMessage(playerSender, Message.cmdReload));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                case "globalmute":
                    ServerData.globalMute = !ServerData.globalMute;
                    sender.sendMessage(ChatLib.translateMessage(playerSender, Message.cmdGlobalMute) + ServerData.globalMute);
                    return false;
            }
        }
        sender.sendMessage("§bABChat Command Help:");
        sender.sendMessage("§e/abchat filter add <word>");
        sender.sendMessage("§e/abchat filter remove <word>");
        sender.sendMessage("  ");
        sender.sendMessage("§c/abchat globalmute");
        sender.sendMessage("§6/abchat reload");
        sender.sendMessage("§f------------§bABChat§f------------");
        return false;
    }
}
