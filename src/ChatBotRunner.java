//ANGELA ZHENG
import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBlackButler_Angela chatbot1 = new ChatBlackButler_Angela();
		ChatFoodWar_Edmund chatbot2 = new ChatFoodWar_Edmund();
		ChatRwby_Rachel chatbot3 = new ChatRwby_Rachel();
		ChatReborn_Vincent chatbot4 = new ChatReborn_Vincent();

		Scanner in = new Scanner (System.in);
		System.out.println("This chatbot has 4 different chats in it. The theme is ANIME!!!! type 0-3 to choose a bot." +
				"\n0 = Black Butler" +
				"\n1 = Food War" +
				"\n2 = Rwby" +
				"\n3 = Reborn");
		// Keep Scanner consistent using .nextLine()
		int chosenBot = Integer.valueOf(in.nextLine().trim());

		System.out.println("Welcome to the Anime chatbot, nice to meet you.");
		String statement = in.nextLine();


		while (!statement.equals("Bye"))
		{
			// A not so ideal way to use multiple bots
			switch (chosenBot) {
				case 0:
					chatbot1.chatLoop(statement);
					break;
				case 1:
					chatbot2.chatLoop(statement);
					break;
				case 2:
					chatbot3.chatLoop(statement);
					break;
				case 3:
					chatbot4.chatLoop(statement);
					break;
				default:
					System.exit(1);
			}

			statement = in.nextLine();
		}
	}

}
