package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CMD_warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1){
            String warpname = args[0];
            if (SMP.warpyml.getString(warpname) != null) {
                TeleportWarp(player, warpname);
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + "Der Warp " + warpname + " existiert nicht"));
            }
        }
        return false;
    }

    private static void TeleportWarp(Player player, String warpname){

        Double X = SMP.warpyml.getDouble(warpname + ".X");
        int Y = SMP.warpyml.getInt(warpname + ".Y");
        Double Z = SMP.warpyml.getDouble(warpname + ".Z");
        int Pitch = SMP.warpyml.getInt(warpname + ".Pitch");
        int Yaw = SMP.warpyml.getInt(warpname + ".Yaw");
        String Wold = SMP.warpyml.getString(warpname + ".World");

        player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

        player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.home.teleport"), warpname)));

    }

    private static String ConfigMessages(String message, String homeName) {
        if (message.contains("%home%")) {
            return message.replace("%home%", homeName);
        }
        return message;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        List<String> warpNames = new ArrayList<>();

        Set<String> keys = SMP.warpyml.getKeys(false);
        warpNames.addAll(keys);

        return warpNames ;
    }
}
