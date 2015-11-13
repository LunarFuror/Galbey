import java.util.concurrent.atomic.AtomicInteger;

public class Global {
	//variables
	private AtomicInteger joy;
	private AtomicInteger sadness;
	private AtomicInteger anger; 
	private AtomicInteger humor;
	private AtomicInteger dissapointment;
	private AtomicInteger scared;
	
	public Global(){
		joy = new AtomicInteger(0);
		sadness = new AtomicInteger(0);
		anger = new AtomicInteger(0);
		humor = new AtomicInteger(0);
		dissapointment = new AtomicInteger(0);
		scared = new AtomicInteger(0);
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

	public String toString(){
		return "joy:" + joy.get() + " sadness:" + sadness.get() + " anger:" + anger.get() + " humor:" + humor.get() +
				" dissapointment:" + dissapointment.get() + " scared:" + scared.get();
	}
}
