// Brandon Smith
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class GroceryBot
{
    //emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
    int emotion = 0;
    //conversation changes depending on which type you're looking for.
    int path = 0;
    String category;
    //variable used to store the previous statement before it is overwritten
    String statement1;
    //variable used to store if a value is found in an array.
    int found = 0;
    /**
     * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
     * @param statement the statement typed by the user
     */
    public void groceryLoop(String statement)
    {
        Scanner in = new Scanner (System.in);
        emotion = 0;
        path = 0;
        System.out.println (getGreeting());
        statement = in.nextLine();

        while (!statement.equals("Bye") && !statement.equals("change store") && !statement.equals("leave") && emotion != -10)
        {
            //getResponse handles the user reply
            System.out.println(getResponse(statement));
            if(emotion != -10)
                statement = in.nextLine();
        }
        if(emotion == -10){
            System.out.println("You were kicked out of the grocery store.");
        } else {
            System.out.println("Understandable, have a nice day.");
        }
        System.out.println("Which store would you like to visit now? The other stores are for phones, games, and home appliances.");
    }
    /**
     * Get a default greeting
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello there, and welcome to Smith and Sons International Grocers! What category of groceries are you looking for? " +
                "We have beverages, bakery items, canned goods, dairy items, dry goods, frozen foods, meat, produce, cleaners, paper goods and personal care.";
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

        /* else if (findKeyword(statement, "no") >= 0)
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
        }*/

        // Response transforming I want to statement
        /* else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
        else if (findKeyword(statement, "I want",0) >= 0)
        {
            response = transformIWantStatement(statement);
        }*/
        else if (findKeyword(statement, "I am looking for",0) >= 0)
        {
            response = transformIAmLookingForStatement(statement);
        }
        else if (findKeyword(statement, "beverages",0) >= 0 && path == 0){
            response = "What kind of beverage? We have coffee, tea, juice, and soda.";
            category = "A";
            path++;
        }
        else if (findKeyword(statement, "bakery items",0) >= 0 && path == 0){
            response = "What kind of bakery item? We have sandwich loaves, dinner rolls, tortillas and bagels.";
            category = "B";
            path++;
        }
        else if (findKeyword(statement, "canned goods",0) >= 0 && path == 0){
            response = "What kind of canned good? We have vegetables, spaghetti sauce and ketchup.";
            category = "C";
            path++;
        }
        else if (findKeyword(statement, "dairy items",0) >= 0 && path == 0){
            response = "What kind of dairy item? We have cheese, eggs, milk, yogurt and butter.";
            category = "D";
            path++;
        }
        else if (findKeyword(statement, "dry goods",0) >= 0 && path == 0){
            response = "What kind of dry good? We have cereal, flour, sugar, pasta and mixes.";
            category = "E";
            path++;
        }
        else if (findKeyword(statement, "frozen foods",0) >= 0 && path == 0){
            response = "What kind of frozen food? We have waffles, vegetables, individual meals and ice cream.";
            category = "F";
            path++;
        }
        else if (findKeyword(statement, "meat",0) >= 0 && path == 0){
            response = "What kind of bakery item? We have lunch meat, poultry, beef, pork";
            category = "G";
            path++;
        }
        else if (findKeyword(statement, "produce",0) >= 0 && path == 0){
            response = "What kind of produce? We have fruits and vegetables.";
            category = "H";
            path++;
        }
        else if (findKeyword(statement, "cleaners",0) >= 0 && path == 0){
            response = "What kind of cleaner? We have all-purpose, laundry detergent and dishwashing liquid.";
            category = "I";
            path++;
        }
        else if (findKeyword(statement, "paper goods",0) >= 0 && path == 0){
            response = "What kind of paper good? We have paper towels, toilet paper, aluminum foil and sandwich bags.";
            category = "J";
            path++;
        }
        else if (findKeyword(statement, "personal care",0) >= 0 && path == 0) {
            response = "What kind of personal care item? We have shampoo, soap, hand soap and shaving cream.";
            category = "K";
            path++;
        }
        else if (path == 1){
            statement1 = statement.trim();
            Random r = new Random ();
            response = "You really want " + statement1 + ", don't you?";
            path++;
        }
        else if (path == 2){
            if(category.equals("A")){
                for(String value : catA) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("B")){
                for(String value : catB) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("C")){
                for(String value : catC) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("D")){
                for(String value : catD) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("E")){
                for(String value : catE) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("F")){
                for(String value : catF) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("G")){
                for(String value : catG) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("H")){
                for(String value : catH) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("I")){
                for(String value : catI) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("J")){
                for(String value : catJ) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
            else if(category.equals("K")){
                for(String value : catK) {
                    if(value.equals(statement1)){
                        found++;
                    }
                    if(found > 0){
                        response = "Well, we should have " + statement1 + " in isle " + category + ". What would you like to do now? Find another item or leave?";
                    }
                    else {
                        response = "Well, sorry to tell you but we don't have that item here. What would like to do now? Find another item or leave?";
                    }
                    path++;
                }
            }
        }
        else if (findKeyword(statement,"I would like to",0) >= 0 && path == 3){
            statement1 = statement.trim();
            response = transformIWouldLikeToStatement(statement);
            path++;
        }
        if(path == 3){
            if(findKeyword(statement.toLowerCase(),"find another item",0) >= 0){
                path = 0;
                response = "Okay. What item would you like to find now? We have beverages, bakery items, canned goods, dairy items, dry goods, frozen foods, meat, produce, cleaners, paper goods and personal care.";
            }
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
     * Take a statement with "I would like to <something>." and transform it into
     * "Why would you like to <something>?"
     * @param statement the user statement, assumed to contain "I would like to"
     * @return the transformed statement
     */
    private String transformIWouldLikeToStatement(String statement)
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
        int psn = findKeyword (statement, "I would like to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "Why would you like to " + restOfStatement + "?";
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
     * Take a statement with "I am looking for <something>" and transform it into
     * "What kind of (something)?"
     * @param statement the user statement, assumed to contain "I am looking for"
     * @return the transformed statement
     */
    private String transformIAmLookingForStatement(String statement)
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

        int psn = findKeyword (statement, "I am looking for", 0);
        String restOfStatement = statement.substring(psn + 16).trim();
        return "What kind of " + restOfStatement + "?";
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
        if (emotion >= -5 && emotion <= 0)
        {
            emotion--;
            return confusedList [r.nextInt(confusedList.length)];
        }
        else if (emotion > -9 && emotion < -5)
        {
            emotion--;
            return annoyedList [r.nextInt(annoyedList.length)];
        } else {
            emotion--;
            return limitList[r.nextInt(limitList.length)];
        }
    }

    /* private String [] randomNeutralResponses = {"Interesting, tell me more",
            "Hmmm.",
            "Do you really think so?",
            "You don't say.",
            "It's all boolean to me.",
            "So, would you like to go for a walk?",
            "Could you say that again?"
    };
    private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
    private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};*/

    private String [] confusedList = {"I don't think I understand..",
            "Can you rephrase that?",
            "Sorry, I don't get what you mean.",
            "¡Repite por favor!"
    };
    private String [] annoyedList = {"Make up your mind already!",
            "Be serious here! This isn't just a game!",
            "Just tell me what you want!!",
            "I don't have time for this.."
    };
    private String [] limitList = {"That's it, I'm calling the cops...",
            "I've had enough!!",
            "I'm done."
    };
    private String[] path1 = {"You really want " + statement1 + ", don't you?",
            "Are you sure that you want " + statement1 + "?",
            "It would be nice if we had " + statement1 + " wouldn't it?"
    };
    private String[] catA = {"coffee", "tea", "juice", "soda"};
    private String[] catB = {"sandwich loaves", "dinner rolls", "tortillas", "bagels"};
    private String[] catC = {"vegetables", "spaghetti sauce", "ketchup"};
    private String[] catD = {"cheese", "eggs", "milk", "yogurt", "butter"};
    private String[] catE = {"cereal", "flour", "sugar", "pasta", "mixes"};
    private String[] catF = {"waffles", "vegetables", "individual meals", "ice cream"};
    private String[] catG = {"lunch meat", "poultry", "beef", "pork"};
    private String[] catH = {"fruits", "vegetables"};
    private String[] catI = {"all-purpose", "laundry detergent", "dishwashing liquid"};
    private String[] catJ = {"paper towels", "toilet paper", "aluminum foil", "sandwich bags"};
    private String[] catK = {"shampoo", "soap", "hand soap", "shaving cream"};


}
