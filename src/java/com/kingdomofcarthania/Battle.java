public Class Battle{
	private int characterLevel;
	private int characterZone;
	private ArrayList<Zone> characterMap;
	private ArrayList<Follower> characterFollowers;
	private Character character;

	private Enemy[] enemies;

	private boolean faster;
	private boolean victorious;
	private boolean alive;
	private boolean enemiesAlive;

	public Battle(Character c){
		character = c;
		characterLevel = c.getLevel();
		characterZone = c.getCurrentZone();
		characterMap = c.getMap();
		characterFollowers = c.getFollowers();

		victorious = false;
	}

	public void fight(){
		generateEnemies();
		checkDex();
		int roundCounter = 0;
		while(alive && enemiesAlive){
			roundCounter++;
			round(i);
		}
		if(alive){
			victorious = true;
		}
		endBattle();
	}

	public void checkDex(){
		if(c.getDexterity() > enemies[0].getDexterity()){
			faster = true;
		}
		else
			faster = false;
	}

	public void round(int roundNum){
		if(faster) {
			turn();
			enemyTurn();
		}
		else{
			enemyTurn();
			turn();
		}
	}

	public void turn(){
		String typeOfAttack = "";
		int intensity;
		if(typeOfAttack.equals("Aggresive")){
			intensity = 2;
		}
		else if(typeOfAttack.equals("Standard")){
			intensity = 1;
		}
		else if(typeOfAttack.equals("Spell")){
			intensity = 1.5;
		}
		else if(typeOfAttack.equals("Quick")){
			intensity = 1.25;
		}
		else if(typeOfAttack,equals("Defensive")){
			intensity = 1;
		}
		damage * intensity
	}

	public void generateEnemies() {
		int numberOfEnemies = characterFollowers.size()+1;
		enemies = new Enemy[numberOfEnemies];
		enemy[0] = new Enemy(0, characterLevel, characterZone);
		for(int i=1; i<enemies.length; i++){
			enemies[i] = new Enemy(0, characterLevel, characterZone);
		}
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