package de.mauricius17.enderride.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import de.mauricius17.enderride.system.EnderRide;
import de.mauricius17.enderride.utils.Locations;
import de.mauricius17.enderride.utils.UUIDFetcher;
import de.mauricius17.enderride.utils.Utils;

public class EntityListener implements Listener {

	List<UUID> damage = new ArrayList<>();
	List<UUID> list = new ArrayList<>();
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
		
			if((p.getLocation().getBlock().getType() != Material.AIR)) {
				
				if(!damage.contains(UUIDFetcher.getUUID(p.getName()))) {
					damage.add(UUIDFetcher.getUUID(p.getName()));
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(EnderRide.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							if(damage.contains(UUIDFetcher.getUUID(p.getName()))) {
								damage.remove(UUIDFetcher.getUUID(p.getName()));
							}
							
							if(p.getLocation().getBlock().getType() != Material.AIR && p.getLocation().getBlock().getType() != Material.WHEAT
									&& p.getLocation().getBlock().getType() != Material.GRASS && p.getLocation().getBlock().getType() != Material.FLOWER_POT
									&& p.getLocation().getBlock().getType() != Material.YELLOW_FLOWER && p.getLocation().getBlock().getType() != Material.RED_ROSE
									&& p.getLocation().getBlock().getType() != Material.LONG_GRASS && p.getLocation().getBlock().getType() != Material.WATER
									&& p.getLocation().getBlock().getType() != Material.LAVA && p.getLocation().getBlock().getType() != Material.CROPS
									&& p.getLocation().getBlock().getType() != Material.WEB && p.getLocation().getBlock().getType() != Material.DEAD_BUSH
									&& p.getLocation().getBlock().getType() != Material.BROWN_MUSHROOM && p.getLocation().getBlock().getType() != Material.RED_MUSHROOM
									&& p.getLocation().getBlock().getType() != Material.SUGAR_CANE && p.getLocation().getBlock().getType() != Material.SUGAR_CANE_BLOCK
									&& p.getLocation().getBlock().getType() != Material.FENCE && p.getLocation().getBlock().getType() != Material.FENCE_GATE
									&& p.getLocation().getBlock().getType() != Material.WATER_LILY && p.getLocation().getBlock().getType() != Material.STONE_PLATE
									&& p.getLocation().getBlock().getType() != Material.GOLD_PLATE  && p.getLocation().getBlock().getType() != Material.IRON_PLATE
									&& p.getLocation().getBlock().getType() != Material.STONE_BUTTON && p.getLocation().getBlock().getType() != Material.WOOD_PLATE
									&& p.getLocation().getBlock().getType() != Material.WOOD_BUTTON && p.getLocation().getBlock().getType() != Material.RAILS
									&& p.getLocation().getBlock().getType() != Material.MINECART && p.getLocation().getBlock().getType() != Material.BOAT
									&& p.getLocation().getBlock().getType() != Material.SAPLING && p.getLocation().getBlock().getType() != Material.FIRE
									&& p.getLocation().getBlock().getType() != Material.TORCH && p.getLocation().getBlock().getType() != Material.REDSTONE_COMPARATOR_OFF
									&& p.getLocation().getBlock().getType() != Material.LEVER && p.getLocation().getBlock().getType() != Material.REDSTONE_TORCH_OFF
									&& p.getLocation().getBlock().getType() != Material.REDSTONE_TORCH_ON && p.getLocation().getBlock().getType() != Material.REDSTONE_COMPARATOR
									&& p.getLocation().getBlock().getType() != Material.REDSTONE_WIRE && p.getLocation().getBlock().getType() != Material.REDSTONE_COMPARATOR_ON
									&& p.getLocation().getBlock().getType() != Material.PUMPKIN_SEEDS && p.getLocation().getBlock().getType() != Material.PUMPKIN_STEM
									&& p.getLocation().getBlock().getType() != Material.VINE && p.getLocation().getBlock().getType() != Material.NETHER_WARTS
									&& p.getLocation().getBlock().getType() != Material.ENDER_PORTAL && p.getLocation().getBlock().getType() != Material.TRIPWIRE 
									&& p.getLocation().getBlock().getType() != Material.TRIPWIRE_HOOK && p.getLocation().getBlock().getType() != Material.CARROT
									&& p.getLocation().getBlock().getType() != Material.POTATO && p.getLocation().getBlock().getType() != Material.DAYLIGHT_DETECTOR
									&& p.getLocation().getBlock().getType() != Material.DAYLIGHT_DETECTOR_INVERTED && p.getLocation().getBlock().getType() != Material.DOUBLE_PLANT
									&& p.getLocation().getBlock().getType() != Material.CARPET && p.getLocation().getBlock().getType() != Material.STEP
									&& p.getLocation().getBlock().getType() != Material.DARK_OAK_STAIRS && p.getLocation().getBlock().getType() != Material.COBBLESTONE_STAIRS
									&& p.getLocation().getBlock().getType() != Material.BRICK_STAIRS && p.getLocation().getBlock().getType() != Material.SANDSTONE_STAIRS
									&& p.getLocation().getBlock().getType() != Material.RED_SANDSTONE_STAIRS && p.getLocation().getBlock().getType() != Material.NETHER_BRICK_STAIRS
									&& p.getLocation().getBlock().getType() != Material.SPRUCE_WOOD_STAIRS && p.getLocation().getBlock().getType() != Material.BIRCH_WOOD_STAIRS
									&& p.getLocation().getBlock().getType() != Material.JUNGLE_WOOD_STAIRS && p.getLocation().getBlock().getType() != Material.QUARTZ_STAIRS
									&& p.getLocation().getBlock().getType() != Material.ACACIA_STAIRS && p.getLocation().getBlock().getType() != Material.SMOOTH_STAIRS
									&& p.getLocation().getBlock().getType() != Material.WOOD_STAIRS && p.getLocation().getBlock().getType() != Material.STATIONARY_LAVA
									&& p.getLocation().getBlock().getType() != Material.STATIONARY_WATER && p.getLocation().getBlock().getType() != Material.WOOD_STEP
									&& p.getLocation().getBlock().getType() != Material.SKULL) {
								p.teleport(Locations.getLocation(Locations.RESPAWNPOINT));
								
								
								if(!list.contains(UUIDFetcher.getUUID(p.getName()))) {
									list.add(UUIDFetcher.getUUID(p.getName()));
									p.sendMessage(Utils.getPrefix() + ChatColor.translateAlternateColorCodes('&', Utils.getMessages().getString("inside.block")));
															
									Bukkit.getScheduler().scheduleSyncDelayedTask(EnderRide.getInstance(), new Runnable() {
										
										@Override
										public void run() {
											if(list.contains(UUIDFetcher.getUUID(p.getName()))) {
												list.remove(UUIDFetcher.getUUID(p.getName()));
											}
										}
									}, 20);
								}					
							}
						}
					}, 20*2);
				}
			}
		}
	}
}
