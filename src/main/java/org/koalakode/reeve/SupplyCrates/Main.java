package org.koalakode.reeve.SupplyCrates;
// made by reeve
// on 12:52 AM

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.koalakode.reeve.SupplyCrates.Listeners.ListenerHandler;
import org.koalakode.reeve.SupplyCrates.Schedulers.CountdownLoop;
import org.koalakode.reeve.SupplyCrates.Util.ConfigCheck;

public class Main extends JavaPlugin implements Listener {


	public static Economy eco = null;

	@Override
	public void onEnable() {

		ConfigCheck configCheck = new ConfigCheck(this);

		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + getDescription().getName() + " Enabled.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + getDescription().getName() + " made by Xwy.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Current Version: " + ChatColor.GRAY + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");

		if (!setupEconomy()) {
			System.out.println("No vault found, disabling SupplyCrates.");
			setEnabled(false);
		}

		if (eco != null) {
			new CountdownLoop(this).runTaskTimer(this,0,1200);

			ListenerHandler listenerHandler = new ListenerHandler(this,eco, CountdownLoop.crates);
			getServer().getPluginManager().registerEvents(this,this);
			listenerHandler.Init();
		}
		else {
			setEnabled(false);
		}
	}

	public void onDisable() {

		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + getDescription().getName() + " Disabled.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + getDescription().getName() + " made by Xwy.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Current Version: " + ChatColor.GRAY + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");

		if (CountdownLoop.crates != null) {
			for (Crate c: CountdownLoop.crates) {
				if (c != null && !c.getRemoved())
					c.setRemoved();
			}
		}
	}

	@EventHandler
	public void onComman(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/spc")) {
			e.setCancelled(true);
			Player sender = e.getPlayer();
			if (e.getMessage().length() != 10 || !e.getPlayer().hasPermission("supplycrates.admin")) {
				if (!CountdownLoop.broadcast) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&4&lSPC&8&l] &cthere is &e" + CountdownLoop.time + "m&c until " +
							                                                              "supplycrates removal."));
				}
				else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&4&lSPC&8&l] &cthere is &e" + CountdownLoop.time + "m&c until " +
							                                                              "supplycrates are dropped."));
				}
			}
			else if (e.getMessage().length() == 10 && e.getMessage().substring(5,10).equalsIgnoreCase("force")) {
				sender.sendMessage(ChatColor.RED + "Switching to the next phase soon...");
				CountdownLoop.time = 0;
			}
		}
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		eco = rsp.getProvider();
		return eco != null;
	}

}
