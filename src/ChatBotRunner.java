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
		System.out.println("Welcome to the chatbot, nice to meet you.");
		String statement = in.nextLine();


		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1



			chatbot1.chatLoop(statement);


			statement = in.nextLine();


		}
	}

}
