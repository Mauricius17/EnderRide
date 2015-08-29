package de.mauricius17.enderride.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.mauricius17.enderride.utils.UUIDFetcher;
import de.mauricius17.enderride.utils.Utils;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		
		if(Utils.getEnderRide().contains(UUIDFetcher.getUUID(p.getName()))) {
			Utils.getEnderRide().remove(UUIDFetcher.getUUID(p.getName()));
		}
	}
}