package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CMD_setwarp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("SMP.setwarp")) {
            if (args.length == 1) {
                String warpname = args[0];

                setwarp(player, warpname);

            }
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.noperms));
        }

        return false;
    }

    private static void setwarp(Player player, String warpname) {

        int X = (int) player.getLocation().getX();
        int Y = (int) player.getLocation().getY();
        int Z = (int) player.getLocation().getZ();

        double Yaw = player.getLocation().getYaw();
        double Pitch = player.getLocation().getPitch();

        double Xdouble = X + 0.5;
        double Ydouble = Y + 0.5;

        if (player.getLocation().getWorld() == null) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " Fehler: Welt konnte nicht ermittelt werden."));
            return;
        }

        String world = player.getLocation().getWorld().getName();

        SMP.warpyml.set(warpname + ".X", Xdouble);
        SMP.warpyml.set(warpname + ".Y", Ydouble);
        SMP.warpyml.set(warpname + ".Z", Z);
        SMP.warpyml.set(warpname + ".Yaw", Yaw);
        SMP.warpyml.set(warpname + ".Pitch", Pitch);
        SMP.warpyml.set(warpname + ".World", world);

        try {
            SMP.warpyml.save(SMP.warpfile);
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " Warp '" + warpname + "' erfolgreich gesetzt!"));
        } catch (IOException e) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " Fehler beim Speichern des Warps."));
            throw new RuntimeException(e);
        }
    }

}
