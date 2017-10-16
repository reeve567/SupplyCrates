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
	public Location mrborder = new Location(Bukkit.getWorld("world"), -499, 80, -2611);
	public Location Default = new Location(Bukkit.getWorld("asdf"), 0, 0, 0);
	
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
			
			double x = e.getClickedBlock().getX() - mrborder.getBlockX();
			double z = e.getClickedBlock().getZ() - mrborder.getBlockZ();
			
			if (((-outerBounds < x && x < -midSize) || (midSize < x && x < outerBounds)) || ((-outerBounds < z && z < -midSize) || (midSize < z && z < outerBounds))) {
				e.setCancelled(true);
				
				Player player = e.getPlayer();
				
				double chance = 0;
				
				double r = CrateUtil.getRandom(0, 100);
				if (r <= (chance += Prizes.OBBY.getChance())) {
					int a = CrateUtil.getRandom(1, 3);
					for (int i = 1; i <= a; i++) {
						inv.addItem(new ItemStack(Material.OBSIDIAN, 64));
					}
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.PROTARMOR.getChance())) {
					int k = CrateUtil.getRandom(0,3);
					ItemStack[] list = new ItemStack[4];
				
					list[0] = new ItemStack(Material.DIAMOND_HELMET);
					list[1] = new ItemStack(Material.DIAMOND_LEGGINGS);
					list[2] = new ItemStack(Material.DIAMOND_BOOTS);
					list[3] = new ItemStack(Material.DIAMOND_CHESTPLATE);
					
					ItemStack it = list[k];
					it.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);
					inv.addItem(it);
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
					
					
					inv.addItem(MainUtil.bookGive(C.getName(), false));
					player.openInventory(inv);
					
				} else if (r <= (chance += Prizes.RANDENCH.getChance())) {
					
					Enchantment randEnchant;
					int lvl;
					do {
						randEnchant = Enchantment.values()[(int) (Math.random() * Enchantment.values().length)];
						lvl = CrateUtil.getRandom(randEnchant.getStartLevel(), randEnchant.getMaxLevel());
					}
					while (randEnchant.equals(Enchantment.DAMAGE_ALL) && lvl == 5);
					
					
					ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta im = book.getItemMeta();
					im.addEnchant(randEnchant, lvl, false);
					book.setItemMeta(im);
					inv.addItem(book);
					
					
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
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &e" + player.getName() + "&c Has " +
							"just got " +
							"&5&l&oMysticial Soul&c In a Supply crate. Catch " +
							"them quickly!"));
					player.openInventory(inv);
				} else if (r <= (chance += Prizes.HYDROSOUL.getChance())) {
					inv.addItem(Souls.HYDRO.getItem());
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &e" + player.getName() + "&c Has " +
							"just got " +
							"&1&l&oHydro Soul&c In a Supply crate. Catch them " +
							"quickly!"));
					player.openInventory(inv);
				} else if (r <= chance + Prizes.MONEY.getChance()) {
					int l = CrateUtil.getRandom(1, 5);
					
					if (l == 1) {
						economy.depositPlayer(player, 5000);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &cYou have gotten &e$5000!"));
					} else if (l == 2) {
						economy.depositPlayer(player, 2500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &cYou have gotten &e$2500!"));
					} else if (l == 3) {
						economy.depositPlayer(player, 1500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &cYou have gotten &e$1500!"));
					} else if (l == 4) {
						economy.depositPlayer(player, 7500);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &cYou have gotten &e$7500!"));
					} else if (l == 5) {
						economy.depositPlayer(player, 10000);
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l] &cYou have gotten &e$10000!"));
					}
					
				} else {
					player.sendMessage("uhm something happened...");
					player.sendMessage(String.valueOf(r));
					player.sendMessage(String.valueOf(chance));
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
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4SPC&8&l]&c All Supply Crates has been taken, " +
							"another round of supply crates will " +
							"start in 30minutes!"));
				} else if (left % 5 == 0) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSPC&8&l]&c There is &e" + left + "&c " +
							"supplycrates left in the warzone go get them" +
							" quick before they end!"));
				}
				
			}
		}
	}
	
}
