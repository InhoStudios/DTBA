package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Game {

	private Map map;
	private ArrayList<Quest> quests = new ArrayList<Quest>();
	private Location loc;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public Game() {
		map = new Map();
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
}
