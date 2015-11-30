import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class TimeEvent extends Event implements GenericMessageEvent {

	private Channel theChannel;
	private String command;
	public TimeEvent(PircBotX bot, Channel theChannel) {
		super(bot);
		this.theChannel = theChannel;
		command = "TimeTick";
	}
	
	public TimeEvent(PircBotX bot, Channel theChannel, String message) {
		super(bot);
		this.theChannel = theChannel;
		command = message;
	}

	@Override
	public User getUser() {
		return null;
	}

	@Override
	public String getMessage() {
		return command;
	}

	@Override
	public void respond(String arg0) {
		bot.sendIRC().message(theChannel.getName(), arg0);
	}

}
