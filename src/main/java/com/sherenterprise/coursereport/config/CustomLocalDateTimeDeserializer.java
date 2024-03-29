package com.sherenterprise.coursereport.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER_FOR_SINGLE_DIGIT_HOUR = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MMMM d, yyyy 'at' h:mma 'UTC'")
            .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter FORMATTER_FOR_TWO_DIGIT_HOUR = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("MMMM d, yyyy 'at'h:mma 'UTC'")
            .toFormatter(Locale.ENGLISH);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String dateTimeString = jsonParser.getText();
        LocalDateTime parsedDateTime = null;

        try {
            parsedDateTime = LocalDateTime.parse(dateTimeString, FORMATTER_FOR_SINGLE_DIGIT_HOUR);
        } catch (DateTimeException dateTimeException) {
            parsedDateTime = LocalDateTime.parse(dateTimeString, FORMATTER_FOR_TWO_DIGIT_HOUR);
        }
        return parsedDateTime;

    }

}
