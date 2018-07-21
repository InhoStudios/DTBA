package git.inhostudios.dtba;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import git.inhostudios.dtba.gameobjects.Game;
import git.inhostudios.dtba.gameobjects.Inventory;
import git.inhostudios.dtba.gameobjects.Player;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {

	private static ArrayList<Player> players = new ArrayList<Player>();
	static Game game;
	
	public static void main(String[] args) throws Exception{
		JDA jda = new JDABuilder(AccountType.BOT).setToken(Globals.token).buildBlocking();
		jda.addEventListener(new Main());
		
		game = new Game();
		players = game.getPlayers();
	}
	
	public void onMessageReceived(MessageReceivedEvent event) {
		
		User user = event.getAuthor();
		Message msg = event.getMessage();
		MessageChannel ch = event.getChannel();
		String input = msg.getContentRaw();
		if(input.length() >= 4) {
			String command = input.substring(Globals.prefix.length());
			
			if(input.startsWith(Globals.prefix + command)) {
				// getting a command
				
				Player userPlayer = null;
				
				if(playerExists(user)) {
					userPlayer = getPlayerByUser(user);
				}
				
				// help
				if(command.equalsIgnoreCase(Globals.help)) {
					ch.sendMessage(formatString(
							"eon.help: "
							+ "\nregister - Create a new player"
							+ "\nname - Find your in game name"
							+ "\nstats - See your in game stats"
							+ "\nchange-name [New Name] - Change your player's name"
							+ "\nquest-list - Shows the current quests"
							+ "\nsuicide - Kill your player for a fresh start"
							)).queue();
				} else				
					
				// SINGLE ARGUMENT COMMANDS
					
				// register a player
				if(command.equalsIgnoreCase(Globals.register)) {
					if(playerExists(user)) {
						ch.sendMessage(formatString("Player already exists.")).queue();
						return;
					} else {
						Player player = new Player(user, new Inventory(), 0);
						//game.read(player);
						game.addPlayer(player);
						game.savePlayer(player);
						ch.sendMessage(formatString("Player added!")).queue();
					}
				} else 
				// get name
				if(command.equalsIgnoreCase(Globals.name)){
					if(playerExists(user)) {
						ch.sendMessage(formatString(userPlayer.getName())).queue();
					} else {
						ch.sendMessage(formatString("Player doesn't exist. Use eon.register to create a new player")).queue();
					}
				} else 
				// get stats
				if(command.equalsIgnoreCase(Globals.stats)){
					if(playerExists(user)) {
						ch.sendMessage(formatString(userPlayer.getName() + "'s Stats\nLevel: " + userPlayer.getLevel() + "\nCoins: " + userPlayer.getBalance())).queue();
					} else {
						ch.sendMessage(formatString("Player doesn't exist. Use eon.register to create a new player")).queue();
					}
				} else
				// quest list
				if(command.equalsIgnoreCase(Globals.questList)){
					String quests = game.getQuests();
					if(playerExists(user)) {
						ch.sendMessage(formatString(quests)).queue();	
					} else {
						ch.sendMessage(formatString("Player doesn't exist. Use eon.register to create a new player")).queue();
					}
				} else
				// suicide
				if(command.equalsIgnoreCase(Globals.suicide)) {
					if(playerExists(user)) {
						players = game.getPlayers();
						for(int i = 0; i < players.size(); i++) {
							if(players.get(i).getUser().equals(user)) {
								players.remove(i);
								ch.sendMessage(formatString("You killed yourself\nX_X")).queue();
								return;
							}
						}	
						ch.sendMessage(formatString("Unable to kill yourself. You are too weak")).queue();
					} else {
						ch.sendMessage(formatString("Player doesn't exist. Use eon.register to create a new player")).queue();
					}
				} else
					
				// MULTI ARGUMENT COMMANDS
					
				// change name
				if(command.contains(Globals.changeName)){
					if(playerExists(user)) {
						String name;
						int cnLength = Globals.changeName.length();
						if(command.substring(cnLength, cnLength + 1).equalsIgnoreCase(" ")) {
							name = command.substring(cnLength + 1);
							getPlayerByUser(user).setName(name);
							ch.sendMessage(formatString("Name changed to " + name)).queue();
						} else {
							ch.sendMessage(formatString("Not enough arguments to function. Proper use: change-name [New Name]")).queue();
						}
					} else {
						ch.sendMessage(formatString("Player doesn't exist. Use eon.register to create a new player")).queue();
					}
				}
				
				// no command
				else
				{
					ch.sendMessage(formatString("Command not found")).queue();
				}
			}
		}
		
		// printing the message
		if(event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                        event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                        event.getMessage().getContentDisplay());
        }
	}
	
	private boolean playerExists(User user) {
		players = game.getPlayers();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	private Player getPlayerByUser(User user) {
		players = game.getPlayers();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getUser().equals(user)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	private String formatString(String text) {
		return ("```" + text + "```");
	}
//
//	public static void savePlayer(Player player) {
//		String fileName = player.getName() + ".json";
//		String userName = player.toJson();
//		File Old = new File("../Save/" + fileName);
//		Old.delete();
//		File New = new File("../Save/" + fileName);
//		
//		try {
//			PrintWriter out = new PrintWriter(New);
//			out.write(userName);
//			out.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static String readFile(String path, Charset encoding) throws IOException {
//		byte[] encoded = Files.readAllBytes(Paths.get(path));
//		return new String(encoded, encoding);
//	}
//	
//	public static void read() {
//		JsonParser parser = new JsonParser();
//		
//		String output = "";
//		try {
//			output = readFile("../Save/" + localFilename, StandardCharsets.UTF_8);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JsonElement obj = parser.parse(output);
//		localSave = obj.getAsJsonArray(); 
//		
//		Gson gson = new Gson();
//		playerchain = gson.fromJson(output, new TypeToken<ArrayList<Block>>(){}.getType());
//		
//		System.out.println("Save read!");
//	}
	
}
