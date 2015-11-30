import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Global {
	//variables
	private AtomicInteger joy;
	private AtomicInteger sadness;
	private AtomicInteger anger; 
	private AtomicInteger humor;
	private AtomicInteger dissapointment;
	private AtomicInteger scared;
	private AtomicInteger[] feelsCode;
	private String channel;
	Random rand;
	
	public Global(){
		rand = new Random();
		joy = new AtomicInteger(0);
		sadness = new AtomicInteger(0);
		anger = new AtomicInteger(0);
		humor = new AtomicInteger(0);
		dissapointment = new AtomicInteger(0);
		scared = new AtomicInteger(0);
		feelsCode = new AtomicInteger[2];
		channel = "#lunarfuror";
	}
	
	//methods

	public void incrimentJoy(){
		joy.incrementAndGet();
		System.out.println("joy incrimented");
	}
	
	public void incrimentSadness(){
		sadness.incrementAndGet();
		System.out.println("saddness incrimented");
	}
	
	public void incrimentAnger(){
		anger.incrementAndGet();
		System.out.println("anger incrimented");
	}
	
	public void incrimentHumor(){
		humor.incrementAndGet();
		System.out.println("Humor incrimented");
	}
	
	public void incrimentDissapointment(){
		dissapointment.incrementAndGet();
		System.out.println("dissapointment incrimented");
	}
	
	public void incrimentScared(){
		scared.incrementAndGet();
		System.out.println("scared incrimented");
	}
	
	public void resetFeels(){
		joy.set(0);
		sadness.set(0);
		anger.set(0);
		humor.set(0);
		dissapointment.set(0);
		scared.set(0);
		feelsCode[0].set(0);
		feelsCode[1].set(0);
	}
	
	public void generateAction(){
		int feel = 0;
		int max = 0;
		if(joy.get() > max){feel = 1; max = joy.get();}
		if(sadness.get() > max){feel = 2; max = sadness.get();}
		if(anger.get() > max){feel = 3; max = anger.get();}
		if(humor.get() > max){feel = 4; max = humor.get();}
		if(dissapointment.get() > max){feel = 5; max = dissapointment.get();}
		if(scared.get() > max){feel = 6; max = scared.get();}
		feelsCode[0].set(feel);
		feelsCode[1].set(rand.nextInt(2)+1);
	}
	
	//getters and setters
	public int getJoy() {
		return joy.get();
	}

	public void setJoy(int joy) {
		this.joy.set(joy);
	}

	public int getSadness() {
		return sadness.get();
	}

	public void setSadness(int sadness) {
		this.sadness.set(sadness);
	}

	public int getAnger() {
		return anger.get();
	}

	public void setAnger(int anger) {
		this.anger.set(anger);
	}

	public int getHumor() {
		return humor.get();
	}

	public void setHumor(int humor) {
		this.humor.set(humor);
	}

	public int getDissapointment() {
		return dissapointment.get();
	}

	public void setDissapointment(int dissapointment) {
		this.dissapointment.set(dissapointment);
	}

	public int getScared() {
		return scared.get();
	}

	public void setScared(int scared) {
		this.scared.set(scared);
	}
	
	public int[] getFeelCode(){
		int[] output = {feelsCode[0].get(), feelsCode[1].get()};
		return output;
	}
	
	public void setFeelCode(int[] feelCode){
		this.feelsCode[0].set(feelCode[0]);
		this.feelsCode[1].set(feelCode[1]);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String toString(){
		return "joy:" + joy.get() + " sadness:" + sadness.get() + " anger:" + anger.get() + " humor:" + humor.get() +
				" dissapointment:" + dissapointment.get() + " scared:" + scared.get();
	}
}
