package com.marvel.dingdangcat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

    private static final Pattern hhmmPattern = Pattern.compile("\\d\\d:\\d\\d");
    private static final Pattern hhmmssPattern = Pattern.compile("\\d\\d:\\d\\d:\\d\\d");

    public static final DateTimeFormatter hhmmFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter hhmmssFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalTime convert(String source) {
        if (hhmmPattern.matcher(source).matches()) {
            return LocalTime.parse(source, hhmmFormatter);
        } else if (hhmmssPattern.matcher(source).matches()) {
            return LocalTime.parse(source, hhmmssFormatter);
        } else {
            throw new RuntimeException("时间格式不正确");
        }
    }
}
