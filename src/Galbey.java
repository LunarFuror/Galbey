public class Galbey {
	//methods
	
	public static void main(String[] args){
		//initialize stuff
		Global masterGlobal = new Global();
		boolean loop = true;
		
		//start the word processing which connects to the channel.
		Thread read = new Thread(new WordProcessor(masterGlobal));
		Thread move = new Thread(new MovementMaster(masterGlobal));
		Thread speak = new Thread(new VoiceMaster(masterGlobal));
		
		read.start();
		move.start();
		speak.start();
		
		while(loop){
						
		}
	}
}
