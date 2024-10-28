package de.melone.sMP.listener;

import de.melone.sMP.SMP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(MiniMessage.miniMessage().deserialize("<yellow><b>" + player.getPlayerProfile().getName() + "</b> <gray>ist nun der <green><b>Wildnis</b> <gray>beigetreten."));

        if(!player.hasPlayedBefore()) {
            PlayerTelepor(player);

            player.getInventory().addItem(ItemStack.of(Material.IRON_HELMET));
            player.getInventory().addItem(ItemStack.of(Material.IRON_CHESTPLATE));
            player.getInventory().addItem(ItemStack.of(Material.IRON_LEGGINGS));
            player.getInventory().addItem(ItemStack.of(Material.IRON_BOOTS));

            player.getInventory().addItem(ItemStack.of(Material.IRON_SWORD));
            player.getInventory().addItem(ItemStack.of(Material.IRON_AXE));
            player.getInventory().addItem(ItemStack.of(Material.IRON_PICKAXE));
            player.getInventory().addItem(ItemStack.of(Material.IRON_SHOVEL));
            player.getInventory().addItem(ItemStack.of(Material.COOKED_PORKCHOP, 64));
            player.getInventory().addItem(ItemStack.of(Material.WHITE_BED));

            for (Player online: Bukkit.getOnlinePlayers()){
                online.sendMessage(MiniMessage.miniMessage().deserialize("<aqua><bold>" + player.getPlayerProfile().getName() + "</bold> <gray>ist zum ersten Mal in der <green><b>Wildnis</b>. <gray>Join, heißt ihn <yellow><b>Herzlich Willkommen</b> <gray>auf dem Server."));
            }

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.quitMessage(MiniMessage.miniMessage().deserialize("<yellow><b>" + player.getPlayerProfile().getName() + "</B> <gray>hat die <green><b>Wildnis</b> <gray>verlassen"));
    }

    private void PlayerTelepor(Player player){

        Double X = SMP.warpyml.getDouble("Spawn.X");
        int Y = SMP.warpyml.getInt("Spawn.Y");
        Double Z = SMP.warpyml.getDouble("Spawn.Z");
        int Pitch = SMP.warpyml.getInt("Spawn.Pitch");
        int Yaw = SMP.warpyml.getInt("Spawn.Yaw");
        String Wold = SMP.warpyml.getString("Spawn.World");

        player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

    }
}
