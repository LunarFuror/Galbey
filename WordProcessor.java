import java.io.IOException;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

@SuppressWarnings("rawtypes")
public class WordProcessor extends ListenerAdapter implements Runnable {
	static PircBotX bot;
	Global thisGlobal;
	
	//CONSTRUCTORS GO HERE
	public WordProcessor(){
		super();
	}
	
	public WordProcessor(Global newGlobal){
		super();
		thisGlobal = newGlobal;
	}

	//NORMAL METHODS

	//LISTENER OVERRIDE METHODS
	public void onConnect(ConnectEvent event) {
		System.out.println("Connected!");
	}

	// lets us know when we've entered the channel
	public void onJoin(JoinEvent event) {
		System.out.println("Joined!");
		event.respond("Hello!");
	}

	// parse messages for commands and emotions
	public void onGenericMessage(GenericMessageEvent event) throws InterruptedException {
		// When someone says ?helloworld respond with "Hello World"
		if (event.getMessage().toLowerCase().contains("!galbey")) {
			event.respond("Yes boss?");
		}

		if (event.getMessage().toLowerCase().contains("@lunargalbey")) {
			event.respond("I was built to read, not to lead.");
		}

		if (event.getMessage().toLowerCase().contains("#joy")) {
			System.out.println("joy");
			thisGlobal.incrimentJoy();
		}

		if (event.getMessage().toLowerCase().contains("#sadness")) {
			thisGlobal.incrimentSadness();
		}

		if (event.getMessage().toLowerCase().contains("#anger")) {
			thisGlobal.incrimentAnger();
		}

		if (event.getMessage().toLowerCase().contains("#humor")) {
			thisGlobal.incrimentHumor();
		}

		if (event.getMessage().toLowerCase().contains("#dissapointment")) {
			thisGlobal.incrimentDissapointment();
		}

		if (event.getMessage().toLowerCase().contains("#scared")) {
			thisGlobal.incrimentScared();
		}
	}

	@Override
	public void run() {
		@SuppressWarnings("unchecked")
		Configuration<PircBotX> configuration = new Configuration.Builder<PircBotX>().setAutoNickChange(false)
			.addCapHandler(new EnableCapHandler("twitch.tv/membership"))
	
			.setName("lunargalbey") // Set the nick of the bot. CHANGE IN YOUR CODE
			.setServerHostname("irc.twitch.tv") // Join the freenode network
			.setServerPort(6667).setServerPassword("oauth:sapooyzgzir30i31mzd8u9xdvbgt97")
			.addAutoJoinChannel("#lunargalbey") // Join the official pircbotx channel
	
			.addListener(new WordProcessor(thisGlobal)) // Add our listener that will be called on Events
			.buildConfiguration();

		// Create our bot with the configuration
		bot = new PircBotX(configuration);
		// Connect to the server
		System.out.println("Connecting...");
		try {
			bot.startBot();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IrcException e) {
			e.printStackTrace();
		}
	}
}
