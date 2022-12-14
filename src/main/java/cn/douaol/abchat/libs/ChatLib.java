package cn.douaol.abchat.libs;

import cn.douaol.abchat.data.*;
import com.spreada.utils.chinese.ZHConverter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ChatLib {
    public static boolean needBlock(Player player, String message) {
        if (Config.antiUnicode && Pattern.matches("[\\uff10-\\uff19]", message) && !player.hasPermission("abchat.bypass.unicode")) {
            player.sendMessage(translateMessage(player, Message.blockUnicode));
            return true;
        } else if(Config.antiUnicode && Pattern.matches("[\\uff21-\\uff5a]", message) && !player.hasPermission("abchat.bypass.unicode")) {
            player.sendMessage(translateMessage(player, Message.blockUnicode));
            return true;
        }

        String unChangedMessage = message;
        message = message.toLowerCase(Locale.ROOT); //To Lower Case
        message = removeCharacters(message);        //remove Characters
        if(message.equals("")) {
            message = unChangedMessage;
        }
        message = removeWhitelisted(message);       //remove whitelisted
        message = tcToCHS(message);

        if (Config.blockFilter && hasFilterWords(message) && !player.hasPermission("abchat.bypass.filter")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockFilter));                                               //Filter words
            return true;
        }

        for (String str : PlayerData.getMessages(player)) {
            if (Config.blockRepeat && getSimilarityRatio(str, message) >= Config.repeatSimilarity && !player.hasPermission("abchat.bypass.repeat")) {
                player.sendMessage(ChatLib.translateMessage(player, Message.blockRepeat));                                           //Repeat?
                return true;
            }
        }

        if (Config.blockAdv && hasAdv(message) && !player.hasPermission("abchat.bypass.adv")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockAdv));                                                  //Adv?
            return true;
        }

        if (Config.blockDomain && hasDomainName(unChangedMessage) && !player.hasPermission("abchat.bypass.domain")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockDomain));                                               //Domain?
            return true;
        }

        if (Config.blockSingleFilter && !hasSingleFilter(message).equals("") && !player.hasPermission("abchat.bypass.singlefilter")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockSingleFilter + hasSingleFilter(message)));     //Single Words
            return true;
        }

        if (PlayerData.isDelay(player) && !player.hasPermission("abchat.bypass.delay")) {
            player.sendMessage(ChatLib.translateMessage(player, Message.blockDelay));                                                //Delay?
            return true;
        }
        PlayerData.setDelay(player, true);
        PlayerData.addMessage(player, message);                                                                                      //Send
        return false;
    }

    public static String translateMessage(Player player, String message) {
        message = message.replaceAll("&", "??");
        if (ServerData.hasPlaceholderAPI) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        message = Config.prefix.replaceAll("&", "??") + message;
        return message;
    }

    private static String tcToCHS(String message) {
        ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
        return converter.convert(message);
    }

    private static String removeCharacters(String message) {
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
            ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
            filters = converter.convert(filters);
            if (message.contains(removeCharacters(filters.toLowerCase(Locale.ROOT)))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasAdv(String message) {
        for (String adv : Filter.advList) {
            if (message.toLowerCase(Locale.ROOT).contains(adv.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDomainName(String unChangedMessage) {
        List<String> characters = Filter.ignoreCharacterList;
        for (String whitelist : Filter.whitelist) {
            unChangedMessage = unChangedMessage.replaceAll(whitelist, "");
        }
        String[] strings = unChangedMessage.split(characters.toString());
        for (String string : strings) {
            for (String domain : Filter.domainList) {
                if (string.toLowerCase(Locale.ROOT).equals(domain.toLowerCase(Locale.ROOT)) && strings.length > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String hasSingleFilter(String message) {
        for (String singleFilter : Filter.singleFilterWordList) {
            if (message.toLowerCase(Locale.ROOT).replaceAll(singleFilter.toLowerCase(Locale.ROOT), "").equals("")) {
                return singleFilter;
            }
        }
        return "";
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
