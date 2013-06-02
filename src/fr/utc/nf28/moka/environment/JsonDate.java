package fr.utc.nf28.moka.environment;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class JsonDate {
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");

	public static final class Deserializer extends JsonDeserializer<Date> {
		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
			try {
				return sDateFormat.parse(jsonParser.getText());
			} catch (ParseException e) {
				return Calendar.getInstance().getTime();
			}
		}
	}

	public static final class Serializer extends JsonSerializer<Date> {
		@Override
		public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			jsonGenerator.writeString(sDateFormat.format(date));
		}
	}
}
