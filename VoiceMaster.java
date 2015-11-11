
public class VoiceMaster implements Runnable {
	Global thisGlobal;

	public VoiceMaster(Global newGlobal){
		thisGlobal = newGlobal;
	}
	
	public void run() {
		thisGlobal.incrimentHumor();
	}
}
