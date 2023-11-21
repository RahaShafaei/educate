package edu.educate.validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.educate.exception.ParametersNotValidException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomDateDeserializer extends StdDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {

        String dateTimeString = jsonparser.getText();
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ParametersNotValidException("Entered date format isn't correct.");
        }
    }
}
