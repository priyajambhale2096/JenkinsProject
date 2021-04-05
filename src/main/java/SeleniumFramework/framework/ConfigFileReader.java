package SeleniumFramework.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    static Properties properties = new Properties();
    public static String readApplicationFile(String key) throws Exception {
    String value = null;
    File f = new File(System.getProperty("user.dir") + File.separator + "Config.properties");
    if(f.exists())
    {
        properties.load(new FileInputStream(f));
        value=properties.getProperty(key);
    }
    if (value==null)
    {
        throw new Exception("Key not found in properties file");
    }
    return value;
}

}
