package cn.douaol.abchat.data;

import cn.douaol.abchat.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerData {
    private static Map<Player, List<String>> playerDataMap = new HashMap<>();
    public static void addPlayer(Player player) {
        List<String> list = new ArrayList<>();
        list.add("FALSE");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        playerDataMap.put(player, list);
    }
    public static void removePlayer(Player player) {
        List<String> list = new ArrayList<>();
        playerDataMap.put(player, list);
    }
    public static void addMessage(Player player, String message) {
        List<String> list = playerDataMap.get(player);
        String msg1 = list.get(1);
        String msg2 = list.get(2);
        String msg3;
        msg3 = msg2;
        msg2 = msg1;
        msg1 = message;
        list.set(1, msg1);
        list.set(2, msg2);
        list.set(3, msg3);
        playerDataMap.put(player, list);
    }
    public static List<String> getMessages(Player player) {
        List<String> plist = playerDataMap.get(player);
        List<String> list = new ArrayList<>();
        list.add(plist.get(1));
        list.add(plist.get(2));
        list.add(plist.get(3));
        return list;
    }
    public static boolean isDelay(Player player) {
        List<String> list = playerDataMap.get(player);
        return Objects.equals(list.get(0), "TRUE");
    }
    public static void setDelay(Player player, boolean b) {
        List<String> list = playerDataMap.get(player);
        if(b) {
            list.set(0, "TRUE");
            playerDataMap.put(player, list);
        }
        Double delay = Config.chatDelay;
        delay = delay*20;
        String delayString = String.valueOf(delay);
        long delayLong = Long.parseLong(delayString.split("\\.")[0]);
        new BukkitRunnable() {
            @Override
            public void run() {
                list.set(0, "FALSE");
                playerDataMap.put(player, list);
            }
        }.runTaskLater(Main.instance, delayLong);
    }
}
