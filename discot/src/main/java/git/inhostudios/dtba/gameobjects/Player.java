package git.inhostudios.dtba.gameobjects;

import net.dv8tion.jda.core.entities.User;

public class Player {

	private User user;
	private Inventory inventory;
	private int level, levelcap = 50, balance, hp, maxhp = 15;
	private double xp;
	private String name;
	
	public Player(User user, Inventory inventory, int level) {
		this.user = user;
		this.inventory = inventory;
		this.level = level;
		this.name = user.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
			maxhp += 5;
		}
	}
	
}
