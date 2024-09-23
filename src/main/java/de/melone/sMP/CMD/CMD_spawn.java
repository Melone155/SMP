package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMD_spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        Double X = SMP.warpyml.getDouble("Spawn.X");
        int Y = SMP.warpyml.getInt("Spawn.Y");
        Double Z = SMP.warpyml.getDouble("Spawn.Z");
        int Pitch = SMP.warpyml.getInt("Spawn.Pitch");
        int Yaw = SMP.warpyml.getInt("Spawn.Yaw");
        String Wold = SMP.warpyml.getString("Spawn.World");

        player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

        player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.messageyml.getString("Message.spawn.teleport")));

        return false;
    }
}
