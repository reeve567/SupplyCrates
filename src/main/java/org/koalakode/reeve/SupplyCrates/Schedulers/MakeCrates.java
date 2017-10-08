package org.koalakode.reeve.SupplyCrates.Schedulers;
// made by reeve
// on 1:34 PM

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.koalakode.reeve.SupplyCrates.Crate;
import org.koalakode.reeve.SupplyCrates.Util.CrateUtil;

public class MakeCrates extends BukkitRunnable {

	private JavaPlugin main;

	private Crate[] crates;
	public Location mrborder = new Location(Bukkit.getWorld("world"),-499,80,-2611);
	public Location Default = new Location(Bukkit.getWorld("asdf"),0,0,0);
	private int i;

	MakeCrates(Crate[] crates,int i,JavaPlugin main) {
		this.crates = crates;
		this.i = i;
		this.main = main;
	}

	@Override
	public void run() {
		crates[i] = new Crate(i, CrateUtil.randLoc(Default),main);
	}
}
