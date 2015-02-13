public class Item{
	private int id;
	private String type;
	private int value;

	public Item(int i){
		id = i;
		if (id < 100) {
			type = "Good";
		}
		else if(id > 100 && id < 200){
			type = "Weapon";
		}
		else if(id > 200 && id < 300){
			type = "Relic";
		}
	}

	public int getValue(){
		return value;
	}

	public int getItemId(){
		return id;
	}
}