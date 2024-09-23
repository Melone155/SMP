package de.melone.sMP.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMD_r implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("§cBenutzung: /r <Nachricht>");
            return true;
        }

        if (!CMD_msg.lastMessage.containsKey(player.getUniqueId())) {
            player.sendMessage("§cNiemand hat dir eine Nachricht geschickt.");
            return true;
        }

        Player target = Bukkit.getPlayer(CMD_msg.lastMessage.get(player.getUniqueId()));
        if (target == null || !target.isOnline()) {
            player.sendMessage("§cDer Spieler ist nicht mehr online.");
            return true;
        }

        String message = String.join(" ", args);
        sendMessage(player, target, message);

        return false;
    }

    private void sendMessage(Player sender, Player receiver, String message) {
        sender.sendMessage("§6[Du] §7-> §6[" + receiver.getName() + "]: §f" + message);
        receiver.sendMessage("§6[" + sender.getName() + "] §7-> §6[Dir]: §f" + message);

        // Den letzten Spieler, der eine Nachricht geschickt hat, speichern
        CMD_msg.lastMessage.put(receiver.getUniqueId(), sender.getUniqueId());
    }
}
