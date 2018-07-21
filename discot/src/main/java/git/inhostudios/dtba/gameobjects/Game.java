package git.inhostudios.dtba.gameobjects;

import java.util.ArrayList;

public class Game {

	private Map map;
	
	private int distance = 10;
	
	private Location pureia, mykovia, excelsior, munea, galei;
	
	private SubLocation trixn, hexna, hydrox, florin; // pureia	
	private SubLocation topivia, mydroxn, dexlr; // mykovia
	private SubLocation neolex, nancis, hypodea, isain; // excelsior
	private SubLocation shraxor, gaman, vaskite, kypovia; // munea
	private SubLocation sisadea, aldrox, stylox, axnor, xleon; // galei
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Quest tavernEspionage;
	private ArrayList<Quest> quests = new ArrayList<Quest>();
	
	public Game() {
		map = new Map();
		addAndInitLocations();
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public String getQuests() {
		
		tavernEspionage = new Quest("Tavern Espionage");
		
		quests.add(tavernEspionage);
		
		String questTitles = "Quests: ";
		
		for(int i = 0; i < quests.size(); i++) {
			questTitles = questTitles + "\n- " + quests.get(i).getQuestTitle();
		}
		
		return questTitles;
	}
	
	private void addAndInitLocations() {
		// pureia
		pureia = new Location(5);
		
			trixn = new SubLocation("Trixn");
		pureia.addSubLocation(trixn);
		
			hexna = new SubLocation("Hexna");
		pureia.addSubLocation(hexna);
		
			hydrox = new SubLocation("Hydrox");
		pureia.addSubLocation(hydrox);
		
			florin = new SubLocation("Florin");
		pureia.addSubLocation(florin);
		
		// mykovia
		mykovia = new Location(distance);
		
			topivia = new SubLocation("Topivia");
		mykovia.addSubLocation(topivia);
		
			mydroxn = new SubLocation("Mydroxn");
		mykovia.addSubLocation(mydroxn);
		
			dexlr = new SubLocation("Dexlr");
		mykovia.addSubLocation(dexlr);
		// excelsior
		excelsior = new Location(distance);
		
			neolex = new SubLocation("Neolex");
		excelsior.addSubLocation(neolex);
		
			nancis = new SubLocation("Paer-Nancis");
		excelsior.addSubLocation(nancis);
		
			hypodea = new SubLocation("Hypodea");
		excelsior.addSubLocation(hypodea);
		
			isain = new SubLocation("Isain");
		excelsior.addSubLocation(isain);
		// munea
		munea = new Location(distance);
		
			shraxor = new SubLocation("Shraxor");
		munea.addSubLocation(shraxor);
		
			gaman = new SubLocation("Gaman");
		munea.addSubLocation(gaman);
		
			vaskite = new SubLocation("Vaskite");
		munea.addSubLocation(vaskite);
		
			kypovia = new SubLocation("Kypovia");
		munea.addSubLocation(kypovia);
		// galei
		galei = new Location(distance);
		
			sisadea = new SubLocation("Sisadea");
		galei.addSubLocation(sisadea);
		
			aldrox = new SubLocation("Aldrox");
		galei.addSubLocation(aldrox);
		
			stylox = new SubLocation("Stylox");
		galei.addSubLocation(stylox);
		
			axnor = new SubLocation("Axnor");
		galei.addSubLocation(axnor);
			
			xleon = new SubLocation("Xleon");
		galei.addSubLocation(xleon);
		
//		map.addLocation(pureia);
//		map.addLocation(mykovia);
//		map.addLocation(excelsior);
//		map.addLocation(munea);
//		map.addLocation(galei);
	}
	
}
