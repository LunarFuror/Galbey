import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

public class ConfigurationLoader {
	
	private boolean loaded = false;
	
	private boolean fileOk = false;

	private String fileName;
	
	private String name;
	
	private String password;
	
	private String[] channels;
	
	private String hostname;
	
	private int port;
	
	public ConfigurationLoader()
	{
		loaded = false;
		fileOk = false;
		ResetProperties();
		SetFilename("galbey.properties");
	}
	
	public ConfigurationLoader(String fn)
	{
		loaded = false;
		fileOk = false;
		ResetProperties();
		SetFilename(fileName = fn);
	}
	
	public void LoadConfiguration() throws IOException
	{
		loaded = false;
		if (!fileOk)
		{
			throw new IOException("Filename is invalid or cannot be opened");
		}
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
 
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}
 
			if (prop.containsKey("name"))
			{
				SetName(prop.getProperty("name"));
			}
			if (prop.containsKey("password"))
			{
				SetPassword(prop.getProperty("password"));
			}
			if (prop.containsKey("channels"))
			{
				SetChannels(prop.getProperty("channels"));
			}
			if (prop.containsKey("hostname"))
			{
				SetHostname(prop.getProperty("hostname"));
			}
			if (prop.containsKey("port") && TryParseInt(prop.getProperty("port")))
			{
				SetPort(Integer.parseInt(prop.getProperty("port")));
			}
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
	
	public Configuration.Builder<PircBotX> GetConfiguration() throws Exception
	{
		if (loaded)
		{
			Builder<PircBotX> returnme = new Configuration.Builder<PircBotX>();
			if (name != null && !name.isEmpty())
			{
				returnme.setName(name);
			}
			if (password != null && !password.isEmpty())
			{
				returnme.setServerPassword(password);
			}
			if (channels != null && channels.length > 0)
			{
				for(String channel : channels)
				{
					returnme.addAutoJoinChannel(channel);
				}
			}
			if (hostname != null && !hostname.isEmpty())
			{
				returnme.setServerHostname(hostname);
			}
			if (port > 0)
			{
				returnme.setServerPort(port);
			}
			return returnme;
		}
		throw new Exception("Error in loading configuration");
	}
	
	private boolean TryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	private void ResetProperties()
	{
		name = null;
		password = null;
		channels = null;
		hostname = null;
		port = 0;
	}
	
	public void SetFilename(String fn)
	{
		
		if (getClass().getClassLoader().getResource(fn).getPath().isEmpty())
		{
			fileOk = false;
		}
		else
		{
			fileName = fn;
			fileOk = true;
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
	
	public String GetName()
	{
		return name;
	}
	
	public void SetPassword(String p)
	{
		password = p;
	}
	
	public String GetPassword()
	{
		return password;
	}
	
	public void SetChannels(String c)
	{
		if (c != null && !c.isEmpty())
		{
			if (c.contains(","))
			{
				channels = c.split(",");
			}
			else
			{
				channels = new String[]{c};
			}
		}
	}
	
	public String[] GetChannels()
	{
		return channels;
	}
	
	public void SetHostname(String h)
	{
		hostname = h;
	}
	
	public String GetHostname()
	{
		return hostname;
	}
	
	public void SetPort(int p)
	{
		port = p;
	}
	
	public int GetPort()
	{
		return port;
	}
	
	public static void main(String[] args) {
		ConfigurationLoader configLoad = new ConfigurationLoader();
		try {
			configLoad.LoadConfiguration();
			Builder temp = configLoad.GetConfiguration();
			String test = temp.getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
