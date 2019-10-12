package com.marvel.dingdangcat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    private static final Pattern yyyyMMddPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    private static final DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String source) {
        if (yyyyMMddPattern.matcher(source).matches()) {
            return LocalDate.parse(source, yyyyMMddFormatter);
        } else {
            throw new RuntimeException("时间格式不正确");
        }
    }
}
