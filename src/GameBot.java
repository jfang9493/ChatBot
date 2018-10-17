import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class GameBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
    int randomResponse = 0;
	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void gameLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		emotion = 0;
		System.out.println (getGreeting());
		statement = in.nextLine();

		while (!statement.equals("Bye") && !statement.equals("change store") && emotion != -10)
		{
			//getResponse handles the user reply
			System.out.println(getResponse(statement));
			if(emotion != -10)
				statement = in.nextLine();
		}
		if(emotion == -10){
			System.out.println("Yeah we dont really want you here as well.");
		} else {
			System.out.println("Understandable, have a nice day.");
		}
		System.out.println("Which store would you like to visit now? The other stores are for phones, games, and home appliances.");
		System.out.println(statement);
	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hi, what can you help you with today at GameStart today?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "So you don't like to play games?";
            emotion--;
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why don't you enjoy game?";
                	emotion--;
		}
		
		else if (findKeyword(statement, "buy") >= 0)
		{
			response = "So what kind of game would u like to buy?";
			emotion++;
		}

		else if (findKeyword(statement, "yes") >= 0)
		{
			response = "So what kind of game would u like to buy? We have RPG, MOBA, Card,and FPS";
			emotion++;
		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToBuyStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantToPlayStatement(statement);
		}
		else if (findKeyword(statement, "I",0) >= 0)
		{
			response = transformIYouStatement(statement);
		}
		else if (findKeyword(statement, "you",0) >= 0)
		{
			response = transformIYouStatement(statement);
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
        }
        else if (findKeyword(statement, "Card", 0) >= 0)
        {
            response = recommendGames(statement);
        }
        else if (randomResponse >= 5)
        {
            response = "I would recommend some Awesome games for you. What kind of game do you like ? RPG ? FPS ? Card? or MOBA?";
        }
		else
		{
		    randomResponse ++;
			response = getRandomResponse();
		}
		
		return response;
	}
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToBuyStatement(String statement)
	{
		//  Remove the final period, if there is one
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
		return "Why do you like to buy " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
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
		}
		if(statement.equals("MOBA"))
		{
			suggest = randomMOBAGame [r.nextInt(randomMOBAGame.length)];
		}
		if(statement.equals("RPG"))
		{
			suggest = randomRPGGame [r.nextInt(randomRPGGame.length)];
		}
		if(statement.equals("Card"))
		{
			suggest = randomCardGame [r.nextInt(randomCardGame.length)];
		}
        return "So you really like to play" + statement + "? I see. I would suggest you to play "+ suggest;
    }
	private String transformIWantToPlayStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to play", 0);
		String restOfStatement = statement.substring(psn + 13).trim();
		return "So you really like to play " + restOfStatement + "? I see.";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}
	

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
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

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
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
			"i'm willing to hear more"
	};
	private String [] randomAngryResponses = {"Please don't make me get mad more.", "I don't like that", "please say something useful"};
	private String [] randomHappyResponses = {"I would be happy to serve you anytime.", "Feels good to find someone interested in this", "You make me feel like we know each other."};
	private String [] randomFPSGame = {"OverWatch","PUBG","Tom Clancy's Rainbow Six Siege",};
	private String [] randomRPGGame = {"World of Warcraft","Blade and Soul","MapleStory 2"};
	private String [] randomMOBAGame = {"League of Legends","DOTA2","Hero Of The Storm"};
	private String [] randomCardGame = {"Hearthstone","UNO","Yo-Gi-Yu"};
}
