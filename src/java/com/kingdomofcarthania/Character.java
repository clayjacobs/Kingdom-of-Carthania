package com.kingdomofcarthania;

public class Character{
	private int xCoord;
	private int yCoord;
	private int currentZone;
	private Map world;
	private ArrayList<Zone> map;
	private Inventory inventory;
	private int gold;

	private ArrayList<Follower> followers;

	private int might;
	private int wisdom;
	private int dexterity;
	private int toughness;
	private int vigor;
	private int maxVigor;

	private int level;
	private int reputation;
	private int greatness;
	private int greatnessNeeded;

	private String title;


	public Character(){
	}

	public void resetCharacter(){
		xCoord = 0;
		yCoord = 0;
		currentZone = 1;
		world = new Map();
		map = world.getZones();
		inventory = new Inventory();
		gold = 100;
		followers = new ArrayList<Follower>;
		might = 10;
		wisdom = 10;
		dexterity = 10;
		toughness = 10;
		vigor = 50;
		maxVigor = 50;
		level = 1;
		reputation = 0;
		greatness = 0;
		greatnessNeeded = 100;
		title = "Citizen of Carthania"
	}

	public int getLevel(){
		return level;
	}

	public int getCurrentZone(){
		return currentZone;
	}

	public ArrayList<Zone> getMap(){
		return map;
	}

	public ArrayList<Follower> getFollowers(){
		return followers;
	}

	public int getGreatness(){
		return greatness;
	}

	public int getGreatnessNeeded(){
		return greatnessNeeded;
	}

	public int getDexterity(){
		return dexterity;
	}

	//make new stats
	public void levelUp(){
		level++;
		newTitle();
		getNewStats();
		updateGreatnessNeeded();
	}

	public void addGreatness(int xp){
		greatness += xp;
		if(greatness > greatnessNeeded){
			greatness = greatness - greatnessNeeded;
			levelUp();
		}
	}

	//Write algorithm for getting updated Stats
	public void getNewStats(){
		might
		wisdom
		dexterity
		toughness
		maxVigor
		vigor = maxVigor;
	}

	public void updateGreatnessNeeded(){
		greatnessNeeded = (greatnessNeeded *1.2) + 20 + level;
	}

	public void newTitle(){
		if(getLevel() < 10)
			title = "Citizen of Carthania";
		else if(getLevel() < 20)
			title = "Squire of Carthania";
		else if (getLevel() < 30)
			title = "Warrior of Carthania";
		else if(getLevel() < 40)
			title = "Knight of Carthania";
		else if(getLevel() < 50)
			title = "Earl of Carthania";
		else
			title = "King of Carthania";
	}

	public void moveRight(){
		xCoord++;
		randomBattle();
	}

	public void moveLeft(){
		xCoord--;
		randomBattle();
	}

	public void moveUp(){
		yCoord++;
		randomBattle();
	}

	public void moveDown(){
		yCoord--;
		randomBattle();
	}

	//Value will have to be negative coming into the method
	public void changeGold(int v){
		gold += v;
	}

	//has a random chance of starting a battle, should be higher/lower probability on the kind of terrain
	public void randomBattle(){
		if(map.get(currentZone).getStatus(xCoord, yCoord).equals("Building")){
			break;
		}
		else if (map.get(currentZone).getStatus(xCoord, yCoord).equals("Grass")){
			if(Math.floor((Math.random() * 6) + 1) == 1)
				Battle newBattle = new Battle(self);
				newBattle.fight();
				break;
			else
				break;
		}
	}

	public void takeDamage(int d){
		vigor -= d;
	}

	public boolean alive(){
		if(vigor > 0)
			return true;
		else
			return false;
	}

	public void heal(){
		vigor = maxVigor;
	}

	public boolean randomChance(int c){
		int random = Math.floor((Math.random() * 100) + 1);
		if(c > random || c == random)
			return true;
		else
			return false;
	}
}