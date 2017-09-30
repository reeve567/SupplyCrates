package org.koalakode.reeve.SupplyCrates;
// made by reeve
// on 12:02 PM

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Crate {

	private int id;
	private Location loc;
	private boolean found = false;
	private boolean removed = false;
	private Hologram holo;


	public Crate(int id, Location loc, JavaPlugin main) {

		this.id = id;
		this.loc = loc;
		Location holoLoc = new Location(loc.getWorld(),loc.getBlockX()+0.5,loc.getBlockY()+1.5,loc.getBlockZ()+0.5);

		loc.getBlock().setType(Material.ENDER_CHEST);

		holo = HologramsAPI.createHologram(main,holoLoc);
		TextLine it = holo.appendTextLine(ChatColor.translateAlternateColorCodes('&',"&8-=-=-=-&4SupplyCrate&8-=-=-=-"));
	}

	public int getId() {

		return id;
	}

	public void setRemoved() {

		removed = true;
		loc.getBlock().setType(Material.AIR);
		holo.delete();
	}

	public boolean getRemoved() {

		return removed;
	}

	public void setFound() {

		found = true;
	}

	public boolean getFound() {

		return found;
	}

	public Location getLoc() {

		return loc;
	}
}
