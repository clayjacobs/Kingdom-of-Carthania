public Class Character{
	private int xCoord;
	private int yCoord;
	private int currentZone;
	private Inventory inventory;
	private int gold;

	private ArrayList<Follower> followers;

	private int might;
	private int wisdom;
	private int dexterity;
	private int toughness;

	private int level;
	private int reputation;
	private int greatness;

	private String title;


	public Character(){
		resetCharacter();
	}

	public void resetCharacter(){
		xCoord = 0;
		yCoord = 0;
		currentZone = 1;
		inventory = new Inventory();
		gold = 100;
		followers = new ArrayList<Follower>;
		might = 10;
		wisdom = 10;
		dexterity = 10;
		toughness = 10;
		level = 1;
		reputation = 0;
		greatness = 0;
		title = "Citizen of Carthania"
	}

	//make new stats
	public void levelUp(){
		level++;
		newTitle();
	}

	public int getLevel(){
		return level;
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

	public void changeGold(int v){
		gold += v;
	}

	//has a random chance of starting a battle, should be higher/lower probability on the kind of terrain
	public void randomBattle(){

	}
}