package com.kingdomofcarthania;

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
	private double enemyVunerability;

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
			round(roundCounter);
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
			if(enemiesAlive)
				enemyTurn();
			else
				break;
		}
		else{
			enemyTurn();
			if(alive)
				playerTurn();
			else
				break;
		}
	}

	public double[] dealDamage(double v, String type){
		double[] returnValues = new int[2];
		returnValues[1] = v;
		if(type.equals("Aggresive")){
			if(c.randomChance(75)) {
				returnValues[0] = damageRange(typeOfAttack);
				returnValues[1] += 1;
			}
			else {
				returnValues[1] += 2;
			}
		}
		else if(typeOfAttack.equals("Standard")){
			if(c.randomChance(90)) {
				returnValues[0] = damageRange(typeOfAttack);
			}
			else {
				returnValues[1] += 1;
			}
		}
		else if(typeOfAttack.equals("Spell")){
			if(c.randomChance(80)){
				returnValues[0] = damageRange(typeOfAttack);
				returnValues[1] += .5;
			}
			else {
				returnValues[1] += 1;
			}
		}
		else if(typeOfAttack.equals("Quick")){
			if(c.randomChance(80)){
				returnValues[0] = damageRange(typeOfAttack);
			}
			else {
				returnValues[1] += 1.5;
			}
		}
		else if(typeOfAttack.equals("Defensive")){
			if(c.randomChance(90)) {
				returnValues[0] = damageRange(typeOfAttack);
				returnValues[1] -= 1;
			}
		}
		return returnValues;
	}

	//The player needs to first select the type of attack then the process of the turn goes afterward
	public void playerTurn(){
		String typeOfAttack = "";
		aoe = false;
		int[] values = dealDamage(playerVunerability, typeOfAttack);
		int damage = values[0];
		damage = (int)(damage * ((10.0 + enemyVunerability) / 10.0));
		playerVunerability = values[1];
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

	public void enemyTurn(){
		int damage = enemyDamage();
		damage = (int)(damage * ((10.0 + playerVunerability) / 10.0));
		c.takeDamage(damage);
		alive = c.alive();
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
			return (Math.floor((Math.random()*c.getMight() * 2 * .5) + (int)(c.getMight() * .5)) + Math.floor((Math.random()*c.getDexterity() * 2 * .5) + (int)(c.getDexterity() * .5)));
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

	public int enemyDamage(){
		int typeNum = Math.floor((Math.random() * 6) + 1);
		int i = getEnemyPosititonAlive();
		if(type == 1){
			enemyVunerability += 2;
			return Math.floor((Math.random()*enemies[i].getMight() * 1.75 * 2) + (int)(enemies[i].getMight() * 2));
		}
		else if(type == 2){
			enemyVunerability += 1;
			return (Math.floor((Math.random()*enemies[i].getMight() * 1.75 * .5) + (int)(enemies[i].getMight() * .5)) + Math.floor((Math.random()*enemies[i].getDexterity() * 1.75 * .5) + (int)(enemies[i].getDexterity() * .5)));
		}
		else if(type == 3){
			enemyVunerability += 1;
			return Math.floor((Math.random()*enemies[i].getWisdom() * 1.75 * 1.5) + (int)(enemies[i].getWisdom() * 1.5));
		}
		else if(type == 4){
			enemyVunerability += 1.5;
			return Math.floor((Math.random()*enemies[i].getDexterity() * 1.75 * 1.25) + (int)(enemies[i].getDexterity() * 1.25));
		}
		else if(type == 5){
			return (Math.floor((Math.random()*enemies[i].getMight() * 1.75 * .5) + (int)(enemies[i].getMight() * .5)) + Math.floor((Math.random()*enemies[i].getToughness() * 1.75 * .5) + (int)(enemies[i].getToughness() * .5)));
		}
		else
			return 0;
	}

	public int getEnemyPosititonAlive(){
		for(int i = 0; i < enemies.length; i++){
			if(enemies[i].alive()){
				return i;
			}
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
		int earnedGreatness = (int)((characterLevel * 2.5) + (20) + randomXP);
		return earnedGreatness;
	}

	//Calculate how much gold was earned by a won battle
	public int calcEarnedGold(){
		if(victorious)
			return (int)((50) * (enemies.length) + (Math.floor(Math.random() * 100 + (characterLevel * 1.5) + (1 * characterLevel)));
		else
			return Math.floor(Math.random() * 100 + ((characterLevel * 1.5)(int))) + (1 * characterLevel);
	}

	public void endBattle(){
		c.changeGold(calcEarnedGold());
		c.addGreatness(calcEarnedGreatness());
	}

}