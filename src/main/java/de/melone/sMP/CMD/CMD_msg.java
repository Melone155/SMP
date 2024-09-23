package de.melone.sMP.CMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class CMD_msg implements CommandExecutor {

    public static final HashMap<UUID, UUID> lastMessage = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("§cBenutzung: /msg <Spieler> <Nachricht>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage("§cDer Spieler ist nicht online.");
            return true;
        }

        String message = String.join(" ", args).substring(args[0].length()).trim();
        sendMessage(player, target, message);

        return false;
    }

    private void sendMessage(Player sender, Player receiver, String message) {
        sender.sendMessage("§6Du §7-> §6[" + receiver.getName() + "]: §f" + message);
        receiver.sendMessage("§6" + sender.getName() + " §7-> §6[Dir]: §f" + message);

        lastMessage.put(receiver.getUniqueId(), sender.getUniqueId());
    }
}
