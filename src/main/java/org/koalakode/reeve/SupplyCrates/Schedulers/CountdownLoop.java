package org.koalakode.reeve.SupplyCrates.Schedulers;
// made by reeve
// on 1:21 AM
//paypal.me/ReeveB

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.koalakode.reeve.SupplyCrates.Crate;

public class CountdownLoop extends BukkitRunnable {

	public static int time = 0;
	public static boolean broadcast = true;
	public static Crate[] crates = new Crate[100];
	private JavaPlugin main;
	private final static String webHook = "https://discordapp" +
			                                     ".com/api/webhooks/361219288637636610/EmW7TyxK4z7h0Xoz7MumsFmyVRpt_fb1DVfKlGYHHif6XkeqM23WKi40nRqQocQa0_JR";
	private static TemmieWebhook temmie = new TemmieWebhook(webHook);

	public CountdownLoop(JavaPlugin main) {
		this.main = main;
	}

	@Override
	public void run() {

		if (time == 0) {

			if (broadcast) {
				broadcast = false;
				DiscordMessage dm = new DiscordMessage("Crates Notifications", " » Crates have been dropped!\nGet them while you can!",
						                                       "https://camo" +
						                                                                                                                              ".githubusercontent" +
						                                                                                                                              ".com/a4e336d9971686ae5698d2a39011e8e499a82475/68747470733a2f2f6a6172692e6c6f6c2f5a6e4e305451654d6d692e706e67");
				//temmie.sendMessage(dm);


				Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&4&lSP&8&l] &cSupplyCrates have been dropped in the Warzone go get them quick!"));
				for (int i = 0; i < 100; i++) {
					new MakeCrates(crates,i,main).runTaskLater(main,i);

				}
			}
			else {
				DiscordMessage dm1 = new DiscordMessage("Crates Notifications"," » Crates are gone!\nCrates will be dropped in 30 mins!", "https://camo.githubusercontent" +
				".com/a4e336d9971686ae5698d2a39011e8e499a82475/68747470733a2f2f6a6172692e6c6f6c2f5a6e4e305451654d6d692e706e67");
				//temmie.sendMessage(dm1);


				Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&4&lSP&8&l] &cSupplyCrates have been removed."));
				broadcast = true;
				for (int i = 0; i < 100; i++) {
					if (!crates[i].getRemoved()) {
						crates[i].setRemoved();
					}
				}
			}
			time = 30;
		}

		time--;
	}

}
