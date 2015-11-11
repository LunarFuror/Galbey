
public class MovementMaster implements Runnable {
	Global thisGlobal;
	
	public MovementMaster(Global newGlobal){
		thisGlobal = newGlobal;
	}
	
	public void run() {
		thisGlobal.incrimentJoy();
	}
}
