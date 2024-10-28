package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.chat.SignedMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CMD_ec implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage("Der angegebene Spieler ist nicht online.");
                return true;
            }

            player.openInventory(target.getEnderChest());
        }

        player.openInventory(player.getEnderChest());

        return false;
    }
}
