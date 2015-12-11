import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class ConfigurationLoader {
	
	private boolean loaded = false;
	
	private boolean fileOk = false;

	private String fileName;
	
	private String name;
	
	private String password;
	
	private String channels;
	
	private String hostname;
	
	private int port;
	
	public ConfigurationLoader()
	{
		SetFilename("galbey.properties");
		loaded = false;
		fileOk = false;
	}
	
	public ConfigurationLoader(String fn)
	{
		SetFilename(fileName = fn);
		loaded = false;
		fileOk = false;
	}
	
	public void LoadConfiguration() throws IOException
	{
		if (!fileOk)
		{
			loaded = false;
			throw new IOException("Filename is invalid or cannot be opened");
		}
		loaded = false;
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
 
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}
 
			if (prop.contains("name"))
			{
				SetName(prop.getProperty("name");
			}
			if (prop.contains("password"))
			{
				SetPassword(prop.getProperty("password");
			}
			if (prop.contains("channels"))
			{
				SetChannels(prop.getProperty("channels");
			}
			if (prop.contains("hostname"))
			{
				SetHostname(prop.getProperty("hostname");
			}
			if (prop.contains("port") && TryParseInt(prop.getProperty("port")))
			{
				SetPort(Integer.parseInt(prop.getProperty("port")));
			}
			
			String result = "Name: " + name + "\nPassword: " + password + "\nChannels: " + channels + "\nHostName: " + hostname + "\nPort: " + port;
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		loaded = true;
	}
	
	public void LoadConfiguration(String fn) throws IOException
	{
		fileName = fn;
		LoadConfiguration();
	}
	
	public Configuration.Builder<PircBotX> GetConfiguration()
	{
		return new Configuration.Builder<PircBotX>()
			.setName(name)
			.setServerPassword(password)
			.addAutoJoinChannel(channels)
			.setServerHostname(hostname)
			.setServerPort(port);
	}
	
	private boolean TryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	public void SetFilename(String fn)
	{
		File tempFile = new File(fn);
		if (tempFile.exists() && !tempFile.isDirectory())
		{
			fileOk = true;
		}
		else
		{
			fileOk = false;
		}
	}
	
	public String GetFileName()
	{
		return fileName;
	}
	
	public void SetName(String n)
	{
		name = n;
	}
	
	public String GetName
	{
		return name;
	}
	
	public void String SetPassword(String p)
	{
		password = p;
	}
	
	public String GetPassword
	{
		return password;
	}
	
	public void SetChannels(String c)
	{
		channels = c;
	}
	
	public String GetChannels
	{
		return channels;
	}
	
	public void SetHostname(String h)
	{
		hostname = h;
	}
	
	public String GetHostname
	{
		return hostname;
	}
	
	public void SetPort(int p)
	{
		port = p;
	}
	
	public int GetPort
	{
		return port;
	}
	
	public static void main(String[] args) {
		ConfigurationLoader configLoad = new ConfigurationLoader();
		try {
			configLoad.LoadConfiguration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
