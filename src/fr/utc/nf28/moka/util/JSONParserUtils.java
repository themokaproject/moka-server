package fr.utc.nf28.moka.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.utc.nf28.moka.environment.User;

import java.io.IOException;
import java.io.StringWriter;

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
}
