package git.inhostudios.dtba.gameobjects;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import net.dv8tion.jda.core.entities.User;

public class Player {

	// change from user to userid
	private String userID;
	private int level, levelcap = 50, balance, hp, maxhp = 15;
	private double xp;
	private String name;
	
	private ArrayList<Item> inventory;
	
	// creation constructor
	public Player(String userID, String userName, ArrayList<Item> inventory, int level) {
		this.userID = userID;
		this.inventory = inventory;
		this.level = level;
		this.name = userName;
	}
	
	// checking constructor
	public Player(String userID) {
		this.userID = userID;
	}
	
	public Player(String userID, String userName) {
		this.userID = userID;
		this.name = userName;
	}
	
	// reimport constructor
	public Player(String userID, String userName, ArrayList<Item> inventory, int level, int levelcap, int balance, int hp, int maxhp, double xp) {
		this.userID = userID;
		this.inventory = inventory;
		this.level = level;
		this.levelcap = levelcap;
		this.balance = balance;
		this.hp = hp;
		this.maxhp = maxhp;
		this.xp = xp;
		this.name = userName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
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
	
	public Player fromJson(String json) {

    	JsonParser jp = new JsonParser();
    	JsonObject jo = jp.parse(json).getAsJsonObject();
    	
    	String imUserID = jo.get("userID").getAsString();
		ArrayList<Item> imInventory = new ArrayList<Item>();
		int imLevel = jo.get("level").getAsInt();
		int imLevelcap = jo.get("levelcap").getAsInt();
		int imBalance = jo.get("balance").getAsInt();
		int imHp = jo.get("hp").getAsInt();
		int imMaxhp = jo.get("maxhp").getAsInt();
		double imXp = jo.get("xp").getAsDouble();
		String imName = jo.get("name").getAsString();
    	
    	Player output = new Player(imUserID,imName,imInventory,imLevel,imLevelcap,imBalance,imHp,imMaxhp,imXp);
    	
    	return output;
	}
	
}
