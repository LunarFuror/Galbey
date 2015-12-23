import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.pircbotx.Configuration;
import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;

public class ConfigurationLoader {
	
	private final String NAMEKEY = "name";
	private final String PASSWORDKEY = "password";
	private final String CHANNELSKEY = "channels";
	private final String HOSTNAMEKEY = "hostname";
	private final String PORTKEY = "port";
	private final String FEELSDIRECTORYKEY = "feelsdirectory";
	private final String FEELSFILESKEY = "feelsfiles";	
	
	private boolean configLoaded = false;
	
	private boolean feelsLoaded = false;
	
	private boolean fileOk = false;

	private String fileName;
	
	private String name;
	
	private String password;
	
	private String[] channels;
	
	private String hostName;
	
	private String feelsDirectory;
	
	private String[] feelsFiles;
	
	private int port;
	
	public ConfigurationLoader()
	{
		configLoaded = false;
		fileOk = false;
		ResetProperties();
		SetFilename("galbey.properties");
	}
	
	public ConfigurationLoader(String fn)
	{
		configLoaded = false;
		fileOk = false;
		ResetProperties();
		SetFilename(fileName = fn);
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
				for (int i = 0; i < channels.length; i++)
				{
					if (!channels[i].startsWith("#"))
					{
						channels[i] = "#" + channels[i];
					}
				}
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
		hostName = h;
	}
	
	public String GetHostname()
	{
		return hostName;
	}
	
	public void SetPort(int p)
	{
		port = p;
	}
	
	public int GetPort()
	{
		return port;
	}
	
	public void SetFeelsDirectory(String fd)
	{
		feelsDirectory = fd;
	}
	
	public String GetFeelsDirectory()
	{
		return feelsDirectory;
	}
	
	public void SetFeelsFiles(String ff)
	{
		if (ff != null && !ff.isEmpty())
		{
			if (ff.contains(","))
			{
				feelsFiles = ff.split(",");
				for (int i = 0; i < feelsFiles.length; i++)
				{
					feelsFiles[i] = feelsFiles[i].trim();
				}
			}
			else
			{
				feelsFiles = new String[]{ff};
			}
		}
	}
	
	public String[] GetFeelsFiles()
	{
		return feelsFiles;
	}
	
	public void LoadConfiguration(String fn) throws IOException
	{
		fileName = fn;
		LoadConfiguration();
	}
	
	public void LoadConfiguration() throws IOException
	{
		configLoaded = false;
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
 
			if (prop.containsKey(NAMEKEY))
			{
				SetName(prop.getProperty(NAMEKEY));
			}
			if (prop.containsKey(PASSWORDKEY))
			{
				SetPassword(prop.getProperty(PASSWORDKEY));
			}
			if (prop.containsKey(CHANNELSKEY))
			{
				SetChannels(prop.getProperty(CHANNELSKEY));
			}
			if (prop.containsKey(HOSTNAMEKEY))
			{
				SetHostname(prop.getProperty(HOSTNAMEKEY));
			}
			if (prop.containsKey(PORTKEY) && TryParseInt(prop.getProperty(PORTKEY)))
			{
				SetPort(Integer.parseInt(prop.getProperty(PORTKEY)));
			}
			if (prop.containsKey(FEELSDIRECTORYKEY))
			{
				SetFeelsDirectory(prop.getProperty(FEELSDIRECTORYKEY));
			}
			if (prop.containsKey(FEELSFILESKEY))
			{
				SetFeelsFiles(prop.getProperty(FEELSFILESKEY));
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		configLoaded = true;
	}
	
	public Vector<Feels> GetFeels() throws IOException,Exception
	{
		if (feelsDirectory != null && feelsFiles != null && feelsFiles.length > 0)
		{
			Vector<Feels> returnMe = new Vector<Feels>();
			
			for (String fileName : feelsFiles)
			{
				try
				{
					returnMe.add(new Feels(feelsDirectory + fileName));
				}
				catch(IOException ex)
				{
					System.out.println(ex.toString());
				}
			}
			return returnMe;
		}
		throw new Exception("Feels did not load correclty");
	}
	
	public Configuration.Builder<PircBotX> GetConfiguration() throws Exception
	{
		if (!configLoaded)
		{
			LoadConfiguration();
		}
		if (configLoaded)
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
			if (hostName != null && !hostName.isEmpty())
			{
				returnme.setServerHostname(hostName);
			}
			if (port > 0)
			{
				returnme.setServerPort(port);
			}
			return returnme;
		}
		throw new Exception("Configuration not Loaded correctly");
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
		hostName = null;
		feelsFiles = null;
		feelsDirectory = null;
		port = 0;
	}
}
