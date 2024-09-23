package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMD_event implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 0){

            if(!SMP.warpyml.getString("event").equals(null)) {

                Double X = SMP.warpyml.getDouble("event.X");
                int Y = SMP.warpyml.getInt("event.Y");
                Double Z = SMP.warpyml.getDouble("event.Z");
                int Pitch = SMP.warpyml.getInt("event.Pitch");
                int Yaw = SMP.warpyml.getInt("event.Yaw");
                String Wold = SMP.warpyml.getString("event.World");

                player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Message.event.teleport")));
            }
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Message.event.error")));
        }
        return false;
    }
}
