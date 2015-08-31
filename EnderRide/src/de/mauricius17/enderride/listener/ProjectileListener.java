package de.mauricius17.enderride.listener;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import de.mauricius17.enderride.enderride.EntityEnderRide;
import de.mauricius17.enderride.utils.OnlineMode;
import de.mauricius17.enderride.utils.Permissions;
import de.mauricius17.enderride.utils.UUIDFetcher;
import de.mauricius17.enderride.utils.Utils;

public class ProjectileListener implements Listener {

	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		if(e.getEntity() instanceof EnderPearl && e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
		
			if(Utils.getOnlineMode().equals(OnlineMode.ON)) {
				if(p.hasPermission(Permissions.ENDERRIDEUSE.getPermission()) && Utils.getEnderRide().contains(UUIDFetcher.getUUID(p.getName()))) {
					if(Utils.getConfig().getBoolean("certain_worlds")) {
						String[] worlds = Utils.getConfig().getString("worlds").split(",");
						
						for(String w : worlds) {
							if(w.equals(p.getWorld().getName())) {
								EntityEnderRide ride = new EntityEnderRide(((CraftWorld)Bukkit.getWorld(p.getWorld().getName())).getHandle());
								
								ride.teleportTo(e.getEntity().getLocation(), true);
								ride.getBukkitEntity().setVelocity(e.getEntity().getVelocity());
								e.getEntity().remove();
								ride.getBukkitEntity().setPassenger(p);	
								return;
							}
						}
					} else {
						EntityEnderRide ride = new EntityEnderRide(((CraftWorld)Bukkit.getWorld(p.getWorld().getName())).getHandle());
						
						ride.teleportTo(e.getEntity().getLocation(), true);
						ride.getBukkitEntity().setVelocity(e.getEntity().getVelocity());
						e.getEntity().remove();
						ride.getBukkitEntity().setPassenger(p);	
					}
				}	
			} else {
				if(p.hasPermission(Permissions.ENDERRIDEUSE.getPermission()) && Utils.getEnderRide().contains(p.getUniqueId())) {
					if(Utils.getConfig().getBoolean("certain_worlds")) {
						String[] worlds = Utils.getConfig().getString("worlds").split(",");
						
						for(String w : worlds) {
							if(w.equals(p.getWorld().getName())) {
								EntityEnderRide ride = new EntityEnderRide(((CraftWorld)Bukkit.getWorld(p.getWorld().getName())).getHandle());
								
								ride.teleportTo(e.getEntity().getLocation(), true);
								ride.getBukkitEntity().setVelocity(e.getEntity().getVelocity());
								e.getEntity().remove();
								ride.getBukkitEntity().setPassenger(p);	
								return;
							}
						}
					} else {
						EntityEnderRide ride = new EntityEnderRide(((CraftWorld)Bukkit.getWorld(p.getWorld().getName())).getHandle());
						
						ride.teleportTo(e.getEntity().getLocation(), true);
						ride.getBukkitEntity().setVelocity(e.getEntity().getVelocity());
						e.getEntity().remove();
						ride.getBukkitEntity().setPassenger(p);	
					}
				}
			}
		}
	}
}
