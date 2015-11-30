import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.managers.ThreadedListenerManager;

@SuppressWarnings("rawtypes")
public class WordProcessor{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, IrcException, InterruptedException {
		Global thisGlobal = new Global();
		final Scanner sc = new Scanner(System.in);
		ThreadedListenerManager man = new ThreadedListenerManager();
		PircBotListener theListener = new PircBotListener(thisGlobal);
		man.addListener(theListener);
		
		Configuration<PircBotX> config = new Configuration.Builder<PircBotX>()
			.setName("lunargalbey")
			.setServerPassword("oauth:sapooyzgzir30i31mzd8u9xdvbgt97")
			.addAutoJoinChannel(thisGlobal.getChannel())
			.setServerHostname("irc.twitch.tv")
			.setServerPort(6667)
			.setListenerManager(man)
			.buildConfiguration();
		
		MultiBotManager<PircBotX> manager = new MultiBotManager<PircBotX>();
		PircBotX theBot = new PircBotX(config);
		manager.addBot(theBot);
		manager.start();
		Thread th = new Thread(){
			public void run() {

				System.out.println("Bots should be started");
				if (sc.hasNext())
				{
					String in = sc.next();
					if (in.equals("Stop"))
						return;
				}
			}
		};
		th.start();
		System.out.println("Bots should be started");
		
		//MAIN TIMING LOOP
		LocalTime timeCheck = LocalTime.now();
		LocalTime timedResponse = LocalTime.now();
		int seconds = 0;
		while (th.isAlive())
		{
			if (theBot.isConnected())
			{
				System.out.println("Here I am with a time check");
				if (theListener.getLastMessageRespondedTime().plusSeconds(30).isBefore(LocalTime.now()))
				{
					man.dispatchEvent(new TimeEvent(theBot, theBot.getUserBot().getChannels().first()));
				}
				if (timeCheck.plusSeconds(10).isBefore(LocalTime.now()))
				{
					seconds += 10;
					System.out.println("Seconds:" + seconds);
					if(seconds == 30){seconds = 0;}
					timeCheck = LocalTime.now();
				}
				if (timedResponse.plusSeconds(10).isBefore(LocalTime.now()))
				{
					man.dispatchEvent(new TimeEvent(theBot, theBot.getUserBot().getChannels().first(),"TimedResponse"));
				}
			}
			Thread.sleep(100);
		}
		
		try {
			manager.stopAndWait();
		} catch (InterruptedException e) {
			System.out.println("Something went horribly wrong" + e.getMessage());
		}
		System.out.println("AllBots Stopped");
	}
}
