package com.tujuhsembilan.template.components;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtilComponent {

    @Autowired
    private MessageSource messageSource;

    /**
     * Get i18n message for some code on system locale, and fill up its arguments.
     * 
     * <br>
     * <br>
     * 
     * For example template that have {0} or {1,date} or {2,time} will need to be
     * filled up using the args argument.
     * 
     * @param code Message code, such as "system.generic.error", look on
     *             <code>src/main/resources/i18n</code> to add or see available
     *             messages
     * @param args List of objects to fill up template (Like "{0}" in the
     *             template)
     * 
     * @return The message on the system locale
     */
    public String getMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();

        return this.getMessage(locale, code, args);
    }

    /**
     * Get i18n message for some code on some locale, and fill up its arguments.
     * 
     * <br>
     * <br>
     * 
     * For example template that have {0} or {1,date} or {2,time} will need to be
     * filled up using the args argument.
     * 
     * @param code   Message code, such as "system.generic.error", look on
     *               <code>src/main/resources/i18n</code> to add or see available
     *               messages
     * @param args   List of objects to fill up template (Like "{0}" in the
     *               template)
     * @param locale Locality of the message, <code>java.util.Locale</code> class
     *               have constants for it
     * 
     * @return The message on the specified locale, filled with the data from args
     */
    public String getMessage(Locale locale, String code, Object... args) {
        return messageSource.getMessage(code, args, null, locale);
    }
}
