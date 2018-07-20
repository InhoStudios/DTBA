package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Location {

	private ArrayList<SubLocation> spots = new ArrayList<SubLocation>();
	private int distance;
	
	public Location(ArrayList<SubLocation> spots, int distance) {
		this.spots = spots;
		this.distance = distance;
	}
	
}
