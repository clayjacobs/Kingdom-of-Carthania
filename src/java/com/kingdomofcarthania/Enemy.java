public class Enemy{
	private int might;
	private int wisdom;
	private int dexterity;
	private int toughness;
	private int vigor;

	public Enemy(int i, Character c) {
		generateStats(i, c);
	}

	//TODO : take into account the character level and current zone to generate stats randomly within a range
	public void generateStats(int level, Character toon){
		int combatZone = toon.getMap().get(toon.getCurrentZone()).getCombatZone();
		double cLevel =  * ((100 + toon.getLevel()) / 100);
		might = generateRandomStat(level, combatZone, cLevel);
		wisdom = generateRandomStat(level, combatZone, cLevel);
		dexterity = generateRandomStat(level, combatZone, cLevel);
		toughness = generateRandomStat(level, combatZone, cLevel);
		vigor = generateRandomVigor(level, combatZone, cLevel);
	}

	public int generateRandomVigor(int lvl, int z, double l){
		return (((int)((1.5 - (lvl * .25)) * l)) * ((z * 25) + Math.floor((Math.random() * 5 + (z * 2)) + (1 + z))));
	}

	public int generateRandomStat(int lvl, int z, double l) {
		return (((int)((1.5 - (lvl * .25)) * l)) * ((z * 5) + Math.floor((Math.random() * 5 + (z * 2)) + (1 + z))));
	}

	public void takeDamage(int d) {
		vigor -= d;
	}

	public boolean alive(){
		if(vigor > 0)
			return true;
		else
			return false;
	}

	public int getEnemyMight(){
		return might;
	}

	public int getEnemyWisdom(){
		return wisdom;
	}

	public int getEnemyDexterity(){
		return dexterity;
	}

	public int getEnemyToughness(){
		return toughness;
	}

	public int getEnemyVigor(){
		return vigor;
	}
}