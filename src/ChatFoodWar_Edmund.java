import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatFoodWar_Edmund
{
	private final DialogFlow comm = new DialogFlow("d58bb98417f14ec1857725027e11bb26", 20170712);
	private final ChatBot chatBot = new ChatBot();
	// emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;


	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{
		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());


		while (!statement.equals("Bye"))
		{


			statement = in.nextLine();
			//getResponse handles the user reply
			System.out.println(getResponse(statement));


		}

	}

	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello, this bot can say stuff regarding Shokugeki no Soma (or Food Wars).";
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
		// Seems to be a good spot for DialogFlow
		// Characters intent works but not to point of expectation
		else if (this.comm.hasResponse(statement)) {
			response = this.comm.getResponse();
		}
		else if (chatBot.findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
                	emotion--;
		}
		else if (chatBot.findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream amiright?";
			emotion++;
		}
		// Response transforming I want to statement
		else if (chatBot.findKeyword(statement, "I want to", 0) >= 0) {
			response = chatBot.transformIWantToStatement(statement);
		}
		else if (chatBot.findKeyword(statement, "I want", 0) >= 0) {
			response = chatBot.transformIWantStatement(statement);
		}
		else if (chatBot.findKeyword(statement, "I", 0) >= 0 && chatBot.findKeyword(statement, "you", 0) >= 0) {
			response = chatBot.transformIYouStatement(statement);
		}
		else
		{
			response = getRandomResponse();
		}
		
		return response;
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
