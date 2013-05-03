package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.environment.User;

import java.io.IOException;
import java.io.StringWriter;

public class JSONParserUtils {
    private static final ObjectMapper sMapper = new ObjectMapper();

    public static String serializeUser(User user) {
        StringWriter sw = new StringWriter();
        try {
            sMapper.writeValue(sw, user);
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static User deserializeUser(final String json) {
        try {
            return sMapper.readValue(json, User.class);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
