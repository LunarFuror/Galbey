import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class ConfigurationLoader {
	
	private boolean loaded = false;

	private String fileName;
	
	private String name;
	
	private String password;
	
	private String channels;
	
	private String hostname;
	
	private int port;
	
	public ConfigurationLoader()
	{
		fileName = "galbey.properties";
	}
	
	public ConfigurationLoader(String fn)
	{
		fileName = fn;
		loaded = false;
	}
	
	public void LoadConfiguration() throws IOException
	{
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
 
			// get the property value and print it out
			name = prop.getProperty("name");
			password = prop.getProperty("password");
			channels = prop.getProperty("channels");
			hostname = prop.getProperty("hostname");
			if (tryParseInt(prop.getProperty("port")))
			{
				port = Integer.parseInt(prop.getProperty("port"));
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
	
	public Configuration<PircBotX> GetConfiguration()
	{
		return null;
	}
	
	private boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
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
