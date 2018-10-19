//Jackie Fang, ShuYi Jiang, Sullivan O’Connor, Brandon Smith

import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner {

    /**
     * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
     */
    public static void main(String[] args) {
        PhoneBot chatbot1 = new PhoneBot();
        GameBot chatbot2 = new GameBot();
        HomeAppBot chatbot3 = new HomeAppBot();
        GroceryBot chatbot4 = new GroceryBot();

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the chatbot, nice to meet you.");
        System.out.println("What kind of store are you looking to check? We have phones, games, home appliances, and groceries.");
        String statement = in.nextLine();


        while (!statement.equals("Bye")) {
            //Use Logic to control which chatbot is handling the conversation
            if (statement.equals("phones") || statement.equals("phone")) {
                chatbot1.phoneLoop(statement);
                statement = in.nextLine();
            }
            else if (statement.equals("games") || statement.equals("game")) {
                chatbot2.gameLoop(statement);
                statement = in.nextLine();
            }
            else if (statement.equals("home appliances") || statement.equals("home appliance")) {
                chatbot3.homeLoop(statement);
                statement = in.nextLine();
            }
            else if (statement.equals("groceries") || statement.equals("grocery")) {
                chatbot4.groceryLoop(statement);
                statement = in.nextLine();
            }
            else {
                System.out.println("Sorry, we don't have this here, please choose from phones, games, home appliances, or groceries");
                statement = in.nextLine();
            }
        }
        System.out.println("Have a nice day!");
    }
}
