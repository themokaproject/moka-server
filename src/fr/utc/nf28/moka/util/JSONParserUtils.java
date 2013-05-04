package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.environment.items.MokaItem;
import fr.utc.nf28.moka.environment.users.User;

import java.io.IOException;
import java.io.StringWriter;

/**
 * a JSON serializer/deserializer that uses Jackson
 */
public class JSONParserUtils {
    private static final ObjectMapper sMapper = new ObjectMapper();

    public static String serializeUser(User user) throws IOException {
        StringWriter sw = new StringWriter();
        sMapper.writeValue(sw, user);
        return sw.toString();
    }

    public static User deserializeUser(final String json) throws IOException {
        return sMapper.readValue(json, User.class);
    }

    public static String serializeItem(MokaItem item) throws IOException{
        StringWriter sw = new StringWriter();
        sMapper.writeValue(sw, item);
        return sw.toString();
    }

    public static MokaItem deserializeItem(final String json) throws IOException {
        return sMapper.readValue(json, MokaItem.class);
    }


}
