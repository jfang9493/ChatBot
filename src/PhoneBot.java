//Jackie Fang 10/10/18
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class PhoneBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;
	int cart = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void phoneLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		emotion = 0;
		System.out.println (getGreeting());
		statement = in.nextLine();
		while (!statement.equals("Bye") && !statement.equals("change store"))
		{
			//getResponse handles the user reply
			System.out.println(getResponse(statement));
				statement = in.nextLine();
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
		return "Hi, welcome to the Fang's Famous Fones! We sell the most up to date phones in the world! What can I do for you today?";
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
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
                	emotion--;
		}
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream, amiright?";
			emotion++;
		}
		else if (findKeyword(statement, "folwell") >= 0)
		{
			response = "Watch your backpacks, Mr. Folwell doesn't fall well.";
			emotion++;
		}
		else if (findKeyword(statement, "goldman") >= 0)
		{
			response = "Go for the gold, man.";
			emotion++;
		}
        else if (findKeyword(statement, "check", 0)>= 0 || findKeyword(statement, "see", 0)>= 0)
        {
            return customerCheckPhones(statement);
        }
		else if (findKeyword(statement, "hi", 0) >= 0)
		{
			return customerSayHi(statement);
		}
		else if (findKeyword(statement, "how much is", 0) >= 0)
		{
			return phonePriceStatement(statement);
		}
		else if (findKeyword(statement, "samsung", 0) >= 0)
		{
			return samsungPhones(statement);
		}
		else if (findKeyword(statement, "apple", 0) >= 0)
		{
			return applePhones(statement);
		}
		else if (findKeyword(statement, "lg", 0) >= 0)
		{
			return lgPhones(statement);
		}
		else if (findKeyword(statement, "google", 0) >= 0)
		{
			return googlePhones(statement);
		}
        else if (findKeyword(statement, "buy a phone", 0) >= 0)
		{
			return customerBuyPhone(statement);
		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
            return transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want a",0) >= 0)
		{
            return transformIWantStatement(statement);
		}
		else if (findKeyword(statement,"I would like",0)>=0)
		{
            return transformIWouldStatement(statement);
		}
		else if (findKeyword(statement,"complete",0)>=0)
		{
			return completePurchase(statement);
		}
		else {
            return getRandomResponse();
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
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
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
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
		int psn = findKeyword (statement, "I want a", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String customerSayHi(String statement)
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
		return "Welcome!";
	}

	private String transformIWouldStatement(String statement)
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

		int psnOfIWould = findKeyword (statement, "I would like ", 0);

		String restOfStatement = statement.substring(psnOfIWould+ 1).trim();
		return "Is this" + restOfStatement + " what you are looking for?";
	}

	private String customerCheckPhones(String statement)
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
		return "What kind of phone do you want? We have Apple, Samsung, Google, and LG phones.";
	}

	private String samsungPhones(String statement)
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
		return "We have the Samsung Galaxy S9 and the Samsung Galaxy Note 9. Would you like to pick a phone or view our other phones? We also have Apple phones, Google phones, and LG phones.";
	}

	private String applePhones(String statement)
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
		return "We have the iPhone 8, the iPhone X, and the iPhone XS. Would you like to pick a phone or view our other phones? We also have Samsung phones, Google phones, and LG phones.";
	}

	private String lgPhones(String statement)
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
		return "We have the LG V40 and the LG G7 ThinQ. Would you like to pick a phone or view our other phones? We also have Apple phones, Google phones, and Samsung phones.";
	}

	private String googlePhones(String statement)
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
		return "We have the Google Pixel 2 and the Google Pixel 3. Would you like to pick a phone or view our other phones? We also have Apple phones, Samsung phones, and LG phones.";
	}

	private String phonePriceStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("?"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		statement = statement.toLowerCase();
		if (findKeyword(statement, "samsung galaxy s9",0) >=0) return "The Samsung Galaxy S9 costs $750. Is there anything else you need help with?";
		else if (findKeyword(statement, "samsung galaxy note 9",0) >=0) return "The Samsung Galaxy Note 9 costs $1000. Is there anything else you need help with?";
		else if (findKeyword(statement, "iphone 8",0) >=0) return "The iPhone 8 costs $700. Is there anything else you need help with?";
		else if (findKeyword(statement, "iphone X",0) >=0) return "The iPhone X costs $900. Is there anything else you need help with?";
		else if (findKeyword(statement, "iphone XS",0) >=0) return "The iPhone XS costs $1000. Is there anything else you need help with?";
		else if (findKeyword(statement, "lg v40",0) >=0) return "The LG V40 costs $950. Is there anything else you need help with?";
		else if (findKeyword(statement, "lg g7 thinq",0) >=0) return "The LG G7 ThinQ costs $1000. Is there anything else you need help with?";
		else if (findKeyword(statement, "google pixel 2",0) >=0) return "The Google Pixel 2 costs $650. Is there anything else you need help with?";
		else if (findKeyword(statement, "google pixel 3",0) >=0) return "The Google Pixel 3 costs $800. Is there anything else you need help with?";
		else return "I'm sorry, I don't think I understand that";
	}

	private String customerBuyPhone(String statement)
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
		if (findKeyword(statement, "buy",0) >=0) {
			if (findKeyword(statement, "samsung galaxy s9", 0) >= 0) cart += 750;
			else if (findKeyword(statement, "samsung galaxy note 9", 0) >= 0) cart += 1000;
			else if (findKeyword(statement, "iphone 8", 0) >= 0) cart += 700;
			else if (findKeyword(statement, "iphone X", 0) >= 0) cart += 900;
			else if (findKeyword(statement, "iphone XS", 0) >= 0) cart += 1000;
			else if (findKeyword(statement, "lg v40", 0) >= 0) cart += 950;
			else if (findKeyword(statement, "lg g7 thinq", 0) >= 0) cart += 1000;
			else if (findKeyword(statement, "google pixel 2", 0) >= 0) cart += 650;
			else if (findKeyword(statement, "google pixel 3", 0) >= 0) cart += 800;
			return "That will bring your cart to $" + cart + ". Would you like anything else or would you like to complete your purchase?";
		}
		return "I don't understand";
	}

	private String completePurchase(String statement)
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

		return "Your total cost will be"+ cart + " Thank you for your purchase and have a nice day!";
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
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"It's all boolean to me.",
			"So, would you like to go for a walk?",
			"Could you say that again?"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	
}
