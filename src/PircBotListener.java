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
			thisGlobal.generateAction();
			int[] feelCode = thisGlobal.getFeelCode();

			//voice thread here they can get their own code or be sent the code
			//move thread here they can get their own code or be sent the code
			event.getBot().sendIRC().message(thisGlobal.getChannel(), decodeFeels(feelCode));
		}
		
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
	
	public String decodeFeels(int[] feels){
		String output = "";
		if (feels != null){
			switch(feels[0]){
				case 1: 
					switch(feels[1]){
						case 1: output = "Joy 1";
							break;
						case 2: output = "Joy 2";
							break;
						case 3: output = "Joy 3";
							break;
					}
					break;
				
				case 2: 
					switch(feels[1]){
						case 1: output = "Sad 1";
							break;
						case 2: output = "Sad 2";
							break;
						case 3: output = "Sad 3";
							break;
					}
					break;
				
				case 3: 
					switch(feels[1]){
						case 1: output = "Mad 1";
							break;
						case 2: output = "Mad 2";
							break;
						case 3: output = "Mad 3";
							break;
					}
					break;
				
				case 4: 
					switch(feels[1]){
						case 1: output = "Funny 1";
							break;
						case 2: output = "Funny 2";
							break;
						case 3: output = "Funny 3";
							break;
					}
					break;
				
				case 5: 
					switch(feels[1]){
						case 1: output = "Dissapoint 1";
							break;
						case 2: output = "Dissapoint 2";
							break;
						case 3: output = "Dissapoint 3";
							break;
					}
					break;
				
				case 6: 
					switch(feels[1]){
						case 1: output = "Scared 1";
							break;
						case 2: output = "Scared 2";
							break;
						case 3: output = "Scared 3";
							break;
					}
					break;
			}
		}
		return output;
	}
}
