package git.inhostudios.dtba.gameobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	public Player(User user, Inventory inventory, int level, int levelcap, int balance, int hp, int maxhp, double xp, String name) {
		this.user = user;
		this.inventory = inventory;
		this.level = level;
		this.levelcap = levelcap;
		this.balance = balance;
		this.hp = hp;
		this.maxhp = maxhp;
		this.xp = xp;
		this.name = name;
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
	
	// GSon JSon
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static Player fromJson(String json) {
		
		Gson gson = new Gson();

    	JsonParser jp = new JsonParser();
    	JsonObject jo = jp.parse(json).getAsJsonObject();
    	
    	User imUser = gson.fromJson(jo, User.class);
		Inventory imInv = gson.fromJson(jo, Inventory.class);
		int imLevel = jo.get("level").getAsInt();
		int imLevelcap = jo.get("levelcap").getAsInt();
		int imBalance = jo.get("balance").getAsInt();
		int imHp = jo.get("hp").getAsInt();
		int imMaxhp = jo.get("maxhp").getAsInt();
		double imXp = jo.get("xp").getAsDouble();
		String imName = jo.get("name").getAsString();
    	
    	Player output = new Player(imUser,imInv,imLevel,imLevelcap,imBalance,imHp,imMaxhp,imXp,imName);
    	
    	return output;
	}
	
}
