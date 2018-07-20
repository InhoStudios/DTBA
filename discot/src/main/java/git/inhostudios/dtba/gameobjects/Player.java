package git.inhostudios.dtba.gameobjects;

import net.dv8tion.jda.core.entities.User;

public class Player {

	private User user;
	private Inventory inventory;
	private int level, levelcap = 50, balance;
	private double xp;
	
	public Player(User user, Inventory inventory, int level) {
		this.user = user;
		this.inventory = inventory;
		this.level = level;
	}
	
	public User getUser() {
		return user;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void checkForLevelUp() {
		if(xp >= levelcap) {
			level++;
			levelcap *= 1.25;
			xp = 0;
		}
	}
	
}
