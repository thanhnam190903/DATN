package com.example.DATN.Service;
import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomTimeEditor extends PropertyEditorSupport {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            if (text != null && !text.isEmpty()) {
                setValue(new Time(dateFormat.parse(text).getTime()));
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse time: " + e.getMessage(), e);
        }
    }
}
