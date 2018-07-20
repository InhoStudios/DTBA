package git.inhostudios.dtba;

import java.util.ArrayList;

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

	private ArrayList<Player> players = new ArrayList<Player>();
	
	public static void main(String[] args) throws Exception{
		JDA jda = new JDABuilder(AccountType.BOT).setToken(Globals.token).buildBlocking();
		jda.addEventListener(new Main());
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
				
				// register a player
				if(command.equalsIgnoreCase(Globals.register)) {
					for(int i = 0; i < players.size(); i++) {
						if(players.get(i).getUser().equals(user)) {
							ch.sendMessage("User already exists.").queue();
							return;
						}
					}
					Player player = new Player(user, new Inventory(), 0);
					players.add(player);
					ch.sendMessage("User added!").queue();
				} else {
					ch.sendMessage("Command not found").queue();
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

}
