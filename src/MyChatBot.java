import org.jibble.pircbot.PircBot;
import com.google.gson.*;

import twitter4j.TwitterException;

import java.net.*;
import java.net.http.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MyChatBot extends PircBot{
	
	private static final String chatBotName = "JoeBot";

	
	public MyChatBot()
	{
		this.setName("JoeBot");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		
		String[] input = message.split(" ");
		
		if(message.toLowerCase().equalsIgnoreCase("time"))
		{
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": The time is now " + time);
		}
		
		if(message.toLowerCase().contains("weather"))
		{
			String local = "75080";
			if(input.length == 2)
			{
				if(input[0].toLowerCase().equals("weather"))
				{
					local = input[1];
				}
				else
				{
					local = input[1];
				}
			}
			else
			{
				sendMessage(channel, chatBotName + " Says: " + "No weather detected... Displaying local weather!");
			}
			
		
			DATAFORMATTER data = Weather.getData(local);
		
			if(data == null)
			{
				data = Weather.getData(local);
				if(input.length == 2)
				{
					sendMessage(channel, chatBotName + " Says: " + "That zipcode doesn't exist. Please try again.");
				}
				
			}
			
			String displayWeather = data.toString();
			sendMessage(channel, chatBotName + " Says: " + displayWeather);
		}
		
		
		if(message.toLowerCase().contains("twitter"))
		{
			TwitterAPI twitter;
			int DEFAULT_TRENDS = 10;
			try {
				twitter = new TwitterAPI();
				String trends[] = new String[10];
				
				trends = twitter.getTopTrends();
				if(input.length == 2)
				{
					if(Integer.parseInt(input[1]) > 10)
					{
						sendMessage(channel, chatBotName + " Says: The maximum amount of trending hashtags that can be displayed is 10. Displaying only 10...");
						sendMessage(channel, chatBotName + " Says - Here are the top " + DEFAULT_TRENDS + " twitter hashtag trends!");
						for(int counter = 0; counter < DEFAULT_TRENDS; counter++)
						{
							sendMessage(channel, chatBotName + " says: " + "#" + (counter+1) + " [" + trends[counter] + "]\n");
						}
						
					}
					else
					{
						sendMessage(channel, chatBotName + " Says - Here are the top " + input[1] + " twitter hashtag trends!");
						for(int counter = 0; counter < Integer.parseInt(input[1]); counter++)
						{
							sendMessage(channel, chatBotName + " says: " + "#" + (counter+1) + " [" + trends[counter] + "]\n");
						}
					}
				}
				else
				{
					sendMessage(channel, chatBotName + " Says: No value was given, displaying the top 10 twitter hashtag trends!");
					for(int counter2 = 0; counter2 < DEFAULT_TRENDS; counter2++)
					{
						sendMessage(channel, chatBotName + " says: " + "#" + (counter2+1) + " [" + trends[counter2] + "]\n");
					}
				}
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		// start bot
		MyChatBot bot = new MyChatBot();
		String channel = null;
		// enables debugging output
		bot.setVerbose(true);
		bot.connect("irc.freenode.net");
		bot.joinChannel("#joesroom");
		bot.sendMessage("#joesroom", "Joe's ChatBot. Created by Joe.");
		bot.sendMessage("#joesroom", "This bot can currently carry out three different functions. Below you will find the correct syntax for each of these functions.");
		bot.sendMessage("#joesroom", " 									CORRECT SYNTAX(IGNORE THE BRACKETS)");
		bot.sendMessage("#joesroom", "Weather [zipcode] OR Weather(this will display your local forecast.)");
		bot.sendMessage("#joesroom", "Twitter [NumberOfTrends] OR Twitter(this will display the default value of 10 hashtag trends.)");
		bot.sendMessage("#joesroom", "Time");
	}
	
}
