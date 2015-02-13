public class Inventory{
	private ArrayList<Item> current;

	public Inventory()
	{

	}

	public void removeItem(Item i)
	{
		int otherId = i.getItemId();
		for (int x=0; x<current.size(); x++) {
			if(otherId == current.get(x).getItemId()){
				current.remove(x);
				break;
			}
		}
	}

	public void addItem(Item i){
		current.add(i);
	}

	public void sellItem(Character c, Item i){
		c.changeGold(i.getValue());
		removeItem(i);
	}

	public void buyItem(Character c, Item i){
		c.changeGold(-1*i.getValue());
		addItem(i);
	}
}