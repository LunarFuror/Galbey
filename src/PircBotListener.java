import java.time.LocalTime;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class PircBotListener extends ListenerAdapter {

	
	private LocalTime lastMessageResponded;
	Global thisGlobal;
	
	public PircBotListener(){
		lastMessageResponded = LocalTime.now();
	}
	
	public PircBotListener(Global newGlobal){
		thisGlobal = newGlobal;
		lastMessageResponded = LocalTime.now();
	}
	
	public LocalTime getLastMessageRespondedTime()
	{
		return lastMessageResponded;
	}
	
	public void setLastMessageRespondedTime(LocalTime lm)
	{
		lastMessageResponded = LocalTime.now();
	}
	
	@Override
	public void onConnect(ConnectEvent event) {
		System.out.println("Connected!");
	}

	// lets us know when we've entered the channel
	public void onJoin(JoinEvent event) {
		System.out.println("Joined!");
		event.getBot().sendIRC().message(thisGlobal.getChannel(), "Hello!");
	}
	
	public void onGenericMessage(final GenericMessageEvent event) throws Exception {
		String message = event.getMessage();
		
		if (event.getMessage().toLowerCase().contains("?hello"))
		{
			event.getBot().sendIRC().message(thisGlobal.getChannel(), "This was a triumph");
			lastMessageResponded = LocalTime.now();
		}
		else if (message.startsWith("TimerTick"))
		{
			event.respond("Lonely I am so lonely please won't someone talk some more?");
			lastMessageResponded = LocalTime.now();
		}
		else if (message.startsWith("TimedResponse")){
			System.out.println("timed response recieved");
			thisGlobal.generateAction();
			int[] feelCode = thisGlobal.getFeelCode();
			System.out.println("feels set confirm");
			//voice thread here they can get their own code or be sent the code
			//move thread here they can get their own code or be sent the code
			String say = "nothing";
			say=decodeFeels(feelCode);
			System.out.println("say generated");
			if(!say.equals("SAY NOTHING")){
				event.getBot().sendIRC().message(thisGlobal.getChannel(), say);
			}
			thisGlobal.resetFeels();
		}
		
	// When someone says ?helloworld respond with "Hello World"
		if (event.getMessage().toLowerCase().contains("!galbey")) {
			event.respond("Yes boss?");
		}
		
		if (event.getMessage().toLowerCase().equals("brb")) {
			event.respond("Come back soon!");
		}
		
		if (event.getMessage().toLowerCase().equals("back")) {
			event.respond("Welcome back :D");
		}
		
		if(event.getMessage().toLowerCase().contains("?how")){
			event.getBot().sendIRC().message(thisGlobal.getChannel(), "Use #joy, #sad, #anger, #funny, #dissapoint, and #scarey to influence what I say!"
				+ "If I hear nothing I wont say anything though :(");
		}

		if (event.getMessage().toLowerCase().contains("@lunargalbey")) {
			event.respond("I was built to read, not to lead.");
		}

		if (event.getMessage().toLowerCase().contains("#joy") || event.getMessage().toLowerCase().contains("#happy")) {
			System.out.println("joy");
			thisGlobal.incrimentJoy();
		}

		if (event.getMessage().toLowerCase().contains("#sadness") || event.getMessage().toLowerCase().contains("#sad")) {
			thisGlobal.incrimentSadness();
		}

		if (event.getMessage().toLowerCase().contains("#anger") || event.getMessage().toLowerCase().contains("#angry")) {
			thisGlobal.incrimentAnger();
		}

		if (event.getMessage().toLowerCase().contains("#humor") || event.getMessage().toLowerCase().contains("#funny")) {
			thisGlobal.incrimentHumor();
		}

		if (event.getMessage().toLowerCase().contains("#dissapointment") || event.getMessage().toLowerCase().contains("#dissapoint")) {
			thisGlobal.incrimentDissapointment();
		}

		if (event.getMessage().toLowerCase().contains("#scared") || event.getMessage().toLowerCase().contains("#scarey")) {
			thisGlobal.incrimentScared();
		}
	}
	
	public String decodeFeels(int[] feels){
		System.out.println("feel:" + feels[0] + " message:" + feels[1]);
		String output = "";
		if (feels != null){
			switch(feels[0]){
				case 1: 
					switch(feels[1]){
						case 1: output = "I'm liking this! ArgieB8";
							break;
						case 2: output = "I feel... happy! :)";
							break;
						case 3: output = "/me cheers";
							break;
					}
					break;
				
				case 2: 
					switch(feels[1]){
						case 1: output = "Oh no... BibleThump";
							break;
						case 2: output = "I feel... sad... :(";
							break;
						case 3: output = "/me crys sofly";
							break;
					}
					break;
				
				case 3: 
					switch(feels[1]){
						case 1: output = "WTF?! SwiftRage";
							break;
						case 2: output = "I feel... ANGRY! >(";
							break;
						case 3: output = "/me rages for a while";
							break;
					}
					break;
				
				case 4: 
					switch(feels[1]){
						case 1: output = "LOL 4Head";
							break;
						case 2: output = "I feel... like laughing! :D";
							break;
						case 3: output = "/me laughs way to hard";
							break;
					}
					break;
				
				case 5: 
					switch(feels[1]){
						case 1: output = "Seriously? DansGame";
							break;
						case 2: output = "I feel... dissapointed.";
							break;
						case 3: output = "/me just shakes his head";
							break;
					}
					break;
				
				case 6: 
					switch(feels[1]){
						case 1: output = "Run! babyRage";
							break;
						case 2: output = "I feel... scawed...";
							break;
						case 3: output = "/me hides";
							break;
					}
					break;
				default: output = "SAY NOTHING";
					break;
			}
		}
		System.out.println(output);
		return output;
	}
}
