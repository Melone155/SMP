package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import static de.melone.sMP.SMP.homeyml;

public class CMD_sethome implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        int homeCount = getHomeCount(player.getUniqueId());

        if (args.length == 1){
            String homename = args[0];

            if (player.hasPermission("SMP.sethomePremium")){
                if (!(homeCount == 6)){

                    sethome(player, homename);

                    player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.set"), homename)));

                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.messageyml.getString("Message.home.max")));
                }

            } else {

                if (!(homeCount == 3)){

                    sethome(player, homename);

                    player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.set"), homename)));

                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.messageyml.getString("Message.home.max")));
                }
            }
        } else {

        }
        return false;
    }

    private static void sethome(Player player, String name){

        String uuid = player.getUniqueId().toString();

        int X = (int) player.getLocation().getX();
        int Y = (int) player.getLocation().getY();
        int Z = (int) player.getLocation().getZ();

        double Yaw = player.getLocation().getYaw();
        double Pitch = player.getLocation().getPitch();

        double Xdouble = X + 0.5;
        double Ydouble = Y + 0.5;

        String world = player.getLocation().getWorld().getName();;

        SMP.homeyml.set(uuid + "." + name + ".X", Xdouble);
        SMP.homeyml.set(uuid + "." + name + ".Y", Ydouble);
        SMP.homeyml.set(uuid + "." + name + ".Z", Z);
        SMP.homeyml.set(uuid + "." + name + ".Yaw", Yaw);
        SMP.homeyml.set(uuid + "." + name + ".Pitch", Pitch);
        SMP.homeyml.set(uuid + "." + name + ".World", world);

        try {
            SMP.homeyml.save(SMP.homefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static int getHomeCount(UUID playerUUID) {
        String uuidString = playerUUID.toString();

        if (homeyml.contains(uuidString)) {
            Set<String> homes = homeyml.getConfigurationSection(uuidString).getKeys(false);

            return homes.size();
        } else {
            return 0;
        }
    }

    private static String ConfigMessages(String message, String homeName) {
        // Überprüfen, ob die Nachricht nicht null ist
        if (message != null && message.contains("%home%")) {
            return message.replace("%home%", homeName);
        }
        return message != null ? message : "Nachricht konnte nicht geladen werden."; // Rückgabe einer Standardmeldung, falls null
    }

}
