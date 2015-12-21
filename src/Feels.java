import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Feels
{
	private Random generator = new Random(System.currentTimeMillis());
	private Vector<String> keywords;
	private String name;
	private Vector<String> responses;
	private AtomicInteger currentValue = new AtomicInteger(0);
	
	public Feels(String fileName) throws FileNotFoundException
	{
		
		LoadFeels(fileName);
	}
	
	public boolean ContainsKeyword(String keyword)
	{
		return keywords.contains(keyword.toLowerCase());
	}
	
	public String GetResponse()
	{
		int randResponse = generator.nextInt(responses.size());
		return responses.get(randResponse);
	}
	
	public void AddToCurrentValue(int v)
	{
		currentValue.addAndGet(v);
	}
	
	public void IncrementCurrentValue()
	{
		AddToCurrentValue(1);
	}
	
	public String GetName()
	{
		return name;
	}
	
	public void SetName(String n)
	{
		name = n;
	}
	
	public int GetCurrentValue()
	{
		return currentValue.get();
	}
	
	public void SetCurrentValue(int v)
	{
		currentValue.set(v);
	}
	
	
	private void LoadFeels(String fn) throws FileNotFoundException
	{
		keywords = new Vector<String>();
		responses = new Vector<String>();
		Scanner in = new Scanner(new FileReader(fn));
		SetName(in.nextLine().trim());
		String tempKeywords = in.nextLine();
		for(String s : tempKeywords.split(","))
		{
			keywords.add(s.trim().toLowerCase());
		}
		while (in.hasNextLine())
		{
			responses.add(in.nextLine());
		}
		in.close();
	}
	
}