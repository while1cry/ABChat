package cn.douaol.abchat.commands;

import cn.douaol.abchat.Main;
import cn.douaol.abchat.data.Config;
import cn.douaol.abchat.data.Filter;
import cn.douaol.abchat.data.ServerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ABChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(! sender.hasPermission("abchat.use")) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
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
                            sender.sendMessage("§aFilter word added successfully!");
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
                                    sender.sendMessage("§aFilter word removed successfully!");
                                    return false;
                                }
                            }
                            sender.sendMessage("§cFilter word removed failed! Cannot find the word: " + args[2]);
                            return false;
                        }
                    }
                case "reload":
                    try {
                        Config.loadConfig();
                        Filter.loadFilter();
                        sender.sendMessage("§aPlugin has been reloaded.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                case "globalmute":
                    ServerData.globalMute = !ServerData.globalMute;
                    sender.sendMessage("§aServer global mute mode toggle to: " + ServerData.globalMute);
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
