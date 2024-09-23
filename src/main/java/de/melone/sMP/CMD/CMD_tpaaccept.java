package de.melone.sMP.CMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class CMD_tpaaccept implements CommandExecutor {

    public static HashMap<UUID, UUID> teleportRequests = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nur Spieler können diesen Befehl verwenden.");
            return true;
        }

        Player target = (Player) sender;

        UUID requesterUUID = teleportRequests.get(target.getUniqueId());

        if (requesterUUID == null) {
            target.sendMessage(ChatColor.RED + "Es gibt keine ausstehende Teleportanfrage.");
            return true;
        }

        Player requester = Bukkit.getPlayer(requesterUUID);

        if (requester == null) {
            target.sendMessage(ChatColor.RED + "Der anfragende Spieler ist nicht mehr online.");
            teleportRequests.remove(target.getUniqueId());
            return true;
        }

        Location targetLocation = target.getLocation();
        requester.teleport(targetLocation);

        requester.sendMessage(ChatColor.GREEN + "Du wurdest zu " + target.getName() + " teleportiert.");
        target.sendMessage(ChatColor.GREEN + "Du hast die Teleportanfrage von " + requester.getName() + " akzeptiert.");

        teleportRequests.remove(target.getUniqueId());

        return false;
    }
}
