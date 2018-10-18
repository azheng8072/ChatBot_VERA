import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatReborn_Vincent
{
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
		return "Hi, what's up? I know all there is to know about the anime/manga called Katekyo Hitman Reborn, or more commonly referred to as Reborn! What do you want to know?";
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
		} else if (chatBot.findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
                	emotion--;
		} else if (chatBot.findKeyword(statement, "levin") >= 0)
		{
			response = "More like LevinTheDream amiright?";
			emotion++;
		}

		// Response transforming I want to statement
		else if (chatBot.findKeyword(statement, "I want to", 0) >= 0) {
			response = chatBot.transformIWantToStatement(statement);
		} else if (chatBot.findKeyword(statement, "I want", 0) >= 0) {
			response = chatBot.transformIWantStatement(statement);
		}

		else if (chatBot.findKeyword(statement, "characters", 0) >=0){
		    response = "The main characters of the series are Tsunayoshi Sawada, Reborn, Gokudera Hayato, Takeshi Yamamoto, Lambo, Ryohei Sasagawa, Kyoya Hibari, Mukuro Rokudo, and Chrome Dokuro. To learn more about a specific character, just type their name."
        }
        else if (chatBot.findKeyword(statement, "Tsunayoshi Sawada", 0) >=0){
            response = "Tsunayoshi Sawada, usually called Tsuna, is the main character of the series. Tsuna is a junior-high student who becomes the boss-in-training for the Vongola Family's mafia, a mafia that exists in Italy. He is the one who is to be the next leader of the Vongola, the tenth Vongola boss and it is later revealed he is the holder of the sky ring. To set him up to that position, hitman Reborn becomes his home tutor and trains him to be a suitable boss. With Reborn's help, Tsuna confronts his fears and befriends several people, some of which become his guardians within the Vongola Family. However, due to Tsuna's high position in the Mafia, various other families conspire to kill Tsuna, which forces him and his friends to stand up to their enemies. For mor info, go to https://en.wikipedia.org/wiki/Tsuna_Sawada"
        }
        else if (chatBot.findKeyword(statement, "Reborn", 0) >=0) {
            response = "Reborn is an infant hitman belonging to the Vongola Family. As the most trusted member of the previous boss's family, he becomes Tsuna's home tutor and trains him into becoming a Mafia boss. For more info go to https://en.wikipedia.org/wiki/List_of_Reborn!_characters#Reborn"
        }
        else if (chatBot.findKeyword(statement, "Gokudera Hayato", 0) >=0) {
            response = "Gokudera Hayato is the storm ring holder and  is a 14-year-old transfer student from Italy who was placed in Tsuna's class. Hayato is a dynamite expert nicknamed \"Smokin' Bomb Hayato\", and he strives to be Tsuna's right hand man."
        }
		else
		{
			response = getRandomResponse();
		}
		
		return response;
	}


	/**
	 * Take a statement with "I <something> you" and transform it into
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement) { return chatBot.transformIYouStatement(statement); }


	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		return chatBot.transformIWantToStatement(statement);
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
	{
		return chatBot.transformIWantStatement(statement);
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
		return chatBot.findKeyword(statement, goal, startPos);
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
		return chatBot.findKeyword(statement, goal);
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
