import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ConfigReader {
    private static JSONObject config;

    static {
        try {
            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/config.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getBaseUrl() {
        return (String) config.get("baseUrl");
    }

    public static List<JSONObject> getPagesToTest() {
        return (List<JSONObject>) config.get("pagesToTest");
    }

}