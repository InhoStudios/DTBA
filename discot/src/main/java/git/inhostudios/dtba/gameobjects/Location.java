package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Location {

	private ArrayList<SubLocation> spots = new ArrayList<SubLocation>();
	private int distance = 0;
	
	public Location(int distance) {
		this.distance = distance;
	}
	
	public void addSubLocation(SubLocation sub) {
		spots.add(sub);
	}
	
}
