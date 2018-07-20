package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Map {

	private ArrayList<Location> map;
	
	public Map() {
		
	}
	
	public void addLocation(Location location) {
		map.add(location);
	}
	
}
