package de.melone.sMP.CMD;

import de.melone.sMP.SMP;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Befehl kann nur von einem Spieler verwendet werden.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + "<gray>Der angegebene Spieler ist nicht online oder existiert nicht."));
                return true;
            }

            if (!player.hasPermission("smp.ec")) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + SMP.noperms));
                return true;
            }

            player.openInventory(target.getEnderChest());
            player.sendMessage(MiniMessage.miniMessage().deserialize(SMP.prefix + "<gray>Du schaust nun in die Enderchest von <b><yellow>" + target.getName()));
        } else {

            player.openInventory(player.getEnderChest());
        }

        return true;
    }
}
