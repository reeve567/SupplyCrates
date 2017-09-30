package org.koalakode.reeve.SupplyCrates.Listeners;
// made by reeve
// on 1:12 AM

import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.koalakode.reeve.SupplyCrates.Crate;

public class ListenerHandler {

	private JavaPlugin main;
	private Economy economy;
	private Crate[] crates;

	public ListenerHandler(JavaPlugin main, Economy economy,Crate[] crates) {

		this.main = main;
		this.economy = economy;
		this.crates = crates;
	}


	public void Init() {

		register(new EnderchestListener(economy, crates));
	}


	private void register(Listener l) {

		main.getServer().getPluginManager().registerEvents(l, main);
	}

}
