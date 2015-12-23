import java.util.Vector;

public class Global {
	//variables
	private int feelCode;
	private Vector<Feels> allTheFeels;
	
	public Global(Vector<Feels> feel){
		allTheFeels = feel;
		feelCode = -1;
	}
	
	//methods	
	public synchronized void checkAndUpdateFeels(String checkString)
	{
		for (Feels f : allTheFeels)
		{
			if (f.ContainsKeyword(checkString))
			{
				f.AddToCurrentValue(1);
				System.out.println(f.GetName() + " updated");
			}
		}
	}
	
	public synchronized void resetFeels(){
		for (Feels f : allTheFeels)
		{
			f.SetCurrentValue(0);
		}
		feelCode = -1;
	}
	
	public synchronized void generateAction(){
		System.out.println("feels start");
		feelCode = -1;
		int max = 0;
		for (int i = 0; i < allTheFeels.size(); i++)
		{
			if (allTheFeels.get(i).GetCurrentValue() > max)
			{
				feelCode = i;
				max = allTheFeels.get(i).GetCurrentValue();
			}
		}
		
		System.out.println("feels set");
	}
	
	public synchronized String getFeelString()
	{
		String returnValue = "nothing";
		if (feelCode > -1)
		{
			returnValue = allTheFeels.get(feelCode).GetResponse();
		}
		return returnValue;
	}
	
	public int getFeelCode()
	{
		return feelCode;
	}
	
	public void setFeelCode(int fc)
	{
		if (feelCode >= -1 && feelCode < allTheFeels.size())
		{
			feelCode = fc;
		}
		else
		{
			feelCode = -1;
		}
	}
	
	public String toString(){
		String returnString = "";
		for (Feels f : allTheFeels)
		{
			returnString = returnString + f.GetName() + ":" + f.GetCurrentValue() + " ";
		}
		return returnString;
	}
}
