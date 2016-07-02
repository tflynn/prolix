package com.tsftsf.prolix.text;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Created by tracy on 7/1/16.
 */
public class Encodings {

    private static Locale defaultLocale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
    private static String defaultLocaleString = defaultLocale.toString();

    public static String defaultLocaleLanguage = defaultLocaleString;

    public static String defaultCharsetEncoding = Charset.defaultCharset().toString();

    public static String defaultFileEncoding = System.getProperty("file.encoding");

}
