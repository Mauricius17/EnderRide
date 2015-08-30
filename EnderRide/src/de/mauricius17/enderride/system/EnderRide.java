package de.mauricius17.enderride.system;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import de.mauricius17.enderride.commands.EnderRideCommand;
import de.mauricius17.enderride.enderride.EntityEnderRide;
import de.mauricius17.enderride.enderride.EntityUtils;
import de.mauricius17.enderride.mysql.MySQL;
import de.mauricius17.enderride.utils.Locations;
import de.mauricius17.enderride.utils.OnlineMode;
import de.mauricius17.enderride.utils.Utils;

public class EnderRide extends JavaPlugin {

	private static EnderRide instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		loadMessages();
		loadConfig();
		loadLocations();
		registerCommands();
		registerEvents();
		
		new MySQL();
		
		if(MySQL.getSql().getBoolean("mysql")) {
			MySQL.connect();
			MySQL.createTable();
		}
		
		Utils.setPrefix(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("prefix")));
		Utils.setConsole(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("console")));
		Utils.setNoPermission(ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("nopermission")));
		
		if(Utils.getConfig().getBoolean("online-mode")) {
			Utils.setOnlineMode(OnlineMode.ON);
		} else {
			Utils.setOnlineMode(OnlineMode.OFF);
			Bukkit.getConsoleSender().sendMessage("§cOnline-Mode of the plugin is off! This mode is only usefull for non-premium-servers!");
		}
		
		EntityUtils.register("EnderRide", 14, EntityEnderRide.class);
	}
	
	@Override
	public void onDisable() {
		instance = null;
		
		MySQL.disconnect();
	}
	
	public static EnderRide getInstance() {
		return instance;
	}
	
	private void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		try {
			for(ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.mauricius17.enderride.listener")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				
				if(Listener.class.isAssignableFrom(clazz)) {
					pluginManager.registerEvents((Listener) clazz.newInstance(), this);
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}	
	}
	
	private void registerCommands() {
		getCommand("enderride").setExecutor(new EnderRideCommand());
	}
	
	private void loadLocations() {
		for(int i = 0; i < Locations.values().length; i++) {
			Utils.getLocations().addDefault("location." + Locations.values()[i].toString() + ".set", false);
		}
	}
	
	private void loadConfig() {
		Utils.getConfig().options().header("In this file you can edit some settings");
		
		Utils.getConfig().addDefault("online-mode", true);
		Utils.getConfig().addDefault("certain_worlds", false);		
		Utils.getConfig().addDefault("worlds", "world,world_nether");
		
		Utils.getConfig().options().copyDefaults(true);
		
		try {
			Utils.getConfig().save(Utils.getConfigFile());
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cThe config.yml could not be saved! Restart your Server!");
			e.printStackTrace();
		}
	}
	
	private void loadMessages() {
		Utils.getMessages().options().header("In this file you can edit some messages");
		
		Utils.getMessages().addDefault("prefix", "&8[&5EnderRide&8] ");
	    Utils.getMessages().addDefault("console", "&cYou can use that command only ingame!");
	    Utils.getMessages().addDefault("nopermission", "&cYou do not have permissions!");
	    
	    Utils.getMessages().addDefault("command.help.header", "&8==========[&5EnderRide&8]==========");
	    Utils.getMessages().addDefault("command.help.line01", "&7/enderride on &5- Set your endermode on");
	    Utils.getMessages().addDefault("command.help.line02", "&7/enderride off &5- Set your endermode off");
	    Utils.getMessages().addDefault("command.help.line03", "&7/enderride set &5- Set the respawnpoint");
	    Utils.getMessages().addDefault("command.help.footer", "&8===============================");
	    
	    Utils.getMessages().addDefault("command.switch.on.success", "&aYou switched your endermode on!");
	    Utils.getMessages().addDefault("command.switch.on.failed", "&cYour endermode is already on!");
	    Utils.getMessages().addDefault("command.switch.off.success", "&cYou switched your endermode off!");
	    Utils.getMessages().addDefault("command.switch.off.failed", "&cYour endermode is already off!");
	    
	    Utils.getMessages().addDefault("command.set.success", "&aYou set the EnderRide respawnpoint!");
	    Utils.getMessages().addDefault("command.set.failed", "&cSomething went wrong! The respawnpoint could not be saved!");
	    Utils.getMessages().addDefault("inside.block", "&cIt looks as if you were in a block! You have been teleported to spawn!");

	    
	    Utils.getMessages().options().copyDefaults(true);
		
		try {
			Utils.getMessages().save(Utils.getMessageFile());
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cThe message.yml could not be saved! Restart your Server!");
			e.printStackTrace();
		}
	}
}