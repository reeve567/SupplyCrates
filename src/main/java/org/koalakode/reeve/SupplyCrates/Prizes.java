package org.koalakode.reeve.SupplyCrates;
// made by reeve
// on 3:39 PM

public enum Prizes {
	
	OBBY(15), PROTARMOR(5), RANDENCH(35), RANDCE(11.5), COMMONSOUL(10), UNCOMMONSOUL(7), RARESOUL(5), MYSTICALSOUL(1), HYDROSOUL(0.5), MONEY(10);

	private double chance;

	Prizes(double chance) {
		this.chance = chance;
	}

	public double getChance() {
		return chance;
	}
}