public class Global {
	//variables
	private int joy;
	private int sadness;
	private int anger; 
	private int humor;
	private int dissapointment;
	private int scared;
	
	public Global(){
		joy = 0;
		sadness = 0;
		anger = 0;
		humor = 0;
		dissapointment = 0;
		scared = 0;
	}
	
	//methods

	public synchronized void incrimentJoy(){
		joy ++;
		System.out.println("joy incrimented");
	}
	public synchronized void incrimentSadness(){
		sadness ++;
		System.out.println("saddness incrimented");
	}
	public synchronized void incrimentAnger(){
		anger ++;
		System.out.println("anger incrimented");
	}
	public synchronized void incrimentHumor(){
		humor ++;
		System.out.println("Humor incrimented");
	}
	public synchronized void incrimentDissapointment(){
		dissapointment ++;
		System.out.println("dissapointment incrimented");
	}
	public synchronized void incrimentScared(){
		scared ++;
		System.out.println("scared incrimented");
	}
	
	//getters and setters
	public synchronized int getJoy() {
		return joy;
	}

	public void setJoy(int joy) {
		this.joy = joy;
	}

	public synchronized int getSadness() {
		return sadness;
	}

	public void setSadness(int sadness) {
		this.sadness = sadness;
	}

	public synchronized int getAnger() {
		return anger;
	}

	public void setAnger(int anger) {
		this.anger = anger;
	}

	public synchronized int getHumor() {
		return humor;
	}

	public void setHumor(int humor) {
		this.humor = humor;
	}

	public synchronized int getDissapointment() {
		return dissapointment;
	}

	public void setDissapointment(int dissapointment) {
		this.dissapointment = dissapointment;
	}

	public synchronized int getScared() {
		return scared;
	}

	public void setScared(int scared) {
		this.scared = scared;
	}

	public String toString(){
		return "joy:" + joy + " sadness:" + sadness + " anger:" + anger + " humor:" + humor + " dissapointment:" + dissapointment + " scared:" + scared;
	}
}
