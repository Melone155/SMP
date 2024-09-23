package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static de.melone.sMP.CMD.CMD_tpaaccept.teleportRequests;

public class CMD_tpa implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player requester = (Player) sender;

        if (args.length != 1) {
            requester.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Message.tpa.help")));
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            requester.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Message.tpa.offline")));
        }

        if (target.equals(requester)) {
            requester.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + SMP.messageyml.getString("Message.tpa.self")));
        }

        teleportRequests.put(target.getUniqueId(), requester.getUniqueId());

        requester.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.tpa.send"), target, requester)));
        target.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + " " + ConfigMessages(SMP.messageyml.getString("Message.tpa.request"), target, requester)));

        return false;
    }

    private static String ConfigMessages(String message, Player target, Player sender) {
        if (message.contains("%target%") || message.contains("%sender%")) {
            message = message.replace("%target%", target.getName());
            message = message.replace("%sender%", sender.getName());
        }
        return message;
    }
}
