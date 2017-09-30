package org.koalakode.reeve.SupplyCrates.Util;
// made by reeve
// on 12:05 PM

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class CrateUtil {

	public static int check(int x, int z, World world) {

		Location loc = new Location(world,x,0,z);
		for (int y = 150; y >= 50; y--) {
			loc.setY(y);
			if (loc.getBlock() != null) {
				if (loc.getBlock().getType().equals(Material.AIR)) {
					loc.setY(y - 1);
					if (!(loc.getBlock().getType().equals(Material.AIR))) {
						return y;
					}
				}
			}
		}
		return 255;
	}

	public static Location randLoc(Location mid) {

		final int midSize = 75;
		final int outerBounds = 375;

		Location loc = new Location(mid.getWorld(),mid.getBlockX(),0,mid.getBlockZ());

		int ranX = getRandom(-outerBounds,outerBounds);
		int ranZ = getRandom(-outerBounds,outerBounds);

		while (true) {
			if (((ranZ < -midSize || ranZ > midSize) || (ranX < -midSize || ranX > midSize))) {
				break;
			}
			ranX = getRandom(-outerBounds,outerBounds);
			ranZ = getRandom(-outerBounds,outerBounds);
		}

		loc.setX(ranX + mid.getBlockX());
		loc.setZ(ranZ + mid.getBlockZ());
		loc.setY(check(loc.getBlockX(),loc.getBlockZ(),loc.getWorld()));

		return loc;
	}



	public static int getRandom(int min,int max) {
		Random r = new Random();
		return r.nextInt((max + 1) - min) + min;
	}

}
