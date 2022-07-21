package net.capbear.welcomemsg.welcomemsg;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;

public final class WelcomeMsg extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        FileConfiguration config = this.getConfig(); // create config var

        String[] defaultMsgs = { "Welcome to the server!", "Enjoy your stay!" }; // default message
        config.addDefault("messages", defaultMsgs); // define default messagelist

        config.options().setHeader(Collections.singletonList("Use '§' not '&' when defining colors and whatnot"));

        config.options().copyDefaults(true);
        saveConfig(); // create default config

        this.getCommand("welcomemsg").setExecutor(new CommandWelcomeMsg()); // add reload command

        getServer().getPluginManager().registerEvents(this, this); // register plugin
        getLogger().info("WelcomeMsg enabled"); // confirm plugin activity
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        FileConfiguration config = this.getConfig(); // create config var

        // send a random message
        ArrayList<String> msgs = (ArrayList<String>) config.getStringList("messages");
        p.sendMessage(msgs.get((int) (Math.random() * msgs.size())));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("WelcomeMsg disabled");
    }
}

