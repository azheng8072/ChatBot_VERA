import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Class used for facilitating basic communication with DialogFlow.
 */
public class DialogFlow {
    // Fields
    private String baseURL;
    private String clientSecret;
    private int protocol;

    // Internally used workaround because I want to, cough just lazy
    private String response;

    /**
     * Getter for private field response.
     *
     * @return the response
     */
    public String getResponse () {
        return this.response;
    }

    /**
     * Tells you whether or not ChatBot has an answer for you.
     *
     * @param statement the statement the user said
     * @return          whether or not there is a response from ChatBot
     */
    public boolean hasResponse (String statement) {
        // A run of the mill try/catch probably implemented wrong but does it's purpose
        try {
            HttpResponse<JsonNode> response = Unirest.get(this.baseURL + "/query")
                    .queryString("v", this.protocol)
                    .queryString("lang", "en")
                    .queryString("query", statement)
                    .queryString("sessionId", "01")
                    .queryString("timezone", "America/New_York")
                    .header("Authorization", "Bearer " + this.clientSecret)
                    .asJson();

            JSONObject serializedResponse = response.getBody().getObject();
            JSONObject responseBasePath = serializedResponse.getJSONObject("result").getJSONObject("fulfillment");

            if (responseBasePath.has("messages")) {
                this.response = responseBasePath
                        .getJSONArray("messages")
                        .getJSONObject(0)
                        .get("speech").toString();
                return true;
            }
            return false;
        } catch (UnirestException e) {
            System.out.println("Failed to send request, terminating...");
            System.exit(1);
        }
        return false;
    }

    /**
     * Creates an instance of the DialogFlow communicator.
     *
     * @param clientSecret client access token
     * @param protocol     protocol version for the api
     */
    public DialogFlow (String clientSecret, int protocol) {
        // Rather not mess with OAuth, complicates things way more than it needs to be.
        this.baseURL = "https://api.dialogflow.com/v1";
        this.clientSecret = clientSecret;
        this.protocol = protocol;
    }
}