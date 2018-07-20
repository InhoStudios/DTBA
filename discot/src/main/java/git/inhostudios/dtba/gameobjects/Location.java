package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Location {

	private ArrayList<SubLocation> spots = new ArrayList<SubLocation>();
	
	public Location(ArrayList<SubLocation> spots) {
		this.spots = spots;
	}
	
}
