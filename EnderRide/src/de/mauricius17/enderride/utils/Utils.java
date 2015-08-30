package de.mauricius17.enderride.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Utils {

	private static File configFile = new File("plugins/EnderRide", "config.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	
	private static File messageFile = new File("plugins/EnderRide", "messages.yml");
	private static FileConfiguration messages = YamlConfiguration.loadConfiguration(messageFile);
	
	private static File locationsFile = new File("plugins/EnderRide", "locations.yml");
	private static FileConfiguration locations = YamlConfiguration.loadConfiguration(locationsFile);

	private static String prefix, noPermission, console;

	private static List<UUID> enderRide = new ArrayList<>();

	private static OnlineMode onlineMode = OnlineMode.ON;
	
	public static OnlineMode getOnlineMode() {
		return onlineMode;
	}
	
	public static void setOnlineMode(OnlineMode onlineMode) {
		Utils.onlineMode = onlineMode;
	}
	
	public static FileConfiguration getLocations() {
		return locations;
	}
	
	public static File getLocationsFile() {
		return locationsFile;
	}
	
	public static List<UUID> getEnderRide() {
		return enderRide;
	}
	
	public static File getMessageFile() {
		return messageFile;
	}
	
	public static FileConfiguration getMessages() {
		return messages;
	}
	
	public static FileConfiguration getConfig() {
		return config;
	}
	
	public static File getConfigFile() {
		return configFile;
	}
	
	public static void setConsole(String console) {
		Utils.console = console;
	}
	
	public static void setNoPermission(String noPermission) {
		Utils.noPermission = noPermission;
	}
	
	public static void setPrefix(String prefix) {
		Utils.prefix = prefix;
	}
	
	public static String getConsole() {
		return console;
	}
	
	public static String getNoPermission() {
		return noPermission;
	}
	
	public static String getPrefix() {
		return prefix;
	}
}