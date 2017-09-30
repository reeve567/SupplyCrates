package org.koalakode.reeve.SupplyCrates;
// made by reeve
// on 3:39 PM

public enum  Prizes {
	OBBY(10),
	RANDCE(30),
	COMMONSOUL(15),
	UNCOMMONSOUL(10),
	RARESOUL(7),
	MYSTICALSOUL(3),
	HYDROSOUL(2),
	MONEY(13),
	GOLDENAPPLES(10)

	;

	private int chance;

	Prizes(int chance) {
		this.chance = chance;
	}

	public int getChance() {

		return chance;
	}
}


