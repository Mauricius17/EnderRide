package de.mauricius17.enderride.enderride;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.EntityEnderPearl;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;
import net.minecraft.server.v1_8_R3.World;

public class EntityEnderRide extends EntityEnderPearl {

	public EntityEnderRide(World world) {
        super(world);
    }

    @Override
    protected void a(MovingObjectPosition movingobjectposition) {
    	double x = this.getBukkitEntity().getLocation().getX();
    	double y = this.getBukkitEntity().getLocation().getY() - 1;
    	double z = this.getBukkitEntity().getLocation().getZ();
    	
    	Location loc = new Location(Bukkit.getWorld(this.getBukkitEntity().getWorld().getName()), x, y, z);
    	
    	if(loc.getBlock().getType() != org.bukkit.Material.AIR) {
    		this.die();
    	}
    }
}