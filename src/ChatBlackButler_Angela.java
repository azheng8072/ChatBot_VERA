//TRANSFORM just means to turn the question around to give the answer.
import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBlackButler_Angela
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
		return "Hi, what's up?";
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
		// USE THIS AS EXAMPLE. you caN CHAnge goal to be a certain word in sentence to give answer.
        // response can be changed based on the found word
        else if (chatBot.findKeyword(statement, "name") >= 0 || chatBot.findKeyword(statement, "title") >= 0 || chatBot.findKeyword(statement, "anime") >= 0)
		{
			response = "The name of this anime is Black Butler.\nBlack Butler is a Japanese manga series written and illustrated by Yana Toboso. \nIt debuted on September 16, 2006, it has been serialized in Square Enix's shōnen manga magazine Monthly GFantasy.";
        }
		else if (chatBot.findKeyword(statement, "black") >= 0 || chatBot.findKeyword(statement, "butler") >= 0 || chatBot.findKeyword(statement, "anime") >= 0)
		{
			response = "Black Butler is a Japanese manga series written and illustrated by Yana Toboso. \nIt debuted on September 16, 2006, it has been serialized in Square Enix's shōnen manga magazine Monthly GFantasy.\nIn July 2008, it was announced that an anime adaption of Black Butler, directed by Toshiya Shinohara and produced by A-1 Pictures, was expected.\nIt premiered in October 2008 and broadcast on MBS as well as the TBS";
		}
        else if (chatBot.findKeyword(statement, "plot") >= 0 || chatBot.findKeyword(statement, "what is it about") >= 0)
		{
			response = "The plot is about a young boy sells his soul to a demon in order to avenge his family's death and successfully lead their influential toy manufacturing company. \nThe demon takes the form of a loyal butler who's always dressed in black and is required to protect, serve and arrive whenever summoned by his young master Ciel.";
                	emotion--;
        } else if (chatBot.findKeyword(statement, "main characters") >= 0 || chatBot.findKeyword(statement, "main") >= 0 || chatBot.findKeyword(statement, "character") >= 0)
		{
			response = "Main characters are Sebastian Michaelis and Ciel Phantomhive.";
			emotion++;
        } else if (chatBot.findKeyword(statement, "Ciel") >= 0 || chatBot.findKeyword(statement, "Phantomhive") >= 0)
		{
			response = "Ciel Phantomhive is a short, thirteen-year-old boy with bluish-black hair and rich, deep blue eyes.\nCiel nearly always wears a black eye-patch made of gauze with a single cord over his right eye to hide the location of his Faustian contract with Sebastian Michaelis. ";
			emotion++;
        } else if (chatBot.findKeyword(statement, "Sebastian") >= 0 || chatBot.findKeyword(statement, "Michaelis") >= 0)
		{
			response = "Sebastian Michaelis takes on the appearance of a tall, handsome adult with black hair, red eyes, and pale skin. \nSebastian is a dedicated butler to Ciel Phantomhive and upholds a strong sense of duty to his master and household. \nHe faithfully follows and carries out every one of Ciel's orders, while ensuring that the Phantomhive household runs smoothly. ";
			emotion++;
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
	
	private String [] randomNeutralResponses = {"Interesting...what did you say again?",
			"Hmmm...still dont get you",
			"I don't understand",
			"you mean?",
			"Could you say that again?",
			"Do you want to know something about Black Butler?"
	};
	private String [] randomAngryResponses = {"Bubbles!", "Hmph", "What are you saying!"};
	private String [] randomHappyResponses = {"Hurray!", "Yippie!", "Yahoo!"};
	
}
