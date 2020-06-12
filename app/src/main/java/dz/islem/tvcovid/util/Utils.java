package dz.islem.tvcovid.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    public static String decimalFormat(long number){
        NumberFormat nf = DecimalFormat.getInstance(Locale.ENGLISH);
        DecimalFormat decimalFormatter = (DecimalFormat) nf;
        decimalFormatter.applyPattern("#,###,###.##");
        return decimalFormatter.format(number);
    }
}


