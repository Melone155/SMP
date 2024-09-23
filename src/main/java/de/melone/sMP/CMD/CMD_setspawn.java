package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CMD_setspawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("SMP.setspawn")){

            int X = (int) player.getLocation().getX();
            int Y = (int) player.getLocation().getY();
            int Z = (int) player.getLocation().getZ();

            double Yaw = player.getLocation().getYaw();
            double Pitch = player.getLocation().getPitch();

            double Xdouble = X + 0.5;
            double Ydouble = Y + 0.5;

            String world = player.getLocation().getWorld().getName();;

            SMP.warpyml.set("Spawn.X", Xdouble);
            SMP.warpyml.set("Spawn.Y", Ydouble);
            SMP.warpyml.set("Spawn.Z", Z);
            SMP.warpyml.set("Spawn.Yaw", Yaw);
            SMP.warpyml.set("Spawn.Pitch", Pitch);
            SMP.warpyml.set("Spawn.World", world);

            try {
                SMP.warpyml.save(SMP.warpfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " Du hat den Spawn gesetzt"));

        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.noperms));
        }

        return false;
    }
}
