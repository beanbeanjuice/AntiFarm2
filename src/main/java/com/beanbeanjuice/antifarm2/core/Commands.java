package com.beanbeanjuice.antifarm2.core;

import com.beanbeanjuice.antifarm2.AntiFarm2;
import com.beanbeanjuice.antifarm2.configuration.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private final AntiFarm2 plugin;
    private final Configuration config;
    private final Configuration spawners;

    public Commands(AntiFarm2 plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.spawners = plugin.getSpawners();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("antifarm") && sender.isOp() || sender.hasPermission("antifarm.admin")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    config.reload("config");
                    spawners.reload("spawners");
                    sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Configuration file reloaded!");
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Anti AFK farm blocker plugin.");
        sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Author(s): " + plugin.getDescription().getAuthors());
        sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Version: " + plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Thanks for using my plugin :) (Slightly Modified by beanbeanjuice)");
        sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Spigot: https://www.spigotmc.org/resources/anti-farm.99472/");
        return false;
    }

}
