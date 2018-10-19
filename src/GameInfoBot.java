import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Random;
import java.util.Scanner;
//Shuyi Jiang
public class GameInfoBot
{
	int emotion = 0;
    int randomResponse = 0;
    private String[] WantedGames = {};
	public void gameLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		emotion = 0;
		System.out.println (getGreeting());
		while (!statement.equals("Bye") && !statement.equals("change store") && emotion != -10)
		{
			System.out.println(getResponse(statement));
			if(emotion != -10)
				statement = in.nextLine();
		}
		if(emotion == -10){
			System.out.println("Yeah we dont really want you here.Please leave as soon as you can." +
                    "System Message: You have been kicked out from the store GameStart");
		} else {
			System.out.println("Understandable, have a nice day.");
		}
		System.out.println("Which store would you like to visit now? The other stores are for phones, games, and home appliances.");
		System.out.println(statement);
	}

	public String getGreeting()
	{
		return "Hi, what can you help you with today at GameStart today? I am here to help. Would you like Buy a game";
	}

	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() > 0)
		{
            emotion++;
		}
        if (statement.length() == 0)
        {
            emotion--;
        }
		else if (findKeyword(statement, "no") >= 0&&statement.length() == 0)
		{
			response = "Why don't you enjoy game?";
                	emotion--;
		}
		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "So what kind of game would u like to buy? We have RPG, MOBA, Card,and FPS";
			emotion++;
		}
		else if (findKeyword(statement, "I want to buy", 0) >= 0)
		{
			response = transformIWantToBuyStatement(statement);
		}
		else if (findKeyword(statement, "I want to play",0) >= 0)
		{
			response = transformIWantToPlayStatement(statement);
		}
        else if (findKeyword(statement, "RPG", 0) >= 0)
        {
            response = recommendGames(statement);
        }
        else if (findKeyword(statement, "MOBA", 0) >= 0)
        {
            response = recommendGames(statement);
            emotion ++;
        }
        else if (findKeyword(statement, "FPS", 0) >= 0)
        {
            response = recommendGames(statement);
            emotion ++;
        }
        else if (findKeyword(statement, "Card", 0) >= 0)
        {
            response = recommendGames(statement);
            emotion ++;
        }
        else if (randomResponse >= 5)
        {
            response = "I would recommend some Awesome games for you. What kind of game do you like again? RPG ? FPS ? Card? or MOBA?";
        }
		else
		{
		    randomResponse ++;
			response = getRandomResponse();
		}
		
		return response;
	}

	private  String transformIWantToBuyStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to buy", 0);
		String restOfStatement = statement.substring(psn + 13).trim();
		randomResponse++;
		return "Why do you like to buy " + restOfStatement + "?";
	}

    private String recommendGames(String statement)
    {
    	String suggest = "";
		Random r = new Random ();
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        if(statement.equals("FPS"))
		{
			suggest = randomFPSGame [r.nextInt(randomFPSGame.length)];
            System.out.println("So you really like to play" + statement + "? I see. I would suggest you to play "+ suggest);
            System.out.println("Would you like to know more about this game?");
            if(statement.equals("yes"))
            {
                System.out.println("Theses games are usually about gun fights either in real life or fantasy. It consist of strategy based game play for either Attacking or Defending side, and the well performing your team members. Reaction and mouse control is also important for a good FPS player to destroy their enemy");
				System.out.println("Here are some FPS games we have\"OverWatch\",\"PUBG\",\"Tom Clancy's Rainbow Six Siege\",\"CounterStrike: Global Offence\",\"Destiny 2\",\"Call Of Duty: BlackOPS\"");
            }
		}
		if(statement.equals("MOBA"))
		{
			suggest = randomMOBAGame [r.nextInt(randomMOBAGame.length)];
            System.out.println("So you really like to play" + statement + "? I see. I would suggest you to play games like "+ suggest);
            System.out.println("Would you like to know more about this game?");
            if(statement.equals("yes"))
            {
                System.out.println("This type of games are challenging but also fun for players who want skill based competition with other players online. The victory of 1 game is determine by how well you know the mechanic of the game, strategy you perform and teamwork of you other 4 team members.");
                System.out.println("Here is our list of MOBA games,League of Legends,DOTA2,Hero Of The Storm");
            }
		}
		if(statement.equals("RPG"))
		{
			suggest = randomRPGGame [r.nextInt(randomRPGGame.length)];
            System.out.println("So you really like to play" + statement + "? I see. I would suggest you to play "+ suggest);
            System.out.println("Would you like to know more about this game?");
            if(statement.equals("yes"))
            {
                System.out.println("This kind of games is fun for people who like to make a character in a different world. You can build up your character, make friends with other players, and have group events such as raiding. This type of game usually uses more time as if you are living in that world.");
				System.out.println("World of Warcraft, MapleStory2,");
            }
		}
		if(statement.equals("Card"))
		{
			suggest = randomCardGame [r.nextInt(randomCardGame.length)];
            System.out.println("So you really like to play" + statement + "? I see. I would suggest you to play "+ suggest);
            System.out.println("Would you like to know more about this game?");
            if(statement.equals("yes"))
            {
                System.out.println("Card games like this are fun because of how you can get collect rare cards from the store using the currency in the game. The currency is gained by playing games with other players online using a set of deck you make to perform strategy that can defeat the enemy through care thought proccess");
				System.out.println("Hearthstone,UNO,Yo-Gi-Yu,Pokemon Card Games");
            }
		}
        if(statement.equals("I would lik to know more about FPS"))
        {
            System.out.println("Theses games are usually about gun fights either in real life or fantasy. It consist of strategy based game play for either Attacking or Defending side, and the well performing your team members. Reaction and mouse control is also important for a good FPS player to destroy their enemy");

        }
        if(statement.equals("I would like to know more about MOBA"))
        {
            System.out.println("This type of games are challenging but also fun for players who want skill based competition with other players online. The victory of 1 game is determine by how well you know the mechanic of the game, strategy you perform and teamwork of you other 4 team members.");

        }
        if(statement.equals("I would like to know more about RPG"))
        {
            System.out.println("This kind of games is fun for people who like to make a character in a different world. You can build up your character, make friends with other players, and have group events such as raiding. This type of game usually uses more time as if you are living in that world.");

        }
        if(statement.equals("I would like to know more about Card"))
        {
            System.out.println("Card games like this are fun because of how you can get collect rare cards from the store using the currency in the game. The currency is gained by playing games with other players online using a set of deck you make to perform strategy that can defeat the enemy through care thought proccess");

        }
        return suggest + "would be a good game for you to start, have a nice day";
    }
	private String transformIWantToPlayStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to play", 0);
		String restOfStatement = statement.substring(psn + 14).trim();
        randomResponse++;
		return "So you really like to play " + restOfStatement + "? I see.";
	}

	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();
		int psn = phrase.indexOf(goal, startPos);

		while (psn >= 0)
		{
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}

	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"So be it.",
			"uhm... I would like to know more",
			"Why though?",
			"Why is it ?",
			"Can you explain more?.",
			"Take your time.",
			"Uhm, interesting",
			"i'm willing to hear more",
            "please continue, I'm glad that I can hear you talk about it"
	};
	private String [] randomAngryResponses = {"Please don't make me get mad more.", "I don't like that", "please say something useful"};
	private String [] randomHappyResponses = {"I am really happy right now.", "Feels good to find someone interested in this", "You make me feel like we know each other."};
	private String [] randomFPSGame = {"OverWatch","PUBG","Tom Clancy's Rainbow Six Siege","CounterStrike: Global Offence","Destiny 2","Call Of Duty: BlackOPS"};
	private String [] randomRPGGame = {"World of Warcraft","Blade and Soul","MapleStory 2"};
	private String [] randomMOBAGame = {"League of Legends","DOTA2","Hero Of The Storm"};
	private String [] randomCardGame = {"Hearthstone","UNO","Yo-Gi-Yu","Pokemon Card Games"};
}
