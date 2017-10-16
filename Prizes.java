package org.koalakode.reeve.SupplyCrates;
// made by reeve
// on 3:39 PM

public enum Prizes {
	
	/*
	hydrosoul;0.5%
	mysticalsoul:1%
	rare soul;5%
	uncommon soul;7%
	common soul;10%
	random common ce;11.5
	any piece of amor prot 4 unbreaking 1;15%
	random vanilla enchant (expect sharp V) (ALL ENCHANTS);35
	money;10%
	obey;15
	*/
	
	OBBY(15), PROTARMOR(5), RANDENCH(35), RANDCE(11.5), COMMONSOUL(10), UNCOMMONSOUL(7), RARESOUL(5), MYSTICALSOUL(1), HYDROSOUL(0.5), MONEY(10);

	private double chance;

	Prizes(double chance) {
		this.chance = chance;
	}

	public double getChance() {
		return chance;
	}
}