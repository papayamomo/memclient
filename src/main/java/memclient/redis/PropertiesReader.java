package memclient.redis;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesReader {

    HashMap<String, String> data = new HashMap<String, String>();

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public void getPropertiesReader() throws FileNotFoundException {
        Properties properties=new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/config.properties"));
        try {
            properties.load(bufferedReader);
            Enumeration enumeration=properties.propertyNames();
            while(enumeration.hasMoreElements()){
                String key=(String) enumeration.nextElement();
                data.put(key, properties.getProperty(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}