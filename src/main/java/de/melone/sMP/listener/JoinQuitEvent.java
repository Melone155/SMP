package de.melone.sMP.listener;

import de.melone.sMP.SMP;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(null);

        if(!player.hasPlayedBefore()) {
            PlayerTelepor(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.quitMessage(null);
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
