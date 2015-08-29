package de.mauricius17.enderride.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mauricius17.enderride.mysql.EnderRideMySQL;
import de.mauricius17.enderride.mysql.MySQL;
import de.mauricius17.enderride.utils.Locations;
import de.mauricius17.enderride.utils.Permissions;
import de.mauricius17.enderride.utils.UUIDFetcher;
import de.mauricius17.enderride.utils.Utils;

public class EnderRideCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getConsole());
			return true;
		}
		
		Player p = (Player) sender;
		
		if(args.length != 1) {
			sendHelp(p);
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("set")) {
				if(p.hasPermission(Permissions.ENDERRIDESWITCH.getPermission())) {
					if(args[0].equalsIgnoreCase("on")) {
						if(Utils.getEnderRide().contains(UUIDFetcher.getUUID(p.getName()))) {
							p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.switch.on.failed")));
						} else {
							Utils.getEnderRide().add(UUIDFetcher.getUUID(p.getName()));
							
							if(MySQL.getSql().getBoolean("mysql")) {
								EnderRideMySQL.setEnderRide(UUIDFetcher.getUUID(p.getName()).toString(), EnderRideMySQL.ON);
							}
							
							p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.switch.on.success")));
						}
					}
					
					if(args[0].equalsIgnoreCase("off")) {
						if(Utils.getEnderRide().contains(UUIDFetcher.getUUID(p.getName()))) {
							Utils.getEnderRide().remove(UUIDFetcher.getUUID(p.getName()));
							
							if(MySQL.getSql().getBoolean("mysql")) {
								EnderRideMySQL.setEnderRide(UUIDFetcher.getUUID(p.getName()).toString(), EnderRideMySQL.OFF);
							}
							
							p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.switch.off.success")));
						} else {
							p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.switch.off.failed")));
						}
					}
					
					if(args[0].equalsIgnoreCase("set")) {
						if(p.hasPermission(Permissions.ENDERRIDESET.getPermission())) {
							Locations.setLocation(p, Locations.RESPAWNPOINT);
						} else {
							p.sendMessage(Utils.getPrefix() + Utils.getNoPermission());
						}
					}
				} else {
					p.sendMessage(Utils.getPrefix() + Utils.getNoPermission());
				}
			} else {
				sendHelp(p);
			}
		}
		
		return true;
	}
	
	private void sendHelp(Player p) {
		if(p.hasPermission(Permissions.ENDERRIDEHELP.getPermission())) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.help.header")));
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.help.line01")));
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.help.line02")));
			
			if(p.hasPermission(Permissions.ENDERRIDESET.getPermission())) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.help.line03")));				
			}
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.help.footer")));
		} else {
			p.sendMessage(Utils.getPrefix() + Utils.getNoPermission());
		}
	}
}