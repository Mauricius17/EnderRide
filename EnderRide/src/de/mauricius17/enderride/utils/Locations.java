package de.mauricius17.enderride.utils;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public enum Locations {

	RESPAWNPOINT;
	
	public static void setLocation(Player p, Locations location) {
		Location loc = p.getLocation();
		
		String world = p.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double yaw = loc.getYaw();
		double pitch = loc.getPitch();
		
		Utils.getLocations().set("location." + location.toString() + ".set", true);
		Utils.getLocations().set("location." + location.toString() + ".world", world);
		Utils.getLocations().set("location." + location.toString() + ".x", x);
		Utils.getLocations().set("location." + location.toString() + ".y", y);
		Utils.getLocations().set("location." + location.toString() + ".z", z);
		Utils.getLocations().set("location." + location.toString() + ".yaw", yaw);
		Utils.getLocations().set("location." + location.toString() + ".pitch", pitch);
		
		try {
			Utils.getLocations().save(Utils.getLocationsFile());
			p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.set.success")));
		} catch (IOException e) {
			p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("command.set.failed")));
			e.printStackTrace();
		}
	}
	
	public static Location getLocation(Locations location) {
		Boolean set = Utils.getLocations().getBoolean("location." + location.toString() + ".set");
		
		if(set) {
			String world = Utils.getLocations().getString("location." + location.toString() + ".world");
			double x = Utils.getLocations().getDouble("location." + location.toString() + ".x");
			double y = Utils.getLocations().getDouble("location." + location.toString() + ".y");
			double z = Utils.getLocations().getDouble("location." + location.toString() + ".z");
			double yaw = Utils.getLocations().getDouble("location." + location.toString() + ".yaw");
			double pitch = Utils.getLocations().getDouble("location." + location.toString() + ".pitch");
			
			Bukkit.createWorld(new WorldCreator(world));
			
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			loc.setYaw((float) yaw);
			loc.setPitch((float) pitch);
			
			return loc;	
		} else {
			for(World worlds: Bukkit.getWorlds()) {
				Bukkit.broadcastMessage(Utils.getPrefix() + "§4No Respawnpoint was set! Players with permission §c" + Permissions.ENDERRIDESET.getPermission() + " §4can set the respawnpoint with /enderride set!");
				return worlds.getSpawnLocation();
			}
		}
		
		return null;
	}
}