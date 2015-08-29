package de.mauricius17.enderride.listener;

import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.mauricius17.enderride.mysql.EnderRideMySQL;
import de.mauricius17.enderride.mysql.MySQL;
import de.mauricius17.enderride.utils.Permissions;
import de.mauricius17.enderride.utils.UUIDFetcher;
import de.mauricius17.enderride.utils.Utils;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPermission(Permissions.ENDERRIDESWITCH.getPermission())) {
			if(MySQL.getSql().getBoolean("mysql")) {
				System.out.println("ccc");
				EnderRideMySQL.getEnderRideStats(UUIDFetcher.getUUID(p.getName()).toString(), new Consumer<String>() {
					
					@Override
					public void accept(String result) {
						if(result.equals(EnderRideMySQL.ON.toString())) {
							System.out.println("ggg");
							Utils.getEnderRide().add(UUIDFetcher.getUUID(p.getName()));
						}
					}
				});
			}
		}
	}
}