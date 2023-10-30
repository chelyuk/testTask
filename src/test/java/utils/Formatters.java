package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatters {
    public Formatters() {
    }

    public String weekFormatter(LocalDate weekStart, LocalDate weekEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(weekStart) + " - " + formatter.format(weekEnd);
    }

    public String dateFormatter(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - dd/MM/yyyy");
        return formatter.format(date);
    }
}