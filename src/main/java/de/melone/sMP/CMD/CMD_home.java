package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static io.papermc.paper.entity.TeleportFlag.Relative.X;

public class CMD_home implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1){
            String homeName = args[0];

            if (SMP.homeyml.contains(player.getUniqueId() + "." + homeName)) {
                TeleportHome(player, homeName);
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.notexist"), homeName)));
            }

        } else {
            List<String> homeList = new ArrayList<>();

            if (SMP.homeyml.contains(player.getUniqueId().toString())) {
                ConfigurationSection homeSection = SMP.homeyml.getConfigurationSection(player.getUniqueId() + ".Home");

                player.sendMessage(homeList.toString());

                if (homeSection != null) {
                    homeList.addAll(homeSection.getKeys(false));
                }
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        List<String> homeList = new ArrayList<>();

        if (args.length == 1){
            if (SMP.homeyml.contains(player.getUniqueId().toString())) {
                ConfigurationSection homeSection = SMP.homeyml.getConfigurationSection(player.getUniqueId().toString() );

                if (homeSection != null) {
                    homeList.addAll(homeSection.getKeys(false));
                }
            }
        }

        return homeList;
    }

    private static void TeleportHome(Player player, String homeName){

        Double X = SMP.homeyml.getDouble(player.getUniqueId() + "." + homeName + ".X");
        int Y = SMP.homeyml.getInt(player.getUniqueId() + "." + homeName + ".Y");
        Double Z = SMP.homeyml.getDouble(player.getUniqueId() + "." + homeName + ".Z");
        int Pitch = SMP.homeyml.getInt(player.getUniqueId() + "." + homeName + ".Pitch");
        int Yaw = SMP.homeyml.getInt(player.getUniqueId() + "." + homeName + ".Yaw");
        String Wold = SMP.homeyml.getString(player.getUniqueId() + "." + homeName + ".World");

        player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

        player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.teleport"), homeName)));

    }

    private static String ConfigMessages(String message, String homeName) {
        if (message.contains("%home%")) {
            return message.replace("%home%", homeName);
        }
        return message;
    }
}
