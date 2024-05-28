package edu.educate.helper;

import edu.educate.configurations.ApplicationContextProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/*
* Since we could not have this field in the Entity classes,
* because the Entity classes could not have any field other
* than the table fields, we had to define this class to be
* able to access the messages through a static method.
* */
@Component
public class MessageUtil {

    private static final MessageSource messageSource = ApplicationContextProvider.getMessageSource();


    public static String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}
