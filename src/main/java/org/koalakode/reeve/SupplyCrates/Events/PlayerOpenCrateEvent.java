package org.koalakode.reeve.SupplyCrates.Events;
// made by reeve
// on 1:08 AM

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerOpenCrateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {

		return handlers;
	}

	private Player player;
	private ItemStack content;


	public PlayerOpenCrateEvent(Player player, ItemStack content) {

		this.player = player;
		this.content = content;
	}

	public Player getPlayer() {

		return player;
	}

	public ItemStack getContent() {

		return content;
	}

	@Override
	public String getEventName() {

		return super.getEventName();
	}
}
