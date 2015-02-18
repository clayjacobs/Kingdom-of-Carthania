public class Battle{
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
	private boolean aoe;

	private double playerVunerability;

	public Battle(Character c){
		character = c;
		characterLevel = c.getLevel();
		characterZone = c.getCurrentZone();
		characterMap = c.getMap();
		characterFollowers = c.getFollowers();

		victorious = false;

		playerVunerability = 0;
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
			playerTurn();
			enemyTurn();
		}
		else{
			enemyTurn();
			playerTurn();
		}
	}

	public void playerTurn(){
		String typeOfAttack = "";
		int damage = 0;
		aoe = false;
		if(typeOfAttack.equals("Aggresive")){
			if(c.randomChance(75)) {
				damage = damageRange(typeOfAttack);
				playerVunerability += 1;
			}
			else {
				playerVunerability += 2;
			}
		}
		else if(typeOfAttack.equals("Standard")){
			if(c.randomChance(90)) {
				damage = damageRange(typeOfAttack);
			}
			else {
				playerVunerability += 1;
			}
		}
		else if(typeOfAttack.equals("Spell")){
			if(c.randomChance(80)){
				damage = damageRange(typeOfAttack);
				playerVunerability += .5;
			}
			else {
				playerVunerability += 1;
			}
		}
		else if(typeOfAttack.equals("Quick")){
			if(c.randomChance(80)){
				damage = damageRange(typeOfAttack);
			}
			else {
				playerVunerability += 1.5;
			}
		}
		else if(typeOfAttack,equals("Defensive")){
			if(c.randomChance(90)) {
				damage = damageRange(typeOfAttack);
				playerVunerability -= 1;
			}
		}
		for (int i=0; i < enemies.length; i++){
			if(enemies[i].alive()){
				enemies[i].takeDamage(damage - enemies[i].getToughness());
				if(aoe) {
					for(int x=i+1; x < enemies.length; x++) {
					enemies[x].takeDamage(damage - enemies[x].getToughness());
					}
				}
				break;
			}
		}
		checkEnemiesLiving();
	}

	public void checkEnemiesLiving(){
		for(int i=0; i < enemies.length; i++){
			enemiesAlive = enemies[i].alive();
		}
	}

	//Generates random damage in a range that depends on your stats levels and type of attack
	public int damageRange(String t){
		if(t.equals("Aggresive")){
			aoe = randomChance(20);
			return Math.floor((Math.random()*c.getMight() * 2 * 2) + (int)(c.getMight() * 2));
		}
		else if(t.equals("Standard")){
			aoe = randomChance(25);
			return (Math.floor((Math.random()*c.getMight() * 2 * .5) + (int)(c.getMight() * .5)) + Math.floor((Math.random()*c.getDexterity() * 2 * .5) + (int)(c.getDexterity() * .5));
		}
		else if(t.equals("Spell")){
			aoe = randomChance(60);
			return Math.floor((Math.random()*c.getWisdom() * 2 * 1.5) + (int)(c.getWisdom() * 1.5));
		}
		else if(t.equals("Quick")){
			aoe = randomChance(10);
			return Math.floor((Math.random()*c.getDexterity() * 2 * 1.25) + (int)(c.getDexterity() * 1.25));
		}
		else if(t.equals("Defensive")){
			aoe = randomChance(30);
			return (Math.floor((Math.random()*c.getMight() * 2 * .5) + (int)(c.getMight() * .5)) + Math.floor((Math.random()*c.getToughness() * 2 * .5) + (int)(c.getToughness() * .5)));
		}
	}

	public void generateEnemies() {
		int numberOfEnemies = characterFollowers.size()+1;
		enemies = new Enemy[numberOfEnemies];
		enemy[0] = new Enemy(0, characterLevel, characterZone);
		for(int i=1; i<enemies.length; i++){
			enemies[i] = new Enemy(i, characterLevel, characterZone);
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
		if(victorious)
			return (50) * (enemies.length) + (Math.floor(Math.random() * 100 + ((characterLevel * 1.5)(int))) + (1 * characterLevel));
		else
			return Math.floor(Math.random() * 100 + ((characterLevel * 1.5)(int))) + (1 * characterLevel);
	}

	public void endBattle(){
		c.changeGold(calcEarnedGold());
		c.addGreatness(calcEarnedGreatness());
	}

}