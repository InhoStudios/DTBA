package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Quest {

	private Location questLocation;
	private int minXP;
	private String title;
	
	
	public Quest(String title) {
		this.title = title;
	}
	
	public String getQuestTitle() {
		return title;
	}
	
	public void setLocation(Location location) {
		questLocation = location;
	}
	
	public Location getLocation() {
		return questLocation;
	}
	
}
