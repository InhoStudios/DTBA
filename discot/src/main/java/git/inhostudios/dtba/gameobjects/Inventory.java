package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<Item> inv;
	
	public Inventory() {
		
	}
	
	public void addItem(Item item) {
		inv.add(item);
	}
	
	public ArrayList<Item> getInventory(){
		return inv;
	}
	
	public Item getItemByIndex(int index) {
		if(index <= inv.size()) {
			return inv.get(index);
		} else {
			return null;
		}
	}
	
}
