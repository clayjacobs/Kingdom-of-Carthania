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
		might = (1.5 - (level * .25))
		wisdom = (1.5 - (level * .25))
		dexterity = (1.5 - (level * .25))
		toughness = (1.5 - (level * .25))
		vigor = (1.5 - (level * .25))
	}
}