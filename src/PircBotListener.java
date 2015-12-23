import java.time.LocalTime;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
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

	@Override
	// lets us know when we've entered the channel
	public void onJoin(JoinEvent event) {
		System.out.println("Joined!");
		event.getChannel().send().message("Hello!");
	}
	
	@Override
	public void onGenericMessage(final GenericMessageEvent event)throws Exception
	{
		if (event.getMessage().startsWith("TimedResponse")){
			System.out.println("timed response recieved");
			thisGlobal.generateAction();
			System.out.println("feels set confirm");
			//voice thread here they can get their own code or be sent the code
			//move thread here they can get their own code or be sent the code
			String say = "nothing";
			say=thisGlobal.getFeelString();
			System.out.println("say generated");
			if(!say.equals("nothing")){
				event.respond(say);
			}
			thisGlobal.resetFeels();
		}
	}
	
	
	@Override
	public void onMessage(final MessageEvent event) throws Exception {
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
			event.getBot().sendIRC().message(event.getChannel().getName(), "Use #joy, #sad, #anger, #funny, #dissapoint, and #scarey to influence what I say!"
				+ "If I hear nothing I wont say anything though :(");
		}

		if (event.getMessage().toLowerCase().contains("@lunargalbey")) {
			event.respond("I was built to read, not to lead.");
		}

		thisGlobal.checkAndUpdateFeels(event.getMessage().toLowerCase());
	}
}
