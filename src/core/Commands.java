package core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (cmd.getName().equalsIgnoreCase("antifarm") && sender.isOp() || sender.hasPermission("antifarm.admin")) {
			if (args.length >= 1) {
				if (args[0].equals("reload")) {
					J.configJ.loadConfig();
					sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Configuration file reloaded!");
					return true;
				}
			}
		}
		sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Anti AFK farm blocker plugin.");
		sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Author: " + Main.plugin.getDescription().getAuthors().toString());
		sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Version: " + Main.plugin.getDescription().getVersion().toString());
		sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Thanks for using my plugin :)");
		sender.sendMessage(ChatColor.GREEN + "[AntiFarm] Spigot: https://www.spigotmc.org/resources/anti-farm.99472/");
		return false;
	}
	
}
