package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    static NumberFormat numberFormat = new DecimalFormat("#,##0.00",
            new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")));

    public static String doubleToString(Double value) {
        return numberFormat.format(value);
    }
}