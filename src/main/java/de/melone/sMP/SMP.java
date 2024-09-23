package de.melone.sMP;

import de.melone.sMP.CMD.*;
import de.melone.sMP.listener.JoinQuitEvent;
import org.apache.logging.log4j.message.Message;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SMP extends JavaPlugin {

    File folder = new File("plugins/SMP");

    public static File warpfile = new File("plugins//SMP//warps.yml");
    public static YamlConfiguration warpyml = YamlConfiguration.loadConfiguration(warpfile);

    public static File messagefile = new File("plugins//SMP//message.yml");
    public static YamlConfiguration messageyml = YamlConfiguration.loadConfiguration(messagefile);

    public static File homefile = new File("plugins//SMP//home.yml");
    public static YamlConfiguration homeyml = YamlConfiguration.loadConfiguration(homefile);

    public static String prefix = "";
    public static String noperms = messageyml.getString("Message.noperms");


    @Override
    public void onEnable() {
       RegisterYML();
       registercommands();
       registerlistener();

       prefix = messageyml.getString("Message.prefix");
    }

    @Override
    public void onDisable() {

    }

    private void registercommands() {
        getCommand("setspawn").setExecutor(new CMD_setspawn());
        getCommand("spawn").setExecutor(new CMD_spawn());
        getCommand("home").setExecutor(new CMD_home());
        getCommand("sethome").setExecutor(new CMD_sethome());
        getCommand("delhome").setExecutor(new CMD_delhome());
        getCommand("craft").setExecutor(new CMD_craft());
        getCommand("dc").setExecutor(new CMD_dc());
        getCommand("discord").setExecutor(new CMD_dc());
        getCommand("ec").setExecutor(new CMD_ec());
        getCommand("event").setExecutor(new CMD_event());
        getCommand("setwarp").setExecutor(new CMD_setwarp());
        getCommand("tpa").setExecutor(new CMD_tpa());
        getCommand("tpaccept").setExecutor(new CMD_tpaaccept());
        getCommand("twitch").setExecutor(new CMD_twitch());
        getCommand("warp").setExecutor(new CMD_warp());
        getCommand("msg").setExecutor(new CMD_msg());
        getCommand("r").setExecutor(new CMD_r());
    }

    private void registerlistener() {
        final PluginManager pluginManager = super.getServer().getPluginManager();

        pluginManager.registerEvents(new JoinQuitEvent(), this);
    }

    private void RegisterYML(){
        if (!folder.exists()){
            folder.mkdirs();
        }

        if (!warpfile.exists()){
            try {
                warpfile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!messagefile.exists()){
            try {
                messagefile.createNewFile();
                CreateMessagefile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!homefile.exists()){
            try {
                homefile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void CreateMessagefile(){
        messageyml.set("Message.prefix", "[Prefix]");
        messageyml.set("Message.noperms", "Du hast keine Rechte für diesen Befehl");
        messageyml.set("Message.dc", "Du willst mir uns reden kann komm auf unseren DC <click:open_url:'discord.com'>Klick mich</click>");
        messageyml.set("Message.twitch", "LALALALA");

        messageyml.set("Message.spawn.teleport", "Du wurdest zum Spawn Teleportiert");

        messageyml.set("Message.home.max", "Du hast du maximale anzahl von Home`s erricht");
        messageyml.set("Messgae.home.del", "Du hast dein home %home% gelöscht");
        messageyml.set("Message.home.notexist", "Dein Home Punkt %home% Existiert nicht");
        messageyml.set("Message.home.set", "Du hast den Home Punkt %home% gestezt");
        messageyml.set("Message.home.teleport", "Du wurdest zu einem Home %home% Teleportiert");
        messageyml.set("Messgae.home.help.home", "Benutze /home <homename>");
        messageyml.set("Messgae.home.help.delhome", "Benutze /delhome <homename>");
        messageyml.set("Messgae.home.help.sethome", "Benutze /sethome <homename>");

        messageyml.set("Message.event.teleport", "Wilkommen beim Event");
        messageyml.set("Message.event.error", "Benutze nur /event");

        messageyml.set("Message.tpa.help", "Verwendung: /tpa <Spielername>");
        messageyml.set("Message.tpa.offline", "Der Spieler ist nicht online.");
        messageyml.set("Message.tpa.self", "Du kannst keine TPA-Anfrage an dich selbst senden.");
        messageyml.set("Message.tpa.send", "Du hast eine TPA-Anfrage an %target% gesendet");
        messageyml.set("Message.tpa.request", "Du hast von %sender% eine TPA-Anfrage bekommen");

        try {
            messageyml.save(messagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
