import java.util.Random;

public class Galbey {
	//2 main threads we will utilize
	private static Thread move;
	private static Thread speak;
	
	public static void setThreads(Global newGlobal){
		//make sure they have the right variables
		move = new Thread(new MovementMaster(newGlobal));
		speak = new Thread(new VoiceMaster(newGlobal));
	}
	
	//methods
	public static void act(Global newGlobal){
		//threads should be ready let's do something with them.
		
		if(!move.isAlive())
			move.start();
		
		if(!speak.isAlive())
			speak.start();
	}

	
	public static void main(String[] args){
		//initialize stuff
		Global masterGlobal = new Global();
		WordProcessor wordProc = new WordProcessor();
		wordProc.setGlobal(masterGlobal);
		Random rand = new Random();
		boolean loop = true;
		setThreads(masterGlobal);
		
		//start the word processing which connects to the channel.
		Thread read = new Thread(wordProc);
		read.start();
		
		//main loop
		while(loop){
			try {
				Thread.sleep(1000 * (60 + rand.nextInt(60))); //wait 1-2 mins
				System.out.println(masterGlobal.toString()); //show me how global is looking
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			act(masterGlobal); //do something
		}
	}
}
