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

        String[] defaultMsgs = { "Welcome to the server!", "Enjoy your stay!" }; // create default list of messages
        ArrayList<String> msgs = new ArrayList<String>(Arrays.asList(defaultMsgs)); // convert to list
        config.addDefault("messages", msgs); // define default messagelist
        config.addDefault("title", msgs); // define default title

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

        // show a random title
        ArrayList<String> titles = (ArrayList<String>) config.getStringList("title");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + p.getName() + " title \"" + titles.get((int) (Math.random() * titles.size())) + "\"");

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

