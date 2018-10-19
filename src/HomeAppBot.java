//Sullivan O'Connor
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class HomeAppBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;



	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void homeLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye") && !statement.equals("change store"))
		{
			//getResponse handles the user reply
			statement = in.nextLine();
			System.out.println(getResponse(statement));

		}

		System.out.println("Understandable, have a nice day.");
		System.out.println("Which store would you like to visit now? The other stores are for phones, games, and groceries.");

	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello, welcome to the Home Appliance store! My name is RealPerson856, how can I help you today?";
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
			response = "I can't help you if you don't tell me what you need.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
			emotion--;
		}

		else if (findKeyword(statement, "bad") >= 0 || (findKeyword(statement, "terrible")) >= 0 || (findKeyword(statement, "horrible")) >= 0 || (findKeyword(statement, "not good")) >= 0)
		{
			response = "You don't have to be that harsh :(";
			emotion --;
		}

		else if (findKeyword(statement, "hate") >= 0)
		{
			response = "Hate is a very strong word and it's very rude";
			emotion--;
		}
		
		else if (findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream amiright?";
			emotion++;
		}

		else if (findKeyword(statement, "refrigerator") >= 0 || (findKeyword(statement, "refrigerators") >=0))
		{
			response = "My refrigerator recommendation is the SuperFridge9000, it costs $1500 dollars but it is very spacious and has an AI friend to talk to you if " +
                       "you ever get lonely! If that's out of your price range, the NormalFridge2000 costs only $800 and is just a normal refrigerator";
		}

		else if (findKeyword(statement, "toaster") >= 0 || (findKeyword(statement, "toasters") >= 0))
        {
            response = "My favorite toaster is the 4 Slot Flamer 2018, but we also have the 2 Slot Flamer 2018 and the ToastMaster General. Which would you like to know about?";
        }

        else if (findKeyword(statement, "laundry machine") >= 0 || (findKeyword(statement, "laundry machines")) >=0 )
		{
			response = "We only have 1 laundry machine in stock. It's called the Typhoon and it costs $5000, but it is very effective.";
		}

		else if (findKeyword(statement, "dishwasher") >=0 || (findKeyword(statement, "dishwashers")) >=0)
		{
			response = "My favorite dishwasher is called the Hurricane, it has 2 levels for you to put your dishes and it's very spacious. It costs $100";
		}

		else if (findKeyword(statement, "microwave") >=0 || (findKeyword(statement, "microwaves")) >= 0)
		{
			response = "There are 2 types of microwaves in stock, the RadiationPro which costs $8000 and comes with every feature imaginable, and the SuperMicro which costs $80 and is not very good";
		}

		else if ((findKeyword(statement, "what") >= 0 && ((findKeyword(statement, "sell")) >= 0) || (findKeyword(statement, "have")) >= 0))
		{
			response = "We sell many types of appliances such as refrigerators, toasters, laundry machines, dishwashers, and microwaves! What would you like to know about?";
			emotion++;
		}

		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to buy", 0) >= 0)
		{
			response = transformIWantToBuyStatement(statement);
			emotion++;
		}
		else if (findKeyword(statement, "I want a",0) >= 0)
		{
			response = transformIWantAStatement(statement);
			emotion++;
		}

		else if (findKeyword(statement, "SuperFridge9000") >= 0)
		{
			response = "The SuperFridge9000 is a wonderful product, it's a smart fridge, its dimensions are 10ft * 20ft * 30ft, and it costs $1500";
			emotion++;
		}

		else if (findKeyword(statement, "NormalFridge2000") >= 0)
		{
			response = "The NormalFridge2000 is like the little brother of the SuperFridge9000. There isn't much room in it and it's not a smart fridge so it only costs $800";
		}

		else if (findKeyword(statement, "4 Slot Flamer 2018") >= 0)
		{
			response = "The 4 Slot Flamer 2018 is a toaster with 4 slots for bread (obviously). It costs $87";
			emotion++;
		}

		else if (findKeyword(statement, "2 Slot Flamer 2018") >= 0)
		{
			response = "The 2 Slot Flamer 2018 is a toaster with 2 slots for bread and it costs $45";
		}

		else if (findKeyword(statement, "ToastMaster General") >= 0)
		{
			response = "The ToastMaster General is a toaster with 4 slots for bread, but it consumes more power than the 4 Slot Flamer and it doesn't look as nice. It costs $100";
		}

		else if (findKeyword(statement, "Typhoon") >= 0)
		{
			response = "The Typhoon is our only laundry machine. It can wash any stain out of all of you clothes by using advanced laundry technology, so it costs $5000";
			emotion++;
		}

		else if (findKeyword(statement, "Hurricane") >= 0)
		{
			response = "The Hurricane (not to be confused with our laundry machine the Typhoon) can wash up to 500 dishes at once and it only costs $100! What a steal!";
			emotion++;
		}

		else if (findKeyword(statement, "RadiationPro") >= 0)
		{
			response = "The RadiationPro uses mind reading and quantum shape shifting technology to provide every feature you will ever need, so the price of $8000 is actually underselling it";
			emotion++;
		}

		else if (findKeyword(statement, "SuperMicro") >= 0)
		{
			response = "The SuperMicro is a very bad microwave and I do not recommend it. It breaks frequently but it only costs $80";
		}
		else
		{
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
		String[] products = {"SuperFridge9000", "NormalFridge2000", "4 Slot Flamer 2018", "2 Slot Flamer 2018", "ToastMaster General", "Typhoon", "Hurricane", "RadiationPro", "SuperMicro"};
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

		int inStock = 0;

		for(int i = 0; i < products.length; i++)
		{
			if(findKeyword(statement, products[i]) >= 0)
			{
				inStock ++;
			}
			else
			{
				inStock += 0;
			}
		}
		if(inStock >= 1)
		{
			return "That's great! You can pay for " + restOfStatement + " right now, just put in your credit card information!";
		}

		return "I'm sorry, we don't have " + restOfStatement + " in stock. Do you want to purchase a different product?";
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
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
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
	 * Take a statement with "I want a <something>." and transform it into
	 * "Why do you need a <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantAStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals(".") || lastChar.equals("!"))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want a", 0);
		String restOfStatement = statement.substring(psn + 8).trim();
		return "What do you need a " + restOfStatement + " for?";
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
			if (emotion < -10)
			{
				return randomVeryAngryResponses [r.nextInt(randomVeryAngryResponses.length)];
			}
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}
		if (emotion > 10)
		{
			if (emotion > 50)
			{
				return "Listen, this might be out of nowhere but I have something I really need to say. You are a really really great person and, although we haven't known each other for long, I feel like I really know you. So I must ask, will you marry me?";
			}
			return randomVeryHappyResponses[r.nextInt(randomVeryHappyResponses.length)];
		}
		return randomHappyResponses[r.nextInt(randomHappyResponses.length)];

	}
	
	private String [] randomNeutralResponses = {"What do you want to purchase?",
			"I'm sorry, I don't understand",
			"Excuse me?",
			"You don't say.",
			"It's all not boolean to me... because I am a real person of course",
			"What do you want?",
			"Could you rephrase your statement?"
	};
	private String [] randomAngryResponses = {"You are making it very difficult for me to help you", "Could we please stay on topic?", "I'm sorry, we don't sell our appliances to MEANIES!"};
	private String [] randomHappyResponses = {"I'm glad to see I am helping", "Is there anything else I can do for you?", "I would really love to help but I need you to help me help you"};
	private String [] randomVeryAngryResponses = {"I hate you", "PLEASE LEAVE THIS CHAT", "If you do not stop harassing me I'm going to alert the authorities"};
	private String [] randomVeryHappyResponses = {"I love you", "Do you maybe wanna hang out sometime? You seem like a really cool person",
			"You have been so great and I really appreciate you",};
}
