package net.capbear.welcomemsg.welcomemsg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CommandWelcomeMsg implements CommandExecutor {
    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WelcomeMsg");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage("[WelcomeMsg] Reloaded config!");
            return true;
        } else { return false; }
    }
}
