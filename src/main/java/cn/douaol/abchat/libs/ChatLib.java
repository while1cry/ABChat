package cn.douaol.abchat.libs;

import cn.douaol.abchat.data.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;

public class ChatLib {
    public static boolean needBlock(Player player, String message) {
        message = message.toLowerCase(Locale.ROOT);
        message = removeCharacters(message);
        message = removeWhitelisted(message);
        if (hasFilterWords(message)) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockFilter));
            return true;
        }
        for (String str : PlayerData.getMessages(player)) {
            if (getSimilarityRatio(str, message) >= Config.repeatSimilarity) {
                player.sendMessage(ChatLib.translateMessage(player, Message.blockRepeat));
                return true;
            }
        }

        PlayerData.addMessage(player, message);

        if (PlayerData.isDelay(player) && !player.hasPermission("abchat.bypass.delay")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockDelay));
            return true;
        }
        PlayerData.setDelay(player, true);
        return false;
    }

    public static String translateMessage(Player player, String message) {
        message = message.replaceAll("&", "§");
        if(ServerData.hasPlaceholderAPI) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        message = Config.prefix.replaceAll("&", "§") + message;
        return message;
    }

    public static String removeCharacters(String message) {
        List<String> characters = Filter.ignoreCharacterList;
        String[] strings = message.split(characters.toString());
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    private static String removeWhitelisted(String message) {
        for (String whitelist : Filter.whitelist) {
            message = message.replaceAll(removeCharacters(whitelist), "");
        }
        return message;
    }

    private static boolean hasFilterWords(String message) {
        for (String filters : Filter.filterWordList) {
            if (message.contains(removeCharacters(filters.toLowerCase(Locale.ROOT)))) {
                return true;
            }
        }
        return false;
    }

    private static float getSimilarityRatio(String str, String target) {
        int[][] d;
        int n = str.length();
        int m = target.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0 || m == 0) {
            return 100.0F;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }
        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
    }
}
