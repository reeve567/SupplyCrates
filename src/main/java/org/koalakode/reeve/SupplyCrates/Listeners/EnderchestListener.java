package org.koalakode.reeve.SupplyCrates.Listeners;
// made by reeve
// on 9:37 PM

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.koalakode.reeve.SupplyCrates.Crate;
import org.koalakode.reeve.SupplyCrates.Prizes;
import org.koalakode.reeve.SupplyCrates.Schedulers.CountdownLoop;
import org.koalakode.reeve.SupplyCrates.Util.CrateUtil;
import org.koalakode.reeve.CustomEnchants.Enums.CustomEnchants;
import org.koalakode.reeve.CustomEnchants.Enums.Rarities;
import org.koalakode.reeve.CustomEnchants.Enums.Souls;
import org.koalakode.reeve.CustomEnchants.Util.MainUtil;

import java.util.ArrayList;


public class EnderchestListener implements Listener {

	private Economy economy;
	private Crate[] crates;
	public Location mrborder = new Location(Bukkit.getWorld("world"),-499,80,-2611);
	public Location Default = new Location(Bukkit.getWorld("world"),0,0,0);

	EnderchestListener(Economy economy, Crate[] crates) {
		this.crates = crates;
		this.economy = economy;
	}

	@EventHandler
	public void onChestInteract(PlayerInteractEvent e) {

		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Crate");

		if (!e.isCancelled() && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {

			final int midSize = 75;
			final int outerBounds = 375;

			double x = e.getClickedBlock().getX() - Default.getBlockX();
			double z = e.getClickedBlock().getZ() - Default.getBlockZ();

			if (((-outerBounds < x && x < -midSize) || (midSize < x && x < outerBounds)) || ((-outerBounds < z && z < -midSize) || (midSize < z && z < outerBounds))) {
				e.setCancelled(true);

				Player player = e.getPlayer();

				int chance = 0;

				int r = CrateUtil.getRandom(0, 100);
				if (r <= (chance += Prizes.OBBY.getChance())) {
					int a = CrateUtil.getRandom(1, 3);
					for (int i = 1; i <= a; i++) {
						inv.addItem(new ItemStack(Material.OBSIDIAN, 64));
					}
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.RANDCE.getChance())) {
					CustomEnchants C;
					ArrayList<CustomEnchants> ce = new ArrayList<CustomEnchants>();
					for (CustomEnchants c : CustomEnchants.values()) {
						if (c.getRarity().equals(Rarities.COMMON)) {
							ce.add(c);
						}
					}

					int l = CrateUtil.getRandom(0, ce.size() - 1);
					C = ce.get(l);

					Enchantment randEnchant = Enchantment.values()[(int) (Math.random() * Enchantment.values().length)];
					int lvl = CrateUtil.getRandom(randEnchant.getStartLevel(), randEnchant.getMaxLevel());
					while (randEnchant.equals(Enchantment.DAMAGE_ALL) && lvl == 5) {
						randEnchant = Enchantment.values()[(int) (Math.random() * Enchantment.values().length)];
						lvl = CrateUtil.getRandom(randEnchant.getStartLevel(), randEnchant.getMaxLevel());
					}

					int w = CrateUtil.getRandom(1, 2);
					if (w == 1) {
						inv.addItem(MainUtil.bookGive(C.getName(), false));
					} else {
						ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
						ItemMeta im = book.getItemMeta();
						im.addEnchant(randEnchant, lvl, false);
						book.setItemMeta(im);
						inv.addItem(book);
					}
					player.openInventory(inv);

				} else if (r <= (chance += Prizes.COMMONSOUL.getChance())) {
					inv.addItem(Souls.COMMON.getItem());
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.UNCOMMONSOUL.getChance())) {
					inv.addItem(Souls.UNCOMMON.getItem());
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.RARESOUL.getChance())) {
					inv.addItem(Souls.RARE.getItem());
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.MYSTICALSOUL.getChance())) {
					inv.addItem(Souls.MYSTICAL.getItem());
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &e" + player.getName() + "&c Has just got " +
							                                                                                "&5&l&oMysticial Soul&c In a Supply crate. Catch " +
							                                                                                "them quickly!"));
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.HYDROSOUL.getChance())) {
					inv.addItem(Souls.HYDRO.getItem());
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &e" + player.getName() + "&c Has just got " +
							                                                                                "&1&l&oHydro Soul&c In a Supply crate. Catch them " +
							                                                                                "quickly!"));
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.MONEY.getChance())) {
					int l = CrateUtil.getRandom(1, 5);

					if (l == 1) {
						economy.depositPlayer(player, 5000);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cYou have gotten &e$5000!"));
					}
					else if (l==2) {
						economy.depositPlayer(player, 2500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cYou have gotten &e$2500!"));
					}
					else if (l==3) {
						economy.depositPlayer(player, 1500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cYou have gotten &e$1500!"));
					}
					else if (l==4) {
						economy.depositPlayer(player, 7500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cYou have gotten &e$7500!"));
					}
					else if (l==5) {
						economy.depositPlayer(player, 10000);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cYou have gotten &e$10000!"));
					}

				} else if (r <= chance + Prizes.GOLDENAPPLES.getChance()) {
					inv.addItem(new ItemStack(Material.GOLDEN_APPLE,8, (short) 1));
					player.openInventory(inv);
				} else {
					player.sendMessage("uhm something happened...");
				}
				int left = 100;
				for (Crate c : crates) {
					if (c != null && c.getLoc().equals(e.getClickedBlock().getLocation())) {
						c.setRemoved();
						c.setFound();
					}
					if (c != null && c.getFound()) {
						left--;
					}
				}
				if (left == 0) {
					CountdownLoop.time = 0;
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&4SP&8&l]&c All Supply Crates has been taken, " +
							                                                                               "another round of supply crates will " +
							                                                                               "start in 30minutes!"));
				}
				else if (left%5 == 0) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&4&lSP&8&l]&c There is &e" + left + "&c " +
							                                                                               "supplycrates left in the warzone go get them" +
							                                                                               " quick before they end!"));
				}

			}
		}
	}

}
