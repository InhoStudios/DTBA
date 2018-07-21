package git.inhostudios.dtba;

import java.util.ArrayList;

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
							+ "\ntrade [Player name] [Item] - Start a trade with another player"
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
						game.addPlayer(player);
						ch.sendMessage(formatString("Player added!")).queue();
					}
				} else 
				// get name
				if(command.equalsIgnoreCase(Globals.name)){
					if(playerExists(user)) {
						ch.sendMessage(formatString(userPlayer.getName())).queue();
					} else {
						ch.sendMessage("Player doesn't exist. Use eon.register to create a new player").queue();
					}
				} else 
				// get stats
				if(command.equalsIgnoreCase(Globals.stats)){
					if(playerExists(user)) {
						ch.sendMessage(formatString(userPlayer.getName() + "'s Stats\nLevel: " + userPlayer.getLevel() + "\nBalance: " + userPlayer.getBalance())).queue();
					} else {
						ch.sendMessage("Player doesn't exist. Use eon.register to create a new player").queue();
					}
				} else
					
				// MULTI ARGUMENT COMMANDS
					
				// start a quest
				{
					ch.sendMessage(formatString("Command not found")).queue();
				}
				
				// trade
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
		return ("```\n" + text + "\n```");
	}

}
