package fr.utc.nf28.moka.environment;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class JsonDateSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeString(sDateFormat.format(date));
	}
}
