import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatRwby_Rachel
{
	private final ChatBot chatBot = new ChatBot();
	// emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	private static String weapons[] = {"Ruby has a large scythe blade for close range that transforms into a 'rifle' mode for melee and uses Dust ammunition for elemental attacks.", "Yang uses shot guantlets that allow close combat or ranged combat sending kinetic energy or shells.", "Weiss has a Dust rapier for ranged, melee and elemental attacks."};

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
		return "Hi, what is up?";
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
			response = "Do you like RWBY?";
		} else if (chatBot.findKeyword(statement, "pyrrha") >= 0)
		{
			response = "Don't mention her.";
                	emotion--;
		} else if (chatBot.findKeyword(statement, "weapons") >= 0)
		{
			int random = (int) (3* Math.random());
			response = "A lot of the weapons used in RWBY are based on real weapons but are designed as transformable in order to increase their effectiveness in combat. " + weapons[random];
			emotion++;
		} else if (chatBot.findKeyword(statement, "review") >= 0)
		{
			response = "RWBY has relatively good ratings with an 8.3 out of 10 on IMDb.";
		} else if (chatBot.findKeyword(statement, "software") >= 0)
		{
			response = "RWBY is made with Poser Pro animation software, which is also used for Rooster Teeth Production's other series such as Red vs. Blue.";
		} else if (chatBot.findKeyword(statement, "created") >= 0)
		{
			response = "RWBY was created bt Monty Oum, an American animator and writer. He died from an allergic reaction during a medical procedure that put him into a coma at the afe of 33.";
			emotion--;
		} else if (chatBot.findKeyword(statement, "Rooster Teeth") >= 0)
		{
			response = "RTX";
		} else if (chatBot.findKeyword(statement, "grimm eclipse") >= 0 || chatBot.findKeyword(statement, "game") >= 0 )
		{
			response = "RWBY has a 4 player, online co-op game called Grimm Eclipse. It was originally a fanmade game named Grim Eclipse before the creator Jordan Scott was hired by Rooster Teeth.";
		} else if (chatBot.findKeyword(statement, "goldman") >= 0)
		{
			response = "Go for the gold, man.";
			emotion++;
		} else if (chatBot.findKeyword(statement, "help") >= 0)
		{
			response = "https://www.google.com";
		}

		// Response transforming I want to statement
		else if (chatBot.findKeyword(statement, "I want to", 0) >= 0) {
			response = chatBot.transformIWantToStatement(statement);
		} else if (chatBot.findKeyword(statement, "I want", 0) >= 0) {
			response = chatBot.transformIWantStatement(statement);
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
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
	{
		return chatBot.transformIYouStatement(statement);
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
			"Lalalalala",
			"So, who's your favorite RWBY character?",
			"Say that again?"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	
}
