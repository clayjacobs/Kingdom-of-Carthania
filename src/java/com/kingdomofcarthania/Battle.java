public Class Battle{
	private int characterLevel;
	private int characterZone;
	private ArrayList<Zone> characterMap;
	private ArrayList<Follower> characterFollowers;
	private Character character;

	private boolean victorious;

	public Battle(Character c){
		character = c;
		characterLevel = c.getLevel();
		characterZone = c.getCurrentZone();
		characterMap = c.getMap();
		characterFollowers = c.getFollowers();

		victorious = false;
	}

	public void generateEnemies() {
		int numberOfEnemies = characterFollowers.size()+1;

	}

	//Calculate how much greatness was earned by a battle
	public int calcEarnedGreatness(){
		int randomXP = 0;
		if(victorious)
			randomXP = (Math.floor((Math.random() * (10 + (characterLevel * 2.5)) + (1 + characterLevel))));
		int earnedGreatness = (((characterLevel * 2.5)(int)) + (20) + randomXP);
		return earnedGreatness;
	}

	//Calculate how much gold was earned by a won battle
	public int calcEarnedGold(){

	}

	public void endBattle(){
		c.changeGold(calcEarnedGold());
		c.addGreatness(calcEarnedGreatness());
	}

}