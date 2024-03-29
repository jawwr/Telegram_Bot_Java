package bot.util;

import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }
    private PropertiesUtil() {

    }
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties(){
        try(InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")){
            PROPERTIES.load(inputStream);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
