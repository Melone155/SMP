package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.UUID;

public class CMD_delhome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1){
            String homename = args[0];

            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Messgae.home.del")));

            try {
                deleteHome(player, player.getUniqueId(), homename);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    public static void deleteHome(Player player, UUID playerUUID, String homeName) throws IOException {
        String uuidString = playerUUID.toString();

        if (SMP.homeyml.contains(uuidString)) {

            if (SMP.homeyml.contains(uuidString + "." + homeName)) {
                SMP.homeyml.set(uuidString + "." + homeName, null);
                SMP.homeyml.save(SMP.homefile);

                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.notexist"), homeName)));
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.notexist"), homeName)));
            }
        }
    }

    private static String ConfigMessages(String message, String homeName) {
        if (message.contains("%home%")) {
            return message.replace("%home%", homeName);
        }
        return message;
    }
}
