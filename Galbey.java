import java.util.Date;
import java.util.Random;

public class Galbey {
	//methods
	public static void act(Global newGlobal){
		//Make and start threads to do the dew
		Thread move = new Thread(new MovementMaster(newGlobal));
		Thread speak = new Thread(new VoiceMaster(newGlobal));
		
		move.start();
		speak.start();
	}

	
	public static void main(String[] args){
		//initialize stuff
		Global masterGlobal = new Global();
		WordProcessor wordProc = new WordProcessor();
		wordProc.setGlobal(masterGlobal);
		Random rand = new Random();
		boolean loop = true;
		
		//start the word processing which connects to the channel.
		Thread read = new Thread(wordProc);
		read.start();
		
		//main loop
		while(loop){
			try {
				Thread.sleep(1000 * (3 + rand.nextInt(1))); //wait 
				System.out.println(new Date().toString() + " - " + masterGlobal.toString()); //show me how global is looking
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//act(masterGlobal); //do something
		}
	}
}
